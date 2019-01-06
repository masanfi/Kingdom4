package game;

import java.io.File;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 *
 * Creates different scenes that are switched during the game-play.
 * Copyright (c) 2018 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.0
 *
 */

public class Scenery {

    private GameEngine gameEngine;
    private Pane entities, textOver, hud;
    private StackPane intro;
    private GridPane outro;
    private ScrollPane background;
    private BorderPane playground;
    private File cssFile = new File("css/style.css");
    
    //Music from: http://freemusicarchive.org 
    private File backgroundMusic = new File("music/Livio_Amato_-_14_-_Sugar_doesnt_replace_you_at_all.mp3");
    
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
        hud.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));
        hud.setId("pane");

        hud.setStyle("-fx-background-color: transparent;");
        hud.setStyle("-fx-background-color: rgba(255,255,255,0.0);");
        Text text = new Text("0");
        gameEngine.setHudText(text);
        text.setX(10);
        text.setY(-40);
        text.setFill(Color.WHITE);
        text.setFont(Font.font ("Verdana", 20));

        ImageView keyImageView = new ImageView();
        //keyImageView.setImage(new Image("grass.png", gameEngine.getTileSize(), gameEngine.getTileSize(), true, false));
        keyImageView.relocate(712, 14);
        gameEngine.setHudKeyImageView(keyImageView);
        hud.setMinHeight(96);
        hud.setPrefHeight(96);
        hud.getChildren().addAll(keyImageView);

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

    /**
     * This sets the action square and our hero on the field.
     */
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
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    /**
     * This prepares the final outro as a scene
     * 
     * @param hs
     * @param connectError
     * @return Outro as Scene
     */
	public Scene renderOutro(ObservableList<Highscore> hs, Boolean connectError) {
		int paneWidth = gameEngine.getPaneWidth();
		int paneHeight = gameEngine.getPaneHeight();

		outro = new GridPane();
		outro.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));
		outro.setId("introscreen");
		Text t = new Text();
		if (connectError) {
			// Texthinweis
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

		// Sort the highscore
		Collections.sort(hs, Comparator.comparing(Highscore::getCounter).thenComparing(Highscore::getDuration)
				.thenComparing(Highscore::getUserName).thenComparing(Highscore::getHighScoreTime));

		outro.setPadding(new Insets(10, 10, 10, 10));
		outro.setVgap(10);
		outro.setHgap(10);

		// table for Highscore
		TableView table = new TableView();
		table.setEditable(true);

		TableColumn userNameCol = new TableColumn("Username");
		TableColumn counterCol = new TableColumn("Counter");
		TableColumn durationCol = new TableColumn("Duration");
		TableColumn dateCol = new TableColumn("Highscore Date");

		table.getColumns().addAll(userNameCol, counterCol, durationCol, dateCol);
		table.setMinWidth(paneWidth - 20);
		double cellWidth = (paneWidth) / 4;
		userNameCol.setMinWidth(cellWidth);
		userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
		counterCol.setMinWidth(cellWidth);
		counterCol.setCellValueFactory(new PropertyValueFactory<>("counter"));
		durationCol.setMinWidth(cellWidth);
		durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
		dateCol.setMinWidth(cellWidth);
		dateCol.setCellValueFactory(new PropertyValueFactory<>("highScoreTime"));

		table.setItems(hs);

		GridPane.setConstraints(table, 0,25);
		outro.getChildren().addAll(table,t);

        //return eady scene
    	Scene scene = new Scene(outro, paneWidth, paneHeight);
    	return scene;
    }

    
    private void introAction(TextField playerNameField,VBox vbox,Stage primaryStage) {
        int paneWidth = gameEngine.getPaneWidth();
       	int paneHeight = gameEngine.getPaneHeight();
    	gameEngine.setUserName(playerNameField.getText());
    	
    	WritableImage wi = new WritableImage(paneWidth, paneHeight);
    	
        Image firstImage = intro.snapshot(new SnapshotParameters(),wi);
        ImageView firstImageView= new ImageView(firstImage);
        
        wi = new WritableImage(paneWidth, paneHeight);
        Image secondImage = gameEngine.getScene().snapshot(wi);
        ImageView secondImageView= new ImageView(secondImage);
        
        //firstImageView.setTranslateX(0);
        //secondImageView.setTranslateX(paneWidth);
        secondImageView.setOpacity(0);
        
        StackPane pane= new StackPane(firstImageView,secondImageView);
        pane.setPrefSize(paneWidth,paneHeight);
        pane.setStyle("-fx-background-insets: 0; -fx-padding: 0;");
        intro.getChildren().setAll(pane);
        
        Timeline fadeInTimeline = new Timeline();
        KeyFrame fadeInKey = new KeyFrame(Duration.millis(3000), new KeyValue(secondImageView.opacityProperty(), 1));
        fadeInTimeline.getKeyFrames().add(fadeInKey);
        fadeInTimeline.setOnFinished(t->{
        	intro.getChildren().setAll(vbox);
        	primaryStage.setScene(gameEngine.getScene());
        });
        fadeInTimeline.play();
    }
    
    /**
     * This renders the intro of Kingdom 4.
     */
    public void renderIntro() {
    	intro = new StackPane();
    	//intro.getStylesheets().add(
        //        getClass().getResource("../css_neu/style.css").toExternalForm()
        //);
    	
    	intro.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));
    	intro.setId("introscreen");
    	Stage primaryStage = gameEngine.getPrimaryStage();

    	
    	Media media = new Media("file:///" + backgroundMusic.getAbsolutePath().replace("\\", "/"));
        MediaPlayer player = new MediaPlayer(media); 
        player.play();
        
        int paneWidth = gameEngine.getPaneWidth();
    	int paneHeight = gameEngine.getPaneHeight();
        
    	TextField playerNameField = new TextField();
    	playerNameField.setMaxWidth(200);
    	playerNameField.setText("Enter Playername ...");
    	playerNameField.setId("namefield");
    	
        Button startGameButton = new Button("Start Game");
        startGameButton.setId("startbutton");
        VBox vbox = new VBox(20,playerNameField,startGameButton);
        vbox.setId("playerName");
        
        intro.addEventHandler(KeyEvent.KEY_PRESSED, ev -> {
            if (ev.getCode() == KeyCode.ENTER) {
            	introAction(playerNameField,vbox,primaryStage);
            	player.stop();
               ev.consume(); 
            }
        });
        
        //this action fade the game with the intro, we try to change it as blend effect
        startGameButton.setOnAction(e -> 
        {
        	introAction(playerNameField,vbox,primaryStage);
        	player.stop();
        });
        
        vbox.setAlignment(Pos.CENTER);        
        intro.getChildren().addAll(vbox);
    	Scene scene = new Scene(intro, paneWidth, paneHeight);
        gameEngine.setIntro(scene);
    }
}
