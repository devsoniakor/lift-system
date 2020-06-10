package controller;

import au.edu.uts.ap.javafx.*;
import javafx.collections.ObservableList;
import javafx.event.*;
import model.*;
import javafx.fxml.*;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * The controller for building.fxml.
 */
public class BuildingController extends Controller<Building> {
    @FXML private Slider delaySl;
    @FXML private ListView<Lift> liftsLv;
    @FXML private Button callLiftBtn;
    @FXML private Button viewLiftBtn;
    @FXML private Button peopleBtn;
    @FXML private Button exitBtn;
    @FXML private Text timeTxt;
    @FXML private TableView<Lift> liftTv;
    @FXML private TableColumn<Lift, String> liftClm;
    @FXML private TableColumn<Lift, String> levelClm;
    @FXML private TableColumn<Lift, String> directionClm;
    @FXML private TableColumn<Lift, String> passengerClm;
    @FXML private TableColumn<Lift, String> waitingClm;

    public final Building getBuilding() { return model; }

    @FXML private void initialize() {
        delaySl.valueProperty().addListener((observable, oldValue, newValue) -> {
            if(delaySl.isValueChanging())
               getBuilding().setDelay(newValue.intValue());
        });
        timeTxt.textProperty().bind(getBuilding().getTimeProperty().asString());
        
        liftTv.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldSubject, newSubject) -> viewLiftBtn.setDisable(newSubject == null)
				);
        liftClm.setCellValueFactory(
                                cellData -> cellData.getValue().toStringProperty()
                                );
        levelClm.setCellValueFactory(
                                cellData -> cellData.getValue().getLiftStatus()
                                );
        directionClm.setCellValueFactory(
                                cellData -> cellData.getValue().directionText()
                                );
        passengerClm.setCellValueFactory(
                                cellData -> cellData.getValue().getPassengesSize().asString()
                                );
        waitingClm.setCellValueFactory(
                                cellData -> cellData.getValue().getQueueSize().asString()
                                );
        
        // Custom rendering of the table cell.
	levelClm.setCellFactory((TableColumn<Lift, String> column) -> {
            return new TableCell<Lift, String>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //super.setAlignment(Pos.CENTER_LEFT);
                    //alignmentProperty().setValue(Pos.CENTER_LEFT);
                    //setAlignment(Pos.CENTER_LEFT);
                    setStyle("-fx-alignment: CENTER_Left");
                    setText(item);
                    setFont(Font.font("monospaced"));
                    
                    
                }
            };
        }); 
        
                
        getBuilding().startup();
    }
    
    private Text getTime() {return timeTxt;}
    private ObservableList<Lift> getLift() {return model.getLifts();}
    
    
    private Lift getSelectedLift() {
       return liftTv.getSelectionModel().getSelectedItem();
    }

    @FXML private void handleCallLift(ActionEvent event) throws Exception {
        ViewLoader.showStage(getBuilding(), "/view/call_lift.fxml", "Call lift", new Stage());
        
    }
    
   @FXML private void handleViewLift(ActionEvent event) throws Exception {
        ViewLoader.showStage(getSelectedLift(), "/view/lift.fxml", "Lift", new Stage());
    }
   
   @FXML private void handlePeople(ActionEvent event) throws Exception {
        ViewLoader.showStage(getBuilding(), "/view/people.fxml", "People", new Stage());
    }
   
   @FXML private void handleExit (ActionEvent event) throws Exception {
        getBuilding().shutdown();
        stage.close();
    }
   

    /**
     * This accessor method returns the selected number on the delay slider.
     *
     * @return the the selected delay
     */
    private int getDelay() {
        return (int)delaySl.getValue();
    }
}
