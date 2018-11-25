package game;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

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

    public World(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        this.items = new ArrayList<>();
        this.obstacles = new ArrayList<>();


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
            obstacle.setFill(Color.RED);
            this.obstacles.add(obstacle);
            gameEngine.setObstacle(this.obstacles);
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
                else if (world[x][y] == 'd') {
                    addToPane(x, y, items.get(6), pane);
                }
                else if (world[x][y] == 'b') {
                    addToPane(x, y, items.get(7), pane);
                }
                else if (world[x][y] == 'q') {
                    addToPane(x, y, items.get(8), pane);
                }
                else if (world[x][y] == 'p') {
                    addToPane(x, y, items.get(9), pane);
                }
                else if (world[x][y] == '-') {
                    addToPane(x, y, items.get(2), pane);
                }
                else if (world[x][y] == 'i') {
                    addToPane(x, y, items.get(3), pane);
                }
                else if (world[x][y] == '=') {
                    addToPane(x, y, items.get(4), pane);
                }
                else if (world[x][y] == 'I') {
                    addToPane(x, y, items.get(5), pane);
                }
                else if (world[x][y] == 'f') {
                    addToPane(x, y, items.get(12), pane);
                }
                else if (world[x][y] == '[') {
                    addToPane(x, y, items.get(10), pane);
                }
                else if (world[x][y] == ']') {
                    addToPane(x, y, items.get(11), pane);
                }
                else if (world[x][y] == 's') {
                    addToPane(x, y, items.get(13), pane);
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
