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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author chase
 * 
 * references:
 * https://stackoverflow.com/questions/24164667/javafx-make-object-visible-but-not-consume-ignore-clicks
 * https://stackoverflow.com/questions/13567019/close-fxml-window-by-code-javafx
 * https://stackoverflow.com/questions/22007595/borderpane-with-color-gradient/22008535
 * https://stackoverflow.com/questions/12804664/how-to-swap-screens-in-a-javafx-application-in-the-controller-class
 * https://stackoverflow.com/questions/43242203/how-do-i-create-keyevents-in-java-fxml
 * https://stackoverflow.com/questions/57927816/how-to-make-all-cells-in-gridpane-visible-and-same-size
 * 
 */
public class MazeGameController extends AbstractController  implements Initializable,MusicInterface{

   
    private ArrayList<Tile> tiles = new ArrayList<>();
    private ArrayList<Tile> path = new ArrayList<>();
    private ArrayList<Maze> mazes = new ArrayList<>();
    private static MediaPlayer mediaPlayerMusic;
    private Tile currentTile;
    private final int SIZES[] =  {10, 20, 30, 50};
    private boolean move = true;
    private int size;
    private Alert a = new Alert(AlertType.INFORMATION);
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button upButton;
    @FXML
    private Button downButton;
    @FXML
    private Button rightButton;
    @FXML
    private Button leftButton;
    @FXML
    private AnchorPane mazePane;
    @FXML
    private MenuItem records;
    
