/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmllearner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author arashdeep
 */
public class SetFileController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField PathArea;
    @FXML
    Label ErrLabel;
    @FXML
    AnchorPane ap;
    String PathVar = "";
    Stage st = new Stage();

    @FXML
    private void BrowseAction(ActionEvent event) {
        saveas(st, "");
        PathArea.setText(PathVar);
    }

    @FXML
    private void SubmitAction(ActionEvent event) {
        if ("".equals(PathVar)) {
            MakeAlertErr();
        } else {
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
            try {
                Loader.load();
            } catch (LoadException ex) {
            } catch (IOException ex) {
            }
            Parent logparent = Loader.getRoot();
            Scene scene = new Scene(logparent);
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.setMaximized(true);
            app_stage.setTitle("Try Html");
            app_stage.setScene(scene);
            app_stage.show();
            FXMLDocumentController ToMain = Loader.getController();
            ToMain.setPath(PathVar);
        }
    }

    public void saveas(Stage st, String text) {
        FileWriter fw = null;
        try {
            FileChooser filechooser = new FileChooser();
            filechooser.setInitialDirectory(new File(System.getProperty("user.home")));
            File file = filechooser.showSaveDialog(st);
            fw = new FileWriter(file);
            PathVar = file.getPath();
            BufferedWriter Writer = new BufferedWriter(fw);
            Writer.write(text);
            Writer.flush();
            Writer.close();

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ap.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    if ("".equals(PathVar)) {
                        MakeAlertErr();
                    } else {
                        FXMLLoader Loader = new FXMLLoader();
                        Loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
                        try {
                            Loader.load();
                        } catch (LoadException ex) {
                        } catch (IOException ex) {
                        }
                        Parent logparent = Loader.getRoot();
                        Scene scene = new Scene(logparent);
                        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        app_stage.setMaximized(true);
                        app_stage.setTitle("Try Html");
                        app_stage.setScene(scene);
                        app_stage.show();
                        FXMLDocumentController ToMain = Loader.getController();
                        ToMain.setPath(PathVar);
                    }
                }
            }
        });
    }

    public void MakeAlertErr() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Path Not Given");
        alert.setContentText("Please Give Path Of The File To Create");
        alert.showAndWait();
    }
}
