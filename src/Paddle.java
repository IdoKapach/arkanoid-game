// 322384561 - IDO KAPACH
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * The type Paddle.
 */
public class Paddle implements Sprite, Collidable {
    private static final int ONE_STEP = 10, DEFAULT_WIDTH = 90, HEIGHT = 20,
            SCREEN_WIDTH = 800, PADDLE_Y_POSITION = 550, FRAME_WIDTH = 30;
    private int width;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private Color color;

    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard {biuoop.KeyboardSensor} the keyboard.
     */
    public Paddle(biuoop.KeyboardSensor keyboard) {
        rect = new Rectangle(new Point(SCREEN_WIDTH / 2 - width / 2, PADDLE_Y_POSITION), width, HEIGHT);
        this.keyboard = keyboard;
    }

    /**
     * Instantiates a new Paddle.
     *
     * @param keyboard the keyboard
     * @param width    the width
     * @param color    the color
     */
    public Paddle(biuoop.KeyboardSensor keyboard, int width, Color color) {
        rect = new Rectangle(new Point(SCREEN_WIDTH / 2 - width / 2, PADDLE_Y_POSITION), width, HEIGHT);
        this.keyboard = keyboard;
        this.width = width;
        this.color = color;
    }
    // Sprite methods
    @Override
    public void timePassed() {
        moveLeft();
        moveRight();
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        rect.drawRec(d);
    }
    // Collidable methods
    @Override
    public Rectangle getCollisionRectangle() {
        return rect;
    }
    private Velocity specialColission(Point collisionPoint, Velocity currentVelocity) {
        // divide the up edge of the paddle to 5 parts
        double endOfArea1 = rect.getUpperLeft().getX() + width / 5;
        double endOfArea2 = rect.getUpperLeft().getX() + (2 * width) / 5;
        double endOfArea3 = rect.getUpperLeft().getX() + (3 * width) / 5;
        double endOfArea4 = rect.getUpperLeft().getX() + (4 * width) / 5;
        double endOfArea5 = rect.getUpperLeft().getX() + width;
        double collisionX = collisionPoint.getX();
        double angle = Velocity.angleFromVelocity(currentVelocity) + 180,
                speed = Velocity.speedFromVelocity(currentVelocity);
        /* If the ball hits region 3, it should keep its horizontal direction and only change
         its vertical one. However, if we hit region 1, the ball should bounce back with
         an angle of 300 degrees, regardless of where it came from. Remember, angle 0 = 360 is "up".
         Similarly, for region 2 it should bounce back 330 degrees,
         for region 4 it should bounce in 30 degrees, and for region 5 in 60 degrees. */
        if (Double.compare(endOfArea1, collisionX) >= 0) {
            angle += 300;
        } else if (Double.compare(endOfArea2, collisionX) >= 0) {
            angle += 330;
        } else if (Double.compare(endOfArea3, collisionX) >= 0) {
            return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
        } else if (Double.compare(endOfArea4, collisionX) >= 0) {
            angle += 30;
        } else if (Double.compare(endOfArea5, collisionX) >= 0) {
            angle += 60;
        }
        // return the velocity made of the speed and updated angle.
        angle = angle % 360;
        return Velocity.fromAngleAndSpeed(angle, speed);
    }
    @Override
    public Velocity hit(Point collisionPoint, Velocity currentVelocity, Ball hitter) {
        // getting the collision case: 1 - if it is a horizontal collision,
        // 2 - if it is a vertical collision, 3 - if it is both collisions (if the collision
        // is on the corner of the paddle) , 0 - if there was no collision.
        int hitCase = rect.kindOfCollision(collisionPoint, currentVelocity);
        switch (hitCase) {
            case 1:
                // changing the horizontal velocity.
                return new Velocity(-1 * currentVelocity.getDx(), currentVelocity.getDy());
            case 2:
                // changing the vertical velocity - modified - now the ball change direction regularly.
                return new Velocity(currentVelocity.getDx(), -1 * currentVelocity.getDy());
            case 3:
                // changing both horizontal and vertical velocities.
                return new Velocity(-1 * currentVelocity.getDx(), -1 * currentVelocity.getDy());
            default:
                // doesn't change the velocity.
                return currentVelocity;
        }
    }

    /**
     * Move one step left if the user pressed on the "left" bottom.
     */
    public void moveLeft() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            Point newRectPlace = null;
            // if it's possible to do one step left (-10) it would do it. otherwise, it will be attached
            // to the left corner.
            if (Double.compare(rect.getUpperLeft().getX(), 40) >= 0) {
                newRectPlace = new Point(rect.getUpperLeft().getX() - ONE_STEP, rect.getUpperLeft().getY());
            } else {
                newRectPlace = new Point(30, rect.getUpperLeft().getY());
            }
            rect = new Rectangle(newRectPlace, width, HEIGHT);
        }
    }

    /**
     * Move one step right if the user pressed on the "right" bottom.
     */
    public void moveRight() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            Point newRectPlace = null;
            // if it's possible to do one step right (10) it would do it. otherwise, it will be attached
            // to the right corner.
            if (Double.compare(rect.getUpperLeft().getX() + ONE_STEP + rect.getWidth(),
                    SCREEN_WIDTH - FRAME_WIDTH) <= 0) {
                newRectPlace = new Point(rect.getUpperLeft().getX() + ONE_STEP, rect.getUpperLeft().getY());
            } else {
                newRectPlace = new Point(SCREEN_WIDTH - rect.getWidth() - FRAME_WIDTH, rect.getUpperLeft().getY());
            }
            rect = new Rectangle(newRectPlace, width, HEIGHT);
        }
    }


    /**
     * Add this paddle to a game.
     *
     * @param g {Game} the game.
     */
    public void addToGame(GameLevel g) {
        return;
    }

}