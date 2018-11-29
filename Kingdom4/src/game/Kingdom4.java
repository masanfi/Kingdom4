package game;
 
import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

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

        scenery.renderIntro(primaryStage);
        primaryStage.setScene(gameEngine.getIntro());

        primaryStage.setResizable(false);
        primaryStage.show();
        
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
