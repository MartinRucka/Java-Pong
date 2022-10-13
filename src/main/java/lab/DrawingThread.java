package lab;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class DrawingThread extends AnimationTimer {

	private final GraphicsContext gc;
	private final Game game;

	private long lastTime;
	private double x = 50;
	private double y = 75;
	private int speed = 25;

	public DrawingThread(Canvas canvas) {
		this.gc = canvas.getGraphicsContext2D();
		this.game = new Game(canvas.getWidth(), canvas.getHeight());

	}

	/**
	  * Draws objects into the canvas. Put you code here. 
	 */
	@Override
	public void handle(long now) {
		if (lastTime > 0) {
			double deltaT = (now - lastTime) / 1e9;
			// call simulate on game
			game.simulate(deltaT);

		}
		//call draw on game
		game.draw(gc);
		lastTime= now;

	}


}
