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
    private int x;
    
    private Pane background;

    private ArrayList<ImageView> backgroundCollection;
    private ArrayList<Item> items;

    private Document configFile;
    private NodeList objectList;
    private Item foundItem;
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
    
    private Item findItem(char symbol) {
    	
    	items.forEach(item ->{
    		if(item.getSymbol() == symbol) {
    			this.foundItem = item;
    		}
    	});
    	
    	Item temp = this.foundItem;
    	this.foundItem = null;
    	return temp;
    }
    
    private void handleNotShownItems() {
    	
    	items.forEach(item->{
    	
    		if(item.getType().equals("trophy")) {
    			if(item.getName().equals("clumsy")) {
    				ImageView image = new ImageView(new Image(item.getImage(), 50, 50, true, false));
        			gameEngine.setClumsy(image);
    				
    			}else if(item.getName().equals("confused")) {
    				ImageView image = new ImageView(new Image(item.getImage(), 50, 50, true, false));
        			gameEngine.setConfused(image);
    				
    			}else if(item.getName().equals("influencer")) {
    				ImageView image = new ImageView(new Image(item.getImage(), 50, 50, true, false));
        			gameEngine.setInfluencer(image);
    				
    			}else if(item.getName().equals("stoney")) {
    				ImageView image = new ImageView(new Image(item.getImage(), 50, 50, true, false));
        			gameEngine.setStoney(image);
    				
    			}else if(item.getName().equals("treehugger")) {
    				ImageView image = new ImageView(new Image(item.getImage(), 50, 50, true, false));
        			gameEngine.setTreehugger(image);
    			}
    			
    		}else if (item.getName().equals("sword")) {
    			ImageView image = new ImageView(new Image(item.getImage(), gameEngine.getTileSize(), gameEngine.getTileSize(), true, false));
    			gameEngine.setSword(image);
    			
    		}else if (item.getName().equals("fish")) {
    			ImageView image = new ImageView(new Image(item.getImage(), gameEngine.getTileSize(), gameEngine.getTileSize(), true, false));
    			gameEngine.setFish(image);
    		}
    		
    	});
    	
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
    		gameEngine.setKnight2Collision(trigger);
    		this.collisions.remove(trigger);
        	this.backgroundCollection.remove(image);
        	this.triggers.remove(trigger);
        	
        	ImageView green = new ImageView(new Image(findItem((char)32).getImage(), gameEngine.getTileSize(), gameEngine.getTileSize(), true, false));
        	green.relocate(y * tileSize, x * tileSize);
      	  	this.backgroundCollection.add(green);

    	}//Prepare the change of the knight, create Knight and Footpath
    	else if (itemName.equals("knight")) {
    		gameEngine.setKnightCollision(trigger);
        	gameEngine.setKnight(this.backgroundCollection.get(this.backgroundCollection.size() - 1));
        	
        	ImageView fph = new ImageView(new Image(findItem((char)102).getImage(), gameEngine.getTileSize(), gameEngine.getTileSize(), true, false));
        	fph.relocate(y * tileSize, x * tileSize);
        	
        	gameEngine.setFpH(fph);
    	}else if (itemName.equals("key")) {
    		gameEngine.setKey(image);
    		ImageView green = new ImageView(new Image(findItem((char)32).getImage(), gameEngine.getTileSize(), gameEngine.getTileSize(), true, false));
        	green.relocate(y * tileSize, x * tileSize);
        	gameEngine.setKeyGreen(green);
    	}else if(itemName.equals("rabbit")) {
    		gameEngine.setRabbit(image);
    		ImageView green = new ImageView(new Image(findItem((char)32).getImage(), gameEngine.getTileSize(), gameEngine.getTileSize(), true, false));
        	green.relocate(y * tileSize, x * tileSize);
        	gameEngine.setRabbitGreen(green);
    		
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

        for (x = 0; x < amountVerticalTiles; x++) {
            for (y = 0; y < amountHorizontalTiles; y++) {
            	//System.out.println(world[this.x][this.y]);
            	
            	items.stream()
            	.filter(s -> world[this.x][this.y] == s.getSymbol())
            	.forEach(i -> {
            		//System.out.println(i);
            		addToPane(this.x, this.y, i, pane);
            	}); 
            }
        }
        this.handleNotShownItems();
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
                        //item.setId(Integer.parseInt("0" + eElement.getElementsByTagName("id").item(0).getTextContent()));
                        item.setSymbol((char)Integer.parseInt("0" + eElement.getElementsByTagName("symbol").item(0).getTextContent()));
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
