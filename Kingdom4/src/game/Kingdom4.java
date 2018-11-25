package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Kingdom4 extends Application {

    private World world;
    private Hero hero;
    private GameEngine gameEngine;
    private Scenery scenery;

    @Override
    public void start(Stage primaryStage) {

        gameEngine = new GameEngine();
        new CollisionDetection(gameEngine);
        new TriggerDetection(gameEngine);

        //new Obstacle(gameEngine);
        new Trigger(gameEngine);

        world = new World(gameEngine);
        scenery = new Scenery(gameEngine);

        hero = new Hero(gameEngine);
        world.buildWorld();
        scenery.setPlayerOnField();
        world.setClip();
        gameEngine.setLastUpdate(-1);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                hero.move(now);
            }
        };

        hero.getControls(gameEngine.getScene());
        primaryStage.setTitle("Kingdom 4");
        primaryStage.setScene(gameEngine.getScene());
        primaryStage.setResizable(false);
        primaryStage.show();

        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
