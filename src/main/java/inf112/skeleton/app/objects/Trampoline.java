package inf112.skeleton.app.objects;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.attributes.*;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Trampoline extends GameObjectBase {

    private Image image;

    public Trampoline(Position position) {
        super(position);
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
    public void collide(IGameObject gameObject) {

    }

    @Override
    public void draw(GameWorld gameWorld) {
        var drawBehavior = gameWorld.getDrawImageBehavior();
        drawBehavior.draw(position, size, image);
    }
}
