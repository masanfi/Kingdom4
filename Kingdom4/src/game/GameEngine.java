package game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


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
    private int trophyTreeCounter;
    private Text text;
    private ImageView hudKeyImageView;
    private double viewFactorX, viewFactorY;
    private double actionSquareOffsetX = 16;
    private double actionSquareOffsetY = 16;
    private String primaryDirection = "south";
    private String lastDirection;
    private String userName;
    private Stage primaryStage;
    private long lastCollisionTime=0;
    private long startTime=0;
    private long endTime=0;
    private Boolean triggerStop = false;
    private Scenery scenery;
    private static final char[][] world = Level.getLevel();
    private ImageView knight;
    private ImageView knight2;
    private Boolean knightChanged= false;
    private Boolean keyPickedUP= false;
    private ImageView fph;
    private ImageView key;
    private ImageView keyGreen;
    private Image keyImage;
    private IEvent knightCollision;
    ArrayList<Integer> trophyCollisionWithTrees;
    ArrayList<Integer> trophyCollisionWithFlowers;
    ArrayList<Integer> trophyCollisionWithStones;
    ArrayList<Integer> trophyCollisionWithNPCs;
    ArrayList<Integer> trophyCollisionWithBridge;
    private Boolean trophyBridge = false;
    private Boolean trophyStones = false;
    private Boolean trophyTrees = false;
    private Boolean trophyFlowers = false;
    private Boolean trophyNPCs = false;
    private File attention = new File("music/attention.mp3");
    private Boolean openSpeechBubble = false;
    List<String> wisemanText = new ArrayList<String>();
    
    public GameEngine() {
        trophyCollisionWithTrees = new ArrayList<>();
        trophyCollisionWithFlowers = new ArrayList<>();
        trophyCollisionWithStones = new ArrayList<>();
        trophyCollisionWithNPCs = new ArrayList<>();
        trophyCollisionWithBridge = new ArrayList<>();
        
    	wisemanText.add("Aluminiumfolie reißt nicht\\nso leicht, wenn man sie\\nvor Gebrauch vollflächig auf\\nRigipsplatten klebt.");
    	wisemanText.add("Mein Sohn!\\nDie Schule des Lebens\\nhat niemals Ferien.");
    	wisemanText.add("Schmutziges Geschirr\\nschimmelt nicht, wenn\\nman es in der Gefriertruhe\\naufbewahrt.");
    	wisemanText.add("Alte Matrosen-Weisheit:\\nLieber Rum trinken,\\nals rumsitzen!");
    	wisemanText.add("Zwiebeln statt Kiwis\\nkaufen! Zwiebeln sind\\nbilliger und länger\\nhaltbar.");
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

    public void setKnight(ImageView knight) {
    	this.knight = knight;
    }
    public void setKnightCollision(IEvent collision) {
    	this.knightCollision = collision;
    }
    public void setKnight2(ImageView knight) {
    	this.knight2 = knight;
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

    /**
     * Shows a speech bubble of a conversation.
     * @param trigger
     * @param backgroundColor
     * @param textColor
     */
    public void showSpeechBubble(Trigger trigger, Color backgroundColor, Color textColor) {
    	
    	//Die Sprüche und Textblasen sollten wir noch auslagern in eine extra Klasse
    	
    	
    	    	
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
            int randomNum = ThreadLocalRandom.current().nextInt(1, wisemanText.size());
            textString = wisemanText.get(randomNum);
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
	
	private void changeKnight() {
		knightChanged = true;

		background.getChildren().remove(knight);
    	background.getChildren().add(knight2);
    	background.getChildren().add(fph);
    	
    	collision.remove(knightCollision);
    	trigger.remove(knightCollision);
    	
    	System.out.println("Knight verschoben");
	}
	
	private void pickUpKey() {
		keyPickedUP = true;
		background.getChildren().remove(key);
		background.getChildren().add(keyGreen);
		Platform.runLater(() -> {
			hudKeyImageView.setImage(keyImage);
		});
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
                    if (trigger.getName().contentEquals("lady")) {
                        showSpeechBubble(trigger, Color.RED, Color.WHITE);
                    }
                    else if (trigger.getName().contentEquals("wiseman")) {
                    	if(!openSpeechBubble) {
                    		showSpeechBubble(trigger, Color.DARKGRAY, Color.BLACK);
                    		openSpeechBubble = true;
                    	}
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
                } else if(trigger.getName().equalsIgnoreCase("test")){
                	if(!knightChanged) {
                		changeKnight();
                	}          	
                }else if(trigger.getName().equalsIgnoreCase("key")){
                	if(!keyPickedUP) {
                		System.out.println("Schlüsssel gefunden");
                		pickUpKey();
                	}
                }
                else {
                    //System.out.println("Something's happening!");
                }
            }
        }
        else {
        	openSpeechBubble = false;
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
     * @param name
     */
    public void collisionCounter(int object, Item item) {
    	
    	//
    	if(this.getTrophyCollisionsWithFlowers().size()==20) {
    		
    		if(!this.trophyFlowers) {
    			System.out.println("Trophy gewonnen: Trampel!!!!!");
    		}   
    		this.trophyFlowers = true;
    	}
    	
    	if(this.getTrophyCollisionsWithTrees().size()==20) {
    		
    		if(!trophyTrees) {
    			//trophyPlayer.play();
    			System.out.println("Trophy gewonnen: Treehugger!!!!!");
    		}
    		this.trophyTrees = true;
    	}
    	
    	if(this.getTrophyCollisionsWithStones().size()==10) {
    		
    		if(!trophyStones) {
    			//trophyPlayer.play();
    			System.out.println("Trophy gewonnen: Steinbeisser!!!!!");
    		}
    		this.trophyStones = true;
    	}
    	
    	if(this.getTrophyCollisionsWithNPCs().size()==15) {
    		
    		if(!trophyNPCs) {
    			//trophyPlayer.play();
    			System.out.println("Trophy gewonnen: Influencer!!!!!");
    		}
    		this.trophyNPCs = true;
    	}
    	
    	if(this.getTrophyCollisionsWithBridge().size()==3) {
    		
    		if(!trophyBridge) {
    			//trophyPlayer.play();
    			System.out.println("Trophy gewonnen: Verwirrt!!!!!");
    		}
    		this.trophyBridge = true;
    	}
    	
    	
    	if((System.currentTimeMillis()-lastCollisionTime) > 1000) {
    		//System.out.println("Collision with: " + name);
        	//System.out.println(System.currentTimeMillis() + " " + lastCollisionTime);
    	
    		//Collision with Trees
    		if (item.getName().contentEquals("tree") || item.getName().contentEquals("tree2") || item.getName().contentEquals("tree3")) {
    			this.getTrophyCollisionsWithTrees().add(object);
    			//Platform.runLater(() -> {
	            //    text.textProperty().bind(new SimpleIntegerProperty(this.getTrophyCollisionsWithTrees().size()).asString());
	            //});
    			System.out.println("Trees:" +this.getTrophyCollisionsWithTrees().size());
    		}
    		
    		//Collision with Flowers
    		if (item.getName().contentEquals("flowers") || item.getName().contentEquals("flower2")) {
    			this.getTrophyCollisionsWithFlowers().add(object);
    			//Platform.runLater(() -> {
	            //    text.textProperty().bind(new SimpleIntegerProperty(this.getTrophyCollisionsWithTrees().size()).asString());
	           // });
    			System.out.println("Flowers:" +this.getTrophyCollisionsWithFlowers().size());
    		}
    		
    		//Collision with Stones
    		if (item.getName().contentEquals("stone")) {
    			this.getTrophyCollisionsWithStones().add(object);
    			//Platform.runLater(() -> {
	            //    text.textProperty().bind(new SimpleIntegerProperty(this.getTrophyCollisionsWithTrees().size()).asString());
	           // });
    			System.out.println("Stones:" +this.getTrophyCollisionsWithStones().size());
    		}
    		
    		if (item.getName().contentEquals("stone")) {
    			this.getTrophyCollisionsWithStones().add(object);
    			//Platform.runLater(() -> {
	            //    text.textProperty().bind(new SimpleIntegerProperty(this.getTrophyCollisionsWithTrees().size()).asString());
	           // });
    			System.out.println("Stones:" +this.getTrophyCollisionsWithStones().size());
    		}
    		
    		if(item.isNpc()) {
    			this.getTrophyCollisionsWithNPCs().add(object);
    			System.out.println("NPC:" +this.getTrophyCollisionsWithNPCs().size());
    		}
    		
    		if(item.getName().equals("river_bridge_l")) {
    			this.getTrophyCollisionsWithBridge().add(object);
    			System.out.println("Bridge:" +this.getTrophyCollisionsWithBridge().size());
    		}

    		lastCollisionTime = System.currentTimeMillis();
    	}
    }

    private ArrayList<Integer> getTrophyCollisionsWithBridge() {
        return trophyCollisionWithBridge;
    }
    
    private ArrayList<Integer> getTrophyCollisionsWithNPCs() {
        return trophyCollisionWithNPCs;
    }
    private ArrayList<Integer> getTrophyCollisionsWithTrees() {
        return trophyCollisionWithTrees;
    }
    
    private ArrayList<Integer> getTrophyCollisionsWithStones() {
        return trophyCollisionWithStones;
    }
    
    private ArrayList<Integer> getTrophyCollisionsWithFlowers() {
        return trophyCollisionWithFlowers;
    }

    public int getTrophyTreeCounter() {
        return trophyTreeCounter;
    }

    public void setHudText(Text text) {
        this.text = text;
    }

    public void setHudKeyImageView(ImageView keyImageView) {
        this.hudKeyImageView = keyImageView;
    }
    
    public void setKeyImage(Image key) {
    	this.keyImage = key;
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