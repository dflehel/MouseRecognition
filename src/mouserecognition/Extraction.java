/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mouserecognition;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mouserecognition.Settings.NUM_ACTIONS;

/**
 *
 * @author Denes
 */
public class Extraction {


    public Queue<ArrayList<Event>> getEventslist() {
        return eventslist;
    }

    public void setEventslist(Queue<ArrayList<Event>> eventslist) {
        this.eventslist = eventslist;
    }
    
    
    private Queue<ArrayList<Event>> eventslist = new LinkedList<ArrayList<Event>>();
    
    //itt megvan csinalva hogy apalajaban minden csak IFeatures interface tipusu osztalyokat fogad
    private Queue<IFeature> moves = new LinkedList<IFeature>();

    public Queue<IFeature> getMoves() {
        return moves;
    }

    public void setMoves(Queue<IFeature> moves) {
        this.moves = moves;
    }
    private ArrayList<Event> events = new ArrayList<>();
    private String csvfile;

    
    public void featuresextraction(){
        while (true){
        if (this.eventslist.isEmpty()){
            try {
                synchronized (this.eventslist) {
                this.eventslist.wait();}
            } catch (InterruptedException ex) {
                Logger.getLogger(Extraction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
             synchronized (this.eventslist) {
            this.events = this.eventslist.poll();
             }
            //A csere eseten a megfelelo osztalyt kell peldanyositani
            IFeature feature = new LehellFeature();
            //Annak meg kell hivni az extract featuret
            feature.ExtractFeatures(events);
           synchronized (this.moves) {
            //majd a hjozza adas
            this.moves.add( feature);
           }
            if (this.moves.size() ==NUM_ACTIONS){
                synchronized (this.moves) {
                    this.moves.notify();
                }
            }
            
        }
        }
        
    }
    
    
    
    public ArrayList<Event> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }

    public String getCsvfile() {
        return csvfile;
    }

    public void setCsvfile(String csvfile) {
        this.csvfile = csvfile;
    }

    public Extraction(String csvfile) {
        this.csvfile = csvfile;
    }
     public Extraction() {
    }
    
   
}

