package inf112.skeleton.app.objects;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.attributes.*;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Coin extends GameObjectBase {

    private Image image;
    private final MediaPlayer pickupCoinMediaPlayer;

    public Coin(Position position) {
        super(position);

        try {
            image = new Image(new FileInputStream("src/main/java/inf112/skeleton/app/assets/image/coin.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Media pickupCoinUri = new Media(Paths.get("src/main/java/inf112/skeleton/app/assets/audio/pickupCoin.wav").toUri().toString());
        pickupCoinMediaPlayer = new MediaPlayer(pickupCoinUri);
    }

    @Override
    public ItemType getItemType() {
        return ItemType.Coin;
    }

    @Override
    public void collide(IGameObject gameObject) {
        var itemType = gameObject.getItemType();
        switch(itemType) {
            case Player:
                pickupCoinMediaPlayer.play();
                this.gameWorldObservers.forEach(observer -> observer.addScore(1));
                destory();
        }
    }

    @Override
    public void draw(GameWorld gameWorld) {
        var drawBehavior = gameWorld.getDrawImageBehavior();
        drawBehavior.draw(position, size, image);
    }
}
