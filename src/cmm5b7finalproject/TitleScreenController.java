/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmm5b7finalproject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
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
public class TitleScreenController extends AbstractController implements Initializable,MusicInterface {

    @FXML
    private AnchorPane titlePane;
    @FXML
    private Button nModeGame;
    @FXML
    private Button easy;
    @FXML
    private Button normal;
    @FXML
    private Button startGame;
    @FXML
    private Button timeAttackGame;
    @FXML
    private Button medium;
    @FXML
    private Button hard;
    @FXML
    private Button aboutAlert;
    @FXML
    private Button helpAlert;
    @FXML
    private Button playerNameButton;
    @FXML
    private Text playerNameText;
    Alert a = new Alert(Alert.AlertType.NONE);
    
    private static MediaPlayer mediaPlayerTitle;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    //loads record files into record objects and adds them into a into an array list as well as updates UI elements
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       titlePane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #dc143c, #661a33)");
       setGameType(0);
       setDifficulty(0);
       normalRecords.add(new Record("normal0.txt"));normalRecords.add(new Record("normal1.txt"));normalRecords.add(new Record("normal2.txt"));normalRecords.add(new Record("normal3.txt"));
       timeAttackRecords.add(new Record("timeattack0.txt"));timeAttackRecords.add(new Record("timeattack1.txt"));timeAttackRecords.add(new Record("timeattack2.txt"));timeAttackRecords.add(new Record("timeattack3.txt"));
       playerNameText.setMouseTransparent(true);
       if(!"Anonymos".equals(playerName)){
            playerNameText.setText(playerName+"!");
       }
       
