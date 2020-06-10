package model;

import java.util.LinkedList;
import javafx.beans.InvalidationListener;
import javafx.beans.property.*;

/**
 * A person boards and alights lifts.
 */
public class Person {
    /**
    private int id;
    private String name;
    private int level;
    private int destination;
    private boolean aboard;
    */
    private final IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty level = new SimpleIntegerProperty();
    private IntegerProperty destination = new SimpleIntegerProperty();
    private BooleanProperty aboard = new SimpleBooleanProperty();
    private LinkedList<LevelObserver> observers = new LinkedList<LevelObserver>();
    private StringProperty cellLevel = new SimpleStringProperty();
    private StringProperty cellDestination = new SimpleStringProperty();
    private StringProperty cellAboard = new SimpleStringProperty();

    public Person(int id, String name, int level) {
        this.id.set(id);
        this.name.set(name);
        this.level.set(level);
        this.destination.set(level);
        observers.add(new LevelObserver());
        for(LevelObserver o : observers)
            o.handleCellData (getLevel(), getDestination(), isAboard());
       
    }
    
    //Inner Class observer
    private class LevelObserver {
        public void handleCellData (int newLevel, int newDest, boolean status){
            cellLevel.set("Level " + newLevel);
            cellDestination.set("Level " + newDest);
            if (status)
                cellAboard.set("Yes");
            else
               cellAboard.set("No");
        }
    }

    // PROPERTIES

    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public int getLevel() {
        return level.get();
    }
    

    public int getDestination() {
        return destination.get();
    }
    

    public boolean isAboard() {
        return aboard.get();
    }
    
    //
    
    public IntegerProperty getIdProperty() {
        return id;
    }

    public StringProperty getNameProperty() {
        return name;
    }

    public IntegerProperty getLevelProperty() {
        return level;
    }
    

    public IntegerProperty getDestinationProperty() {
        return destination;
    }
    

    public BooleanProperty isAboardProperty() {
        return aboard;
    }
    
    /// CELL DATA //
    public StringProperty getCellLevelProperty() {return cellLevel;}
    public StringProperty getCellDestinationProperty() {return cellDestination;}
    public StringProperty isCellAboardProperty() {return cellAboard;}

    // FUNCTIONS and PROCEDURES

    public void call(int destination) {
        this.destination.set(destination) ;
        for(LevelObserver o : observers)
            o.handleCellData (getLevel(), getDestination(), isAboard());
    }

    public void move(int direction) {
        level.set(level.getValue() + direction);
        for(LevelObserver o : observers)
            o.handleCellData (getLevel(), getDestination(), isAboard());
    }

    public boolean hasId(int id) {
        return this.id.getValue() == id;
    }

    public boolean canBoard(int liftLevel, int liftDirection) {
        return isAt(liftLevel) && isHeadingIn(liftDirection);
    }

    public boolean isAt(int level) {
        return this.level.getValue() == level;
    }

    public boolean isHeadingIn(int direction) {
        return direction == direction();
    }

    public int direction() {
        return Direction.fromTo(level.getValue(), destination.getValue());
    }

    public boolean isIdle() {
        return (!aboard.getValue() && level.getValue() == destination.getValue());
    }

    public boolean isWaiting() {
        return (!aboard.getValue() && level.getValue() != destination.getValue());
    }

    public boolean hasArrived() {
        return (level.getValue() == destination.getValue());
    }

    /**
     * Determine the direction that this person wants the lift to go in.
     */
    public int liftDirection(int liftLevel) {
        return Direction.fromTo(liftLevel, level.getValue() == liftLevel ? destination.getValue() : level.getValue());
    }

    public void board() {
        aboard.setValue(true);
        for(LevelObserver o : observers)
            o.handleCellData (getLevel(), getDestination(), isAboard());
    }

    public void alight() {
        aboard.setValue(false);
        for(LevelObserver o : observers)
            o.handleCellData (getLevel(), getDestination(), isAboard());
    }

    @Override
    public String toString() {
        return name.getValue();
    }
}