    @FXML
    private Text timerTime;
    @FXML
    private MenuItem menuExit;
    @FXML
    private MenuItem exit;
    @FXML
    private MenuItem reset;
    @FXML
    private MenuItem pause;
    @FXML
    private Text pauseText;
    @FXML
    private Text highScoreText;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        for(int x:SIZES){
            mazes.add(new Maze(x));
            
       }
        loadMaze();
        setCurrentRecord();
    }
   
    
    //lines 103-260 are used for game controls
    //action events for button presses
    @FXML
    private void upButton(ActionEvent event) {
        up();

    }

    @FXML
    private void downButton(ActionEvent event) {
        down();
    }

    @FXML
    private void rightButton(ActionEvent event) {
        right();
    }

    @FXML
    private void leftButton(ActionEvent event) {
        left();
    }

    //action events for wasd and arrow keys 
    @FXML
    void keyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
            down();
        }
        if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
            right();
        }
        if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
            up();
        }
        if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
            left();
        }

    }
    
    //move methods. all check if the circle has been visited or if it is the end of the maze.
    //checks if the user can move into a new tile on the maze then adds a circle when moved.
    public void up() {
        
        if (currentTile.getY() != 0 && !currentTile.n && move) {
            Tile next = mazes.get(difficulty).findTile(currentTile.getX(),currentTile.getY()-1,tiles);
            
            if(checkNotNull(next)){

                    if (next.getVisited()) {
                        
                        currentTile.setVisited(false);
                        currentTile.getChildren().remove(currentTile.getCircle());
                    }
                    else if(next.getEnd()){
                        gameWon();
                    }
                    else{
                        next.getChildren().add(next.getCircle());
                    }
                    path.add(next);
                    currentTile = next;
                    currentTile.setVisited(true);
                    
                    
            
                }
        }
    }

    public void down() {
       
        if (currentTile.getY() != size && !currentTile.s && move) {
        Tile next = mazes.get(difficulty).findTile(currentTile.getX(),currentTile.getY()+1,tiles);
        
        if(checkNotNull(next)){
                    if (next.getVisited()) {
                        
                        currentTile.setVisited(false);
                        currentTile.getChildren().remove(currentTile.getCircle());
                    }
                    else if(next.getEnd()){
                        gameWon();
                    }
                    else{
                        next.getChildren().add(next.getCircle());
                    }
                    path.add(next);
                    currentTile = next;
                    currentTile.setVisited(true);
                    
                    
                    
                    
                }
            }
    }


    private void right() {
        
        if (currentTile.getX() != size && !currentTile.e && move) {
        Tile next = mazes.get(difficulty).findTile(currentTile.getX()+1,currentTile.getY(),tiles);
        
        if(checkNotNull(next)){
                    if (next.getVisited()) {
                        
                        currentTile.setVisited(false);
                        currentTile.getChildren().remove(currentTile.getCircle());
                    }
                    else if(next.getEnd()){
                        gameWon();
                    }
                    else{
                        next.getChildren().add(next.getCircle());
                    }
                   
                    path.add(next);
                    currentTile = next;
                    currentTile.setVisited(true);
                    
                    
                   
                   
                    
                    
                   
                }
            }
    }

    private void left() {
        
        if (currentTile.getX() != 0 && !currentTile.w && move) {
            Tile next = mazes.get(difficulty).findTile(currentTile.getX()-1,currentTile.getY(),tiles);
            
            if(checkNotNull(next)){
                    if (next.getVisited()) {
                        
                        currentTile.setVisited(false);
                        currentTile.getChildren().remove(currentTile.getCircle());
                    }
                    else if(next.getEnd()){
                        gameWon();
                    }
                    else{
                        next.getChildren().add(next.getCircle());
                    }
                    
                    path.add(next);
                    currentTile = next;
                   
                    currentTile.setVisited(true);
                    
                    
                    
            }
          }
       }

       
   
    //gets current top record for the loaded maze and sets the UI highscore to whatever it is.
    // if there is not a record it sets the UI highscore to "no current records"
    public void setCurrentRecord(){
        if(currentRecord.getFirst() != null){
             highScoreText.setText(formatTime(currentRecord.getFirst().getKey())+" By: "+ currentRecord.getFirst().getValue());
        }
        else{
            highScoreText.setText("No Current Records!");
        }
    }
    

    

    //loads the current maze into the UI and allows user to interact with it.
    //first checks for maze type and gamemode to load correct assets 
    //sets music and pane coloring accordingly as well as seting and starting the game timer
    private void loadMaze(){
        path.add(mazes.get(difficulty).currentTile);
        if(gameType==1){
            mainPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #b174ee, #65159a)");
            difficulty=1;
            playMusic(timeAttackFile);
            timer=new Timer(getGameTime());
            
        }
        else{
            playMusic(normalFile);
            timer=new Timer();
            mainPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 100% 100%, #23a8eb, #074968)");
            
        }
        
        size=mazes.get(difficulty).size;
        mazePane.getChildren().add(mazes.get(difficulty).grid);
        Maze currentMaze = mazes.get(difficulty);
        
       currentTile= currentMaze.currentTile;
       tiles = currentMaze.tiles;
       setupTimer();
       timerStart();
        
        
        
    }
    //override of update funtion in AbstractController
    //updates UI timer acording to gametype
    @Override
    public void update(){
        super.update();
        if(gameType==0){
            timerTime.setText(timer.getTotalTimeMins());
        }
        else{
            timerTime.setText(timer.getTotalTimeSecsDown());
            if(timer.getTimerEnd()){
                timerTime.setText("00.00"); 
                gameLost();
            }
        }
        
        
    }
    //scene change funtions
    //menuExit returns the user to the main menu
    @FXML
    private void menuExit(ActionEvent event) throws InterruptedException {
        timerStop();
        MusicInterface.buttonClick();
        mediaPlayerMusic.stop();
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TitleScreen.fxml"));
        Stage stage = (Stage) mainPane.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        }
        catch(IOException io){
            System.out.println("004");
        }
    }
    //records puts the user into the records scene
    @FXML
    private void records(ActionEvent event) throws InterruptedException {
        mediaPlayerMusic.stop();
        MusicInterface.buttonClick();
        timerStop();
        try{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Records.fxml"));
        Stage stage = (Stage) mainPane.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        }
        catch(IOException io){
            System.out.println("002");
        }
    }
    //exit closes the user out of the application entirely 
    @FXML
    private void exit(ActionEvent event) throws InterruptedException {
        mediaPlayerMusic.stop();
        MusicInterface.buttonClick();
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
    }
   
    


    // reset method resets the current maze along with the timer starting the current maze from the beginning
    @FXML
    private void reset(ActionEvent event) throws InterruptedException {
        timerStop();
        currentTile = mazes.get(difficulty).currentTile = mazes.get(difficulty).findTile(0, 0, tiles);
        for(Tile t:path){
            t.getChildren().remove(t.getCircle());
            t.setVisited(false);
        }
        currentTile.setVisited(true);
        MusicInterface.buttonClick();
        
        pauseText.setText("");
        pause.setText("Pause");
        timer.reset();
        move=true;
        timerStart();
       
        
    }
    //pause pauses the game state and timer and if clicked again resumes the game
    @FXML
    private void pause(ActionEvent event) throws InterruptedException {
        MusicInterface.buttonClick();
        if(!currentTile.getEnd()){
            if(pause.getText().equals("Pause")){
            move = false;
            pauseText.setText("Paused");
            timerStop();
            pause.setText("Play");
        }
        else if(pause.getText().equals("Play")){
            move=true;
            timerStart();
            pauseText.setText("");
            pause.setText("Pause");   
         }
            
        }   
    }
    
    
    
    
    
    
    //if user reaches the end, gameWon is called saving their time and has a pop up
    //showing they won. It also rechecks if a new record is made
    private void gameWon(){
        timerStop();
        move=false;
       
        a.setContentText("YOU WIN!");
        a.show();
        currentRecord.saveTime(timer.getTotalSecs(), playerName);
        setCurrentRecord();
        
    }
    //gameLost stops the game and returns a pop up if the user failed to reach the end
    private void gameLost(){
        timerStop();
        move=false;
        
        a.setContentText("You Lose :(");
        a.show();
        
        
    }
    
    
    
    
    
    //tile check to see if tile exists or not
    //used for the move methods to check if the user can move in a direction 
    public boolean checkNotNull(Tile tile){
            if(tile != null){
                return true;
            }
            return false;
        }

    
    
    
    
    
    
    
    //overrides the playMusic method in the MusicInterface playing the main game
    //music on a loop. Also runs the musuc off of a thread
    @Override
    public void playMusic(File f) {
        Media media = new Media(f.toURI().toString());
       mediaPlayerMusic = new MediaPlayer(media);
       mediaPlayerMusic.setAutoPlay(true);
       //running off thread again
       mediaPlayerMusic.setOnEndOfMedia(new Runnable() {
        @Override
        public void run() {
            mediaPlayerMusic.seek(Duration.ZERO);
            mediaPlayerMusic.play();
            }
        }); 
       
    }
    
    
    
    
    
    
    
}
