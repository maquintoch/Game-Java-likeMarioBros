package inf112.skeleton.app.objects;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.attributes.*;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Trampoline extends BaseCollidableTile {

    public Trampoline(GameWorld gameWorld, int xPosition, int yPosition) {
        super(gameWorld, xPosition, yPosition);
        try {
            image = new Image(new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/spring.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    public ItemType getItemType() {
        return ItemType.Trampoline;
    }

    @Override
    public void collide(ItemType itemType) {

    }
}
