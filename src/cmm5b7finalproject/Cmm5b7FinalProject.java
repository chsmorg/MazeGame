/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmm5b7finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author chase
 */
public class Cmm5b7FinalProject extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("TitleScreen.fxml"));
        
        Parent root = (Parent)loader1.load();
        //Parent root2 = (Parent)loader2.load();
        //FXMLDocumentController controller = (FXMLDocumentController)loader1.getController();
        
      //  controller.ready(stage);
        
        
        Scene scene1 = new Scene(root);
        //Scene scene2 = new Scene(root2);
        
        
        stage.setScene(scene1);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
