package inf112.skeleton.app.Entity;

import javafx.scene.canvas.Canvas;
import java.util.List;

import inf112.skeleton.app.CoinUIService;
import inf112.skeleton.app.Entity.EntityAttributes.Position;
import inf112.skeleton.app.Entity.EntityAttributes.Rectangle;
import inf112.skeleton.app.Entity.EntityAttributes.Speed;
import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.Tiles.Tile;
import inf112.skeleton.app.Tiles.TileCollections.CoinCollection;
import javafx.scene.input.KeyCode;

import java.util.ArrayList;

public class PlayerEntity extends Entity {

    private Speed acceleration;
    private Speed speed;
    private IInputHandler inputHandler;
    private ArrayList<Tile> collideableCollection;
    private CoinCollection coins;
    private CoinUIService CoinUI;
 

    public PlayerEntity(Canvas canvas, ArrayList<Tile> collideables, CoinCollection coins, CoinUIService coinUI, IInputHandler inputHandler, IDrawBehavior drawhandler) {
        super(canvas, drawhandler);
        this.position = new Position(3, 10);
        this.boundingBox = new Rectangle(16, 16);
        this.speed = new Speed(0, 0);
        this.acceleration = new Speed(0, -0.5f);
        this.inputHandler = inputHandler;
        this.coins = coins;
        this.CoinUI = coinUI;
       
        

        this.collideableCollection = collideables;
    }

    @Override
    public void Update() {
        if(inputHandler.isActive(KeyCode.W)) speed.velocityY = 4;
        if(inputHandler.isActive(KeyCode.A)) speed.velocityX = -1;
        else if(inputHandler.isActive(KeyCode.D)) speed.velocityX = 1;
        else speed.velocityX = 0;

        speed.velocityY += acceleration.velocityY;
        speed.velocityX += acceleration.velocityX;

        position.x += speed.velocityX;
        for(var collidable : collideableCollection) {
            if (GetCollisionBox().overlap(collidable)) {
                position.x -= speed.velocityX;
                while(!overlap(collidable)) position.x += Math.signum(speed.velocityX);
                position.x -= Math.signum(speed.velocityX);
                speed.velocityX = 0;
                position.x = collidable.GetClosestXPosition(position);
            }
        }


        position.y += speed.velocityY;
        for(var collidable : collideableCollection) {
            if (GetCollisionBox().overlap(collidable)) {
                position.y -= speed.velocityY;
                while(!overlap(collidable)) position.y += Math.signum(speed.velocityY);
                position.y -= Math.signum(speed.velocityY);
                speed.velocityY = 0;
                position.y = collidable.GetClosestYPosition(position);
            }
        }
        
        
        for(var coin : coins.getAll()) {
            if (GetCollisionBox().overlap(coin)) {
            	CoinUI.currentscore.amount++;
            	coin.destroy();
            	
            	
            	
            }
        }
        
        
        

    }

    private boolean overlap(ICollideable collidable) {
        var collisionBox = GetCollisionBox();
        return collisionBox.overlap(collidable);
    }

    @Override
    public Speed GetSpeed() {
        return speed;
    }

    @Override
    public CollisionBox GetCollisionBox() {
        var edgePosition = new Position(this.position);
        edgePosition.Add(boundingBox);
        return new CollisionBox(position, edgePosition);
    }

}
