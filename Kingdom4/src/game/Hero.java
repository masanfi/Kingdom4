package game;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * This is to worship the hero we at Fantastic 4 studios love and appreciate.
 * Copyright (c) 2018-2019 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 1.2
 *
 */

public class Hero {

    private GameEngine gameEngine;
    private Rectangle actionSquare;
    private ImageView[] hero;
    private int tileSize;
    public static boolean movementStop = false;

    public Hero(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
        tileSize = gameEngine.getTileSize();

        // initialize image array with our beloved hero from different perspectives
        this.hero = new ImageView[8];
        this.hero[0] = new ImageView(new Image("front1.png", tileSize, tileSize, true, false));
        this.hero[1] = new ImageView(new Image("front2.png", tileSize, tileSize, true, false));
        this.hero[2] = new ImageView(new Image("back1.png", tileSize, tileSize, true, false));
        this.hero[3] = new ImageView(new Image("back2.png", tileSize, tileSize, true, false));
        this.hero[4] = new ImageView(new Image("left1.png", tileSize, tileSize, true, false));
        this.hero[5] = new ImageView(new Image("left2.png", tileSize, tileSize, true, false));
        this.hero[6] = new ImageView(new Image("right1.png", tileSize, tileSize, true, false));
        this.hero[7] = new ImageView(new Image("right2.png", tileSize, tileSize, true, false));

        // create an action square that is used for the collision detection
        actionSquare = new Rectangle(32, 48);
        actionSquare.setTranslateX(gameEngine.getActionSquareOffsetX());
        actionSquare.setTranslateY(gameEngine.getActionSquareOffsetY());
        actionSquare.setFill(Color.PURPLE);
        gameEngine.setPlayer(this.hero);
        gameEngine.setGeneralVisibility(false);
        gameEngine.getPlayer()[0].setVisible(true);
        gameEngine.setActionSquare(actionSquare);

        // moves camera over the world
        gameEngine.moveCamera();
    }

    /**
     * Defines the controls for the game.
     * @param scene
     */
    public void getControls(Scene scene) {
    	
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    gameEngine.setNorth(true);gameEngine.setSouth(false);gameEngine.setWest(false);gameEngine.setEast(false); break;
                case DOWN:  gameEngine.setSouth(true); gameEngine.setNorth(false);gameEngine.setWest(false);gameEngine.setEast(false);break;
                case LEFT:  gameEngine.setWest(true); gameEngine.setSouth(false);gameEngine.setNorth(false);gameEngine.setEast(false);break;
                case RIGHT: gameEngine.setEast(true); gameEngine.setSouth(false);gameEngine.setWest(false);gameEngine.setNorth(false);break;
                case D: gameEngine.getEntities().setVisible(true); break;
                case M: gameEngine.toggleMusic(); break;
                case K: gameEngine.showTrophyCollectionMessage("K"); gameEngine.changeKnight(); break;
                case S: gameEngine.showAllStates(); break;
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

    /**
     * This method moves our hero.
     * @param now
     */
    public void move(long now) {
    	    	
        gameEngine.setTimestamp(now);
        gameEngine.movePlayer();
    }
}
