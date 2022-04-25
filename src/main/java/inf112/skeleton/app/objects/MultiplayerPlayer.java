package inf112.skeleton.app.objects;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.attributes.Position;

public class MultiplayerPlayer extends Player {

    public MultiplayerPlayer(Position position, IInputHandler inputHandler) {
        super(position, inputHandler);
    }
}
