package game;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class Control {
    private Node hero;
    private Node hero_hover;
    private double width;
    private double height;
    private Text text;

    private DataModel model = new DataModel(width, height);

    Control(Node hero, Node hero_hover, double width, double height, Text text) {
        this.hero = hero;
        this.hero_hover = hero_hover;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    void moveHeroTo(double x, double y) {
        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;

        if (x - cx >= 0 &&
                x + cx <= width &&
                y - cy >= 0 &&
                y + cy <= height) {

            hero.relocate(x - cx, y - cy);
            hero_hover.relocate(x - cx, y - cy);
        }
    }

    void moveHeroBy(int dx, int dy) {
        if (dx == 0 && dy == 0) return;

        final double cx = hero.getBoundsInLocal().getWidth()  / 2;
        final double cy = hero.getBoundsInLocal().getHeight() / 2;

        double x = cx + hero.getLayoutX() + dx;
        double y = cy + hero.getLayoutY() + dy;
        //System.out.println(x +" " + y);

        String check = model.checkRestricted((int)x,(int)y);

        if(check != null) {

            text.setText(check);
            text.setFill(Color.WHITE);
            text.setFont(Font.font("Verdana", 20));
            text.relocate(0, height-30);

            //System.out.println("Hier " + x + " " + y + " steht etwas im Weg");

        } else {
            text.relocate(-100, -100);
            moveHeroTo(x, y);
        }
    }

}
