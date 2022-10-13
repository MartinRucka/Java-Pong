package lab;
import java.io.*;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball {
    private Game game;
    private Point2D position;
    private Point2D speed;
    private final double size;
    private boolean right = true;
    private boolean down = true;

    public Ball(Game game, Point2D position, Point2D speed, double size) {
        this.game = game;
        this.position = position;
        this.speed = speed;
        this.size = size;
    }

    public void draw(GraphicsContext gc)
    {
        gc.setFill(Color.GREY);
        Point2D p = game.transform2Canvas(position);
        gc.fillOval(p.getX(), p.getY(), size, size);
    }

    public void simulate(double deltaT) {

        if(right == true && down == true){

            MoveRightDown(deltaT);
        }
        if(right == true && down == false){

            MoveRightUp(deltaT);
        }
        if(right == false && down == true){

            MoveLeftDown(deltaT);
        }
        if(right == false && down == false){
            MoveLeftUp(deltaT);
        }

        // MoveLeftUp(deltaT);
        if (position.getX() > 780){
            //   right = false;
            if(down = false){
                MoveLeftDown(deltaT);
            }
            else {
                MoveLeftUp(deltaT);
            }
        }
        if (position.getX() < 1) {
            //   right = true;
            if (down = false) {
                MoveRightDown(deltaT);
            } else {
                MoveRightUp(deltaT);

            }
        }


        if(game.getBBDown().intersects(this.getBB())){
            //  down = false;
            if(right = false){
                MoveLeftUp(deltaT);
            }
            else {
                MoveRightUp(deltaT);
            }
        }
        if(game.getBBUp().intersects(this.getBB())) {
              down = true;
            if(right = false){
                MoveLeftDown(deltaT);
            }
            else {
                MoveRightDown(deltaT);
            }
        }
        position = new Point2D((position.getX() + game.getWidth()) % game.getWidth(), (position.getY() + game.getHeight()) % game.getHeight());
    }

    public void MoveRightUp(double deltaT){
        speed = new Point2D(100, 100);
        position = position.add(speed.multiply(deltaT));
        right=true;
        down=false;
    }

    public void MoveRightDown(double deltaT){
        speed = new Point2D(100, -100);
        position = position.add(speed.multiply(deltaT));
        right = true;
        down = true;
    }

    public void MoveLeftUp(double deltaT){
        speed = new Point2D(100, -100);
        position = position.add(speed.multiply(-deltaT));
        right = false;
        down = false;
    }

    public void MoveLeftDown(double deltaT){
        speed = new Point2D(100, 100);
        position = position.add(speed.multiply(-deltaT));
        right = false;
        down=true;
    }

    public Rectangle2D getBB() {
        Point2D p = game.transform2Canvas(position);
        return new Rectangle2D(p.getX(), p.getY(), size, size);
    }

    public void hitRightBat(double deltaT) {
        if (right == true) {
            position = position.add(speed.multiply(deltaT));
        }
        else {
            position = position.add(speed.multiply(-deltaT));
        }
        position = new Point2D((position.getX() + game.getWidth()) % game.getWidth(), (position.getY() + game.getHeight()) % game.getHeight());
        right = false;

    }

    public void hitLeftBat(double deltaT) {
        if (right == true) {
            position = position.add(speed.multiply(deltaT));
        }
        else {
            position = position.add(speed.multiply(-deltaT));
        }
        position = new Point2D((position.getX() + game.getWidth()) % game.getWidth(), (position.getY() + game.getHeight()) % game.getHeight());
        right = true;

    }
}
