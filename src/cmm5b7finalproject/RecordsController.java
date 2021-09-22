/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmm5b7finalproject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author chase
 */
public class RecordsController extends AbstractController implements Initializable,MusicInterface {

    private int mode=0;
    private int dif=0;
     private static MediaPlayer mediaPlayerRecord;
    @FXML
    private AnchorPane recordPane;
    @FXML
    private MenuItem mazeReturn;
    @FXML
    private Label modeLabel;
    @FXML
    private Label difficultyLabel;
    @FXML
    private MenuItem menuReturn;
    @FXML
    private MenuItem nMode;
    @FXML
    private MenuItem TAMode;
    @FXML
    private MenuItem easy;
    @FXML
    private MenuItem normal;
    @FXML
    private MenuItem medium;
    @FXML
    private MenuItem hard;
    @FXML
    private Text record1;
    @FXML
    private Text record2;
    @FXML
    private Text record3;
    @FXML
    private Text record8;
    @FXML
    private Text record7;
    @FXML
    private Text record6;
    @FXML
    private Text record5;
    @FXML
    private Text record4;
    @FXML
    private Text record9;
    @FXML
    private Text record10;
    private int index;
    private ArrayList<Text> text = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    //adds all record texts from fxml doc into an array list and prints records from
    //normal mode easy difficulty 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        recordPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #3a61ff, #dd5d36)");
        text.add(record1);text.add(record2);text.add(record3);text.add(record4);
        text.add(record5);text.add(record6);text.add(record7);text.add(record8);
        text.add(record9);text.add(record10);
        printTimes(mode,dif);
        playMusic(menuFile);
        
        
        
        
        
    }   
    //retuns user back to maze scene
    @FXML
    private void mazeReturn(ActionEvent event) throws InterruptedException {
        MusicInterface.buttonClick();
        mediaPlayerRecord.stop();
        
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MazeGame.fxml"));
        Stage stage = (Stage) recordPane.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        }
        catch(IOException io){
            System.out.println("005");
        }
    }
    //returns user back to main menu scene
    @FXML
    private void menuReturn(ActionEvent event) throws InterruptedException {
        MusicInterface.buttonClick();
        mediaPlayerRecord.stop();
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TitleScreen.fxml"));
        Stage stage = (Stage) modeLabel.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        }
        catch(IOException io){
            System.out.println("006");
        }
    }
    //game mode record selection 
    @FXML
    private void nMode(ActionEvent event) throws InterruptedException {
        MusicInterface.buttonClick();
        if(mode != 0){
            mode =0;
            modeLabel.setText("NORMAL MODE");
            modeLabel.setStyle("-fx-text-fill: #25ffd0");
            printTimes(mode,dif);
        }
        
        
    }

    @FXML
    private void TAMode(ActionEvent event) throws InterruptedException {
        MusicInterface.buttonClick();
        if(mode != 1){
            mode =1;
            modeLabel.setText("TIME ATTACK");
            modeLabel.setStyle("-fx-text-fill: #b726ff");
            printTimes(mode,dif);
        }
    }
    //game difficulty selection
    @FXML
    private void easy(ActionEvent event) throws InterruptedException {
        MusicInterface.buttonClick();
        if(dif != 0){
            dif=0;
            difficultyLabel.setText("EASY");
            difficultyLabel.setStyle("-fx-text-fill: #1fc260");
            printTimes(mode,dif);
            
           
            
        }
    }

    @FXML
    private void normal(ActionEvent event) throws InterruptedException {
        MusicInterface.buttonClick();
        if(dif != 1){
            dif=1;
            difficultyLabel.setText("NORMAL");
            difficultyLabel.setStyle("-fx-text-fill: #f8da47");
            printTimes(mode,dif);
            
        }
    }

    @FXML
    private void medium(ActionEvent event) throws InterruptedException {
        MusicInterface.buttonClick();
        if(dif != 2){
            dif=2;
            difficultyLabel.setText("MEDIUM");
            difficultyLabel.setStyle("-fx-text-fill: #ffa600");
            printTimes(mode,dif);
            
        }
    }

    @FXML
    private void hard(ActionEvent event) throws InterruptedException {
        MusicInterface.buttonClick();
        if(dif != 3){
            dif=3;
            difficultyLabel.setText("HARD");
            difficultyLabel.setStyle("-fx-text-fill: #ff0000");
            printTimes(mode,dif);
            
            
        }
    }
    //prints the content of the current record loaded into the UI only prints first ten times
    private void printTimes(int mode, int dif){
        for(Text t : text){
            t.setText("");
        }
        Record r= normalRecords.get(0);
        if(mode==0){
            if(dif==0){
                r = normalRecords.get(0);
            }
            if(dif==1){
                r = normalRecords.get(1);
                
            }
            if(dif==2){
                r = normalRecords.get(2);
                
            }
            if(dif==3){
                r = normalRecords.get(3);
                
            }
            
            
        }
        if(mode ==1){
            if(dif==0){
                r = timeAttackRecords.get(0);
            }
            if(dif==1){
                r = timeAttackRecords.get(1);
                
            }
            if(dif==2){
                r = timeAttackRecords.get(2);
                
            }
            if(dif==3){
                r = timeAttackRecords.get(3);
                
            }
  
        }
        for(Entry<Double,String> i : r.getTimes().entrySet()){
            if(index>9){
                break;
            }
            else
            {
                String keyFormat;
                keyFormat=formatTime(i.getKey());
                text.get(index).setText("#"+String.valueOf(index+1)+ " " +keyFormat+" by: "+i.getValue());
            }
            index++;
        }
        index=0;
    }
    //override method from MusicInterface plays music in record scene
    @Override
    public void playMusic(File f) {
         Media media = new Media(f.toURI().toString());
       mediaPlayerRecord = new MediaPlayer(media);
       mediaPlayerRecord.setAutoPlay(true);
       //running off thread again
       mediaPlayerRecord.setOnEndOfMedia(new Runnable() {
        @Override
        public void run() {
            mediaPlayerRecord.seek(Duration.ZERO);
            mediaPlayerRecord.play();
            }
        }); 
    }
    
}
