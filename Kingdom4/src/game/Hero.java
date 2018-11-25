package game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hero {

    private GameEngine gameEngine;
    private Rectangle player;
    private Rectangle actionSquare;
    private Rectangle clip;
    private ImageView[] hero;
    private int tileSize;

    public Hero(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        tileSize = gameEngine.getTileSize();

        this.hero = new ImageView[8];
        this.hero[0] = new ImageView(new Image("front1.png", tileSize, tileSize, true, false));
        this.hero[1] = new ImageView(new Image("front2.png", tileSize, tileSize, true, false));
        this.hero[2] = new ImageView(new Image("back1.png", tileSize, tileSize, true, false));
        this.hero[3] = new ImageView(new Image("back2.png", tileSize, tileSize, true, false));
        this.hero[4] = new ImageView(new Image("left1.png", tileSize, tileSize, true, false));
        this.hero[5] = new ImageView(new Image("left2.png", tileSize, tileSize, true, false));
        this.hero[6] = new ImageView(new Image("right1.png", tileSize, tileSize, true, false));
        this.hero[7] = new ImageView(new Image("right2.png", tileSize, tileSize, true, false));

        actionSquare = new Rectangle(32, 32);
        actionSquare.setTranslateX(16);
        actionSquare.setTranslateY(16);
        actionSquare.setFill(Color.PURPLE);
        gameEngine.setPlayer(this.hero);
        gameEngine.setGeneralVisibility(false);
        gameEngine.getPlayer()[0].setVisible(true);
        gameEngine.setActionSquare(actionSquare);

        gameEngine.moveCamera();
    }

    public void getControls(Scene scene) {
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    gameEngine.setNorth(true); break;
                case DOWN:  gameEngine.setSouth(true); break;
                case LEFT:  gameEngine.setWest(true); break;
                case RIGHT: gameEngine.setEast(true); break;
                case D: gameEngine.getEntities().setVisible(true); break;
                default:
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:    gameEngine.setNorth(false); break;
                case DOWN:  gameEngine.setSouth(false); break;
                case LEFT:  gameEngine.setWest(false); break;
                case RIGHT: gameEngine.setEast(false); break;
                case D: gameEngine.getEntities().setVisible(false); break;
                default:
                    break;
            }
        });
    }

    public void move(long now) {
        gameEngine.setTimestamp(now);
        gameEngine.movePlayer();
    }

/*    public void move(long now) {
        gameEngine.notifyObservers();

        if (gameEngine.isTrigger()) {
            gameEngine.getTriggerField().setFill(Color.BLUE);
        }
        else {
            gameEngine.getTriggerField().setFill(Color.GREEN);
        }

        gameEngine.setTimestamp(now);
        long elapsedNanos = gameEngine.getTimestamp() - gameEngine.getLastUpdate() ;
        if (gameEngine.getLastUpdate() < 0) {
            gameEngine.setLastUpdate(now);
            return;
        }

        double elapsedSeconds = elapsedNanos / 1_000_000_000.0 ;
        double deltaX = 0 ;
        double deltaY = 0 ;

        if (gameEngine.getEast()) deltaX += gameEngine.getSpeed() ;
        if (gameEngine.getWest()) deltaX -= gameEngine.getSpeed() ;
        if (gameEngine.getSouth()) deltaY += gameEngine.getSpeed() ;
        if (gameEngine.getNorth()) deltaY -= gameEngine.getSpeed() ;

        gameEngine.getPlayer().setX(clampRange(gameEngine.getPlayer().getX() + deltaX * elapsedSeconds, 0, gameEngine.getBackground().getWidth() - gameEngine.getPlayer().getWidth()));
        gameEngine.getPlayer().setY(clampRange(gameEngine.getPlayer().getY() + deltaY * elapsedSeconds, 0, gameEngine.getBackground().getHeight() - gameEngine.getPlayer().getHeight()));

        gameEngine.setLastUpdate(now);
    } */
}