       playMusic(menuFile);
        
       
    }    
    //sets the game type off of a button click 
    @FXML
    private void nModeGame(ActionEvent event) throws InterruptedException {
        setGameType(0);
        nModeGame.setStyle("-fx-background-color: #00ff00");
        timeAttackGame.setStyle("-fx-background");
        MusicInterface.buttonClick();
    }
    
    @FXML
    private void timeAttackGame(ActionEvent event) throws InterruptedException {
        setGameType(1);
        timeAttackGame.setStyle("-fx-background-color: #00ff00");
        nModeGame.setStyle("-fx-background");
        MusicInterface.buttonClick();
    }
    
    
    //sets game difficulty from a button click 
    @FXML
    private void easy(ActionEvent event) throws InterruptedException {
        setDifficulty(0);
        setGameTime(100);
        easy.setStyle("-fx-background-color: #00ff00");
        normal.setStyle("-fx-background");
        medium.setStyle("-fx-background");
        hard.setStyle("-fx-background");
        MusicInterface.buttonClick();
    }

    @FXML
    private void normal(ActionEvent event) throws InterruptedException {
        setDifficulty(1);
        setGameTime(60);
        normal.setStyle("-fx-background-color: #00ff00");
        easy.setStyle("-fx-background");
        medium.setStyle("-fx-background");
        hard.setStyle("-fx-background");
        MusicInterface.buttonClick();
    }

    


    @FXML
    private void medium(ActionEvent event) throws InterruptedException {
        setDifficulty(2);
        setGameTime(35);
        medium.setStyle("-fx-background-color: #00ff00");
        normal.setStyle("-fx-background");
        easy.setStyle("-fx-background");
        hard.setStyle("-fx-background");
        MusicInterface.buttonClick();
    }

    @FXML
    private void hard(ActionEvent event) throws InterruptedException {
        setDifficulty(3);
        setGameTime(25);
        hard.setStyle("-fx-background-color: #00ff00");
        normal.setStyle("-fx-background");
        medium.setStyle("-fx-background");
        easy.setStyle("-fx-background");
        MusicInterface.buttonClick();
        
    }
    //loads current record from game difficulty and type and changes scene to MazeGame
    @FXML
    private void startGame(ActionEvent event) {
        setRecordMode(gameType,difficulty);
         mediaPlayerTitle.stop();
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MazeGame.fxml"));
        Stage stage = (Stage) titlePane.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        }
        catch(IOException io){
            System.out.println("001");
        }
    }
    //help and about alert pop ups
    @FXML
    private void aboutAlert(ActionEvent event) throws InterruptedException {
        MusicInterface.buttonClick();
        a.setTitle("ABOUT");
        a.setHeaderText("Created By: Chase Morgan\nCmm5b7");
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText("About me: My name is Chase Morgan, and I am a sophomore here at Mizzou. Coding is always something I was interested in from early on in high school and I wanted to pursue that into college. Java was one of my first languages and it motivated me throughout this class. OOP really helped me grasp much about Java I did not understand before and I am hoping to peruse it more in the future. \n" +
"This Maze game I created came from an idea I had working on a project back in my first Java class in high school where we created something similar and solved it using code. My hope was to create a game based off that, but I never had the experience or understanding to do that. OOP gave me that understanding and I have created something I am proud of. I still have many ideas I would like to add to this game in the future and hope to refine it even more as I learn more about Java.");
        a.show();
       
       
    }

    @FXML
    private void helpAlert(ActionEvent event) throws InterruptedException {
        MusicInterface.buttonClick();
        a.setTitle("HELP");
        a.setHeaderText("Controls and Features");
        a.setContentText("Game Modes and Difficulty: \n" + "The Maze game has two game modes with 4 difficulties each normal mode and time attack.\n" +"Normal mode is just a normal maze you solve as fast as you can ranging in different sizes based off the difficulty selected.\n" +"Easy is a 10x10 maze\n" +"Normal is a 20x20 maze\n" +
        "Medium is a 30x30 maze\n" +"Hard is a 50x50 maze\n" +"Time attack is set on a set size of 20x20 with different time constraints\n" +"Easy gives you 100 seconds to complete the maze  \n" +"Normal gives you 60 seconds to complete the maze  \n" +
        "Medium gives you 35 seconds to complete the maze  \n" +"Hard gives you 25 seconds to complete the maze  \n" +"\n" +"Movement:\n" +"You start at the red square in the top left of that maze and attempt to reach the green square in the middle of the maze.\n" +"You can use WASD, arrow keys, or the buttons on the UI to traverse the maze.\n" +"\n" +
        "Records:\n" +"The Game saves your fasted time you complete the maze on each of the different game modes and difficulties. To check the top records, navigate to the records page under the records menu while in a maze on the top of the screen.\n" +"To change the name for the records, click on the text “Click Here” on the main menu otherwise all records will be saved anonymously.\n" +"\n" +"(pre-made records have already been loaded in but normal mode easy has been left empty for the grader)");
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.show();
    }
    //loads in the current record from arrayList according to difficulty and game type
    private void setRecordMode(int mode, int dif){
           
       
        if(mode==0){
            if(dif==0){
                currentRecord = normalRecords.get(0);
            }
            if(dif==1){
                currentRecord = normalRecords.get(1);
                
            }
            if(dif==2){
               currentRecord = normalRecords.get(2);
                
            }
            if(dif==3){
                currentRecord = normalRecords.get(3);
                
            }
            
            
        }
        if(mode ==1){
            if(dif==0){
                currentRecord = timeAttackRecords.get(0);
            }
            if(dif==1){
                currentRecord = timeAttackRecords.get(1);
                
            }
            if(dif==2){
                currentRecord = timeAttackRecords.get(2);
                
            }
            if(dif==3){
                currentRecord = timeAttackRecords.get(3);
                
            }
  
        }
       }
        //pop up and alert set up for changing the users name 
        public void showAlert(Alert a){
       
        
        
        
        
        TextInputDialog td = new TextInputDialog();
        td.setHeaderText("Enter Your Name!");
        
        
        Optional<String> result = td.showAndWait();
                
        
        if(result.isPresent())
        { 
            if("".equals(result.get()) ){
                playerName="Anonymos";
            }
            else
            {
                playerName= result.get();
            }
        }
        playerNameText.setText(playerName+"!");
        
    }
    //button click that allows the user to change their name to be saved in the records
    @FXML
    private void playerNameButton(ActionEvent event) {
        showAlert(a);
    }
    //plays sound when user hovers their mouse over a button in the main menu
    @FXML
    private void buttonHover(MouseEvent e) throws InterruptedException{
        MusicInterface.hoverSound();
    }


    //overrides playmusic in MusicInterface
    @Override
    public void playMusic(File f) {
       Media media = new Media(f.toURI().toString());
        mediaPlayerTitle = new MediaPlayer(media);
        mediaPlayerTitle.setAutoPlay(true);
        //lambda expression can be used here but running music off a thread insted
        mediaPlayerTitle.setOnEndOfMedia(new Runnable() {
        @Override
        public void run() {
            mediaPlayerTitle.seek(Duration.ZERO);
            mediaPlayerTitle.play();
        }
    }); 
        
    }
    
    
    
    
   
    
}
