/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package contactmanager.controller;

import contactmanager.MainApp;
import contactmanager.model.Person;
import contactmanager.util.CalendarUtil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author TcheutchouaSteve
 */
public class PersonOverviewController implements Initializable {
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
    private MainApp mainApp;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    
    
    
    private void showPersonDetails(Person person) {
        if(person!= null ){
            fNameDetails.setText(person.getFirstName());
            lNameDetails.setText(person.getLastName());
            streetDetails.setText(person.getStreet());
            postalCodeDetails.setText(Integer.toString(person.getPostalCode()));
            cityDetails.setText(person.getCity());
            birthDayDetails.setText(CalendarUtil.format(person.getBirthday()));
        } else {
             fNameDetails.setText("");
             lNameDetails.setText("");
             streetDetails.setText("");
             postalCodeDetails.setText("");
             cityDetails.setText("");
             birthDayDetails.setText("");
        }
        
    }
    
    
     /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Initialize the person table
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        
        // Auto resize columns
        personTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // clear person 
        showPersonDetails(null);
        
          // Listen for selection changes
        personTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>(){

            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {
                showPersonDetails(newValue);
            }
            
        });
    }
    
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        personTable.setItems(mainApp.getPersonData());
    }

    @FXML
    private void handleNewPerson(ActionEvent event) {
         Person tempPerson = new Person();
  boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
  if (okClicked) {
    mainApp.getPersonData().add(tempPerson);
  }
    }

    @FXML
    private void handleEditPerson(ActionEvent event) {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
  if (selectedPerson != null) {
    boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
    if (okClicked) {
      refreshPersonTable();
      showPersonDetails(selectedPerson);
    }

  } else {
    // Nothing selected
    Dialogs.showWarningDialog(mainApp.getPrimaryStage(),
        "Please select a person in the table.",
        "No Person Selected", "No Selection");
  }
    }

    @FXML
    private void handleDeletePerson(ActionEvent event) {
       int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
  if (selectedIndex >= 0) {
    personTable.getItems().remove(selectedIndex);
  } else {
    // Nothing selected
    Dialogs.showWarningDialog(mainApp.getPrimaryStage(),
        "Please select a person in the table.",
        "No Person Selected", "No Selection");
  }
    }
    
    /**
 * Refreshes the table. This is only necessary if an item that is already in
 * the table is changed. New and deleted items are refreshed automatically.
 * 
 * This is a workaround because otherwise we would need to use property
 * bindings in the model class and add a *property() method for each
 * property. Maybe this will not be necessary in future versions of JavaFX
 * (see http://javafx-jira.kenai.com/browse/RT-22599)
 */
private void refreshPersonTable() {
  int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
  personTable.setItems(null);
  personTable.layout();
  personTable.setItems(mainApp.getPersonData());
  // Must set the selected index again (see http://javafx-jira.kenai.com/browse/RT-26291)
  personTable.getSelectionModel().select(selectedIndex);
}
    
}
