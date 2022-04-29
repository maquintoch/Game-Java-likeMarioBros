package inf112.skeleton.app.objects;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.objects.attributes.Position;

import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

public class MultiplayerPlayer extends Player {

    private final int port;
    private final InetAddress ipAddress;

    public MultiplayerPlayer(Position position, InetAddress ipAddress, int port) {
        super(position, null);
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public MultiplayerPlayer(Position position, InetAddress ipAddress, int port, IInputHandler inputHandler) {
        super(position, inputHandler);
        this.ipAddress = ipAddress;
        this.port = port;
    }

    @Override
    public void update(List<IGameObject> gameObjects) {
        super.update(gameObjects);
    }

    public int getPort() {
        return port;
    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

}