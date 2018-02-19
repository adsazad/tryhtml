/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmllearner;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
    GetLinks GetLink = new GetLinks();
    MakeStructer MakeStruct = new MakeStructer();
    File OrgPath;
    String FileName;
    String Author;
    String Title;

    public void setPathVar(String PathVar) {
        this.PathVar = PathVar;
        OrgPath = new File(PathVar);
        FileName = OrgPath.getName();
    }

    public void setPath(String Path, String Author, String Title) {
        PathVar = Path;
        OrgPath = new File(PathVar);
        FileName = OrgPath.getName();
        try {
            String json = null;
            try {
                try (BufferedReader br = new BufferedReader(new FileReader(".Recents"))) {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append("\n");
                        line = br.readLine();
                    }
                    json = sb.toString();
                }
            } catch (IOException ioe) {
            }
            JSONObject MainObj = new JSONObject(json);
            if (!json.contains("\"Paths\"")) {
                String SyntaxJSON = "{\"Paths\":[]}";
                try {
                    Files.write(Paths.get(".Recents"), SyntaxJSON.getBytes());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Some Thing Went Wrong Please Close Program And Try Again");
                    alert.showAndWait();
                    Platform.exit();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JSONArray PathObj = MainObj.getJSONArray("Paths");
            JSONObject AuthoOBJ = new JSONObject().put("Author", Author.toString());
            AuthoOBJ.put("Title", Title.toString());
            MainObj.append("Paths", new JSONObject().append(PathVar, AuthoOBJ));
            try {
                Files.write(Paths.get(".Recents"), MainObj.toString().getBytes());
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JSONException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void FileOpen(String Text, String Path, String Author, String Title) {
        PathVar = Path;
        OrgPath = new File(PathVar);
        FileName = OrgPath.getName();
        System.out.println(Text);
        Textarea.setText(Text);
        try {
            String json = null;
            try {
                try (BufferedReader br = new BufferedReader(new FileReader(".Recents"))) {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();

                    while (line != null) {
                        sb.append(line);
                        sb.append("\n");
                        line = br.readLine();
                    }
                    json = sb.toString();
                }
            } catch (IOException ioe) {
            }
            JSONObject MainObj = new JSONObject(json);
            if (!json.contains("Paths")) {
                String SyntaxJSON = "{\"Paths\":[]}";
                try {
                    Files.write(Paths.get(".Recents"), SyntaxJSON.getBytes());
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Some Thing Went Wrong Please Close Program And Try Again");
                    alert.showAndWait();
                    Platform.exit();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            JSONArray PathObj = MainObj.getJSONArray("Paths");
            JSONObject AuthoOBJ = new JSONObject().put("Author", Author.toString());
            AuthoOBJ.put("Title", Title.toString());
            MainObj.append("Paths", new JSONObject().append(PathVar, AuthoOBJ));
            try {
                Files.write(Paths.get(".Recents"), MainObj.toString().getBytes());
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (JSONException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    SavingBin SavingBin = new SavingBin(PathVar);

    @FXML
    private void TryAction(ActionEvent event) throws IOException, URISyntaxException {
        String HTML = Textarea.getText();
        html = HTML;
        Document doc = Jsoup.parse(HTML);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        title = doc.title();
        stage.setTitle(title);
        web.getEngine().loadContent(HTML);
        if (PathVar == "") {
            SavingBin.saveas(st, HTML);
            PathVar = SavingBin.GetNewPathVar();
            OrgPath = new File(PathVar);
            FileName = OrgPath.getName();
        } else {
            SavingBin.save(HTML, PathVar);
        }

    }

    @FXML
    private void FullScreenAction(ActionEvent event) {
        String HTML = Textarea.getText();
        if (PathVar == "") {
            SavingBin.saveas(st, HTML);
            PathVar = SavingBin.GetNewPathVar();
            OrgPath = new File(PathVar);
            FileName = OrgPath.getName();
        } else {
            SavingBin.save(HTML, PathVar);
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
            SavingBin.saveas(st, Textarea.getText());
            PathVar = SavingBin.GetNewPathVar();
            OrgPath = new File(PathVar);
            FileName = OrgPath.getName();
        } else {
            SavingBin.save(Textarea.getText(), PathVar);
            System.out.println(PathVar);
        }
    }

    @FXML
    private void SaveAsAction(ActionEvent event) {
        SavingBin.saveas(st, Textarea.getText());
        PathVar = SavingBin.GetNewPathVar();
        OrgPath = new File(PathVar);
        FileName = OrgPath.getName();
    }

    @FXML
    private void HtmlEditorAction(ActionEvent event) {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("HTMLEditor.fxml"));
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
        HTMLEditorController ToEditor = Loader.getController();
        ToEditor.SetInit(PathVar, Author, Title);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ap.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.F5) {
                    String HTML = Textarea.getText();
                    html = HTML;
                    Document doc = Jsoup.parse(HTML);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    title = doc.title();
                    stage.setTitle(title);
                    web.getEngine().loadContent(HTML);
                    if (PathVar == "") {
                        SavingBin.saveas(st, HTML);
                        PathVar = SavingBin.GetNewPathVar();
                        OrgPath = new File(PathVar);
                        FileName = OrgPath.getName();
                    } else {
                        SavingBin.save(HTML, PathVar);
                    }
                }
            }
        });
    }

    @FXML
    private void MakeStructerAction(ActionEvent event) {
        MakeStruct.MakeHTML(Textarea);
    }

    @FXML
    private void MakeStructerJquery(ActionEvent event) {
        MakeStruct.MakeStructerJquery(Textarea);
    }

    @FXML
    private void MakeStructerJavaScript(ActionEvent event) {
        MakeStruct.MakeStructerJavaScript(Textarea);
    }

    @FXML
    private void MakeStructerAjax(ActionEvent event) {
        MakeStruct.MakeStructerAjax(Textarea);
    }

    @FXML
    private void MakeStructerBootStrap3(ActionEvent event) {
        MakeStruct.MakeStructerBootStrap3(Textarea);
    }

    @FXML
    private void MakeStructerBootStrap4(ActionEvent event) {
        MakeStruct.MakeStructerBootStrap4(Textarea);
    }

    @FXML
    private void GetJqueryLink(ActionEvent event) {
        String Content = "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>";
        String Title = "Jquery";
        GetLink.GetLinks(Title, Content);
    }

    @FXML
    private void GetBootstrap3Link(ActionEvent event) {
        String Content = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">";
        String Title = "BootStrap 3";
        GetLink.GetLinks(Title, Content);

    }

    @FXML
    private void GetBootstrap4Link(ActionEvent event) {
        String Content = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\">";
        String Title = "BootStrap 4";
        GetLink.GetLinks(Title, Content);
    }

    @FXML
    private void InsertJquery(ActionEvent event) {
        String link = "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>";
        GetLink.InseartLinks(link, Textarea);
    }

    @FXML
    private void InsertBootStrap3(ActionEvent event) {
        String link = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">";
        GetLink.InseartLinks(link, Textarea);
    }

    @FXML
    private void InsertBootStrap4(ActionEvent event) {
        String link = "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css\">";
        GetLink.InseartLinks(link, Textarea);

    }

    @FXML
    private void InsertBody(ActionEvent event) {
        String Code = "<body>\n"
                + " \n"
                + "</body>";
        GetLink.InseartLinks(Code, Textarea);
    }

    @FXML
    private void InsertHTML(ActionEvent event) {
        String Code = "<html>\n"
                + "\n"
                + "</html>";
        GetLink.InseartLinks(Code, Textarea);

    }

    @FXML
    private void InsertHead(ActionEvent event) {
        String Code = "<head>\n"
                + "\n"
                + "</head>";
        GetLink.InseartLinks(Code, Textarea);

    }

    @FXML
    private void InsertTitle(ActionEvent event) {
        String Code = "<title>" + FileName + "</title>";
        GetLink.InseartLinks(Code, Textarea);
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

}
