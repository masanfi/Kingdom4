package game;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameEngine extends Observable {

    private final char[][] world =
            {
                    {'(','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','+','R','r',' ','Z'},
                    {'@',' ','Z','Y','z',' ','Y','x',' ',' ','Z',' ',' ',' ',' ',' ','x','z',' ','Y',' ','X',' ','z',' ','Z',' ',' ','R','r','X',' '},
                    {'@',' ',' ','X',' ',' ','X',' ',' ',' ',' ','M','N',' ','Y',' ',' ','z',' ',' ',' ',' ',' ',' ',' ',' ',' ','x','R','r','Y','X'},
                    {'@',' ',' ',' ',' ',' ',' ','Z',' ',' ',' ','O','P',' ',' ',' ','Z','I','J',' ','X','x','z',' ',' ',' ','A','/','R','r','z',' '},
                    {'@','z',' ',' ','Z',' ',' ',' ',' ',' ',' ','a','c','5',' ',' ',' ','K','L',' ','x','x','X',' ','y','X','C','D','R','r',' ',' '},
                    {'@',' ',' ',' ','Z','Z',' ',' ',' ','X',' ',' ','g',' ',' ',' ',' ','g',' ','X','x','Y',' ',' ',' ','3','g','$','R','r','Y',' '},
                    {'@','X','x',' ',' ','z','X',' ',' ',' ',' ',' ','g','x','z',' ',' ','g',' ',' ','h','f','f','f','f','f','j',' ','R','r',' ','X'},
                    {'@','Y','X','z',' ',' ','Y','x','X',' ',' ','Y','g',' ',' ',' ','X','g',' ','Z','g',' ',' ',' ',' ',' ','z','Y','R','r',' ','X'},
                    {'@',' ',' ','x',' ',' ','E','F',' ',' ',' ',' ','m','f','f','f','f','l','f','f','o',' ','X','X',' ',' ','Z',' ','R','r','X','X'},
                    {'@',' ','X','x',' ',' ','G','H',' ',' ',' ',' ','g',' ',' ',' ',' ',' ',' ',' ','g','Y','X',' ',' ',' ',' ',' ','R','r',' ','Z'},
                    {'@',' ',' ',' ',' ',' ',' ','g','Z',' ','z',' ','g',' ',' ','Z','Y','x',' ',' ','g',' ','Z','z',' ',' ',' ',' ','R','r',' ',' '},
                    {'@','Z',' ',' ',' ',' ',' ','k','f','f','f','f','o','z','x','X','x','Y',' ',' ','g',' ',' ',' ',' ',' ',' ',' ','R','r',' ',' '},
                    {'@',' ','z',' ',' ',' ',' ',' ',' ',' ','X',' ','g',' ',' ','X','x',' ',' ',' ','g','z',' ',' ','X',' ',' ',' ','R','r','z',' '},
                    {'@','X','X',' ',' ',' ','X',' ',' ',' ',' ',' ','g',' ',' ',' ','X','X',' ',' ','g',' ',' ','Y','x',' ','z','X','R','r',' ',' '},
                    {'@','x','Y',' ',' ',' ',' ',' ',' ','z','z',' ','g',' ','X','x',' ',' ','h','f','j',' ',' ',' ',' ',' ',' ','Z','R','r',' ',' '},
                    {'@','z','Y',' ','Z','x','X','V','W',' ',' ',' ','g',' ','z','x',' ',' ','g',' ','X','X',' ',' ',' ',' ',' ',' ','R','r','X',' '},
                    {'@',' ',' ',' ',' ','X','x','%','#',' ',' ',' ','g',' ',' ',' ',' ',' ','g','Y',' ',' ',' ',' ',' ',' ',' ','X','R','r','Y',' '},
                    {'@',' ',' ',' ',' ','z',' ','g','X','x','Z','Z','g','Z','Z',' ',' ','Z','g',' ','z',' ',' ',' ',' ','Z',' ',' ','R','r',' ','Z'},
                    {'@',' ',' ',' ',' ',' ',' ','g',' ','Y','Z','h','l','i','Z',' ',' ','X','g','Z','z',' ','X',' ',' ',' ','x',' ','R','r',' ','z'},
                    {'@','Z','Q','S','X','y','Y','m','f','f','f','o','p','m','f','f','f','f','j','Y',' ',' ','Z',' ',' ',' ',' ','x','R','r','Y',' '},
                    {'@',' ','T','U','2',' ',' ','g',' ',' ','Z','k','n','j','Z',' ',' ',' ',' ',' ',' ','E','F',' ',' ',' ',' ',' ','R','r',' ',' '},
                    {'@',' ',' ','g',' ',' ',' ','g',' ',' ','Z','Z','g','Z','Z',' ',' ','z','X',' ',' ','G','H',' ',' ',' ','X',' ','R','r',' ',' '},
                    {'@','X',' ','k','f','f','f','j',' ','I','J','X','g',' ',' ','z',' ',' ',' ',' ',' ',' ','g',' ',' ',' ',' ','Y','R','r',' ','X'},
                    {'@','Z','X',' ','x','Y','X','Y',' ','K','L',' ','g','4',' ','Y',' ',' ',' ','x','Y',' ','g',' ',' ',' ','Z','Z','R','r',' ','x'},
                    {'@','x','x','Z',' ','x','X','x','X','g',' ',' ','g',' ',' ',' ',' ',' ',' ',' ','X','Z','g','Z',' ',' ','z',' ','R','r','Z',' '},
                    {'@','y',' ','X',' ','X','X','z','Y','k','f','f','l','f','f','f','f','f','f','f','f','f','l','f','f','f','f','1','B','b','f','f'},
                    {'@','X',' ',' ',' ','Z',' ',' ',' ','X',' ','x',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','w','R','r',' ','X'},
                    {'@','Z','X',' ','X','X','x','Y','x','x','Z','X',' ',' ','z','Y',' ',' ','Z','X','X',' ',' ',' ',' ',' ','x','Z','R','r',' ','Y'},
                    {'@','x','X','z','Y','Y','x','X',' ',' ','z','Y','X','X','Y','X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','x','Y','R','r','z','Z'},
                    {'@','Y','x','X',' ',' ',' ','Z','X','x',' ',' ',' ','Z','x','Y','z',' ',' ',' ',' ',' ',' ','z',' ',' ',' ','Y','R','r',' ',' '},
                    {'@','!','x','x',' ','X','Z','X',' ','Y','X','Y','X','Z','x','x','X',' ',' ',' ','z',' ',' ',' ',' ',' ',' ',' ','R','r',' ',' '},
                    {'@',' ',' ','X',' ','X','z',' ',' ','Z','z','Z','X','Y',' ','X',' ','Z',' ',' ',' ','Y','Z','X',' ',' ',' ',' ','R','r',' ','X'},
                    {'@','x',' ',' ','Z','x','X','Y',' ','X',' ','x','x','Z',' ',' ','X','Z','X',' ','Z','x',' ',' ','Z',' ','Y','X','R','r','X','x'},
                    {'@','X','x','y','x','x','X','Z',' ','X',' ','z',' ',' ','y',' ',' ',' ','x',' ',' ','X','y',' ',' ','§','Y','x','R','r','X','x'},
                    {')','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','&','R','r','x','X'}
            };

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
    private Rectangle clip;
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
    private double clampRangeX,clampRangeY;
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
    
    ArrayList<Integer> trophyCollisionWithTrees;

    public GameEngine() {
        trophyCollisionWithTrees = new ArrayList<>();
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

    public void setClip(Rectangle clip) {
        this.clip = clip;
    }

    public Rectangle getClip() {
        return clip;
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

    private void setClampX(double x) {
        clampRangeX = x ;
    }

    public double getClampX() {
        return clampRangeX;
    }

    private void setClampY(double y) {
        clampRangeY = y;
    }

    public double getClampY() {
        return clampRangeY;
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

    public Rectangle getActionSquareFuture() {
        Rectangle actionSquareFuture = new Rectangle(getClampX() + getActionSquare().getWidth() + 48, getClampY() + getActionSquare().getHeight() + 48, getActionSquare().getWidth(), getActionSquare().getWidth());
        return actionSquareFuture;
    }

    public void movePlayer() {
        this.animatePlayer();
        this.setLastDirection(this.getPrimaryDirection());

        this.setTimestamp(this.getTimestamp());
        long elapsedNanos = this.getTimestamp() - this.getLastUpdate() ;
        if (this.getLastUpdate() < 0) {
            this.setLastUpdate(this.getTimestamp());
            return;
        }

        double elapsedSeconds = elapsedNanos / 1_000_000_000.0 ;
        double deltaX = 0 ;
        double deltaY = 0 ;

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

        setClampX(clampRange(this.getActionSquare().getX() + deltaX * elapsedSeconds, 0, this.getBackground().getWidth() - this.getActionSquare().getWidth()));
        setClampY(clampRange(this.getActionSquare().getY() + deltaY * elapsedSeconds, 0, this.getBackground().getHeight() - this.getActionSquare().getHeight()));
        
        this.notifyObservers();

        //this.checkForTriggers();

        if (!isCollision) {
            this.getActionSquare().setX(clampRangeX);
            this.getActionSquare().setY(clampRangeY);

            for (int i = 0; i < this.getPlayer().length; i++) {
                this.getPlayer()[i].setX(clampRangeX);
                this.getPlayer()[i].setY(clampRangeY);
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
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	private void beginnFinale() {
    	Boolean connectError = false;
    	Scene scene;
    	//Nach betreten des Finals Triggers sind alle weiteren TRigger ausgeschaltet!!!   	
    	setTriggerStop();
    	
    	//Highscore Übermitteln
    	//List<Highscore> hs = new ArrayList<Highscore>();
    	final ObservableList<Highscore> hs = FXCollections.observableArrayList();
    	
    	try {
    		//System.out.println();
        	JavaClient.sendMessage(getUserName() + "|" + getTrophyCollisionsWithTrees().size() +"|"+ (endTime-startTime) + "|" +  System.currentTimeMillis());
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        	connectError = true;
        }
    	
    	//Heighscore holen
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
    	Stage primaryStage = getPrimaryStage();
    	if(connectError) {
    		TextArea textArea = new TextArea();
    		textArea.setText("HighScore kann nicht abgerufen werden\n Dein Ergebnis:\n"+ getUserName() + "|" + getTrophyCollisionsWithTrees().size() +"|"+ (endTime-startTime) + "|" +  System.currentTimeMillis());
            VBox vbox = new VBox(textArea);

            scene = new Scene(vbox, 200, 100);
    	}else {
	    	//highscore aufbereiten
	
	         Collections.sort(hs , Comparator.comparing(Highscore::getCounter)
	             .thenComparing(Highscore::getDuration)
	             .thenComparing(Highscore::getUserName)
	             .thenComparing(Highscore::getHighScoreTime));
	
	       //Endscene zeigen Sollte ausgelagert werden !!!!!
	         
	         GridPane grid = new GridPane();
			// weisen das Padding (interner Abstand) zu
			grid.setPadding(new Insets(10, 10, 10, 10));
			// und fügen einen kleinen Außenabstand hinzu
			grid.setVgap(10);
			grid.setHgap(10);
	
			TableView table = new TableView();
			table.setEditable(true);
	
			TableColumn userNameCol = new TableColumn("Username");
			TableColumn counterCol = new TableColumn("Counter");
			TableColumn durationCol = new TableColumn("Duration");
			TableColumn dateCol = new TableColumn("Highscore Date");
	
			table.getColumns().addAll(userNameCol,counterCol,durationCol,dateCol);
			table.setMinWidth(paneWidth-20);
			double cellWidth = (paneWidth)/4;
			userNameCol.setMinWidth(cellWidth);
			userNameCol.setCellValueFactory(
	            new PropertyValueFactory<>("userName"));
			counterCol.setMinWidth(cellWidth);
			counterCol.setCellValueFactory(
	            new PropertyValueFactory<>("counter"));
			durationCol.setMinWidth(cellWidth);
			durationCol.setCellValueFactory(
	            new PropertyValueFactory<>("duration"));
			dateCol.setMinWidth(cellWidth);
			dateCol.setCellValueFactory(
	            new PropertyValueFactory<>("highScoreTime"));
		
			table.setItems(hs);
	
			GridPane.setConstraints(table, 0,25);
			grid.getChildren().addAll(table);
	
	        grid.setStyle(" -fx-background-image: url(\"introScreen.png\"); -fx-background-repeat: stretch; -fx-background-position: center center; -fx-background-insets: 0; -fx-padding: 0;");
	    	scene = new Scene(grid, paneWidth, paneHeight);
    	}
    	scene.setFill(Color.BLACK);
    	primaryStage.setScene(scene);
    	
    }
    
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
                	beginnFinale();
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

    private double clampRange(double value, double min, double max) {
        if (value < min) return min ;
        if (value > max) return max ;
        return value ;
    }

    public void moveCamera() {
        clip = new Rectangle();
        this.setClip(clip);

        this.getClip().widthProperty().bind(this.getScene().widthProperty());
        this.getClip().heightProperty().bind(this.getScene().heightProperty());

        this.getClip().xProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(this.getActionSquare().getX() - this.getScene().getWidth() / 2, 0, this.getBackground().getWidth() - this.getScene().getWidth()),
                this.getActionSquare().xProperty(), this.getScene().widthProperty()));
        this.getClip().yProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(this.getActionSquare().getY() - this.getScene().getHeight() / 2, 0, this.getBackground().getHeight() - this.getScene().getHeight()),
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
    
    public void collisionCounter(int object, String name) {
        if (name.contentEquals("tree") || name.contentEquals("tree2") || name.contentEquals("tree3")) {
           if(object != lastCollisionObject) {
        	// if (!this.getTrophyCollisionsWithTrees().contains(object)) {
                this.getTrophyCollisionsWithTrees().add(object);
            }else {
            	if((System.currentTimeMillis()-lastCollisionTime)>1000) {
            		this.getTrophyCollisionsWithTrees().add(object);
            	}
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