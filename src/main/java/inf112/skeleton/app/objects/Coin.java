package inf112.skeleton.app.objects;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.attributes.*;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class Coin extends BaseCollidableTile {

    private final MediaPlayer pickupCoinMediaPlayer;

    public Coin(GameWorld gameWorld, int xPosition, int yPosition) {
        super(gameWorld, xPosition, yPosition);

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
    public void collide(ItemType itemType) {
        gameWorld.removeCollidable(this);
        pickupCoinMediaPlayer.play();
    }
}
