/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmllearner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    TextField PathArea;
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
            app_stage.setResizable(true);
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
                            ToMain.setPath(PathVar);
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
            JSONArray PathObj = MainObj.getJSONArray("Paths");
            for (int i = 0; i < PathObj.length(); i++) {
                RecentTable.getItems().add(new RecentBin(PathObj.getString(i)));
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
                        try {
                            ToMain.FileOpen(open(PathVar), PathVar);
                        } catch (IOException ex) {
                            Logger.getLogger(SetFileController.class.getName()).log(Level.SEVERE, null, ex);
                        }
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
        try {
            String Text = open(st);
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
            OpenedFile.FileOpen(Text, PathVar);
        } catch (IOException ex) {
            Logger.getLogger(SetFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String open(Stage st) throws IOException {
        String txt = null;
        String text = null;
        FileInputStream fw = null;
        FileChooser filechooser = new FileChooser();
        File file = filechooser.showOpenDialog(st);
        PathVar = file.getPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(SetFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt = stringBuffer.toString();

        return txt;
    }

    public String open(String Path) throws IOException {
        String txt = null;
        String text = null;
        File file = new File(Path);
        PathVar = file.getPath();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line).append("\n");
            }
        } catch (IOException ex) {
            Logger.getLogger(SetFileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        txt = stringBuffer.toString();
        return txt;
    }

    public void MakeAlertErr() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Path Not Given");
        alert.setContentText("Please Give Path Of The File To Create");
        alert.showAndWait();
    }
}
