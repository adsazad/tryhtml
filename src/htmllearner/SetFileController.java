/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmllearner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    TextField PathArea, AuthorField, TitleField;
    @FXML
    Label ErrLabel;
    @FXML
    AnchorPane ap;
    @FXML
    TableView<RecentBin> RecentTable;
    @FXML
    TableColumn<RecentBin, String> PathColumn;
    String PathVar = "";
    Stage st = new Stage();
    SavingBin SavingBin = new SavingBin(PathVar);
    String Author;
    String Title;

    @FXML
    private void BrowseAction(ActionEvent event) {
        SavingBin.saveas(st, "");
        PathVar = SavingBin.GetNewPathVar();
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
            app_stage.setResizable(true);
            app_stage.setScene(scene);
            app_stage.show();
            FXMLDocumentController ToMain = Loader.getController();
            ToMain.setPath(PathVar, AuthorField.getText(), TitleField.getText());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
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
                            app_stage.setResizable(true);
                            app_stage.setScene(scene);
                            app_stage.show();
                            FXMLDocumentController ToMain = Loader.getController();
                            ToMain.setPath(PathVar, AuthorField.getText(), TitleField.getText());
                        }
                    }
                }
            });
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
            for (int i = 0; i < PathObj.length(); i++) {
                String FilePath;
                File FileCh;
                JSONObject Fileobject = PathObj.getJSONObject(i);
                Iterator iterator = Fileobject.keys();
                while (iterator.hasNext()) {
                    FilePath = iterator.next().toString();
                    JSONArray FileArray = Fileobject.getJSONArray(FilePath);
                    FileCh = new File(FilePath);
                    if (FileCh.exists()) {
                        RecentTable.getItems().add(new RecentBin(FilePath));
                    }
                    for (int j = 0; j < FileArray.length(); j++) {
                        JSONObject AuthObj = FileArray.getJSONObject(j);
                        Author = AuthObj.getString("Author");
                        Title = AuthObj.getString("Title");
                        System.out.println(Author);
                        System.out.println(Title);
                    }
                }
            }
            RecentTable.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2) {
                        PathVar = RecentTable.getSelectionModel().getSelectedItem().getPath();
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
                        ToMain.FileOpen(SavingBin.open(PathVar), PathVar, Author, Title);
                    }
                }
            });
            PathColumn.setCellValueFactory(new PropertyValueFactory<>("Path"));
        } catch (JSONException ex) {
            Logger.getLogger(SetFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void OpenAction(ActionEvent event) {
        FileChooser filechooser = new FileChooser();
        File file = filechooser.showOpenDialog(st);
        String FilePath = file.getPath();
        String Text = SavingBin.open(FilePath);
        PathVar = SavingBin.GetNewPathVar();
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
        app_stage.setResizable(true);
        app_stage.setScene(scene);
        app_stage.show();
        FXMLDocumentController OpenedFile = Loader.getController();
        OpenedFile.FileOpen(Text, PathVar, Author, Title);
    }

    public void MakeAlertErr() {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please Select File");
        alert.showAndWait();
    }

}
