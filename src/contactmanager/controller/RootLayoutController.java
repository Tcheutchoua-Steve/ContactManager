/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package contactmanager.controller;

import contactmanager.MainApp;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialogs;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author TcheutchouaSteve
 */
public class RootLayoutController implements Initializable {
    
    
    
    @FXML
    private MenuItem New;
    @FXML
    private MenuItem Open;
    @FXML
    private MenuItem Save;
    @FXML
    private MenuItem SaveAs;
    @FXML
    private MenuItem exit;
    @FXML
    private MenuItem showStatisctics;
    @FXML
    private MenuItem about;
    
        // Reference to the main application
    private MainApp mainApp;

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew(ActionEvent event) {
         mainApp.getPersonData().clear();
        mainApp.setPersonFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPersonDataFromFile(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave(ActionEvent event) {
         File personFile = mainApp.getPersonFilePath();
        if (personFile != null) {
            mainApp.savePersonDataToFile(personFile);
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    private void handleSaveAs() {
         FileChooser fileChooser = new FileChooser();

    // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
    fileChooser.getExtensionFilters().add(extFilter);

    // Show save file dialog
    File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

    if (file != null) {
      // Make sure it has the correct extension
      if (!file.getPath().endsWith(".xml")) {
        file = new File(file.getPath() + ".xml");
      }
      mainApp.savePersonDataToFile(file);
    }
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit(ActionEvent event) {
         System.exit(0);
    }

    @FXML
    private void handleShowStatistics(ActionEvent event) {
    }
/**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout(ActionEvent event) {
        Dialogs.showInformationDialog(mainApp.getPrimaryStage(), "Author: Tcheutchoua Steve", "AddressApp", "About");
    }
    
}
