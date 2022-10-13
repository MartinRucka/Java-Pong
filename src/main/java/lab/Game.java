package lab;

import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Game {
    private final double width;
    private final double height;

    private final Bat bat1;

    private final Bat bat2;

    private final Ball ball;

    public Game(double width, double height) {
        this.width = width;
        this.height = height;
        this.ball = new Ball(this, new Point2D(100, 50), new Point2D(100, -100), 20);
        this.bat1 = new Bat(this, 30, 100, 20, 100);
        this.bat2 = new Bat(this, 750, 150, 20, 100);

    }

    public Point2D transform2Canvas(Point2D point) {
        return new Point2D(point.getX(), height - point.getY());
    }

    public void simulate(double deltaT) {
        this.ball.simulate(deltaT);
        this.bat1.simulate(deltaT);
        this.bat2.simulate(deltaT);
        if (bat1.getBB().intersects(ball.getBB())) {
            ball.hitLeftBat(deltaT);
        }

        if (bat2.getBB().intersects(ball.getBB())) {
            ball.hitRightBat(deltaT);
        }
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, 800, 400);
        this.ball.draw(gc);
        gc.fillRect(0, 0, 800, 20); //horní okraj
        gc.fillRect(0, 380, 800, 20); //spodní okraj
        gc.setFont(Font.font("Sans Serif", FontWeight.BOLD, 80));
        gc.fillText("0", 310, 90); // body
        gc.fillText("0", 440, 90); // body
        for (int i = 20; i < 400; i += 24) {
            gc.fillRect(393.25, i, 12, 12); // střed
        }
        this.bat1.draw(gc);
        this.bat2.draw(gc);
    }

    public Rectangle2D getBBUp() {
        return new Rectangle2D(0, 0, 800, 20);
    }

    public Rectangle2D getBBDown() {
        return new Rectangle2D(0, 380, 800, 20);
    }


    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}