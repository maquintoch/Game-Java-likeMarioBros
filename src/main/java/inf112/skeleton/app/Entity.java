package inf112.skeleton.app;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity implements IDrawable, IUpdateable {

    private IDrawBehavior drawHandler;

    private Position position;
    private Rectangle boundingBox;

    private boolean falling, jumping;

    public Entity(GraphicsContext context) {
        drawHandler = new DrawColorBehavior(context);
    }

    @Override
    public void Draw() {
        drawHandler.Draw(position, boundingBox);
    }

    @Override
    public abstract void Update();

    public Position getPosition() {
    	return position;
    }
    
    public void setPosition(double x, double y) {
    	this.position.x = x;
    	this.position.y = y;
    }
    
    public boolean getFalling() {
        return this.falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean getJumping() {
        return this.jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}
