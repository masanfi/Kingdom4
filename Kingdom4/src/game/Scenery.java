package game;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Scenery {

    private GameEngine gameEngine;
    private Pane entities, hud ,textOver;
    private ScrollPane background;
    private BorderPane playground;

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
        //text.setFill(Color.WHITE);

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
}