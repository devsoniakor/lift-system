package controller;

import au.edu.uts.ap.javafx.*;
import java.util.Locale;
import java.util.StringJoiner;
import javafx.beans.binding.*;
import javafx.event.*;
import model.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

/**
 *
 * @author noche
 */
public class PeopleController extends Controller<Building> {
    @FXML private Button closeBtn;
    @FXML private TableView<Person> peopleTv;
    @FXML private TableColumn<Person, String> idClm;
    @FXML private TableColumn<Person, String> nameClm;
    @FXML private TableColumn<Person, String> levelClm;
    @FXML private TableColumn<Person, String> destinationClm;
    @FXML private TableColumn<Person, String> aboardClm;

    public TableView<Person> getPeopleTv() {
        return peopleTv;
    }
   
    public final Building getBuilding() { return model; }

    @FXML private void initialize() {
        
        idClm.setCellValueFactory(cellData -> cellData.getValue().getIdProperty().asString());
        nameClm.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
	levelClm.setCellValueFactory(cellData -> cellData.getValue().getCellLevelProperty());
        destinationClm.setCellValueFactory(cellData -> cellData.getValue().getCellDestinationProperty());
	aboardClm.setCellValueFactory(cellData -> cellData.getValue().isCellAboardProperty());
        
        
    }
    
    
   @FXML private void handleClose(ActionEvent event) throws Exception {
        stage.close();
    }
   
    
}