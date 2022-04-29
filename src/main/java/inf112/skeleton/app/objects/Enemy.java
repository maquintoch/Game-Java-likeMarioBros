package inf112.skeleton.app.objects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.attributes.*;

import javafx.scene.image.Image;


public class Enemy extends EntityBase {

    private Image image;
    private Speed movementSpeed = new Speed(0.5f, 0);

    public Enemy(Position position) {
        super(position);

        this.velocity = movementSpeed;
        this.acceleration = new Speed(0, -0.5f);

        this.size = new GameObjectSize(16, 16);

        this.bounceAmountX = 1f;

        try {
            image = new Image(new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/enemy.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(GameWorld gameWorld) {
        var drawBehavior = gameWorld.getDrawImageBehavior();
        drawBehavior.draw(position, size, image);
    }

    public Speed getSpeed(){
        return movementSpeed;
    }

    @Override
    public ItemType getItemType() {
        return ItemType.Enemy;
    }

    @Override

    public void collide(IGameObject gameObject) {
        var itemType = gameObject.getItemType();
        switch (itemType) {
            case Player:
                if (gameObject.isAbove(this)) {
                    this.destory();
                }
                break;
            case Trampoline:
                this.velocity.velocityY += 1;
                break;
        }
    }
}

