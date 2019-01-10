package game;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;

import javafx.animation.FadeTransition;
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
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
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
 * StartScreen Music: Eric Skiff - A Night Of Dizzy Spells - Resistor Anthems - Available at http://EricSkiff.com/music
 * Gameplay Music: Eric Skiff - Underclocked - Resistor Anthems - Available at http://EricSkiff.com/music
 */

public class Scenery {

    private GameEngine gameEngine;
    private Pane entities, textOver, hudPane, intro;
    private StackPane outro;
    private ScrollPane background;
    private BorderPane playground;
    private File cssFile = new File("css/style.css");
    private File fanfareFile = new File("music/attention.mp3");
    private File startscreenMusic = new File("music/DizzySpells.mp3");
    private File backgroundMusic = new File("music/Underclocked.mp3");
    Media mediaMain = new Media("file:///" + backgroundMusic.getAbsolutePath().replace("\\", "/"));
    MediaPlayer playerMain = new MediaPlayer(mediaMain);
  
    public Scenery(GameEngine gameEngine,Hud hud) {
        this.gameEngine = gameEngine;

        Media fanfareMedia = new Media("file:///" + fanfareFile.getAbsolutePath().replace("\\", "/"));
        MediaPlayer fanfarePlayer = new MediaPlayer(fanfareMedia);
        gameEngine.setFanfare(fanfarePlayer);
        
        background = new ScrollPane();

        entities = new Pane();
        gameEngine.setEntities(this.entities);

        textOver = new Pane();
        gameEngine.setTextOver(this.textOver);
        //gameEngine.getTextOver().setVisible(false);

        gameEngine.getBackground().getChildren().addAll(gameEngine.getEntities(), gameEngine.getTextOver());
        playground = new BorderPane();
        gameEngine.getEntities().setVisible(false);

        hudPane = new Pane();
        hudPane.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));
        hudPane.setId("pane");

        hudPane.setStyle("-fx-background-color: transparent;");
        hudPane.setStyle("-fx-background-color: rgba(255,255,255,0.0);");

        ImageView itemOneImageView = new ImageView();
        ImageView itemTwoImageView = new ImageView();
        ImageView itemThreeImageView = new ImageView();
        
        HBox hBoxItemOneImageView = new HBox();
        hBoxItemOneImageView.getChildren().add(itemOneImageView);
        hBoxItemOneImageView.relocate(712, 5);
        hBoxItemOneImageView.setMaxHeight(64);
        hBoxItemOneImageView.setPrefHeight(64);
        hBoxItemOneImageView.setPrefWidth(64);
        hBoxItemOneImageView.setMaxWidth(64);
        
        HBox hBoxItemTwoImageView = new HBox();
        hBoxItemTwoImageView.getChildren().add(itemTwoImageView);
        hBoxItemTwoImageView.relocate(788, 5);
        hBoxItemTwoImageView.setMaxHeight(64);
        hBoxItemTwoImageView.setPrefHeight(64);
        hBoxItemTwoImageView.setPrefWidth(64);
        hBoxItemTwoImageView.setMaxWidth(64);
        
        HBox hBoxItemThreeImageView = new HBox();
        hBoxItemThreeImageView.getChildren().add(itemThreeImageView);
        hBoxItemThreeImageView.relocate(864, 5);
        hBoxItemThreeImageView.setMaxHeight(64);
        hBoxItemThreeImageView.setPrefHeight(64);
        hBoxItemThreeImageView.setPrefWidth(64);
        hBoxItemThreeImageView.setMaxWidth(64);
        
        hud.setHuditemOneImageView(itemOneImageView,hBoxItemOneImageView);
        hud.setHuditemTwoImageView(itemTwoImageView,hBoxItemTwoImageView);
        hud.setHuditemThreeImageView(itemThreeImageView,hBoxItemThreeImageView);

        ImageView trophyOneImageView = new ImageView();
        ImageView trophyTwoImageView = new ImageView();
        ImageView trophyThreeImageView = new ImageView();
        ImageView trophyFourImageView = new ImageView();
        ImageView trophyFiveImageView = new ImageView();
        
        trophyOneImageView.relocate(291, 15);
        trophyTwoImageView.relocate(367, 15);
        trophyThreeImageView.relocate(443, 15);
        trophyFourImageView.relocate(519, 15);
        trophyFiveImageView.relocate(595, 15);
        
        hud.setHudTrophyOneImageView(trophyOneImageView);
        hud.setHudTrophyTwoImageView(trophyTwoImageView);
        hud.setHudTrophyThreeImageView(trophyThreeImageView);
        hud.setHudTrophyFourImageView(trophyFourImageView);
        hud.setHudTrophyFiveImageView(trophyFiveImageView);
        
        hudPane.setMinHeight(77);
        hudPane.setPrefHeight(77);
        hudPane.getChildren().addAll(hBoxItemOneImageView,hBoxItemTwoImageView,hBoxItemThreeImageView,trophyOneImageView,trophyTwoImageView,trophyThreeImageView,trophyFourImageView,trophyFiveImageView);
        
        background.setContent(gameEngine.getBackground());
        background.addEventFilter(InputEvent.ANY, (event)-> {
                event.consume();
        });
        
        
        background.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        background.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        background.setStyle("-fx-background-insets: 0; -fx-padding: 0;");
        playground.setCenter(background);
        playground.setBottom(hudPane);

        Scene scene = new Scene(playground, gameEngine.getPaneWidth(), gameEngine.getPaneHeight());
        scene.setFill(Color.web("#eee"));
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

		HBox row;
		outro = new StackPane();
		outro.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));
		outro.setId("outroscreen");
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
		Collections.sort(hs, Comparator.comparing(Highscore::getDuration)
				.thenComparing(Highscore::getUserName).thenComparing(Highscore::getHighScoreTime));

		outro.setPadding(new Insets(0, 0, 0, 0));

		GridPane hsTable = new GridPane();
		hsTable.setStyle("-fx-background-color: rgba(255, 255, 255, 0.9); -fx-background-radius: 10;");
		hsTable.setMaxWidth(gameEngine.getPaneWidth() - 400);
		hsTable.setMaxHeight(gameEngine.getPaneHeight() - 220); 
		hsTable.setPadding(new Insets(10, 10, 10, 10)); 
		hsTable.setVgap(5); 
		hsTable.setHgap(5);  

		int c=0;
		
		Text head = new Text();
		head.setText("Highscore");
		head.setFont(Font.font(null, FontWeight.BOLD, 24));
		head.setTextAlignment(TextAlignment.CENTER);
		hsTable.addRow(c, head);
		c++;
		for(Highscore h : hs)
		{
			Text user = new Text();
			user.setText(h.getUserName());
			user.minWidth(200);
			user.maxWidth(200);
			user.setWrappingWidth(200);
			user.setFont(Font.font(null, FontWeight.NORMAL, 14));
		
			String sek;
		    long millis= h.getDuration();
		    long secs = millis / 1000;
		    long mins = secs / 60;
		    long restsecs = secs % 60;
		    if(restsecs<10) {
		    	sek = "0" + restsecs; 
		    }else {
		    	sek = ""+restsecs;
		    }

			Text duration = new Text();
			duration.setText(mins + ":" +  sek);
			duration.setFont(Font.font(null, FontWeight.NORMAL, 14));
			duration.minWidth(50);
			duration.maxWidth(50);
			duration.setWrappingWidth(50);
			duration.setTextAlignment(TextAlignment.RIGHT);

			Text hsTime = new Text();
			hsTime.setText(h.getHighScoreTime());
			hsTime.setFont(Font.font(null, FontWeight.NORMAL, 14));
			hsTime.setTextAlignment(TextAlignment.RIGHT);
			hsTime.minWidth(200);
			hsTime.maxWidth(200);
			hsTime.setWrappingWidth(200);
			
			HBox vboxTrophy = renderTrophys(h.getTrophy(),c);
			
			row = new HBox();
			row.maxHeight(30);
			row.minHeight(30);
			row.setPrefHeight(30);
			
			row.getChildren().addAll(user,vboxTrophy,duration,hsTime);
			hsTable.addRow(c, row);

			if(c==10) {
				break;
			}
				c++;
		}

		outro.getChildren().addAll(hsTable,t);
		
        //return eady scene
    	Scene scene = new Scene(outro, paneWidth, paneHeight);
    	return scene;
    	
    }
    
	private HBox renderTrophys(String trophy,int c) {
    	ImageView influencer;
    	ImageView confused;
    	ImageView treehugger;
    	ImageView stoney;
    	ImageView clumsy;
    	
    	HBox vboxTrophys = new HBox();
    	vboxTrophys.maxHeight(20);
    	vboxTrophys.prefHeight(20);
    	vboxTrophys.setMinWidth(130);
    	vboxTrophys.setPrefWidth(130);
    	int tCounter = 0;
    	String[] parts = trophy.split("§");
		for (int i = 0; i<parts.length;i++) {
			if(parts[i].startsWith("B*")) {
				confused  = new ImageView(gameEngine.getConfused_s().getImage());
				confused.relocate(c*30, 0);
				vboxTrophys.getChildren().add(confused);
				tCounter++;
			}else if(parts[i].startsWith("N*")) {
				influencer  = new ImageView(gameEngine.getInfluencer_s().getImage());
				influencer.relocate(c*30, 0);
				vboxTrophys.getChildren().add(influencer);
				tCounter++;
			}else if(parts[i].startsWith("T*")) {
				treehugger  = new ImageView(gameEngine.getTreehugger_s().getImage());
				treehugger.relocate(c*30, 0);
				vboxTrophys.getChildren().add(treehugger);
				tCounter++;
			}else if(parts[i].startsWith("S*")) {
				stoney = new ImageView(gameEngine.getStoney_s().getImage());
				stoney.relocate(c*30, 0);
				vboxTrophys.getChildren().add(stoney);
				tCounter++;
			}else if(parts[i].startsWith("F*")) {
				clumsy  = new ImageView(gameEngine.getClumsy_s().getImage());
				clumsy.relocate(c*30, 0);
				vboxTrophys.getChildren().add(clumsy);
				tCounter++;
			}
		}

		if(tCounter<5) {
			for(int i = 0;i<(5-tCounter);i++) {
				//System.out.println("Addiere");
				ImageView placholder = new ImageView(gameEngine.getTransparent().getImage());
				placholder.maxHeight(20);
				placholder.relocate(0, 0);
				vboxTrophys.getChildren().add(placholder);
			}
		}
    	return vboxTrophys;
    }
    
    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);  
   }

    
    private void introAction(TextField playerNameField,VBox vbox,Stage primaryStage) {
    	gameEngine.setUserName(playerNameField.getText());

    	/* Establishes a timeline that fades the intro scene to zero opacity
         * switches the scenes and fades the new scene to full opacity
         */

        Timeline fadeInTimeline = new Timeline();
        fadeInTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), new KeyValue(intro.opacityProperty(), 0.0)));
        fadeInTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.3), new KeyValue(gameEngine.getScene().getRoot().opacityProperty(), 0.0)));
        fadeInTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), ae -> primaryStage.setScene(gameEngine.getScene())));
        fadeInTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(0.5), ae -> intro.getChildren().setAll(vbox)));
        fadeInTimeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1.0), new KeyValue(gameEngine.getScene().getRoot().opacityProperty(), 1.0)));
        fadeInTimeline.play();
    }
    
    /**
     * This renders the intro of Kingdom 4.
     */
    public void renderIntro() {
        int paneWidth = gameEngine.getPaneWidth();
       	int paneHeight = gameEngine.getPaneHeight();
    	intro = new Pane();
    	intro.getStylesheets().add("file:///" + cssFile.getAbsolutePath().replace("\\", "/"));
    	intro.setId("introscreen");
    	Stage primaryStage = gameEngine.getPrimaryStage();

    	Media media = new Media("file:///" + startscreenMusic.getAbsolutePath().replace("\\", "/"));
        MediaPlayer playerStart = new MediaPlayer(media);
        playerStart.setStartTime(new Duration(0));
        playerStart.setCycleCount(MediaPlayer.INDEFINITE);
        playerStart.play();
    	
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
            	playerStart.stop();
                playerMain.setStartTime(new Duration(0));
                playerMain.setCycleCount(MediaPlayer.INDEFINITE);
                playerMain.play();
               ev.consume(); 
            }
        });
        
        //this action fade the game with the intro, we try to change it as blend effect
        startGameButton.setOnAction(e -> 
        {
        	introAction(playerNameField,vbox,primaryStage);
        	playerStart.stop();
            playerMain.setStartTime(new Duration(0));
            playerMain.setCycleCount(MediaPlayer.INDEFINITE);
            playerMain.play();
        });
        
        vbox.setAlignment(Pos.CENTER);        
        intro.getChildren().addAll(vbox);
    	Scene scene = new Scene(intro, paneWidth, paneHeight);
        scene.setFill(Color.web("#eee"));
        gameEngine.setIntro(scene);
    }
    
    public void toggleMusic() {
    	if(playerMain.getStatus().equals(Status.STOPPED)) {
    		playerMain.play();
    	}
    	else {
    		playerMain.stop();
    	}
    }
    
}
