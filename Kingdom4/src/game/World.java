package game;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.css.Rect;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.stream.IntStream;
 
public class World {

    private GameEngine gameEngine;

    private int tileSize;
    private int amountHorizontalTiles;
    private int amountVerticalTiles;
    private int y;

    private Pane background;

    private ArrayList<ImageView> backgroundCollection;
    private ArrayList<Item> items;

    private Document configFile;
    private NodeList objectList;

    private ArrayList<Rectangle> obstacles;
    private ArrayList<Collision> collisions;

    public World(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        this.items = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        this.collisions = new ArrayList<>();

        tileSize = gameEngine.getTileSize();
        amountHorizontalTiles = gameEngine.getAmountHorizontalTiles();
        amountVerticalTiles = gameEngine.getAmountVerticalTiles();

        readObjects();
        //items.forEach(System.out::println);

        this.background = createBackground();
        gameEngine.setBackground(this.background);

    }

    public void setClip() {
        gameEngine.getBackground().setClip(gameEngine.getClip());
        gameEngine.getBackground().translateXProperty().bind(gameEngine.getClip().xProperty().multiply(-1));
        gameEngine.getBackground().translateYProperty().bind(gameEngine.getClip().yProperty().multiply(-1));
    }

    private void setObstacles() {
        gameEngine.getObstacle().forEach(obstacle->{
            gameEngine.getEntities().getChildren().add(obstacle);
        });
    }

    public void buildWorld() {
        this.setObstacles();

        gameEngine.getEntities().getChildren().addAll(gameEngine.getTriggerField());
    }

    private void addToPane(int x, int y, Item item, Pane pane) {
        ImageView image = new ImageView(new Image(item.getImage(), gameEngine.getTileSize(), gameEngine.getTileSize(), true, false));
        this.backgroundCollection.add(image);

        if (!item.isWalkable()) {
            Rectangle obstacle = new Rectangle(y * gameEngine.getTileSize(), x * gameEngine.getTileSize(), gameEngine.getTileSize(), gameEngine.getTileSize());
            Collision collision = new Collision(item.getName(), new Point2D(y * gameEngine.getTileSize(), x * gameEngine.getTileSize()));
            obstacle.setFill(Color.RED);
            this.obstacles.add(obstacle);
            this.collisions.add(collision);
            gameEngine.setObstacle(this.obstacles);
            gameEngine.setCollisionObject(this.collisions);
        }

        this.backgroundCollection.get(this.backgroundCollection.size() - 1).relocate(y * tileSize, x * tileSize);
        pane.getChildren().add(this.backgroundCollection.get(this.backgroundCollection.size() - 1));
    }

