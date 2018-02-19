/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmllearner;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author arashdeep
 */
public class HTMLEditorController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    HTMLEditor HtmlEditor;
    @FXML
    AnchorPane ap;
    SavingBin savingBin;
    String HTML;
    String PathVar;
    String Author;
    String Title;

    public void SetInit(String Path, String Author, String Title) {
        savingBin = new SavingBin(Path);
        HTML = savingBin.open(Path);
        HtmlEditor.setHtmlText(HTML);
        PathVar = Path;
        System.out.println(HTML);
        this.Author = Author;
        this.Title = Title;
    }

    @FXML
    private void BackAction(ActionEvent event) {
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
        app_stage.setTitle("Try Html");
        app_stage.setMaximized(true);
        app_stage.setScene(scene);
        app_stage.show();
        FXMLDocumentController ToMain = Loader.getController();
        ToMain.FileOpen(HTML, PathVar, Author, Title);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HtmlEditor.setOnMouseClicked((MouseEvent event) -> {
            HTML = HtmlEditor.getHtmlText();
        });
        HtmlEditor.setOnKeyReleased((KeyEvent event) -> {
            HTML = HtmlEditor.getHtmlText();
        });
        ap.setOnKeyPressed((KeyEvent event) -> {
            if (event.getCode() == KeyCode.CONTROL.S) {
                System.out.println(HTML);
                savingBin.save(HTML, PathVar);

            }
        });
    }

}
