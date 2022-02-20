package inf112.skeleton.app;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity implements IDrawable, IUpdateable {

//    private IDrawBehavior drawHandler;
//    private Rectangle boundingBox;
    private GraphicsContext context;
    private Position position;
    private boolean falling, jumping;

    public Entity(GraphicsContext context) {
    	this.context = context;
//        drawHandler = new DrawColorBehavior(context);
    }

    @Override
    public abstract void Draw();
//    public void Draw() {
//        drawHandler.Draw(position, boundingBox);
//    }

    @Override
    public abstract void Update();
    
    public GraphicsContext getGraphicsContext() {
    	return this.context;
    }
    
    public Position getPosition() {
    	return position;
    }
    
    public void setPosition(double x, double y) {
    	this.position.x = x;
    	this.position.y = y;
    }
    
    public double getX() {
    	return this.position.x;
    }
    
    public double getY() {
    	return this.position.y;
    }
    
    public void setX(double x) {
    	this.position.x = x;
    }
    
    public void setY(double y) {
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
