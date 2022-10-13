package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Bat {
    private Game game;
    private double x;
    private double y;
    private final double width;
    private final double height;

    private boolean direction = true;

    public Bat(Game game, double x, double y, double width, double height) {
        this.game = game;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public void draw(GraphicsContext gc)
    {
        gc.setFill(Color.GREY);
        gc.fillRect(x,y,width,height);
    }

    public void simulate(double deltaT) {
        if (direction == true){
            this.y = y + 1;
        }
        if (y == 280){
            direction = false;
        }
        if (direction == false){
            this.y = y - 1;
        }
        if (y == 20){
            direction = true;
        }

    }

    public Rectangle2D getBB() {
        return new Rectangle2D(x, y, width, height);
    }
}
