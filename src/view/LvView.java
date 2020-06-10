/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.javafx.geom.RoundRectangle2D;
import javafx.beans.InvalidationListener;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.property.*;
import javafx.beans.value.ObservableIntegerValue;
import javafx.beans.value.ObservableNumberValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.scene.Node.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.*;
import static javafx.scene.paint.Color.BLUE;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import model.Lift;
import javafx.beans.value.*;


/**
 *
 * @author noche
 */


public class LvView extends HBox{
    
    public ObjectProperty<Paint> fillProperty;
    public ObjectProperty<StrokeType> strokeTypeProperty;
    private ObservableList<StackPane> stackBoxes = FXCollections.observableArrayList(); 
    private ObservableList<IntegerProperty> levels = FXCollections.observableArrayList(); 
    private IntegerProperty bottom = new SimpleIntegerProperty();
    private IntegerProperty top = new SimpleIntegerProperty();
    private IntegerProperty level = new SimpleIntegerProperty();
    private Lift lift;

    
    
    public LvView() {
        this.top.addListener((o, old, now) -> setUpLevelBox());
        this.bottom.addListener((o, old, now) -> setUpLevelBox()); 
        this.level.addListener((o, old, now) -> setUpLevelBox());
      
    }
    
    public void setUpLevelBox() {
        this.getChildren().clear();
        for (int i = bottom.get(); i <= top.get(); i++) {
            Rectangle LvRect = new Rectangle(40.0, 40.0);
            StackPane stack = new StackPane();
            
            //boxes.add(level);
            Text eachLevel = new Text();
            eachLevel.setText("" + i);
            eachLevel.fillProperty().setValue(Color.BLACK);
            if(i==this.level.getValue()){
                LvRect.setFill(Color.web("#ff5722"));
            }
            else{
                LvRect.setFill(Color.WHITE);
            }
            //stack.setStyle("*{levelview}");
            stack.getChildren().add(LvRect);
            stack.getChildren().add(eachLevel);
            
            this.getChildren().add(stack);
        }
        
    }
    
    public LvView setLevelBox() {
        
        for (int i = bottom.get(); i <= top.get(); i++) {
            Rectangle LvRect = new Rectangle(40.0, 40.0);
            StackPane stack = new StackPane();
            
            //boxes.add(level);
            Text eachLevel = new Text();
            eachLevel.setText("" + i);
            eachLevel.fillProperty().setValue(Color.BLACK);
            if(i==this.level.getValue()){
                LvRect.setFill(Color.web("#ff5722"));
            }
            else{
                LvRect.setFill(Color.WHITE);
            }
            //stack.setStyle("*{levelview}");
            stack.getChildren().add(LvRect);
            stack.getChildren().add(eachLevel);
            
            this.getChildren().add(stack);
        }
        
        
        
        /*
        for (int i = bottom; i <= top; i++) {
            Text eachLevel = new Text();
            Rectangle eachbox = new Rectangle(50.0, 50.0);
            StackPane stack = new StackPane();
            eachLevel.setText("" + i);
            eachLevel.setFill(Color.BLACK);
            eachbox.setFill(Color.CORAL);
            stack.getChildren().add(eachbox);
            stack.getChildren().add(eachLevel);
            lvVox.getChildren().add(stack);
            if (i == level) {
                stack.setStyle("active");
            }
            //levelbox.getChildren().add(stack);
        }
        */

        return this;
    }
    
    ///////////////////
    
    
    public void setLift(Lift lift){
        this.lift=lift;
        setUpLevelBox();
    }
    
    public Lift getLift(){
        return lift;
    }
    /*
    public void setTop(int top){
        this.top.set(top);
    }
    
    public void setBottom(int bottom){
        this.bottom.set(bottom);
    }
    
    public void setLevel(int level){
        this.level.set(level);
    }
    
    public IntegerProperty getTop(){
       return top;
    }
    
    public IntegerProperty getBottom(){
        return bottom;
    }
    */
    
    /*
    public void setTop(IntegerProperty top){
        this.top=top;
        //setUpLevelBox();
    }
    
    public void setBottom(IntegerProperty bottom){
        this.bottom=bottom;
        //setUpLevelBox();
    }
    
    public void setLevel(IntegerProperty level){
        this.level=level;
    }
    
    */
    
    public void setTop(int top){
        this.top.set(top);
        //setUpLevelBox();
    }
    
    public void setBottom(int bottom){
        this.bottom.set(bottom);
        //setUpLevelBox();
    }
    
    public void setLevel(int level){
        this.level.set(level);
    }
    
    public final int getTop(){
       return top.get();
    }
    
    public final int getBottom(){
        return bottom.get();
    }
    
    public final int getLevel(){
        return level.get();
    }
    
    ////////////
    
    public final IntegerProperty topProperty(){
       return top;
    }
    
    public final IntegerProperty bottomProperty(){
        return bottom;
    }
    
    public IntegerProperty levelProperty(){
        return level;
    }

}
