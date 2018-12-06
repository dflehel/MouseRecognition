/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mouserecognition;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;


/**
 *
 * @author Denes
 */
public class MouseRecognition {
    
      //Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = "\n";
     
    //CSV file header
    private static final String FILE_HEADER = "0,1\n";
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//         try {
//                GlobalScreen.registerNativeHook();
//                Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
//                }
//                catch (NativeHookException ex) {
//                System.err.println("There was a problem registering the native hook.");
//                System.err.println(ex.getMessage());
//                
//                System.exit(1);
//                }
//                
//                DataCollector datacollector = new DataCollector();
//                GlobalScreen.addNativeMouseListener(datacollector);
//                GlobalScreen.addNativeMouseMotionListener(datacollector);
//                GlobalScreen.addNativeMouseWheelListener(datacollector);
           Queue<ArrayList<IEvent>> events = new LinkedList<ArrayList<IEvent>>(); 
           FileWriter fileWriter;
           Display d; 
        d = new Display();
        d.setVisible(true);
        try {
            fileWriter = new FileWriter("kimenet.csv");
            fileWriter.append(FILE_HEADER.toString());
           Queue<IFeature> moves = new LinkedList<IFeature>();
           Thread classthread = new Thread("class"){
               public void run(){
                     System.out.println("run by: " + getName());
                     IClassifier classifier = new DFLRandomForestClassifier(moves,fileWriter,d);
                //     classifier.setFile(fileWriter);
                //     classifier.setMoves(moves);
                //     classifier.setDisplay(d);
                     classifier.classify();
               }
           };
           
           
           Thread t = new Thread("feature"){
               public void run(){
                    System.out.println("run by: " + getName());
                   Extraction extraction = new Extraction();
                   extraction.setEventslist(events);
                   extraction.setMoves(moves);
                   extraction.featuresextraction();
               }
           };
           
           Thread thread = new Thread("New Thread") {
                  public void run(){
                    try {
                GlobalScreen.registerNativeHook();
                Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
                logger.setLevel(Level.OFF);

                // Don't forget to disable the parent handlers.
                logger.setUseParentHandlers(false);
                }
                catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());
                
                System.exit(1);
                }
                
                DataCollector datacollector = new DataCollector();
                datacollector.setEvents(events);
                datacollector.setT(t);
                GlobalScreen.addNativeMouseListener(datacollector);
                GlobalScreen.addNativeMouseMotionListener(datacollector);
                GlobalScreen.addNativeMouseWheelListener(datacollector);
            System.out.println("run by: " + getName());
           }
            };
          classthread.start();
          thread.start();
          t.start();
            classthread.join();
            thread.join();
            t.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(MouseRecognition.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MouseRecognition.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
