package game;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Scenery {

    private GameEngine gameEngine;
    private Pane entities, textOver;


    public Scenery(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        entities = new Pane();
        gameEngine.setEntities(this.entities);

        textOver = new Pane();
        gameEngine.setTextOver(this.textOver);
        //gameEngine.getTextOver().setVisible(false);

        gameEngine.getBackground().getChildren().addAll(gameEngine.getEntities(), gameEngine.getTextOver());
        gameEngine.getEntities().setVisible(false);
        Scene scene = new Scene(new BorderPane(gameEngine.getBackground()), gameEngine.getPaneWidth(), gameEngine.getPaneHeight());
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