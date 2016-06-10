/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package contactmanager;

import contactmanager.controller.FXMLDocumentController;
import contactmanager.model.Person;
import java.io.IOException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author TcheutchouaSteve
 */
public class MainApp extends Application {
    
    // Observable List if for data that is constantly changing and needs to be updated
       private ObservableList<Person> personData = FXCollections.observableArrayList();
        private Stage primaryStage;
    private BorderPane rootLayout;
    
     public MainApp() {
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
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        //this.primaryStage.setTitle("AddressApp");

        try {
            // Load the root layout from the fxml file
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }

        showPersonOverview();
    }
    


     public void showPersonOverview() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/PersonView.fxml"));
            AnchorPane overviewPage = (AnchorPane) loader.load();
            rootLayout.setCenter(overviewPage);
            
            // Give the controller access to the main app
            FXMLDocumentController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
