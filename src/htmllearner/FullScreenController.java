/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmllearner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * FXML Controller class
 *
 * @author arashdeep
 */
public class FullScreenController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    WebView web;
    @FXML
    AnchorPane ap;
    String Pathvar;

    public void setHtml(String HtmlPath) {
        Pathvar = HtmlPath;
        InputStream is = null;
        try {
//        File file = new File(HtmlPath);
            is = new FileInputStream(HtmlPath);
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line = buf.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line).append("\n");
                line = buf.readLine();
            }
            String Html = sb.toString();
            web.getEngine().loadContent(Html);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FullScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FullScreenController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                is.close();
            } catch (IOException ex) {
                Logger.getLogger(FullScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ap.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.F5) {
                    InputStream is = null;
                    try {
                        is = new FileInputStream(Pathvar);
                        BufferedReader buf = new BufferedReader(new InputStreamReader(is));
                        String line = buf.readLine();
                        StringBuilder sb = new StringBuilder();
                        while (line != null) {
                            sb.append(line).append("\n");
                            line = buf.readLine();
                        }
                        String Html = sb.toString();
                        Document doc = Jsoup.parse(Html);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setTitle(doc.title());
                        web.getEngine().loadContent(Html);
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(FullScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FullScreenController.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                        try {
                            is.close();
                        } catch (IOException ex) {
                            Logger.getLogger(FullScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
    }

}
