package inf112.skeleton.app.objects;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.attributes.*;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Tile extends BaseCollidableTile {

    public Tile(GameWorld gameWorld, int xPosition, int yPosition) {
        super(gameWorld, xPosition, yPosition);
        try {
            image = new Image(new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/brick.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ItemType getItemType() {
        return ItemType.Tile;
    }

    @Override
    public void collide(ItemType itemType) {

    }
}
