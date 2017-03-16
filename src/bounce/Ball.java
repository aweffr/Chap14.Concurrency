package bounce;
import java.awt.geom.*;
import java.util.Random;

import static java.lang.Math.abs;

/**
 * A ball that moves and bounces off the edges of a rectangle.
 * Created by ZouLe on 2017/3/16.
 */
public class Ball {
    public static final int XSIZE = 15;
    public static final int YSIZE = 15;
    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;

    public Ball(Rectangle2D bounds){
        Random r = new Random();
        x = (r.nextDouble() + 0.01) * bounds.getMaxX();
        y = (r.nextDouble() + 0.01) * bounds.getMaxY();
        dx = 2 * r.nextDouble() + 0.5;
        dy = 2 * r.nextDouble() + 0.5;
        if(r.nextBoolean())   dx = -dx;
        if(r.nextBoolean())   dy = -dy;
    }

    /**
     * Moves the ball to the next position, reversing direction if it hits one of the edges.
     */
    public void move(Rectangle2D bounds){
        x += dx;
        y += dy;
        if(x < bounds.getMinX()){
            x = bounds.getMinX();
            dx = -dx;
        }
        if(x + XSIZE >= bounds.getMaxX()){
            x = bounds.getMaxX() - XSIZE;
            dx = -dx;
        }
        if(y < bounds.getMinY()){
            y = bounds.getMinY();
            dy = -dy;
        }
        if(y + YSIZE >= bounds.getMaxY()){
            y = bounds.getMaxY() - YSIZE;
            dy = -dy;
        }
    }

    /**
     * Gets the shape of the ball at its current position.
     */
    public Ellipse2D getShape(){
        return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
    }
}
