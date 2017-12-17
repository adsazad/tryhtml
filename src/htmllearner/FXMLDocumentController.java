/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmllearner;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author arashdeep
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    TextArea Textarea;
    @FXML
    WebView web;
    @FXML
    AnchorPane ap;
    String PathVar = "";
    Stage st = new Stage();
    HTMLLearner ht = new HTMLLearner();
    @FXML
    ContextMenu context;
    String title;
    String html;

    public void setPath(String Path) {
        PathVar = Path;
    }

    @FXML
    private void TryAction(ActionEvent event) {
        String HTML = Textarea.getText();
        html = HTML;
        Document doc = Jsoup.parse(HTML);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        title = doc.title();
        stage.setTitle(title);
        web.getEngine().loadContent(HTML);
        if (PathVar == "") {
            saveas(st, HTML);
        } else {
            save();
        }
    }

    @FXML
    private void FullScreenAction(ActionEvent event) {
        String HTML = Textarea.getText();
        if (PathVar == "") {
            saveas(st, HTML);
        } else {
            save();
        }
        Document doc = Jsoup.parse(HTML);

        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("FullScreen.fxml"));
        try {
            Loader.load();
        } catch (LoadException ex) {
        } catch (IOException ex) {
        }
        Parent logparent = Loader.getRoot();
        Scene scene = new Scene(logparent);
        Stage app_stage = new Stage();
        app_stage.setTitle(doc.title());
        app_stage.setScene(scene);
        app_stage.setMaximized(true);
        app_stage.show();
        FullScreenController fullscreen = Loader.getController();
        fullscreen.setHtml(PathVar);
    }

    @FXML
    private void SaveAction(ActionEvent event) {
        if (PathVar == "") {
            saveas(st, Textarea.getText());
        } else {
            save();
        }
    }

    @FXML
    private void SaveAsAction(ActionEvent event) {
        saveas(st, Textarea.getText());
    }

    @FXML
    private void MakeStructerAction(ActionEvent event) {
        Textarea.setText("<html>\n"
                + "<head>\n"
                + "<title>HTML Learner</title>\n"
                + "</head>\n"
                + "<body bgcolor=\"red\">\n"
                + "<h1>hello</h1>\n"
                + "</body>\n"
                + "</html>");
    }

    @FXML
    private void MakeStructerJquery(ActionEvent event) {
        Textarea.setText("<html>\n"
                + "<head>\n"
                + "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n"
                + "<script>\n"
                + "$(document).ready(function(){\n"
                + "    $(\"p\").click(function(){\n"
                + "        $(this).hide();\n"
                + "    });\n"
                + "});\n"
                + "</script>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
                + "<p>If you click on me, I will disappear.</p>\n"
                + "<p>Click me away!</p>\n"
                + "<p>Click me too!</p>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
    }

    @FXML
    private void MakeStructerJavaScript(ActionEvent event) {
        Textarea.setText("<html>\n"
                + "<body>\n"
                + "\n"
                + "<h2>My First JavaScript</h2>\n"
                + "\n"
                + "<button type=\"button\"\n"
                + "onclick=\"document.getElementById('demo').innerHTML = Date()\">\n"
                + "Click me to display Date and Time.</button>\n"
                + "\n"
                + "<p id=\"demo\"></p>\n"
                + "\n"
                + "</body>\n"
                + "</html> ");
    }

    @FXML
    private void MakeStructerAjax(ActionEvent event) {
        Textarea.setText("<html>\n"
                + "<body>\n"
                + "\n"
                + "<div id=\"demo\">\n"
                + "<h2>The XMLHttpRequest Object</h2>\n"
                + "<button type=\"button\" onclick=\"loadDoc()\">Change Content</button>\n"
                + "</div>\n"
                + "\n"
                + "<script>\n"
                + "function loadDoc() {\n"
                + "  var xhttp = new XMLHttpRequest();\n"
                + "  xhttp.onreadystatechange = function() {\n"
                + "    if (this.readyState == 4 && this.status == 200) {\n"
                + "      document.getElementById(\"demo\").innerHTML =\n"
                + "      this.responseText;\n"
                + "    }\n"
                + "  };\n"
                + "  xhttp.open(\"GET\", \"ajax_info.txt\", true);\n"
                + "  xhttp.send();\n"
                + "}\n"
                + "</script>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
    }

    @FXML
    private void MakeStructerBootStrap3(ActionEvent event) {
        Textarea.setText("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <title>Bootstrap Example</title>\n"
                + "  <meta charset=\"utf-8\">\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">\n"
                + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n"
                + "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
                + "<div class=\"jumbotron text-center\">\n"
                + "  <h1>My First Bootstrap Page</h1>\n"
                + "  <p>Resize this responsive page to see the effect!</p> \n"
                + "</div>\n"
                + "  \n"
                + "<div class=\"container\">\n"
                + "  <div class=\"row\">\n"
                + "    <div class=\"col-sm-4\">\n"
                + "      <h3>Column 1</h3>\n"
                + "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>\n"
                + "      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>\n"
                + "    </div>\n"
                + "    <div class=\"col-sm-4\">\n"
                + "      <h3>Column 2</h3>\n"
                + "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>\n"
                + "      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>\n"
                + "    </div>\n"
                + "    <div class=\"col-sm-4\">\n"
                + "      <h3>Column 3</h3>        \n"
                + "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>\n"
                + "      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</div>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
    }

    @FXML
    private void MakeStructerBootStrap4(ActionEvent event) {
        Textarea.setText("<!DOCTYPE html>\n"
                + "<html lang=\"en\">\n"
                + "<head>\n"
                + "  <title>Bootstrap Example</title>\n"
                + "  <meta charset=\"utf-8\">\n"
                + "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\">\n"
                + "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\n"
                + "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js\"></script>\n"
                + "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js\"></script>\n"
                + "</head>\n"
                + "<body>\n"
                + "\n"
                + "<div class=\"jumbotron text-center\">\n"
                + "  <h1>My First Bootstrap Page</h1>\n"
                + "  <p>Resize this responsive page to see the effect!</p> \n"
                + "</div>\n"
                + "  \n"
                + "<div class=\"container\">\n"
                + "  <div class=\"row\">\n"
                + "    <div class=\"col-sm-4\">\n"
                + "      <h3>Column 1</h3>\n"
                + "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>\n"
                + "      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>\n"
                + "    </div>\n"
                + "    <div class=\"col-sm-4\">\n"
                + "      <h3>Column 2</h3>\n"
                + "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>\n"
                + "      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>\n"
                + "    </div>\n"
                + "    <div class=\"col-sm-4\">\n"
                + "      <h3>Column 3</h3>        \n"
                + "      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit...</p>\n"
                + "      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris...</p>\n"
                + "    </div>\n"
                + "  </div>\n"
                + "</div>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
    }

    @FXML
    private void GetJqueryLink(ActionEvent event) {
        String link = "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>";
        TextArea textArea = new TextArea(link);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 0);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Jquery");
        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();
        StringSelection stringSelection = new StringSelection(link);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }

    @FXML
    private void GetBootstrap3Link(ActionEvent event) {
        String link = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">";
        TextArea textArea = new TextArea(link);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 0);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BootStrap 3");
        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();
        StringSelection stringSelection = new StringSelection(link);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }

    @FXML
    private void GetBootstrap4Link(ActionEvent event) {
        String link = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\">";
        TextArea textArea = new TextArea(link);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 0);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("BootStrap 3");
        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();
        StringSelection stringSelection = new StringSelection(link);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }

    @FXML
    private void InsertJquery(ActionEvent event) {
        String link = "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>";
        StringSelection stringSelection = new StringSelection(link);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
        Textarea.paste();
    }

    @FXML
    private void InsertBootStrap3(ActionEvent event) {
        String link = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">";
        StringSelection stringSelection = new StringSelection(link);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
        Textarea.paste();
    }

    @FXML
    private void InsertBootStrap4(ActionEvent event) {
        String link = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\">";
        StringSelection stringSelection = new StringSelection(link);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
        Textarea.paste();
    }

    @FXML
    private void CopyAction(ActionEvent event) {
        String text = Textarea.getSelectedText();
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }

    @FXML
    private void PasteAction(ActionEvent event) {
        Textarea.paste();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    public void save() {
        FileWriter file = null;
        String text = Textarea.getText();
        try {
            file = new FileWriter(PathVar);
            BufferedWriter Writer = new BufferedWriter(file);
            Writer.write(text);
            Writer.flush();
            Writer.close();
            file.close();

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                file.close();

            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
