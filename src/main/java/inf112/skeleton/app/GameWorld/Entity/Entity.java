package inf112.skeleton.app.Entity;

import inf112.skeleton.app.IUpdateable;
import inf112.skeleton.app.DrawBehavior.IDrawBehavior;
import inf112.skeleton.app.DrawBehavior.IDrawable;
import inf112.skeleton.app.Entity.EntityAttributes.IMoveable;
import inf112.skeleton.app.Entity.EntityAttributes.Position;
import inf112.skeleton.app.Entity.EntityAttributes.Rectangle;
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
