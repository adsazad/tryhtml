/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmllearner;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;

/**
 *
 * @author arashdeep
 */
public class GetLinks {

    public void GetLinks(String Title, String Content) {
        String link = Content;
        TextArea textArea = new TextArea(link);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 0);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Title);
        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();
        StringSelection stringSelection = new StringSelection(link);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
    }

    public void InseartLinks(String Link, TextArea Textarea) {
        StringSelection stringSelection = new StringSelection(Link);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
        Textarea.paste();
    }
}