    private Pane createBackground() {
        Pane pane = new Pane();

        pane.setMinSize(amountHorizontalTiles * tileSize, amountVerticalTiles * tileSize);
        pane.setPrefSize(amountHorizontalTiles * tileSize, amountVerticalTiles * tileSize);
        pane.setMaxSize(amountHorizontalTiles * tileSize, amountVerticalTiles * tileSize);

        this.backgroundCollection = new ArrayList();

        char[][] world = gameEngine.getWorld();

        for (int x = 0; x < amountVerticalTiles; x++) {
            for (y = 0; y < amountHorizontalTiles; y++) {
                if (world[x][y] == 'X') {
                    addToPane(x, y, items.get(1), pane);
                }
                else if (world[x][y] == 'Y') {
                    addToPane(x, y, items.get(2), pane);
                }
                else if (world[x][y] == 'Z') {
                    addToPane(x, y, items.get(3), pane);
                }
                else if (world[x][y] == '-') {
                    addToPane(x, y, items.get(4), pane);
                }
                else if (world[x][y] == '=') {
                    addToPane(x, y, items.get(5), pane);
                }
                else if (world[x][y] == '@') {
                    addToPane(x, y, items.get(6), pane);
                }
                else if (world[x][y] == '(') {
                    addToPane(x, y, items.get(7), pane);
                }
                else if (world[x][y] == ')') {
                    addToPane(x, y, items.get(8), pane);
                }
                else if (world[x][y] == '+') {
                    addToPane(x, y, items.get(9), pane);
                }
                else if (world[x][y] == '&') {
                    addToPane(x, y, items.get(10), pane);
                }
                else if (world[x][y] == 'R') {
                    addToPane(x, y, items.get(11), pane);
                }
                else if (world[x][y] == 'r') {
                    addToPane(x, y, items.get(12), pane);
                }
                else if (world[x][y] == 'B') {
                    addToPane(x, y, items.get(13), pane);
                }
                else if (world[x][y] == 'b') {
                    addToPane(x, y, items.get(14), pane);
                }
                else if (world[x][y] == 'f') {
                    addToPane(x, y, items.get(15), pane);
                }
                else if (world[x][y] == 'g') {
                    addToPane(x, y, items.get(16), pane);
                }
                else if (world[x][y] == 'h') {
                    addToPane(x, y, items.get(17), pane);
                }
                else if (world[x][y] == 'i') {
                    addToPane(x, y, items.get(18), pane);
                }
                else if (world[x][y] == 'j') {
                    addToPane(x, y, items.get(19), pane);
                }
                else if (world[x][y] == 'k') {
                    addToPane(x, y, items.get(20), pane);
                }
                else if (world[x][y] == 'l') {
                    addToPane(x, y, items.get(21), pane);
                }
                else if (world[x][y] == 'm') {
                    addToPane(x, y, items.get(22), pane);
                }
                else if (world[x][y] == 'n') {
                    addToPane(x, y, items.get(23), pane);
                }
                else if (world[x][y] == 'o') {
                    addToPane(x, y, items.get(24), pane);
                }
                else if (world[x][y] == 'p') {
                    addToPane(x, y, items.get(25), pane);
                }
                else if (world[x][y] == 'M') {
                    addToPane(x, y, items.get(26), pane);
                }
                else if (world[x][y] == 'N') {
                    addToPane(x, y, items.get(27), pane);
                }
                else if (world[x][y] == 'O') {
                    addToPane(x, y, items.get(28), pane);
                }
                else if (world[x][y] == 'P') {
                    addToPane(x, y, items.get(29), pane);
                }
                else if (world[x][y] == 'Q') {
                    addToPane(x, y, items.get(30), pane);
                }
                else if (world[x][y] == 'S') {
                    addToPane(x, y, items.get(31), pane);
                }
                else if (world[x][y] == 'T') {
                    addToPane(x, y, items.get(32), pane);
                }
                else if (world[x][y] == 'U') {
                    addToPane(x, y, items.get(33), pane);
                }
                else if (world[x][y] == 'A') {
                    addToPane(x, y, items.get(34), pane);
                }
                else if (world[x][y] == '/') {
                    addToPane(x, y, items.get(35), pane);
                }
                else if (world[x][y] == 'C') {
                    addToPane(x, y, items.get(36), pane);
                }
                else if (world[x][y] == 'D') {
                    addToPane(x, y, items.get(37), pane);
                }
                else if (world[x][y] == 'E') {
                    addToPane(x, y, items.get(38), pane);
                }
                else if (world[x][y] == 'F') {
                    addToPane(x, y, items.get(39), pane);
                }
                else if (world[x][y] == 'G') {
                    addToPane(x, y, items.get(40), pane);
                }
                else if (world[x][y] == 'H') {
                    addToPane(x, y, items.get(41), pane);
                }
                else if (world[x][y] == 'I') {
                    addToPane(x, y, items.get(42), pane);
                }
                else if (world[x][y] == 'J') {
                    addToPane(x, y, items.get(43), pane);
                }
                else if (world[x][y] == 'K') {
                    addToPane(x, y, items.get(44), pane);
                }
                else if (world[x][y] == 'L') {
                    addToPane(x, y, items.get(45), pane);
                }
                else if (world[x][y] == 'V') {
                    addToPane(x, y, items.get(46), pane);
                }
                else if (world[x][y] == 'W') {
                    addToPane(x, y, items.get(47), pane);
                }
                else if (world[x][y] == '%') {
                    addToPane(x, y, items.get(48), pane);
                }
                else if (world[x][y] == '#') {
                    addToPane(x, y, items.get(49), pane);
                }
                else if (world[x][y] == '!') {
                    addToPane(x, y, items.get(50), pane);
                }
                else if (world[x][y] == '$') {
                    addToPane(x, y, items.get(51), pane);
                }
                else if (world[x][y] == '§') {
                    addToPane(x, y, items.get(52), pane);
                }
                else if (world[x][y] == '1') {
                    addToPane(x, y, items.get(53), pane);
                }
                else {
                    addToPane(x, y, items.get(0), pane);
                }
            }
        }

        return pane ;
    }

    private void readObjects() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File("./config/config.xml");
            configFile = builder.parse(file);
            configFile.getDocumentElement().normalize();
        } catch (Exception e) {
            e.printStackTrace();
        }

        getAllObjects();
    }

    private void getAllObjects() {
        objectList = configFile.getElementsByTagName("element");

        IntStream.range(0, objectList.getLength())
                .forEach(idx ->{
                    org.w3c.dom.Node nNode = objectList.item(idx);

                    if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        Item item = new Item();
                        item.setId(Integer.parseInt("0" + eElement.getElementsByTagName("id").item(0).getTextContent()));
                        item.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                        item.setType(eElement.getElementsByTagName("type").item(0).getTextContent());
                        item.setImage(eElement.getElementsByTagName("image").item(0).getTextContent());
                        item.setWalkable(Boolean.parseBoolean(eElement.getElementsByTagName("walkable").item(0).getTextContent()));
                        item.setPortable(Boolean.parseBoolean(eElement.getElementsByTagName("portable").item(0).getTextContent()));
                        item.setNpc(Boolean.parseBoolean(eElement.getElementsByTagName("npc").item(0).getTextContent()));
                        item.setInformation(eElement.getElementsByTagName("information").item(0).getTextContent());
                        items.add(item);
                    }
                });
    }
}
