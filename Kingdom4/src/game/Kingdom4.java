package game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Kingdom4 extends Application {

    // Define the window size
    private final double WIDTH = 800, HEIGHT = 600;

    // Initialize graphics
    private final String HERO = "hero.png";
    private final String HERO_HOVER = "hero_hover.png";
    private final String WALL_IMAGE = "mauer.jpg";

    private Node hero;
    private Node hero_hover;
    private Node imageItem;
    private Node menu;

    private Group kd;

    private boolean run, goNorth, goSouth, goEast, goWest;

    @Override
    public void start(Stage stage) throws Exception {

        // Load objects from image files
        hero = new ImageView(new Image(HERO));
        hero_hover = new ImageView(new Image(HERO_HOVER));

        Group kingdom = new Group(hero);
        Scene scene = new Scene(kingdom, WIDTH, HEIGHT, Color.SANDYBROWN);
        Text text = new Text("Testtext");
        text.relocate(100, 100);

        DataModel model = new DataModel(WIDTH, HEIGHT);
        Control control = new Control(hero, hero_hover, WIDTH, HEIGHT, text);

        menu = model.createMenu(WALL_IMAGE);
        kd = model.createItems(kingdom);

        control.moveHeroTo(WIDTH / 2, HEIGHT / 2);

        kd.getChildren().addAll(menu);
        kd.getChildren().addAll(text);
        kd.getChildren().addAll(hero_hover);

        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:    goNorth = true; break;
                case DOWN:  goSouth = true; break;
                case LEFT:  goWest  = true; break;
                case RIGHT: goEast  = true; break;
                case SHIFT: run = true; break;
                default:
                    break;
            }
        });

        scene.setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case UP:    goNorth = false; break;
                case DOWN:  goSouth = false; break;
                case LEFT:  goWest  = false; break;
                case RIGHT: goEast  = false; break;
                case SHIFT: run = false; break;
                default:
                    break;
            }
        });

        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int dx = 0, dy = 0;

                if (goNorth) dy -= 1;
                if (goSouth) dy += 1;
                if (goEast)  dx += 1;
                if (goWest)  dx -= 1;
                if (run) { dx *= 3; dy *= 3; }

                control.moveHeroBy(dx, dy);
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
 
}
