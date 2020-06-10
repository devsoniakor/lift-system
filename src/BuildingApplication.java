import au.edu.uts.ap.javafx.*;
import model.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.StackPane;

/**
 * This class launches the building application.
 */
public class BuildingApplication extends Application {
    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage stage) throws Exception {
        ViewLoader.showStage(new Building(), "/view/building.fxml", "Building", stage);
        //stage.setHeight(400);
        //stage.setWidth(450);
        
     
    }
}
