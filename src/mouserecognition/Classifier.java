/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mouserecognition;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mouserecognition.Settings.LOAD_MODEL;
import static mouserecognition.Settings.NUM_ACTIONS;
import weka.core.Instances;
import weka.core.Attribute;
import weka.classifiers.trees.RandomForest;
import weka.core.Instance;

/**
 *
 * @author Denes
 */
public class Classifier {

    private RandomForest randomforest;
    private ArrayList<Double> output = new ArrayList<>();
    private ArrayList<Attribute> att = new ArrayList<>();
    private Instances instances;
    private Instances toclassify;
    private FileWriter file;
    private int counter = 0;
    private Display display;

    public Display getDisplay() {
        return display;
    }

    public void setDisplay(Display display) {
        this.display = display;
    }

    public FileWriter getFile() {
        return file;
    }

    public void setFile(FileWriter file) {
        this.file = file;
    }

    public Queue<Feature> getMoves() {
        return moves;
    }

    public void setMoves(Queue<Feature> moves) {
        this.moves = moves;
    }

    public void classify() {
        while (true) {
            if (this.moves.size() < NUM_ACTIONS) {
                try {
                    synchronized (this.moves) {
                        System.out.println("[WaitingThread]: Waiting for another thread" + "to notify me...");
                        this.moves.wait();
                        System.out.println("[WaitingThread]: Successfully notified!");
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(Extraction.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                double movesProbs[] = new double[2];
                for (Feature f : this.moves) {
                    Instance instance = f.getInstance();
                    instance.setDataset(this.instances);

                    try {
                        double[] probs = this.randomforest.distributionForInstance(instance);
                        //for(double p:probs){
                        //System.out.print(p+"  ");

                        //}
                        movesProbs[0] += probs[0];
                        movesProbs[1] += probs[1];
                    } catch (Exception ex) {
                        Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    System.out.println((counter++) + ". Time: " + Calendar.getInstance().getTime() + "\tProbability: " + movesProbs[1] / NUM_ACTIONS);
                    this.file.append(movesProbs[1] / NUM_ACTIONS + "\n");
                    
                    this.display.setscore((counter) + ". Time: " + Calendar.getInstance().getTime() + "\tProbability: " + String.format("%.4f" ,movesProbs[1] / NUM_ACTIONS));
                    this.file.flush();
                } catch (IOException ex) {
                    Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.toclassify.clear();

                synchronized (this.moves) {
                    this.moves.poll();
                }
                System.out.println("Kimenet :" + this.toString());
                // } catch (Exception ex) {
                // Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
                //}
            }
        }
    }

    @Override
    public String toString() {
        return "Classifier{" + "output=" + output + '}';
    }

    private Queue<Feature> moves = new LinkedList<Feature>();

    public Classifier() {
        //train classifier
        if (!LOAD_MODEL) {
            BufferedReader datafile = null;
            try {
                datafile = new BufferedReader(new FileReader("training.arff"));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
            }
            Instances data = null;
            try {
                data = new Instances(datafile);
            } catch (IOException ex) {
                Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
            }
            data.setClassIndex(data.numAttributes() - 1);
            this.randomforest = new RandomForest();
            //this.randomforest.setNumFeatures(23);
            //this.randomforest.setMaxDepth(10);
            try {
                this.randomforest.buildClassifier(data);
            } catch (Exception ex) {
                Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                ObjectInputStream objectInputStream
                        = new ObjectInputStream(new FileInputStream("betoltes.model"));
                this.randomforest = (RandomForest) objectInputStream.readObject();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            String f = "f";
            this.instances = new Instances(new BufferedReader(new FileReader("header.arff")));
            this.instances.setClassIndex(this.instances.numAttributes() - 1);
            this.toclassify = new Instances(new BufferedReader(new FileReader("header.arff")));
            this.toclassify.setClassIndex(this.instances.numAttributes() - 1);
            for (Integer i = 1; i < 24; ++i) {
                this.att.add(new Attribute(new String(f + i.toString() + " numeric")));
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
