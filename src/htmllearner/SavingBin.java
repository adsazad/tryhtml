/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package htmllearner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author arashdeep
 */
public class SavingBin {

    String PathVar;

    public SavingBin(String PathVar) {
        this.PathVar = PathVar;
    }

    public String open(String Path) {
        String txt = null;
        BufferedReader bufferedReader = null;
        try {
            String text = null;
            File file = new File(Path);
            PathVar = file.getPath();
            bufferedReader = new BufferedReader(new FileReader(file));
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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SavingBin.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException ex) {
                Logger.getLogger(SavingBin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return txt;
    }

    public void save(String Text, String path) {
        FileWriter file = null;
        String text = Text;
        try {
            file = new FileWriter(path);
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

    public String GetNewPathVar() {
        return PathVar;
    }
}
