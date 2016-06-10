/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package contactmanager.controller;

import contactmanager.ContactManager;
import contactmanager.model.Person;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author TcheutchouaSteve
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private AnchorPane leftAnchorPane;
    @FXML
    private TableView<Person> personTable;
    @FXML
    private AnchorPane rightAnchorPane;
    @FXML
    private Label personDetail;
    @FXML
    private Label fNameLabel;
    @FXML
    private Label lNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label fNameDetails;
    @FXML
    private Label lNameDetails;
    @FXML
    private Label streetDetails;
    @FXML
    private Label postalCodeDetails;
    @FXML
    private Label cityLabel;
    @FXML
    private Label cityDetails;
    @FXML
    private Label birthDayLabel;
    @FXML
    private Label birthDayDetails;
    @FXML
    private Button newButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    // Reference to the main application
    private ContactManager mainApp;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    
    
    
    
     /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the person table
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(ContactManager mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }
    
}
