/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package contactmanager;

import contactmanager.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author TcheutchouaSteve
 */
public class ContactManager extends Application {
    
    // Observable List if for data that is constantly changing and needs to be updated
       private ObservableList<Person> personData = FXCollections.observableArrayList();
       
     public ContactManager() {
        // Add some sample data
        personData.add(new Person("Tcheutchoua", "Steve"));
        personData.add(new Person("Ulrich", "Tchuenkam"));
        personData.add(new Person("Nsiany", "Sandra"));
        personData.add(new Person("Ivoline", "Ngong"));
        personData.add(new Person("Femencha", "Azombo"));
        personData.add(new Person("Same", "Lydie"));
        personData.add(new Person("Fongoh", "Martin"));
        personData.add(new Person("Derick", "Alangi"));
        personData.add(new Person("Eugene", "Egbe"));
    }
     
     
    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Person> getPersonData() {
        return personData;
    }
       
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/PersonView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
