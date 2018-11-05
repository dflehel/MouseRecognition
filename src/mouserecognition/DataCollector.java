package mouserecognition;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Denes
 */
public class DataCollector implements NativeMouseInputListener , NativeMouseWheelListener{
    
    
    private Queue<ArrayList<Event>> events = new LinkedList<ArrayList<Event>>();
    private ArrayList<Event> eventlist = new ArrayList<>();
    private Thread t;

    public Thread getT() {
        return t;
    }

    public void setT(Thread t) {
        this.t = t;
    }

    public DataCollector() {
    }

    public Queue<ArrayList<Event>> getEvents() {
        return events;
    }

    public void setEvents(Queue<ArrayList<Event>> events) {
        this.events = events;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    private long startTime = System.currentTimeMillis();
    @Override
    public void nativeMouseClicked(NativeMouseEvent nme) {
    }

    @Override
    public void nativeMousePressed(NativeMouseEvent nme) {
        if (nme.getButton() == 1){
                    long estimatedTime = System.currentTimeMillis() - startTime;
                    Event event = new Event();
                    event.setTime(estimatedTime);
                    event.setX(nme.getX());
                    event.setY(nme.getY());
                    event.setButtonype("Left");
                    event.setActiontype("Pressed");
                    this.eventlist.add(event);
                    if (this.eventlist.size()>10){
                      if (this.events.isEmpty()){
                        this.events.add(this.eventlist);
                         synchronized (this.events) {
                        this.events.notify();
                         }
                    }
                    else{
                        this.events.add(this.eventlist);
                    }
                    }
                }
         if (nme.getButton()==2){
                long estimatedTime = System.currentTimeMillis() - startTime;
                   Event event = new Event();
                    event.setTime(estimatedTime);
                    event.setX(nme.getX());
                    event.setY(nme.getY());
                    event.setButtonype("Right");
                    event.setActiontype("Pressed");
                    this.eventlist.add(event);
                    if (this.eventlist.size()>10){
                     if (this.events.isEmpty()){
                        this.events.add(this.eventlist);
                         synchronized (this.events) {
                        this.events.notify();
                         }
                    }
                    else{
                        this.events.add(this.eventlist);
                    }
                    }
                }
                
         this.eventlist = new ArrayList<>();
    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent nme) {
         if (nme.getButton() == 1){
                    long estimatedTime = System.currentTimeMillis() - startTime;
                    Event event = new Event();
                    event.setTime(estimatedTime);
                    event.setX(nme.getX());
                    event.setY(nme.getY());
                    event.setButtonype("Left");
                    event.setActiontype("Released");
                    this.eventlist.add(event);
                }
         if (nme.getButton()==2){
                long estimatedTime = System.currentTimeMillis() - startTime;
                   Event event = new Event();
                    event.setTime(estimatedTime);
                    event.setX(nme.getX());
                    event.setY(nme.getY());
                    event.setButtonype("Right");
                    event.setActiontype("Released");
                    this.eventlist.add(event);
                }
    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent nme) {
        long estimatedTime = System.currentTimeMillis() - startTime;
          Event event = new Event();
                    event.setTime(estimatedTime);
                    event.setX(nme.getX());
                    event.setY(nme.getY());
                    event.setButtonype("NoButton");
                    event.setActiontype("Moved");
                    if(this.eventlist.size()>10){
                    if(this.eventlist.get(this.eventlist.size()-1).getTime()-event.getTime() >10000){
                         if (this.events.isEmpty()){
                        this.events.add(this.eventlist);
                         synchronized (this.events) {
                        this.events.notify();
                         }
                    }
                    else{
                           synchronized (this.events) {  
                        this.events.add(this.eventlist);
                           }
                    }
                         this.eventlist = new ArrayList<>();
                    }
                    }
                    this.eventlist.add(event);
       
    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent nme) {
     long estimatedTime = System.currentTimeMillis() - startTime;
       Event event = new Event();
                    event.setTime(estimatedTime);
                    event.setX(nme.getX());
                    event.setY(nme.getY());
                    event.setButtonype("NoButton");
                    event.setActiontype("Drag");
                    this.eventlist.add(event);
     
    }

    @Override
    public void nativeMouseWheelMoved(NativeMouseWheelEvent nmwe) {
     long estimatedTime = System.currentTimeMillis() - startTime;
    }
    
}
