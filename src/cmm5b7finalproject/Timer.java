/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmm5b7finalproject;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author chase
 */
public class Timer {
    
    private final double TICK_TIME_IN_SECONDS = 0.01;
    
    private double totalSecondsElapsed;
    private double minsElapsed;
    
    private double secondsElapsed;
    private double countDown;
    private boolean end= false;
    
    private NumberFormat secs;
    private NumberFormat mins;
    
    //timer the only counts up
    public Timer(){
        secondsElapsed=0;
        totalSecondsElapsed=0;
        secs = new DecimalFormat("#00.00");
        mins = new DecimalFormat("#00");
        
       
    }
    //timer that has an end time
    public Timer(int time){
        secondsElapsed=0;
        totalSecondsElapsed=0;
        secs = new DecimalFormat("#00.00");
        mins = new DecimalFormat("#00");
        countDown=time;  
    }
    
    
    //updates the timers time and checks if the timer is at the end time
    protected void update()
    {
        secondsElapsed += TICK_TIME_IN_SECONDS;
        totalSecondsElapsed += TICK_TIME_IN_SECONDS; 
        if(secondsElapsed>=59.99){
            minsElapsed++;
            secondsElapsed=0;
        }
        if(secs.format(countDown).equals(secs.format(totalSecondsElapsed))){
            end = true;
        }
        
    }
    //returns total time elapsed formated in minutes
    public String getTotalTimeMins(){
        return mins.format(minsElapsed)+":"+secs.format(secondsElapsed);
    }
    //returns total time in seconds out of the count down time
    public String getTotalTimeSecsDown(){
        return secs.format(countDown-totalSecondsElapsed);
    }
    //gets if the timer has ended
    public boolean getTimerEnd(){
        return this.end;
    }
    //returns total time in seconds unformated
    public double getTotalSecs(){
        return this.totalSecondsElapsed;
    }
    //resets the timer back it its starting state 
    public void reset(){
        this.secondsElapsed=0;
        this.minsElapsed=0;
        this.end=false;
        this.totalSecondsElapsed=0;
        
        
        
    }
    
    
}
