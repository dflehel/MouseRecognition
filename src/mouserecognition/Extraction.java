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
    private Queue<Feature> moves = new LinkedList<Feature>();

    public Queue<Feature> getMoves() {
        return moves;
    }

    public void setMoves(Queue<Feature> moves) {
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
            Feature feature = new Feature();
            feature.setAtlagsebesseg(this.attalagsebesseg());
            feature.setAtlagszogseb(this.atlagszogseb());
            feature.setEuklideszi(this.eukledeszi());
            feature.setGyorsulas(this.gyorsulas());
            feature.setJerkpontonkenti(this.jerkpontonkenti());
            feature.setJerkue(this.jerkeu());
            feature.setMelyikgomb(this.milyengob());
            feature.setMilyen(this.melyikmozgas());
            feature.setMozgaspontok(this.mozgapontok());
            feature.setOssszszog(this.osszszog());
            feature.setPontonkentigyorsulas(this.pontonkentigyorsulas());
            feature.setPontonkentitav(this.pontonkentitavolsag());
            feature.setSebesseg(this.sebesseg());
            feature.setXatlagszabesseg(this.xatlagsebesseg());
            feature.setXgyorsulas(this.xgyorsulas());
            feature.setXpontonkentigyosulas(this.xpontonkentigyorsulas());
            feature.setXsebesseg(this.xsebesseg());
            feature.setXtav(this.xtav());
            feature.setYatlagsebesseg(this.yatlagsebesseg());
            feature.setYgyorsulas(this.ygyorsulas());
            feature.setYpontonkentigyorsulas(this.ypontonkentigyorsulas());
            feature.setYtav(this.ytav());
            feature.setYsebesseg(this.ysebesseg());
           synchronized (this.moves) {
            this.moves.add(feature);
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
    
    private double sebesseg(){
            int x1 = this.events.get(0).getX();
            int x2 = this.events.get(this.events.size()-1).getX();
            int y1 = this.events.get(0).getY();
            int y2 = this.events.get(this.events.size()-1).getY();
            long time1 = this.events.get(0).getTime();
            long time2 = this.events.get(this.events.size()-1).getTime();
            Double distance = Math.sqrt((Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2)));
            distance = (distance/(time2-time1));
            return distance;
    }
    
    private double eukledeszi(){
            int x1 = this.events.get(0).getX();
            int x2 = this.events.get(this.events.size()-1).getX();
            int y1 = this.events.get(0).getY();
            int y2 = this.events.get(this.events.size()-1).getY();
            Double distance = Math.sqrt((Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2)));
            return distance;
    }
    
    private double xtav(){
         int x1 = this.events.get(0).getX();
         int x2 = this.events.get(this.events.size()-1).getX();
         Double distance = Math.sqrt((Math.pow((x2-x1), 2)));
         return distance;
    }
    
    private double ytav(){
            int y1 = this.events.get(0).getY();
            int y2 = this.events.get(this.events.size()-1).getY();
            Double distance = Math.sqrt((Math.pow((y2-y1), 2)));
            return distance;
    }
    
    private double xsebesseg(){
         int x1 = this.events.get(0).getX();
            int x2 = this.events.get(this.events.size()-1).getX();
            long time1 = this.events.get(0).getTime();
            long time2 = this.events.get(this.events.size()-1).getTime();
            Double distance = Math.sqrt((Math.pow((x2-x1), 2)));
            distance = (distance/(time2-time1));
            return distance;
    }
    
    private double ysebesseg(){
            int y1 = this.events.get(0).getY();
            int y2 = this.events.get(this.events.size()-1).getY();
            long time1 = this.events.get(0).getTime();
            long time2 = this.events.get(this.events.size()-1).getTime();
            Double distance = Math.sqrt((Math.pow((y2-y1), 2)));
            distance = (distance/(time2-time1));
            return distance;
    }
    
    private double pontonkentitavolsag(){
            Double result = 0.0;
            int x1 = this.events.get(0).getX();
            int y1 = this.events.get(0).getY();
            int x2 = 0;
            int y2 = 0;
            for (int i =1;i<this.events.size();++i){
                x2 = this.events.get(i).getX();
                y2 = this.events.get(i).getY();
                result +=  Math.sqrt((Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2)));
                x1 = x2;
                y1 = y2;
            }
            return result;
    }
    
    private double attalagsebesseg(){
         Double result = 0.0;
            int x1 = this.events.get(0).getX();
            int y1 = this.events.get(0).getY();
            long time1 = this.events.get(0).getTime();
            int x2 = 0;
            int y2 = 0;
            long time2 = 0;
            for (int i =1;i<this.events.size();++i){
                x2 = this.events.get(i).getX();
                y2 = this.events.get(i).getY();
                time2 = this.events.get(i).getTime();
                  Double distance =Math.sqrt((Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2)));
                result += (distance/(time2-time1));
                x1 = x2;
                y1 = y2;
                time1 = time2;
            }
            result = result/this.events.size();
            return result;
    }
    
    private double xatlagsebesseg(){
         Double result = 0.0;
            int x1 = this.events.get(0).getX();
            long time1 = this.events.get(0).getTime();
            int x2 = 0;
            long time2 = 0;
            for (int i =1;i<this.events.size();++i){
                x2 = this.events.get(i).getX();
                time2 = this.events.get(i).getTime();
               Double distance =Math.sqrt((Math.pow((x2-x1), 2)));
                result += (distance/(time2-time1));
                x1 = x2;
                time1 = time2;
            }
            result = result/this.events.size();
            return result;
    }
    
    private double yatlagsebesseg(){
         Double result = 0.0;
            int y1 = this.events.get(0).getY();
            long time1 = this.events.get(0).getTime();
            int y2 = 0;
            long time2 = 0;
            for (int i =1;i<this.events.size();++i){
                y2 = this.events.get(i).getY();
                time2 = this.events.get(i).getTime();
                  Double distance =Math.sqrt((Math.pow((y2-y1), 2)));
                result += (distance/(time2-time1));
                y1 = y2;
                time1 = time2;
            }
            result = result/this.events.size();
            return result;
    }
    
    private double gyorsulas(){
         int x1 = this.events.get(0).getX();
            int x2 = this.events.get(this.events.size()-1).getX();
            int y1 = this.events.get(0).getY();
            int y2 = this.events.get(this.events.size()-1).getY();
            long time1 = this.events.get(0).getTime();
            long time2 = this.events.get(this.events.size()-1).getTime();
            Double distance = Math.sqrt((Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2)));
            distance = (distance/(time2-time1));
            distance = (distance/(time2-time1));
            return distance;
    }
    
    private double xgyorsulas(){
         int x1 = this.events.get(0).getX();
            int x2 = this.events.get(this.events.size()-1).getX();
            long time1 = this.events.get(0).getTime();
            long time2 = this.events.get(this.events.size()-1).getTime();
            Double distance = Math.sqrt((Math.pow((x2-x1), 2)));
            distance = (distance/(time2-time1));
            distance = (distance/(time2-time1));
            return distance;
    }
    
    private double ygyorsulas(){
            int y1 = this.events.get(0).getY();
            int y2 = this.events.get(this.events.size()-1).getY();
            long time1 = this.events.get(0).getTime();
            long time2 = this.events.get(this.events.size()-1).getTime();
            Double distance = Math.sqrt((Math.pow((y2-y1), 2)));
            distance = (distance/(time2-time1));
            distance = (distance/(time2-time1));
            return distance;
    }
    
    private double pontonkentigyorsulas(){
         Double result = 0.0;
            int x1 = this.events.get(0).getX();
            int y1 = this.events.get(0).getY();
            long time1 = this.events.get(0).getTime();
            int x2 = 0;
            int y2 = 0;
            long time2 = 0;
            for (int i =1;i<this.events.size();++i){
                x2 = this.events.get(i).getX();
                y2 = this.events.get(i).getY();
                time2 = this.events.get(i).getTime();
                  Double distance =Math.sqrt((Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2)));
                   distance = (distance/(time2-time1));
                result += (distance/(time2-time1));
                x1 = x2;
                y1 = y2;
                time1 = time2;
            }
            result = result/this.events.size();
            return result;
    }
    
    private double xpontonkentigyorsulas(){
         Double result = 0.0;
            int x1 = this.events.get(0).getX();
            long time1 = this.events.get(0).getTime();
            int x2 = 0;
            long time2 = 0;
            for (int i =1;i<this.events.size();++i){
                x2 = this.events.get(i).getX();
                time2 = this.events.get(i).getTime();
                  Double distance =Math.sqrt((Math.pow((x2-x1), 2)));
                   distance = (distance/(time2-time1));
                result += (distance/(time2-time1));
                x1 = x2;
                time1 = time2;
            }
            result = result/this.events.size();
            return result;
    }
    
    private double ypontonkentigyorsulas(){
         Double result = 0.0;
            int y1 = this.events.get(0).getY();
            long time1 = this.events.get(0).getTime();
            int y2 = 0;
            long time2 = 0;
            for (int i =1;i<this.events.size();++i){
                y2 = this.events.get(i).getY();
                time2 = this.events.get(i).getTime();
                  Double distance =Math.sqrt((Math.pow((y2-y1), 2)));
                   distance = (distance/(time2-time1));
                result += (distance/(time2-time1));
                y1 = y2;
                time1 = time2;
            }
            result = result/this.events.size();
            return result;
    }
    
    
    private double jerkeu(){
         int x1 = this.events.get(0).getX();
            int x2 = this.events.get(this.events.size()-1).getX();
            int y1 = this.events.get(0).getY();
            int y2 = this.events.get(this.events.size()-1).getY();
            long time1 = this.events.get(0).getTime();
            long time2 = this.events.get(this.events.size()-1).getTime();
            Double distance = Math.sqrt((Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2)));
            distance = (distance/(time2-time1));
            distance = (distance/(time2-time1));
            distance = (distance/(time2-time1));
            return distance;
    }
    
    private double jerkpontonkenti(){
        Double result = 0.0;
            int x1 = this.events.get(0).getX();
            int y1 = this.events.get(0).getY();
            long time1 = this.events.get(0).getTime();
            int x2 = 0;
            int y2 = 0;
            long time2 = 0;
            for (int i =1;i<this.events.size();++i){
                x2 = this.events.get(i).getX();
                y2 = this.events.get(i).getY();
                time2 = this.events.get(i).getTime();
                  Double distance =Math.sqrt((Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2)));
                   distance = (distance/(time2-time1));
                   distance = (distance/(time2-time1));
                result += (distance/(time2-time1));
                x1 = x2;
                y1 = y2;
                time1 = time2;
            }
            result = result/this.events.size();
            return result;
    }
    
    private int mozgapontok(){
        Integer s = this.events.size();
        return s;
     }
    
    private double osszszog(){
         Double result = 0.0;
         int x1 = this.events.get(0).getX();
         int y1 = this.events.get(0).getY();
         int x2 = 0;
         int y2 = 0;
         for (int i  = 1 ; i<this.events.size();++i){
            double disty = Math.sqrt(Math.pow((y2-y1), 2));
            double distx = Math.sqrt(Math.pow((x2-x1), 2));
             result += Math.atan2(disty, distx);
         }
         return result;
    }
    
    private double atlagosszog(){
        Double result = 0.0;
         int x1 = this.events.get(0).getX();
         int y1 = this.events.get(0).getY();
         int x2 = 0;
         int y2 = 0;
         for (int i  = 1 ; i<this.events.size();++i){
           double  disty = Math.sqrt(Math.pow((y2-y1), 2));
           double  distx = Math.sqrt(Math.pow((x2-x1), 2));
             result += Math.atan2(disty, distx);
         }
         result = result / this.events.size();
         return result;
    }
    
    private double atlagszogseb(){
          Double result = 0.0;
            int x1 = this.events.get(0).getX();
            int y1 = this.events.get(0).getY();
            long time1 = this.events.get(0).getTime();
            int  x2 = this.events.get(1).getX();
            int    y2 = this.events.get(1).getY();
            long    time2 = this.events.get(1).getTime();
            double  disty = Math.sqrt(Math.pow((y2-y1), 2));
            double  distx = Math.sqrt(Math.pow((x2-x1), 2));
           double szog = Math.atan2(disty, distx);
              for (int i =2;i<this.events.size();++i){
                x2 = this.events.get(i).getX();
                y2 = this.events.get(i).getY();
                time2 = this.events.get(i).getTime();
                disty = Math.sqrt(Math.pow((y2-y1), 2));
                distx = Math.sqrt(Math.pow((x2-x1), 2));
                Double distance = Math.atan2(disty, distx) - szog;
                result += distance/(time1-time2);
                }
              result = result/this.events.size();
              return result;
    }
    
    private int melyikmozgas(){
        for (int i=0;i< this.events.size();++i){
            if(this.events.get(i).getActiontype().equalsIgnoreCase("Drag")){
                return 0;
            }
        }
        if (this.events.get(this.events.size()-1).getActiontype().equalsIgnoreCase("Move")){
            return 2;
        }
        return 1;
    }
    
    private int milyengob(){
        if (this.events.get(this.events.size()-1).getButtonype().equalsIgnoreCase("Left")){
            return 1;
        }
         if (this.events.get(this.events.size()-1).getButtonype().equalsIgnoreCase("Right")){
            return 2;
        }
           return 0;
        }
}

