package game;

import java.util.Collections;
import java.util.Comparator;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Scenery {

    private GameEngine gameEngine;
    private Pane entities, hud ,textOver;
    private StackPane intro;
    private GridPane outro;
    private ScrollPane background;
    private BorderPane playground;
    private MediaPlayer player;
    private Boolean gameReady = false;
    
    
    public Scenery(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        background = new ScrollPane();

        entities = new Pane();
        gameEngine.setEntities(this.entities);

        textOver = new Pane();
        gameEngine.setTextOver(this.textOver);
        //gameEngine.getTextOver().setVisible(false);

        gameEngine.getBackground().getChildren().addAll(gameEngine.getEntities(), gameEngine.getTextOver());
        playground = new BorderPane();
        gameEngine.getEntities().setVisible(false);

        hud = new Pane();
        Rectangle rect = new Rectangle(0, -64, 200, 80);
        rect.setFill(Color.DARKGRAY);
        Text text = new Text("0");
        gameEngine.setHudText(text);
        text.setX(10);
        text.setY(-40);
        text.setFill(Color.WHITE);
        text.setFont(Font.font ("Verdana", 20));

        hud.setMinHeight(0);
        hud.setPrefHeight(0);
        hud.getChildren().addAll(rect, text);

        background.setContent(gameEngine.getBackground());
        background.addEventFilter(InputEvent.ANY, (event)-> {
                event.consume();
        });
        background.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        background.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        background.setStyle("-fx-background-insets: 0; -fx-padding: 0;");
        playground.setCenter(background);
        playground.setBottom(hud);

        Scene scene = new Scene(playground, gameEngine.getPaneWidth(), gameEngine.getPaneHeight());
        gameEngine.setScene(scene);
    }

    public void setPlayerOnField() {
        gameEngine.getEntities().getChildren().add(gameEngine.getActionSquare());
        gameEngine.getActionSquare().relocate(64, 64);
        for(int i = 0; i < gameEngine.getPlayer().length; i++) {
            //gameEngine.getPlayer()[i].relocate(gameEngine.getAmountHorizontalTiles()*gameEngine.getTileSize()/2, gameEngine.getAmountVerticalTiles()*gameEngine.getTileSize()/2);
            gameEngine.getPlayer()[i].setTranslateX(64);
            gameEngine.getPlayer()[i].setTranslateY(64);
            gameEngine.getBackground().getChildren().add(gameEngine.getPlayer()[i]);
        }
    }
    
    public void setGameReady(Boolean x) {
    	this.gameReady=x;
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    /**
     * method witch prepare the outro as Scene 
     * 
     * @param hs
     * @param connectError
     * @return Outro as Scene
     */
	public Scene renderOutro(ObservableList<Highscore> hs, Boolean connectError) {
    	int paneWidth = gameEngine.getPaneWidth();
    	int paneHeight = gameEngine.getPaneHeight();
    	
    	outro = new GridPane();
    	outro.setStyle(" -fx-background-image: url(\"introScreen.png\"); -fx-background-repeat: stretch; -fx-background-position: center center; -fx-background-insets: 0; -fx-padding: 0;");
    	Text t = new Text();
    	if(connectError) {
	    	//Texthinweis
    		DropShadow ds = new DropShadow();
    		ds.setOffsetY(3.0f);
    		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
    		 
    		
    		t.setEffect(ds);
    		t.setCache(true);
    		t.setX(10.0f);
    		t.setY(270.0f);
    		t.setFill(Color.RED);
    		t.setText("Failed connect to Server ...");
    		t.setFont(Font.font(null, FontWeight.BOLD, 32));
    		
    	}
    	
    	//Sort the highscore
		Collections.sort(hs , Comparator.comparing(Highscore::getCounter)
             .thenComparing(Highscore::getDuration)
             .thenComparing(Highscore::getUserName)
             .thenComparing(Highscore::getHighScoreTime));

		outro.setPadding(new Insets(10, 10, 10, 10));
		outro.setVgap(10);
		outro.setHgap(10);

		//table for Highscore
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
		outro.getChildren().addAll(table,t);

        outro.setStyle("-fx-background-image: url(\"introScreen.png\"); -fx-background-repeat: stretch; -fx-background-position: center center; -fx-background-insets: 0; -fx-padding: 0;");
        //return eady scene
    	Scene scene = new Scene(outro, paneWidth, paneHeight);
    	return scene;
    }
    
    /**
     * render the Intro of the game
     */
    public void renderIntro() {
    	intro = new StackPane();
        intro.setStyle(" -fx-background-image: url(\"introScreen.png\"); -fx-background-repeat: stretch; -fx-background-position: center center; -fx-background-insets: 0; -fx-padding: 0;");
        Stage primaryStage = gameEngine.getPrimaryStage();
        
        int paneWidth = gameEngine.getPaneWidth();
    	int paneHeight = gameEngine.getPaneHeight();
        
    	TextField playerNameField = new TextField();
    	playerNameField.setMaxWidth(200);
    	playerNameField.setText("Enter Playername ...");
    	
        Button startGameButton = new Button("Start Game");
        VBox vbox = new VBox(20,playerNameField,startGameButton);
        
        //this action fade the game with the intro, we try to change it as blend effect
        startGameButton.setOnAction(e -> 
        {
        	gameEngine.setUserName(playerNameField.getText());
        	
        	WritableImage wi = new WritableImage(paneWidth, paneHeight);
        	
            Image firstImage = intro.snapshot(new SnapshotParameters(),wi);
            ImageView firstImageView= new ImageView(firstImage);
            
            wi = new WritableImage(paneWidth, paneHeight);
            Image secondImage = gameEngine.getScene().snapshot(wi);
            ImageView secondImageView= new ImageView(secondImage);
            
            firstImageView.setTranslateX(0);
            secondImageView.setTranslateX(paneWidth);
            
            StackPane pane= new StackPane(firstImageView,secondImageView);
            pane.setPrefSize(paneWidth,paneHeight);
            pane.setStyle("-fx-background-insets: 0; -fx-padding: 0;");
            intro.getChildren().setAll(pane);
            
            Timeline timeline = new Timeline();
            KeyValue kv = new KeyValue(secondImageView.translateXProperty(), 0, Interpolator.EASE_BOTH);
            KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
            timeline.getKeyFrames().add(kf);
            timeline.setOnFinished(t->{
            	intro.getChildren().setAll(vbox);
                primaryStage.setScene(gameEngine.getScene());
            });
            timeline.play();
        });
        
        vbox.setAlignment(Pos.CENTER);
        intro.getChildren().addAll(vbox);
    	Scene scene = new Scene(intro, paneWidth, paneHeight);
        gameEngine.setIntro(scene);
    }
}