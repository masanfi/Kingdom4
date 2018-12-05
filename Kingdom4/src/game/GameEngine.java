package game;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * This is the core class and the heart of the game. It is the toolkit for all the mechanics and both the object and the value store.
 * Copyright (c) 2018 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public class GameEngine extends Observable {
    private final int tileSize = 64 ;
    private final int amountHorizontalTiles = this.getWorld()[0].length;
    private final int amountVerticalTiles = this.getWorld().length;
    private final int paneWidth = 960;
    private final int paneHeight = 640;

    private final int speed = 200 ; // pixels / second
    private final double animationSpeed = 0.15;
    private double animationTimer = 0;
    private int currentHero = 0;
    private ImageView[] player;
    private Scene scene;
    private Scene intro;
    private Pane background;
    private Rectangle camera;
    private long lastUpdate;
    private long timestamp;
    private boolean north, east, south, west, isCollision, isTrigger;
    private ArrayList<Trigger> trigger;
    private ArrayList<IEvent> collision;
    private ArrayList<Rectangle> obstacle;
    private Rectangle actionSquare;
    private Pane entities;
    private Pane textOver;
    private double noCollisionX, noCollisionY;
    private int trophyTreeCounter;
    private Text text;
    private double viewFactorX, viewFactorY;
    private double actionSquareOffsetX = 16;
    private double actionSquareOffsetY = 16;
    private String primaryDirection = "south";
    private String lastDirection;
    private String userName;
    private Stage primaryStage;
    private int lastCollisionObject=0;
    private long lastCollisionTime=0;
    private long startTime=0;
    private long endTime=0;
    private Boolean triggerStop = false;
    private Scenery scenery;
    private GridPane outro;
    private static final char[][] world = Level.getLevel();
        
    ArrayList<Integer> trophyCollisionWithTrees;

    public GameEngine() {
        trophyCollisionWithTrees = new ArrayList<>();
    }

    public void setScenery(Scenery scenery) {
    	this.scenery = scenery;
    }
    
    public Scenery getScenery() {
    	return scenery;
    }
    
    public void setStartTime() {
    	this.startTime = System.currentTimeMillis();
    }
    
    public void setEndTime(long time) {
    	if(this.endTime == 0) {
    		this.endTime = time;
    	}
    }
    
    public void setPrimaryStage(Stage primaryStage) {
    	this.primaryStage=primaryStage;
    }
    
    public Stage getPrimaryStage() {
    	return primaryStage;
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getAmountHorizontalTiles() {
        return amountHorizontalTiles;
    }

    public int getAmountVerticalTiles() {
        return amountVerticalTiles;
    }

    public int getSpeed() {
        return speed;
    }

    public void setPlayer(ImageView[] player) {
        this.player = player;
    }

    public ImageView[] getPlayer() {
        return player;
    }

    public int getPaneWidth() {
        return paneWidth;
    }

    public int getPaneHeight() {
        return paneHeight;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setIntro(Scene scene) {
        this.intro = scene;
    }

    public Scene getIntro() {
        return intro;
    }

    public void setBackground(Pane background) {
        this.background = background;
    }

    public Pane getBackground() {
        return background;
    }

    public void setCamera(Rectangle camera) {
        this.camera = camera;
    }

    public Rectangle getCamera() {
        return camera;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setNorth(boolean north) {
        this.north = north;
    }

    public void setEast(boolean east) {
        this.east = east;
    }

    public void setSouth(boolean south) {
        this.south = south;
    }

    public void setWest(boolean west) {
        this.west = west;
    }

    public boolean getNorth() {
        return north;
    }

    public boolean getEast() {
        return east;
    }

    public boolean getSouth() {
        return south;
    }

    public boolean getWest() {
        return west;
    }

    public void setTrigger(boolean isTrigger) {
        this.isTrigger = isTrigger;
    }

    public boolean isTrigger() {
        return isTrigger;
    }

    public void setCollision(boolean isCollision) {
        this.isCollision = isCollision;
    }

    public boolean isCollision() {
        return isCollision;
    }

    public ArrayList<Rectangle> getObstacle() {
        return obstacle;
    }

    public void setObstacle(ArrayList<Rectangle> obstacle) {
        this.obstacle = obstacle;
    }

    public ArrayList<IEvent> getCollisionObject() {
        return collision;
    }

    public void setCollisionObject(ArrayList<IEvent> collision) {
        this.collision = collision;
    }

    private void setViewFactorX(double x) {
        viewFactorX = x ;
    }

    public double getViewFactorY() {
        return viewFactorX;
    }

    private void setViewFactorY(double y) {
        viewFactorY = y;
    }

    public double getViewFactorX() {
        return viewFactorY;
    }

    public void setLastDirection(String lastDirection) {
        this.lastDirection = lastDirection;
    }

    public String getLastDirection() {
        return lastDirection;
    }

    public void setPrimaryDirection(String primaryDirection) {
        this.primaryDirection = primaryDirection;
    }

    public String getPrimaryDirection() {
        return primaryDirection;
    }

    /**
     * Generates a action square that implements the movement the player has made to check if there were any collisions on our hero's way.
     * @return the future action square
     */
    public Rectangle getActionSquareFuture() {
        Rectangle actionSquareFuture = new Rectangle(getViewFactorY() + getActionSquare().getWidth() + 48, getViewFactorX() + getActionSquare().getHeight() + 48, getActionSquare().getWidth(), getActionSquare().getWidth());
        return actionSquareFuture;
    }

    /**
     * Moves what we love most: Our hero.
     */
    public void movePlayer() {
        this.animatePlayer();
        this.setLastDirection(this.getPrimaryDirection());

        this.setTimestamp(this.getTimestamp());
        long elapsedTimeInNanoseconds = this.getTimestamp() - this.getLastUpdate();
        if (this.getLastUpdate() < 0) {
            this.setLastUpdate(this.getTimestamp());
            return;
        }

        double elapsedTimeInSeconds = elapsedTimeInNanoseconds / 1_000_000_000.0;
        double deltaX = 0;
        double deltaY = 0;

        if (this.getEast()) {
            deltaX += this.getSpeed();
        }
        if (this.getWest()) {
            deltaX -= this.getSpeed();
        }
        if (this.getSouth()) {
            deltaY += this.getSpeed();
        }
        if (this.getNorth()) {
            deltaY -= this.getSpeed();
        }

        // This sets the movement the user has made
        setViewFactorX(viewFactor(this.getActionSquare().getX() + deltaX * elapsedTimeInSeconds, 0, this.getBackground().getWidth() - this.getActionSquare().getWidth()));
        setViewFactorY(viewFactor(this.getActionSquare().getY() + deltaY * elapsedTimeInSeconds, 0, this.getBackground().getHeight() - this.getActionSquare().getHeight()));
        
        this.notifyObservers();

        //this.checkForTriggers();

        // Processes the isCollision flag set by the collision detection.
        if (!isCollision) {
            this.getActionSquare().setX(viewFactorX);
            this.getActionSquare().setY(viewFactorY);

            for (int i = 0; i < this.getPlayer().length; i++) {
                this.getPlayer()[i].setX(viewFactorX);
                this.getPlayer()[i].setY(viewFactorY);
            }
        }

        this.setLastUpdate(this.getTimestamp());
    }

    private void setNoCollisionX(double x) {
        this.noCollisionX = x;
    }

    private void setNoCollisionY(double y) {
        this.noCollisionY = y;
    }

    private double getNoCollisionX() {
        return noCollisionX;
    }

    private double getNoCollisionY() {
        return noCollisionY;
    }

    /**
     * Shows a speech bubble of a conversation.
     * @param trigger
     * @param backgroundColor
     * @param textColor
     */
    public void showSpeechBubble(Trigger trigger, Color backgroundColor, Color textColor) {
        String textString = "";
        Polygon littlePointer = new Polygon();
        littlePointer.getPoints().addAll(new Double[]{trigger.getCoordinates().getX() + 55, trigger.getCoordinates().getY() + 20, trigger.getCoordinates().getX() + 64, trigger.getCoordinates().getY() + 20, trigger.getCoordinates().getX() + 64, trigger.getCoordinates().getY() + 36 });
        littlePointer.setFill(backgroundColor);
        Rectangle bubble = new Rectangle(trigger.getCoordinates().getX() + this.getTileSize(), trigger.getCoordinates().getY() + 20, 200, 100);
        bubble.setFill(backgroundColor);
        if (trigger.getName().contentEquals("lady")) {
            textString = "Willkommen im\nKönigreich Faboma,\n" + this.getUserName() + "!";
        }
        else if (trigger.getName().contentEquals("wiseman")) {
            textString = "Mein Sohn!\nDie Schule des Lebens\nhat niemals Ferien.";
        }
        else if (trigger.getName().contentEquals("blacksmith")) {
            textString = "Harrr! Hast du\nheute schon dein\nKinderbier getrunken?";
        }
        else if (trigger.getName().contentEquals("fishman")) {
            textString = "Wenn du so\nprogrammierst, wie\ndu aussiehst, ist die\nWelt verloren!";
        }
        Text text = new Text(trigger.getCoordinates().getX() + 74, trigger.getCoordinates().getY() + 46, textString);
        text.setFont(Font.font ("Verdana", 16));
        text.setFill(textColor);
        this.getTextOver().getChildren().addAll(littlePointer, bubble, text);
        this.getTextOver().setVisible(true);
    }

    private void setTriggerStop() {
    	triggerStop=true;
    }
    
    /**
     * This method prepares the outro and plays it.
     */
	private void beginFinale() {
    	Boolean connectError = false;
    	Scene scene;
    	long timestamp = System.currentTimeMillis();
    	
    	//Nach betreten des Finals Triggers sind alle weiteren Trigger ausgeschaltet!!!   	
    	setTriggerStop();
    	
    	//Highscore Übermitteln
    	final ObservableList<Highscore> hs = FXCollections.observableArrayList();
    	
    	try {
        	JavaClient.sendMessage(getUserName() + "|" + getTrophyCollisionsWithTrees().size() +"|"+ (endTime-startTime) + "|" +  timestamp);
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        	connectError = true;
        }
    	
    	//Highscore holen
    	try {
    		List<String> highscore = JavaClient.sendMessage("getHighScore");
    		highscore.forEach(item ->{
    			//System.out.println(item + "\n\n");
    			String[] hsLine = item.split("\\|", -1);
    			hs.add(new Highscore(hsLine[0],Integer.parseInt(hsLine[1]), Integer.parseInt(hsLine[2]), Long.parseLong(hsLine[3])));
    		});
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    		connectError = true;
    	}
    	
    	//Wenn keine Highscore abgerufen werde kann wird das aktuelle Ergebnis ausgegeben
    	if(connectError) {
    		hs.add(new Highscore(getUserName(),getTrophyCollisionsWithTrees().size(), (endTime-startTime), timestamp));

    	}
    	//Endscene wird in der Scenery erstellt und hier ausgegeben
		scene = scenery.renderOutro(hs,connectError);
    	primaryStage.setScene(scene);
    	
    }

    /**
     * This method checks for any triggers that are being passed.
     * @param trigger
     */
    public void checkForTriggers(Trigger trigger) {
        if (trigger != null) {
            if (this.isTrigger() && !triggerStop) {
                if (trigger.isNpc()) {
                    if (trigger.getName().contentEquals("lady")) {
                        showSpeechBubble(trigger, Color.RED, Color.WHITE);
                    }
                    else if (trigger.getName().contentEquals("wiseman")) {
                        showSpeechBubble(trigger, Color.DARKGRAY, Color.BLACK);
                    }
                    else if (trigger.getName().contentEquals("blacksmith")) {
                        showSpeechBubble(trigger, Color.BROWN, Color.WHITE);
                    }
                    else if (trigger.getName().contentEquals("fishman")) {
                        showSpeechBubble(trigger, Color.DARKBLUE, Color.WHITE);
                    }
                }else if(trigger.getName().equalsIgnoreCase("finale")){
                	setEndTime(System.currentTimeMillis());
                	System.out.println("Finale oh oh");
                	beginFinale();
                }
                else {
                    System.out.println("Something's happening!");
                }
            }
        }
        else {
            this.getTextOver().setVisible(false);
        }
    }

    /**
     * Helper method for the camera movement.
     * @param value
     * @param min
     * @param max
     * @return
     */
    private double viewFactor(double value, double min, double max) {
        if (value < min) return min ;
        if (value > max) return max ;
        return value ;
    }

    /**
     * Moves and pans the camera over the playground.
     */
    public void moveCamera() {
        camera = new Rectangle();
        this.setCamera(camera);

        this.getCamera().widthProperty().bind(this.getScene().widthProperty());
        this.getCamera().heightProperty().bind(this.getScene().heightProperty());

        this.getCamera().xProperty().bind(Bindings.createDoubleBinding(
                () -> viewFactor(this.getActionSquare().getX() - this.getScene().getWidth() / 2, 0, this.getBackground().getWidth() - this.getScene().getWidth()),
                this.getActionSquare().xProperty(), this.getScene().widthProperty()));
        this.getCamera().yProperty().bind(Bindings.createDoubleBinding(
                () -> viewFactor(this.getActionSquare().getY() - this.getScene().getHeight() / 2, 0, this.getBackground().getHeight() - this.getScene().getHeight()),
                this.getActionSquare().yProperty(), this.getScene().heightProperty()));
    }

    public char[][] getWorld() {
        return world;
    }

    public void setActionSquare(Rectangle actionSquare) {
        this.actionSquare = actionSquare;
    }

    public Rectangle getActionSquare() {
        return actionSquare;
    }

    /**
     * Animates our hero.
     */
    public void animatePlayer() {
        if (north) {
            if (!getLastDirection().equals("north")) {
                this.setGeneralVisibility(false);
                this.getPlayer()[2].setVisible(true);
                this.setAnimationTimer(this.getAnimationSpeed());
            }
            if (north && !south) {
                this.setAnimationTimer(this.getAnimationTimer() + ((this.getTimestamp() - this.getLastUpdate()) / 1_000_000_000.0));
                if (this.getAnimationTimer() > this.getAnimationSpeed()) {
                    if (this.getCurrentHero() == 2) {
                        this.setGeneralVisibility(false);
                        this.setCurrentHero(3);
                        this.getPlayer()[3].setVisible(true);
                    } else {
                        this.setGeneralVisibility(false);
                        this.setCurrentHero(2);
                        this.getPlayer()[2].setVisible(true);
                    }
                    this.setAnimationTimer(0);
                }
            }
            this.setPrimaryDirection("north");
        }

        if (east) {
            if (!getLastDirection().equals("east")) {
                this.setGeneralVisibility(false);
                this.getPlayer()[6].setVisible(true);
                this.setAnimationTimer(this.getAnimationSpeed());
            }
            if (east && !west) {
                this.setAnimationTimer(this.getAnimationTimer() + ((this.getTimestamp() - this.getLastUpdate()) / 1_000_000_000.0));
                if (this.getAnimationTimer() > this.getAnimationSpeed()) {
                    if (this.getCurrentHero() == 6) {
                        this.setGeneralVisibility(false);
                        this.setCurrentHero(7);
                        this.getPlayer()[7].setVisible(true);
                    } else {
                        this.setGeneralVisibility(false);
                        this.setCurrentHero(6);
                        this.getPlayer()[6].setVisible(true);
                    }
                    this.setAnimationTimer(0);
                }
            }
            this.setPrimaryDirection("east");
        }

        if (south) {
            if (!getLastDirection().equals("south")) {
                this.setGeneralVisibility(false);
                this.getPlayer()[0].setVisible(true);
                this.setAnimationTimer(this.getAnimationSpeed());
            }
            if (south && !north) {
                this.setAnimationTimer(this.getAnimationTimer() + ((this.getTimestamp() - this.getLastUpdate()) / 1_000_000_000.0));
                if (this.getAnimationTimer() > this.getAnimationSpeed()) {
                    if (this.getCurrentHero() == 0) {
                        this.setGeneralVisibility(false);
                        this.setCurrentHero(1);
                        this.getPlayer()[1].setVisible(true);
                    } else {
                        this.setGeneralVisibility(false);
                        this.setCurrentHero(0);
                        this.getPlayer()[0].setVisible(true);
                    }
                    this.setAnimationTimer(0);
                }
            }
            this.setPrimaryDirection("south");
        }

        if (west) {
            if (!getLastDirection().equals("west")) {
                this.setGeneralVisibility(false);
                this.getPlayer()[4].setVisible(true);
                this.setAnimationTimer(this.getAnimationSpeed());
            }
            if (west && !east) {
                this.setAnimationTimer(this.getAnimationTimer() + ((this.getTimestamp() - this.getLastUpdate()) / 1_000_000_000.0));
                if (this.getAnimationTimer() > this.getAnimationSpeed()) {
                    if (this.getCurrentHero() == 4) {
                        this.setGeneralVisibility(false);
                        this.setCurrentHero(5);
                        this.getPlayer()[5].setVisible(true);
                    } else {
                        this.setGeneralVisibility(false);
                        this.setCurrentHero(4);
                        this.getPlayer()[4].setVisible(true);
                    }
                    this.setAnimationTimer(0);
                }
            }
            this.setPrimaryDirection("west");
        }
    }

    public void setGeneralVisibility(boolean visibility) {
        if (visibility == false) {
            for (int i = 0; i < this.getPlayer().length; i++) {
                this.getPlayer()[i].setVisible(false);
            }
        }
    }

    public void setEntities(Pane entities) {
        this.entities = entities;
    }

    public Pane getEntities() {
        return entities;
    }

    public void setTextOver(Pane textOver) {
        this.textOver = textOver;
    }

    public Pane getTextOver() {
        return textOver;
    }

    /**
     * This counts the collisions for the fun of counting.
     * @param object
     * @param name
     */
    public void collisionCounter(int object, String name) {
        if (name.contentEquals("tree") || name.contentEquals("tree2") || name.contentEquals("tree3")) {

        	if((System.currentTimeMillis()-lastCollisionTime) > 1000) {
        		if(object != lastCollisionObject) {
        			// if (!this.getTrophyCollisionsWithTrees().contains(object)) {
        			this.getTrophyCollisionsWithTrees().add(object);
        		}
        	//}else {
            //		this.getTrophyCollisionsWithTrees().add(object);
            }
            
            Platform.runLater(() -> {
                text.textProperty().bind(new SimpleIntegerProperty(this.getTrophyCollisionsWithTrees().size()).asString());
            });

            lastCollisionObject = object;
            lastCollisionTime = System.currentTimeMillis();
        }
    }

    private ArrayList<Integer> getTrophyCollisionsWithTrees() {
        return trophyCollisionWithTrees;
    }

    public int getTrophyTreeCounter() {
        return trophyTreeCounter;
    }

    public void setHudText(Text text) {
        this.text = text;
    }

    public double getActionSquareOffsetX() {
        return actionSquareOffsetX;
    }

    public double getActionSquareOffsetY() {
        return actionSquareOffsetY;
    }

    public int getCurrentHero() {
        return currentHero;
    }

    public void setCurrentHero(int currentHero) {
        this.currentHero = currentHero;
    }

    public double getAnimationTimer() {
        return animationTimer;
    }

    public void setAnimationTimer(double animationTimer) {
        this.animationTimer = animationTimer;
    }

    public double getAnimationSpeed() {
        return animationSpeed;
    }

    public void setTriggerObject(ArrayList<Trigger> trigger) {
        this.trigger = trigger;
    }

    public ArrayList<Trigger> getTriggerObject() {
        return trigger;
    }
}