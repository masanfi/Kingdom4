package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


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

    private int speed = 200 ; // pixels / second
    private final double animationSpeed = 0.15;
    private double animationTimer = 0;
    private int currentHero = 0;
    private ImageView[] player;
    private Scene scene;
    private Scene intro;
    private Conversations conversations;
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
    private double viewFactorX, viewFactorY;
    private double actionSquareOffsetX = 16;
    private double actionSquareOffsetY = 16;
    private String primaryDirection = "south";
    private String lastDirection;
    private String userName;
    private Stage primaryStage;
    
    private long startTime=0;
    private long endTime=0;
    private Boolean triggerStop = false;
    private Scenery scenery;
    private static final char[][] world = Level.getLevel();
    private ImageView knight;
    private ImageView knight2;
    private Boolean knightChanged= false;
    private Boolean keyPickedUP= false;
    private Boolean rabbitPickedUP= false;
    private ImageView fph;
    private ImageView key;
    private ImageView keyGreen;

    private ImageView transparent;
    private ImageView clumsy;
    private ImageView confused;
    private ImageView influencer;
    private ImageView stoney;
    private ImageView treehugger;
    private ImageView clumsy_s;
    private ImageView confused_s;
    private ImageView influencer_s;
    private ImageView stoney_s;
    private ImageView treehugger_s;
    private ImageView rabbit;
    private ImageView sword;
    private ImageView fish;
    private ImageView rabbitGreen;
    private MediaPlayer fanfare;
    
    
    private IEvent knightCollision;
    private IEvent knight2Collision;
    private Trigger knight2Trigger;
    
    private Boolean movement = true;
    private Map<String, Integer> character = new HashMap<>();
    private Trophy trophy;
    private Hud hud;
    
    public GameEngine() {
    	this.conversations = new Conversations(this);
    }
    
    public void setFanfare(MediaPlayer fanfare) {
    	this.fanfare = fanfare;
    }

    public void setTrophy(Trophy trophy) {
    	this.trophy = trophy;
    }
    public void setHud(Hud hud) {
    	this.hud = hud;
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

    public void setSpeed(int speed) {
        this.speed = speed;
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

    public void setKnight(ImageView knight) {
    	this.knight = knight;
    }
    public void setKnightCollision(IEvent collision) {
    	this.knightCollision = collision;
    }
    public void setKnight2(ImageView knight) {
    	this.knight2 = knight;
    }
    
    public void setKnight2Collision(IEvent collision) {
    	this.knight2Collision = collision;
    }
    
    public void setKnight2Trigger(Trigger trigger) {
    	this.knight2Trigger = trigger;
    }
    
    public void setFpH(ImageView fph) {
    	this.fph = fph;
    }
    public void setKey(ImageView key) {
    	this.key = key;
    }
    public void setKeyGreen(ImageView keyGreen) {
    	this.keyGreen = keyGreen;
    }
    
    public void setRabbit(ImageView rabbit) {
    	this.rabbit = rabbit;
    }
    
    public void setSword(ImageView sword) {
    	this.sword = sword;
    }
    
    public void setFish(ImageView fish) {
    	this.fish = fish;
    }
    public void setClumsy(ImageView clumsy) {
    	this.clumsy = clumsy;
    }
    public ImageView getClumsy() {
    	return this.clumsy;
    }
    public void setInfluencer(ImageView influencer) {
    	this.influencer = influencer;
    }
    
    public ImageView getInfluencer() {
    	return this.influencer;
    }
    public void setTreehugger(ImageView treehugger) {
    	this.treehugger = treehugger;
    }
    public ImageView getTreehugger() {
    	return this.treehugger;
    }
    public void setStoney(ImageView stoney) {
    	this.stoney = stoney;
    }
    public ImageView getStoney() {
    	return this.stoney;
    }
    public void setConfused(ImageView confused) {
    	this.confused = confused;
    }
    public ImageView getConfused() {
    	return this.confused;
    }
    
    public void setClumsy_s(ImageView clumsy) {
    	this.clumsy_s = clumsy;
    }
    public ImageView getClumsy_s() {
    	return this.clumsy_s;
    }
    public void setInfluencer_s(ImageView influencer) {
    	this.influencer_s = influencer;
    }
    public ImageView getInfluencer_s() {
    	return this.influencer_s;
    }
    public void setTreehugger_s(ImageView treehugger) {
    	this.treehugger_s = treehugger;
    }
    public ImageView getTreehugger_s() {
    	return this.treehugger_s;
    }
    public void setStoney_s(ImageView stoney) {
    	this.stoney_s = stoney;
    }
    public ImageView getStoney_s() {
    	return this.stoney_s;
    }
    public void setConfused_s(ImageView confused) {
    	this.confused_s = confused;
    }
    public ImageView getConfused_s() {
    	return this.confused_s;
    }
    
    public void setTransparent(ImageView transparent) {
    	this.transparent = transparent;
    }
    
    public ImageView getTransparent() {
    	return this.transparent;
    }
    
    public void setRabbitGreen(ImageView rabbitGreen) {
    	this.rabbitGreen = rabbitGreen;
    }
    
    public void setMovement(Boolean state) {
    	//System.out.println("setze state :" +state);
    	this.movement=state;
    	
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
    	
    	if(this.movement) {
    	
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
	        	if(!this.getWest() && !this.getSouth() && !this.getNorth()) {
	        		deltaX += this.getSpeed();
	        	}
	        }
	        if (this.getWest()) {
	        	if(!this.getEast() && !this.getSouth() && !this.getNorth()) {
	        		deltaX -= this.getSpeed();
	        	}
	        }
	        if (this.getSouth()) {
	        	if(!this.getWest() && !this.getEast() && !this.getNorth()) {
	        		deltaY += this.getSpeed();
	        	}
	        }
	        if (this.getNorth()) {
	        	if(!this.getWest() && !this.getSouth() && !this.getEast()) {
	        		deltaY -= this.getSpeed();
	        	}
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
	                this.getPlayer()[i].toFront();
	            }
	        }
	        this.setLastUpdate(this.getTimestamp());
    	}
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
        	JavaClient.sendMessage(getUserName() + "|" + trophy.getTrophysForHighScore() +"|"+ (endTime-startTime) + "|" +  timestamp);
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
    			hs.add(new Highscore(hsLine[0],hsLine[1], Integer.parseInt(hsLine[2]), Long.parseLong(hsLine[3])));
    		});
    	}catch(Exception e) {
    		System.out.println(e.getMessage());
    		connectError = true;
    	}
    	
    	//Wenn keine Highscore abgerufen werde kann wird das aktuelle Ergebnis ausgegeben
    	if(connectError) {
    		hs.add(new Highscore(getUserName(),trophy.getTrophysForHighScore(), (endTime-startTime), timestamp));

    	}
    	//Endscene wird in der Scenery erstellt und hier ausgegeben
		scene = scenery.renderOutro(hs,connectError);
    	primaryStage.setScene(scene);
    	
    }
	
	public void changeKnight() {
		knightChanged = true;
		this.getCharacter().put("knight2", 3);
		background.getChildren().remove(knight);
    	background.getChildren().add(knight2);
    	background.getChildren().add(fph);
    	collision.add(knight2Collision);
    	trigger.add(knight2Trigger);
    	collision.remove(knightCollision);
    	trigger.remove(knightCollision);
    	
    	System.out.println("Knight verschoben");
	}
	
	public void collectTrophyClumsy() {
		
		hud.setCollectedTrophy(clumsy.getImage(), "C");
		fanfare.stop();
		fanfare.setStartTime(new Duration(0));
		fanfare.play();
	}
	public void collectTrophyConfused() {
		hud.setCollectedTrophy(confused.getImage(), "O");
		fanfare.stop();
		fanfare.setStartTime(new Duration(0));
		fanfare.play();
	}
	public void collectTrophyInfluencer() {
		hud.setCollectedTrophy(influencer.getImage(), "I");
		fanfare.stop();
		fanfare.setStartTime(new Duration(0));
		fanfare.play();
	}
	public void collectTrophyStoney() {
		hud.setCollectedTrophy(stoney.getImage(), "S");
		fanfare.stop();
		fanfare.setStartTime(new Duration(0));
		fanfare.play();
	}
	public void collectTrophyTreehugger() {
		hud.setCollectedTrophy(treehugger.getImage(), "T");
		fanfare.stop();
		fanfare.setStartTime(new Duration(0));
		fanfare.play();
	}
	
	public void pickUpFish() {
		hud.setPickedUpItem(fish.getImage(),"F");
	}
	
	public void pickUpSword() {
		hud.setPickedUpItem(sword.getImage(),"S");
	}
	
	private void pickUpKey() {
		keyPickedUP = true;
		background.getChildren().remove(key);
		background.getChildren().add(keyGreen);
		hud.setPickedUpItem(key.getImage(),"K");
	}
	
	private void pickUpRabbit() {
		rabbitPickedUP = true;
		background.getChildren().remove(rabbit);
		background.getChildren().add(rabbitGreen);
		hud.setPickedUpItem(rabbit.getImage(),"R");	
	}
	
	public void findRemovePickedUpItem(String name) {
		hud.findRemovePickedUpItem(name);
	}

	public void toggleMusic() {
		scenery.toggleMusic();
	}
	
	public boolean findItemInInventory(String item) {
	    for(int i = 0; i < hud.getPickedUpItems().length; i++) {
	        if(hud.getPickedUpItems()[i].contentEquals(item)) {
	            return true;
            }
        }
	    return false;
    }
	

    /**
     * This method checks for any triggers that are being passed.
     * @param trigger
     */
    public void checkForTriggers(Trigger trigger) {
    	//System.out.println(images);
        if (trigger != null) {
            if (this.isTrigger() && !triggerStop) {
                if (trigger.isNpc()) {
                    conversations.startConversation(trigger);
                    //this.movementStop = true;
                }else if(trigger.getName().equalsIgnoreCase("finale")){
                	setEndTime(System.currentTimeMillis());
                	System.out.println("Finale oh oh");
                	beginFinale();
                } else if(trigger.getName().equalsIgnoreCase("test")){
                	if(!knightChanged) {
                		changeKnight();
                	}          	
                }else if(trigger.getName().equalsIgnoreCase("key")){
                	if(!keyPickedUP) {
                		System.out.println("Schlüssel gefunden");
                		pickUpKey();
                	}
                }else if(trigger.getName().equalsIgnoreCase("rabbit")){
                	if(!rabbitPickedUP) {
                		System.out.println("Hase gefunden");
                		pickUpRabbit();
                	}
                }
                else {
                	
                    //System.out.println("Something's happening!");
                }
            }
        }
        else {
        	conversations.setStatusSpeechBubble(false);
        	trophy.setlastCollisionName("");
            this.getTextOver().setVisible(false);
            this.getTextOver().getChildren().clear();
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
    	
        if (north && !east && !south && !west) {
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

        if (east && !north && !south && !west) {
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

        if (south && !east && !north && !west) {
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

        if (west && !east && !south && !north) {
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
     * @param item
     */
    public void collisionCounter(int object, Item item) {
    	trophy.trophyManager(object, item);
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

    public Map<String, Integer> getCharacter() {
        return character;
    }

    public void setCharacter(Map<String, Integer> character) {
        this.character = character;
    }

    public void showAllStates() {
        System.out.println(" ");
        System.out.println("Character states:");
        System.out.println("-----------------");
        System.out.println("Lady: " + this.getCharacter().get("lady"));
        System.out.println("Knight: " + this.getCharacter().get("knight"));
        System.out.println("Blacksmith: " + this.getCharacter().get("blacksmith"));
        System.out.println("Fisherman: " + this.getCharacter().get("fisherman"));
        System.out.println("Wise man: " + this.getCharacter().get("wiseman"));
        System.out.println("Knight2: " + this.getCharacter().get("knight2"));
        System.out.println(" ");
        System.out.println("Inventory states:");
        System.out.println("-----------------");
        System.out.println("Rabbit: " + this.findItemInInventory("R"));
        System.out.println("Sword: " + this.findItemInInventory("S"));
        System.out.println("Key: " + this.findItemInInventory("K"));
        System.out.println("Fish: " + this.findItemInInventory("F"));
    }
}