package inf112.skeleton.app.net;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.net.packets.*;
import inf112.skeleton.app.objects.IGameObject;
import inf112.skeleton.app.objects.MultiplayerPlayer;
import inf112.skeleton.app.objects.IPlayerObserver;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.attributes.ItemType;
import inf112.skeleton.app.objects.attributes.Position;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class GameClient extends Thread implements IPlayerObserver {

    private InetAddress ipAddress;
    private int port;
    private DatagramSocket socket;
    private GameWorld gameWorld;

    public GameClient(GameWorld gameWorld, String ipAddress, int port) {
        this.gameWorld = gameWorld;
        try {
            this.socket = new DatagramSocket();
            this.ipAddress = InetAddress.getByName(ipAddress);
            this.port = port;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true) {
            byte[] data = new byte[1024 * 16];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            parsePacket(packet.getData(), packet.getAddress(), packet.getPort());

        }
    }

    private void parsePacket(byte[] data, InetAddress address, int port) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        Packet.PacketType type = Packet.getPacketType(buffer.get());
        switch (type) {
            case INVALID:
                break;
            case DISCONNECT:
                break;
            case LOAD_MAP:
                gameWorld.unload();
                LoadMapPacket loadMapPacket = new LoadMapPacket(data, gameWorld.getInputHandler());
                var gameObjects = loadMapPacket.getGameObjects();
                for (IGameObject gameObject : gameObjects) {
                    if(gameObject.getItemType() == ItemType.Player) {
                        var player = (Player)gameObject;
                        player.setInputHandler(gameWorld.getInputHandler());
                        player.addObserver(this);
                        this.gameWorld.addTargetPlayer(player);
                    }
                    gameWorld.addGameObject(gameObject);
                }
            case POSITION:
                PositionPacket positionPacket = new PositionPacket(data);
                var entityPosition = positionPacket.getPosition();
                var entityId = positionPacket.getEntityId();
                gameWorld.setPosition(entityId, entityPosition);
                break;
            case ADD_GAMEOBJECT:
                AddGameObjectPacket addGameObjectPacket = new AddGameObjectPacket(data);
                var gameObject = addGameObjectPacket.getGameObject();
                gameWorld.addGameObject(gameObject);
            case REMOVE_GAMEOBJECT:
                RemoveGameObjectPacket removeGameObjectPacket = new RemoveGameObjectPacket(data);
                var gameObjectId = removeGameObjectPacket.getGameObjectId();
                gameWorld.removeGameObject(gameObjectId);
        }
    }

    public void sendData(byte[] data) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updatePosition(Position position) {
        var packet = new PlayerPositionPacket(position);
        packet.writeData(this);
    }
}
