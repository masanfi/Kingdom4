package game;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class DataModel {

    private Document configFile;
    private double width;
    private double height;

    DataModel(double width, double height) {
        this.width = width;
        this.height = height;
    }

    private final ArrayList<Item> items = new ArrayList<>();
    private final ArrayList<Restricted> restrictedItems = new ArrayList<>();
    private final List<Point2D> coordinates = new ArrayList<>();

    public ArrayList<Item> getItemList() {
        return items;
    }

    public ArrayList<Restricted> getRestrictedItemsList() {
        return restrictedItems;
    }

    public List<Point2D> getCoordinates() {
        return coordinates;
    }

    Node createMenu(String wallImage) {
        Node menu = new ImageView(new Image(wallImage));
        for(int i=0; i < width; i++) {
            coordinates.add(new Point2D(i,(int)height-50));
            coordinates.add(new Point2D(i,(int)height-49));
            coordinates.add(new Point2D(i,(int)height-48));
        }
        menu.relocate(0, height-35);
        return menu;
    }

    Group createItems(Group kingdom) {
        readObjects();

        items.forEach(System.out::println);

        items.stream()
                //.filter(s->s.getType().contains("tree"))
                .forEach(item->{
                    int freq = ThreadLocalRandom.current().nextInt(item.getMinAmount(), item.getMaxAmount() + 1);
                    for(int i=0;i < freq;i++) {
                        placeItem(kingdom,item);
                    }
                });

        return kingdom;

    }

    private void placeItem(Group kingdom, Item item) {
        Node imageItem;
        Restricted row;

        int X;
        int Y;

        if(item.getFixX() == 0) {
            X = ThreadLocalRandom.current().nextInt(item.getXEnd(), ((int)width - item.getXEnd()) + 1);
        } else {
            X = item.getFixX();
        }

        if(item.getFixY() == 0) {
            Y = ThreadLocalRandom.current().nextInt(item.getYEnd(), ((int)height - item.getYEnd()) + 1);
        }else {
            Y = item.getFixY();
        }
        //(System.out.println(item.getWalk());

        if(item.getWalkable().equals("no")) {
            //System.out.println("darf nicht durchquert werden");
            for(int i=X+item.getXBegin();i<=X+item.getXEnd();i++) {
                for(int k=Y+item.getYBegin();k<=Y+item.getYEnd();k++) {
                    coordinates.add(new Point2D(i,k));
                    row = new Restricted(item.getInfo(),new Point2D(i,k));
                    restrictedItems.add(row);
                }
            }
        }
        //System.out.println(cord.toString());

        imageItem = new ImageView(new Image(item.getIcon()));
        imageItem.relocate(X, Y);
        kingdom.getChildren().addAll(imageItem);
    }

    String checkRestricted(int x, int y) {
        Point2D checkPoint = new Point2D(x,y);
        //System.out.println(checkPoint.toString());

        String check = restrictedItems.stream()
                .filter(elem -> elem.getPoint().equals(checkPoint))
                .findAny()
                .map(Restricted::getInfo)
                .orElse(null);


        //System.out.println(check);
        if(check != null) {

            return check;
        }else {
            return null;
        }
    }

    private boolean readObjects() {
        try {  	
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            File file = new File("config/config.xml");
            configFile = builder.parse(file);
            configFile.getDocumentElement().normalize();
        } catch (Exception ex) {
        	
            System.out.println("Error: " + ex);
            return false;
        }
        getAllObjects();
        return true;

    }

    private void getAllObjects(){
    	
        NodeList objectList = configFile.getElementsByTagName("element");

        IntStream.range(0, objectList.getLength()-1)
                .forEach(idx ->{
                    org.w3c.dom.Node nNode = objectList.item(idx);

                    if (nNode.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        Item objekt = new Item();
                        objekt.setId( Integer.parseInt("0" + eElement.getElementsByTagName("id").item(0).getTextContent()));
                        objekt.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                        objekt.setType(eElement.getElementsByTagName("type").item(0).getTextContent());
                        objekt.setIcon(eElement.getElementsByTagName("iconPath").item(0).getTextContent());
                        objekt.setWalkable(eElement.getElementsByTagName("walkable").item(0).getTextContent());
                        objekt.setPortable(eElement.getElementsByTagName("portable").item(0).getTextContent());
                        objekt.setMinAmount(Integer.parseInt("0" + eElement.getElementsByTagName("minAmount").item(0).getTextContent()));
                        objekt.setMaxAmount(Integer.parseInt("0" + eElement.getElementsByTagName("maxAmount").item(0).getTextContent()));
                        objekt.setInfo(eElement.getElementsByTagName("info").item(0).getTextContent());
                        objekt.setXBegin(Integer.parseInt("0" + eElement.getElementsByTagName("xBegin").item(0).getTextContent()));
                        objekt.setXEnd(Integer.parseInt("0" + eElement.getElementsByTagName("xEnd").item(0).getTextContent()));
                        objekt.setYBegin(Integer.parseInt("0" + eElement.getElementsByTagName("yBegin").item(0).getTextContent()));
                        objekt.setYEnd(Integer.parseInt("0" + eElement.getElementsByTagName("yEnd").item(0).getTextContent()));
                        objekt.setFixX(Integer.parseInt("0" + eElement.getElementsByTagName("fixX").item(0).getTextContent()));
                        objekt.setFixY(Integer.parseInt("0" + eElement.getElementsByTagName("fixY").item(0).getTextContent()));
                        items.add(objekt);
                    }

                });
    }

}
