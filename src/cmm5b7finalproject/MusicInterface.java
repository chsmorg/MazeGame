/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmm5b7finalproject;

import java.io.File;
import java.util.concurrent.TimeUnit;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author chase
 * 
 * References:
 * https://stackoverflow.com/questions/43190594/javafx-mediaplayer-loop
 */

public interface MusicInterface{
    //loads 2 often used medias and creates a player
    public final MediaPlayer HOVERSOUND = new MediaPlayer(new Media(new File("hover.mp3").toURI().toString()));
   
    public final  MediaPlayer CLICKSOUND = new MediaPlayer(new Media(new File("click.wav").toURI().toString()));
    //override method to play music
    public void playMusic(File f);
    //static method for hover sound whe nuser hovers over a button
    public static void hoverSound() throws InterruptedException{
        HOVERSOUND.play();
        TimeUnit.MILLISECONDS.sleep(157);
        HOVERSOUND.stop();
        
    }
    //static method when user clicks a button 
    public static void buttonClick() throws InterruptedException{
        CLICKSOUND.play();
        TimeUnit.MILLISECONDS.sleep(227);
        CLICKSOUND.stop();
        
    }
    
}
