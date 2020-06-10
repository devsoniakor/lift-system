package controller;

import au.edu.uts.ap.javafx.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.Building;
import model.Person;

public class CallLiftController extends Controller<Building> {

    @FXML
    private Button cancelBtn;
    @FXML
    private Button callBtn;
    @FXML
    private ComboBox<Person> caller;
    @FXML
    private TextField destTf;
    @FXML
    private Text errorTxt;

    public final Building getBuilding() {
        return model;
    }

    @FXML
    private void initialize() {

    }

    private void setText(String result) {
        errorTxt.setText("" + result);
    }

    private int getDest() {
        return Integer.parseInt(destTf.getText());
    }

    private void setDest(String destination) {
        destTf.setText(destination);
    }

    private boolean isPersonSelected() {
        return (caller.getSelectionModel().getSelectedItem() != null);
    }

    private boolean isDestValid(int destination) {
        return (destination >= getBuilding().getBottom()) && (destination <= getBuilding().getTop());
    }

    @FXML
    private void handleCall(ActionEvent event) throws Exception {
        try {
            if (!isDestValid(getDest())) {
                setText("No suitable lift found");
            } else {
                getBuilding().call(caller.getSelectionModel().getSelectedItem(), getDest());
                stage.close();
            }
        } catch (Exception e) {
            if (!isPersonSelected()) {
                setText("You must select a caller");
            } else {
                setText("Destination must be an integer");
            }
        }

    }

    @FXML
    private void handleCancel(ActionEvent event) throws Exception {
        stage.close();
    }
}
