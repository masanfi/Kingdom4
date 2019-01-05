package game;

import javafx.geometry.Point2D;
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

/**
 *
 * Toolkit to initialize the world
 * Copyright (c) 2018 Fantastic 4 Studios. All Rights Reserved.
 * @author Fabian Schmidt
 * @author Martin Sanfilippo
 * @author Boris Bischoff
 * @version 0.9
 *
 */
 
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
    private ArrayList<IEvent> collisions;
    private ArrayList<Trigger> triggers;
private Boolean provTrigger = false;
    
    public World(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

        this.items = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        this.collisions = new ArrayList<>();
        this.triggers = new ArrayList<>();
        
        tileSize = gameEngine.getTileSize();
        amountHorizontalTiles = gameEngine.getAmountHorizontalTiles();
        amountVerticalTiles = gameEngine.getAmountVerticalTiles();

        readObjects();
        //items.forEach(System.out::println);

        this.background = createBackground();
        gameEngine.setBackground(this.background);

    }

    /**
     * This sets the camera to the camera rectangle.
     */
    public void setCamera() {
        gameEngine.getBackground().setClip(gameEngine.getCamera());
        gameEngine.getBackground().translateXProperty().bind(gameEngine.getCamera().xProperty().multiply(-1));
        gameEngine.getBackground().translateYProperty().bind(gameEngine.getCamera().yProperty().multiply(-1));
    }

    /**
     * This adds obstacles to the Entitiy pane.
     */
    private void setObstacles() {
        gameEngine.getObstacle().forEach(obstacle->{
            gameEngine.getEntities().getChildren().add(obstacle);
        });
    }

    public void buildWorld() {
        this.setObstacles();
    }

    /**
     * This adds an item to different lists, depending on the nature of the object to be added.
     * @param x
     * @param y
     * @param item
     * @param pane
     */
    private void addToPane(int x, int y, Item item, Pane pane) {
    	Collision collision = new Collision();
    	Trigger trigger = new Trigger();
    	ImageView image = new ImageView(new Image(item.getImage(), gameEngine.getTileSize(), gameEngine.getTileSize(), true, false));
    	
    	this.backgroundCollection.add(image);

    	if(!provTrigger) {
    	provTrigger = true;
	        //Provisorisches Finale
	    	Item finalItem = new Item();
	    	finalItem.setId(999);
	    	finalItem.setName("Finale");
	    	finalItem.setType("test");
	        Trigger finale = new Trigger(finalItem, new Point2D(1972,1600), item.isWalkable(), item.isPortable(), item.isNpc());
	        Rectangle finaleObstacle = new Rectangle(1972,1600, gameEngine.getTileSize(), gameEngine.getTileSize());
	        finaleObstacle.setFill(Color.PURPLE);
	        this.obstacles.add(finaleObstacle);
	        this.triggers.add(finale);
	        gameEngine.setObstacle(this.obstacles);
	        gameEngine.setTriggerObject(this.triggers);
	        //Provisorisches Finale
	        
	        //Provisorisches Trigger Feld um den Knight zu verschieben
	        Item testItem = new Item();
	        testItem.setId(666);
	        testItem.setName("Test");
	        testItem.setType("test");
	        Trigger test = new Trigger(testItem, new Point2D(300,300), item.isWalkable(), item.isPortable(), item.isNpc());
	        Rectangle testObstacle = new Rectangle(200,300, gameEngine.getTileSize(), gameEngine.getTileSize());
	        testObstacle.setFill(Color.PURPLE);
	        this.obstacles.add(testObstacle);
	        this.triggers.add(test);
	        gameEngine.setObstacle(this.obstacles);
	        gameEngine.setTriggerObject(this.triggers);
	        //Provisorisches Trigger Feld um den Knight zu verschieben
    	}
        
        if (item.isNpc()) {
            Rectangle obstacle = new Rectangle(y * gameEngine.getTileSize(), x * gameEngine.getTileSize(), gameEngine.getTileSize(), gameEngine.getTileSize());
            trigger = new Trigger(item, new Point2D(y * gameEngine.getTileSize(), x * gameEngine.getTileSize()), item.isWalkable(), item.isPortable(), item.isNpc());
            obstacle.setFill(Color.PURPLE);
            this.collisions.add(trigger);
            this.obstacles.add(obstacle);
            this.triggers.add(trigger);
            gameEngine.setObstacle(this.obstacles);
            gameEngine.setTriggerObject(this.triggers);
            gameEngine.setCollisionObject(this.collisions);
        }
        else if (item.isPortable()) {
            Rectangle obstacle = new Rectangle(y * gameEngine.getTileSize(), x * gameEngine.getTileSize(), gameEngine.getTileSize(), gameEngine.getTileSize());
            trigger = new Trigger(item, new Point2D(y * gameEngine.getTileSize(), x * gameEngine.getTileSize()), item.isWalkable(), item.isPortable(), item.isNpc());
            obstacle.setFill(Color.CYAN);
            this.obstacles.add(obstacle);
            this.triggers.add(trigger);
            gameEngine.setObstacle(this.obstacles);
            gameEngine.setTriggerObject(this.triggers);
        }
        else if (!item.isWalkable()) {
            Rectangle obstacle = new Rectangle(y * gameEngine.getTileSize(), x * gameEngine.getTileSize(), gameEngine.getTileSize(), gameEngine.getTileSize());
            collision = new Collision(item, new Point2D(y * gameEngine.getTileSize(), x * gameEngine.getTileSize()));
            
            obstacle.setFill(Color.RED);
            this.obstacles.add(obstacle);
            this.collisions.add(collision);
            gameEngine.setObstacle(this.obstacles);
            gameEngine.setCollisionObject(this.collisions);
        }else if(item.getType().equals("terrain")){
        	
        	//System.out.println(item.getName());
        	
        	trigger = new Trigger(item, new Point2D(y * gameEngine.getTileSize(), x * gameEngine.getTileSize()), item.isWalkable(), item.isPortable(), item.isNpc());
            this.triggers.add(trigger);
        }

        this.backgroundCollection.get(this.backgroundCollection.size() - 1).relocate(y * tileSize, x * tileSize);

        if(handleSpecialItems(item.getName(),image,trigger,x,y)) {
        	pane.getChildren().add(this.backgroundCollection.get(this.backgroundCollection.size() - 1));
        }

        
    }
    
    /**
     * This mehode handels Special items
     * @param x
     * @param y
     * @param itemName
     * @param image
     * @param trigger
     */
    private Boolean handleSpecialItems(String itemName, ImageView image,Trigger trigger,int x,int y) {
    	
    	//Prepare the change of the knight, create Knight2
    	if(itemName.equals("knight2")) {
    		gameEngine.setKnight2(this.backgroundCollection.get(this.backgroundCollection.size() - 1));
    		this.collisions.remove(trigger);
        	this.backgroundCollection.remove(image);
        	this.triggers.remove(trigger);
        	
        	ImageView green = new ImageView(new Image(items.get(0).getImage(), gameEngine.getTileSize(), gameEngine.getTileSize(), true, false));
        	green.relocate(y * tileSize, x * tileSize);
      	  	this.backgroundCollection.add(green);

    	}//Prepare the change of the knight, create Knight and Footpath
    	else if (itemName.equals("knight")) {
    		gameEngine.setKnightCollision(trigger);
        	gameEngine.setKnight(this.backgroundCollection.get(this.backgroundCollection.size() - 1));
        	
        	ImageView fph = new ImageView(new Image(items.get(15).getImage(), gameEngine.getTileSize(), gameEngine.getTileSize(), true, false));
        	fph.relocate(y * tileSize, x * tileSize);
        	
        	gameEngine.setFpH(fph);
    	}
    	else if (itemName.equals("key")) {
    		gameEngine.setKey(this.backgroundCollection.get(this.backgroundCollection.size() - 1));
    		gameEngine.setKeyImage(image.getImage());
    		ImageView green = new ImageView(new Image(items.get(0).getImage(), gameEngine.getTileSize(), gameEngine.getTileSize(), true, false));
        	green.relocate(y * tileSize, x * tileSize);
        	gameEngine.setKeyGreen(green);
    	}
    	
    	return true;
    	
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    /**
     * This creates the background based on the level provided in the Level class.
     * @return
     */
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
                else if (world[x][y] == '2') {
                    addToPane(x, y, items.get(26), pane);
                }
                else if (world[x][y] == '3') {
                    addToPane(x, y, items.get(27), pane);
                }
                else if (world[x][y] == '4') {
                    addToPane(x, y, items.get(28), pane);
                }
                else if (world[x][y] == '5') {
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
                else if (world[x][y] == 'M') {
                    addToPane(x, y, items.get(54), pane);
                }
                else if (world[x][y] == 'N') {
                    addToPane(x, y, items.get(55), pane);
                }
                else if (world[x][y] == 'O') {
                    addToPane(x, y, items.get(56), pane);
                }
                else if (world[x][y] == 'P') {
                    addToPane(x, y, items.get(57), pane);
                }
                else if (world[x][y] == 'a') {
                    addToPane(x, y, items.get(58), pane);
                }
                else if (world[x][y] == 'c') {
                    addToPane(x, y, items.get(59), pane);
                }
                else if (world[x][y] == 'x') {
                    addToPane(x, y, items.get(60), pane);
                }
                else if (world[x][y] == 'y') {
                    addToPane(x, y, items.get(61), pane);
                }
                else if (world[x][y] == 'z') {
                    addToPane(x, y, items.get(62), pane);
                }
                else if (world[x][y] == 'w') {
                    addToPane(x, y, items.get(63), pane);
                }
                else if (world[x][y] == '6') {
                    addToPane(x, y, items.get(64), pane);
                }
                else if (world[x][y] == 'd') {
                    addToPane(x, y, items.get(65), pane);
                }
                else {
                    addToPane(x, y, items.get(0), pane);
                }
            }
        }

        return pane ;
    }

    /**
     * This reads all objects from an XML file.
     */
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

    /**
     * This processes all objects read in in the readObjects method.
     */
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
