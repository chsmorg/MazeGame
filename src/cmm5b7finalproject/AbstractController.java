/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmm5b7finalproject;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;


/**
 *
 * @author chase
 */
public abstract class AbstractController {
    protected static int difficulty; 
    protected static int gameType;
    protected static Timer timer;
    protected KeyFrame keyFrame;
    protected Timeline timeline;
    protected static ArrayList<Record> normalRecords = new ArrayList<>();
    protected static ArrayList<Record> timeAttackRecords = new ArrayList<>();
    protected static Record currentRecord;
    protected static String playerName = "Anonymos";
    protected static int timerStartTime;
    private NumberFormat secs = new DecimalFormat("#00.00");
    private NumberFormat mins = new DecimalFormat("#0");
    protected static File menuFile = new File("menumusic.wav");
    protected static File normalFile = new File("normalMode.wav");
    protected static File timeAttackFile= new File("timeAttack.wav");
    
    
    
    //retuns game difficulty
    protected static int getDifficulty(){
        return difficulty;
        
    }
    //returns game type
    protected static int getgameType(){
        return gameType;
        
    }
    //sets game difficulty 
    protected static void setDifficulty(int i){
        difficulty = i;
        
    }
    //sets game type
    protected static void setGameType(int i){
        gameType = i;
       
    }
    //sets game time for timer
    protected static void setGameTime(int i){
        timerStartTime = i;
       
    }
    //retruns game time
    protected static int getGameTime(){
        return timerStartTime;
        
    }
    //starts a new timer with the set time
    public void setupTimer(){
        keyFrame = new KeyFrame(Duration.millis(10), (ActionEvent event) -> {
           update(); 
        });
        timeline = new Timeline(keyFrame); 
        timeline.setCycleCount(Animation.INDEFINITE);
        
    }
    //updates the timer by calling the timer update method
    public void update(){
        timer.update();
    }
    //starts timer
    public void timerStart(){
        timeline.play();
    }
    //stops timer
    public void timerStop(){
        timeline.stop();
    }
    //formats game time into a cleaner time
    public String formatTime(double time){
        String keyFormat = "";
                if(time<=59.99){
                    keyFormat=secs.format(time);
                }
                if(time>=60){
                    int min = (int)time/60;
                    double sec = time%60;
                    keyFormat=mins.format(min)+":"+secs.format(sec);
                }
                return keyFormat;
    
    }
    
}
