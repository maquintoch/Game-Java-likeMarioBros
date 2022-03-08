package inf112.skeleton.app.GameWorld.Entity;

import inf112.skeleton.app.GameWorld.IUpdateable;
import inf112.skeleton.app.DrawBehavior.IDrawBehavior;
import inf112.skeleton.app.DrawBehavior.IDrawable;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.IMoveable;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Rectangle;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Entity implements IDrawable, IUpdateable, IMoveable, ICollideable {

    private IDrawBehavior drawHandler;

    protected Position position;
    protected Rectangle boundingBox;

    public Entity(Canvas canvas, IDrawBehavior drawHandler) {
    	this.drawHandler = drawHandler;
        //drawHandler = new DrawColorBehavior(canvas, Color.BLUE);
        
    }

    @Override
    public void Draw() {
        drawHandler.Draw(position, boundingBox);
    }

    @Override
    public Position GetPosition() { return position; }

    @Override
    public abstract void Update();
}
