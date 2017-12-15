/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmllearner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author arashdeep
 */
public class HTMLLearner extends Application {

    Stage st;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        st = stage;
        Scene scene = new Scene(root);
        stage.setMaximized(true);
        stage.setTitle("Try HTML Alpha");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public Stage getst() {
        return st;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
