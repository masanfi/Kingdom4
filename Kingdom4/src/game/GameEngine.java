import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class GameEngine extends Observable {

    private final char[][] world =
    		{
                {'(','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','-','+','R','r',' ','Z'},
                {'@',' ','Z','Y',' ',' ','Y','X',' ',' ','Z',' ',' ',' ',' ',' ','X',' ',' ','Y',' ','X',' ',' ','Z',' ',' ','R','r','X',' '},
                {'@',' ',' ','X',' ',' ','X',' ',' ',' ',' ','M','N',' ','Y',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X','R','r','Y','X'},
                {'@',' ',' ',' ',' ',' ',' ','Z',' ',' ',' ','O','P',' ',' ',' ','Z','I','J',' ','X','X',' ',' ',' ','A','/','R','r',' ',' '},
                {'@',' ',' ',' ','Z',' ',' ',' ',' ',' ',' ',' ','g',' ',' ',' ',' ','K','L',' ','X','X','X',' ','X','C','D','R','r',' ',' '},
                {'@',' ',' ',' ','Z','Z',' ',' ',' ','X',' ',' ','g',' ',' ',' ',' ','g',' ','X','X','Y',' ',' ',' ','g','$','R','r','Y',' '},
                {'@','X','X',' ',' ',' ','X',' ',' ',' ',' ',' ','g','X',' ',' ',' ','g',' ',' ','h','f','f','f','f','j',' ','R','r',' ','X'},
                {'@','Y','X',' ',' ',' ','Y','X','X',' ',' ','Y','g',' ',' ',' ','X','g',' ','Z','g',' ',' ',' ',' ',' ','Y','R','r',' ','X'},
                {'@',' ',' ','X',' ',' ','E','F',' ',' ',' ',' ','m','f','f','f','f','l','f','f','o',' ','X','X',' ','Z',' ','R','r','X','X'},
                {'@',' ','X','X',' ',' ','G','H',' ',' ',' ',' ','g',' ',' ',' ',' ',' ',' ',' ','g','Y','X',' ',' ',' ',' ','R','r',' ','Z'},
                {'@',' ',' ',' ',' ',' ',' ','g','Z',' ',' ',' ','g',' ',' ','Z','Y','X',' ',' ','g',' ','Z',' ',' ',' ',' ','R','r',' ',' '},
                {'@','Z',' ',' ',' ',' ',' ','k','f','f','f','f','o',' ','X','X','X','Y',' ',' ','g',' ',' ',' ',' ',' ',' ','R','r',' ',' '},
                {'@',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ','g',' ',' ','X','X',' ',' ',' ','g',' ',' ',' ','X',' ',' ','R','r',' ',' '},
                {'@','X','X',' ',' ',' ','X',' ',' ',' ',' ',' ','g',' ',' ',' ','X','X',' ',' ','g',' ',' ','Y','X',' ',' ','R','r',' ',' '},
                {'@','X','Y',' ',' ',' ',' ',' ',' ',' ',' ',' ','g',' ','X','X','h','f','f','f','l','f','f','f','f','f','f','B','b','f','f'},
                {'@',' ','Y',' ','Z','X','X','V','W',' ',' ',' ','g',' ',' ','X','g',' ',' ',' ','X','X',' ',' ',' ',' ',' ','R','r','X',' '},
                {'@',' ',' ',' ',' ','X','X','%','#',' ',' ',' ','g',' ',' ',' ','g',' ',' ','Y',' ',' ',' ',' ',' ',' ','X','R','r','Y',' '},
                {'@',' ',' ',' ',' ',' ',' ','g','X','X','Z','Z','g','Z','Z',' ','g',' ',' ',' ',' ',' ',' ',' ','Z',' ',' ','R','r',' ','Z'},
                {'@',' ',' ',' ',' ',' ',' ','g',' ','Y','Z','h','l','i','Z',' ','g',' ','X',' ',' ',' ',' ',' ',' ','X',' ','R','r',' ',' '},
                {'@','Z','Q','S',' ','X','Y','m','f','f','f','o','p','m','f','f','j',' ',' ','Y',' ',' ',' ',' ',' ',' ','X','R','r','Y',' '},
                {'@',' ','T','U',' ',' ',' ','g',' ',' ','Z','k','n','j','Z',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','r',' ',' '},
                {'@',' ',' ','g',' ',' ',' ','g',' ',' ','Z','Z','g','Z','Z',' ',' ',' ','X',' ',' ',' ',' ',' ',' ','X',' ','R','r',' ',' '},
                {'@','X',' ','k','f','f','f','j',' ','I','J','X','g',' ',' ',' ',' ',' ',' ',' ',' ','E','F',' ',' ',' ',' ','R','r',' ','X'},
                {'@','Z','X',' ','X','Y','X','Y',' ','K','L',' ','g',' ',' ','Y',' ',' ',' ','X','X','G','H',' ',' ','Z','Z','R','r',' ','X'},
                {'@','X','X','Z',' ','X','X','X',' ','g',' ',' ','g',' ',' ',' ',' ',' ',' ',' ',' ',' ','g',' ',' ','Z','Y','R','r','Z',' '},
                {'@','Y','X','X',' ','X','X',' ',' ','k','f','f','l','f','f','f','f','f','f','f','f','f','j','Z',' ',' ','Y','R','r',' ',' '},
                {'@','!','X','X',' ','X','Z','X',' ',' ',' ',' ','X',' ',' ','X','X',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','R','r',' ',' '},
                {'@',' ',' ','X',' ','X','Z',' ',' ','Z',' ',' ','X',' ',' ','X',' ','Z',' ',' ',' ','Y','Z','X',' ',' ',' ','R','r',' ','X'},
                {'@','X',' ',' ','Z','X','X','Y',' ',' ',' ','X','X','Z',' ',' ',' ','Z','X',' ','Z','X',' ',' ',' ','Y','X','R','r','X','X'},
                {'@','X','X','X','X','X','X','Z',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','X',' ',' ','X',' ',' ','§','Y','X','R','r','X','X'},
                {')','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','=','&','R','r','X','X'}
        };
    		
    		
         

    private final int tileSize = 64 ;
    private final int amountHorizontalTiles = this.getWorld()[0].length;
    private final int amountVerticalTiles = this.getWorld().length;
    private final int paneWidth = 960;
    private final int paneHeight = 640;

    private final int speed = 200 ; // pixels / second
    private final double animationSpeed = .15;
    private double animationTimer = 0;
    private int currentHero;
    private ImageView[] player;
    private Scene scene;
    private Pane background;
    private Rectangle clip;
    private long lastUpdate;
    private long timestamp;
    private boolean north, east, south, west, isCollision, isTrigger;
    private Shape trigger;
    private ArrayList<Rectangle> obstacle;
    private Rectangle actionSquare;
    private Pane entities;
    private Pane textOver;
    private double noCollisionX, noCollisionY;

    public GameEngine() {

    }

    public int getTileSize() {
        return tileSize;
    }

    public int getAmountHorizontalTiles() {
        return amountHorizontalTiles;
    }

    public int getAmountVerticalTiles() {
        return amountVerticalTiles;
    }

    public int getSpeed() {
        return speed;
    }

    public void setPlayer(ImageView[] player) {
        this.player = player;
    }

    public ImageView[] getPlayer() {
        return player;
    }

    public int getPaneWidth() {
        return paneWidth;
    }

    public int getPaneHeight() {
        return paneHeight;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void setBackground(Pane background) {
        this.background = background;
    }

    public Pane getBackground() {
        return background;
    }

    public void setClip(Rectangle clip) {
        this.clip = clip;
    }

    public Rectangle getClip() {
        return clip;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setNorth(boolean north) {
        this.north = north;
    }

    public void setEast(boolean east) {
        this.east = east;
    }

    public void setSouth(boolean south) {
        this.south = south;
    }

    public void setWest(boolean west) {
        this.west = west;
    }

    public boolean getNorth() {
        return north;
    }

    public boolean getEast() {
        return east;
    }

    public boolean getSouth() {
        return south;
    }

    public boolean getWest() {
        return west;
    }

    public void setTriggerField(Shape trigger) {
        this.trigger = trigger;
    }

    public Shape getTriggerField() {
        return trigger;
    }

    public void setTrigger(boolean isTrigger) {
        this.isTrigger = isTrigger;
    }

    public boolean isTrigger() {
        return isTrigger;
    }

    public void setCollision(boolean isCollision) {
        this.isCollision = isCollision;
    }

    public boolean isCollision() {
        return isCollision;
    }

    public ArrayList<Rectangle> getObstacle() {
        return obstacle;
    }

    public void setObstacle(ArrayList<Rectangle> obstacle) {
        this.obstacle = obstacle;
    }

    public void movePlayer() {
        this.animatePlayer();
        this.checkForTriggers();

        this.setTimestamp(this.getTimestamp());
        long elapsedNanos = this.getTimestamp() - this.getLastUpdate() ;
        if (this.getLastUpdate() < 0) {
            this.setLastUpdate(this.getTimestamp());
            return;
        }

        double elapsedSeconds = elapsedNanos / 1_000_000_000.0 ;
        double deltaX = 0 ;
        double deltaY = 0 ;

        if (this.getEast()) {
            deltaX += this.getSpeed();
        }
        if (this.getWest()) {
            deltaX -= this.getSpeed();
        }
        if (this.getSouth()) {
            deltaY += this.getSpeed();
        }
        if (this.getNorth()) {
            deltaY -= this.getSpeed();
        }

        double clampRangeX = clampRange(this.getActionSquare().getX() + deltaX * elapsedSeconds, 0, this.getBackground().getWidth() - this.getActionSquare().getWidth());
        double clampRangeY = clampRange(this.getActionSquare().getY() + deltaY * elapsedSeconds, 0, this.getBackground().getHeight() - this.getActionSquare().getHeight());

        if (!isCollision) {
            this.setNoCollisionX(this.getActionSquare().getX());
            this.setNoCollisionY(this.getActionSquare().getY());
        }

        if (!isCollision) {
            this.getActionSquare().setX(clampRangeX);
            this.getActionSquare().setY(clampRangeY);

            for (int i = 0; i < this.getPlayer().length; i++) {
                this.getPlayer()[i].setX(clampRangeX);
                this.getPlayer()[i].setY(clampRangeY);
            }

        } else {
            //System.out.println("Collision resolution");
            this.getActionSquare().setX(this.getNoCollisionX());
            this.getActionSquare().setY(this.getNoCollisionY());
            this.setCollision(false);
        }

        this.notifyObservers();
        this.setLastUpdate(this.getTimestamp());
    }

    private void setNoCollisionX(double x) {
        this.noCollisionX = x;
    }

    private void setNoCollisionY(double y) {
        this.noCollisionY = y;
    }

    private double getNoCollisionX() {
        return noCollisionX;
    }

    private double getNoCollisionY() {
        return noCollisionY;
    }

    private void checkForTriggers() {
        if (this.isTrigger()) {
            this.getTriggerField().setFill(Color.PURPLE);
            Rectangle rect = new Rectangle(750, 400, 200, 80);
            rect.setFill(Color.PURPLE);
            Text text = new Text(765, 445, "Trigger detected!");
            text.setFont(Font.font ("Verdana", 20));
            text.setFill(Color.WHITE);
            this.getTextOver().getChildren().addAll(rect, text);
            this.getTextOver().setVisible(true);
        }
        else {
            this.getTriggerField().setFill(Color.GREEN);
            this.getTextOver().setVisible(false);
        }
    }

    private double clampRange(double value, double min, double max) {
        if (value < min) return min ;
        if (value > max) return max ;
        return value ;
    }

    public void moveCamera() {
        clip = new Rectangle();
        this.setClip(clip);

        this.getClip().widthProperty().bind(this.getScene().widthProperty());
        this.getClip().heightProperty().bind(this.getScene().heightProperty());

        this.getClip().xProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(this.getActionSquare().getX() - this.getScene().getWidth() / 2, 0, this.getBackground().getWidth() - this.getScene().getWidth()),
                this.getActionSquare().xProperty(), this.getScene().widthProperty()));
        this.getClip().yProperty().bind(Bindings.createDoubleBinding(
                () -> clampRange(this.getActionSquare().getY() - this.getScene().getHeight() / 2, 0, this.getBackground().getHeight() - this.getScene().getHeight()),
                this.getActionSquare().yProperty(), this.getScene().heightProperty()));
    }

    public char[][] getWorld() {
        return world;
    }

    public void setActionSquare(Rectangle actionSquare) {
        this.actionSquare = actionSquare;
    }

    public Rectangle getActionSquare() {
        return actionSquare;
    }

    public void animatePlayer() {
        if (north) {
                setGeneralVisibility(false);
                this.getPlayer()[2].setVisible(true);
            }
        if (south) {
            setGeneralVisibility(false);
            this.getPlayer()[0].setVisible(true);
        }
        if (west) {
            setGeneralVisibility(false);
            this.getPlayer()[4].setVisible(true);
        }
        if (east) {
            setGeneralVisibility(false);
            this.getPlayer()[6].setVisible(true);
        }
    }

    public void setGeneralVisibility(boolean visibility) {
        if (visibility == false) {
            for (int i = 0; i < this.getPlayer().length; i++) {
                this.getPlayer()[i].setVisible(false);
            }
        }
    }

    public void setEntities(Pane entities) {
        this.entities = entities;
    }

    public Pane getEntities() {
        return entities;
    }

    public void setTextOver(Pane textOver) {
        this.textOver = textOver;
    }

    public Pane getTextOver() {
        return textOver;
    }
}
