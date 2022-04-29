package inf112.skeleton.app.net.packets;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.net.GameClient;
import inf112.skeleton.app.net.GameServer;
import inf112.skeleton.app.objects.*;
import inf112.skeleton.app.objects.attributes.ItemType;
import inf112.skeleton.app.objects.attributes.Position;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;

public class LoadMapPacket extends Packet {

    private final IInputHandler inputHandler;
    private LinkedList<IGameObject> gameObjects = new LinkedList<IGameObject>();

    public LinkedList<IGameObject> getGameObjects() {
        return gameObjects;
    }

    public LoadMapPacket(LinkedList<IGameObject> gameObjects, IInputHandler inputHandler) {
        super(PacketType.LOAD_MAP.getId());
        this.gameObjects = gameObjects;
        this.inputHandler = inputHandler;
    }

    public LoadMapPacket(byte[] data, IInputHandler inputHandler) {
        super(PacketType.LOAD_MAP.getId());
        this.inputHandler = inputHandler;
        readData(data);
    }

    @Override
    public void writeData(GameClient client) {
        client.sendData(getPacketData());
    }

    @Override
    public void writeData(GameServer server) {
        server.sendDataAll(getPacketData());
    }

    @Override
    public void readData(byte[] data) {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        buffer.position(1);
        var gameObjectAmount = buffer.getInt();
        for(var i = 0; i < gameObjectAmount; i++) {
            var itemType = buffer.getInt();
            var entityId = buffer.getInt();
            var positionX = buffer.getDouble();
            var positionY = buffer.getDouble();
            var position = new Position(positionX, positionY);
            IGameObject gameObject = null;
            ItemType type = ItemType.values()[itemType];
            switch (type) {
                case Player:
                    gameObject = new Player(position, inputHandler);
                    break;
                case MultiplayerPlayer:
                    gameObject = new Player(position, null);
                    break;
                case Tile:
                    gameObject = new Tile(position);
                    break;
                case Enemy:
                    gameObject = new Enemy(position);
                    break;
                case Coin:
                    gameObject = new Coin(position);
                    break;
                case Trampoline:
                    gameObject = new Trampoline(position);
                    break;
            }
            gameObject.setId(entityId);
            gameObjects.add(gameObject);
        }
    }

    @Override
    public byte[] getPacketData() {
        ByteBuffer buffer = ByteBuffer.allocate(getPacketLength());
        buffer.put(this.packetId).putInt(gameObjects.size());
        for(IGameObject gameObject: gameObjects) {
            var position = gameObject.getPosition();
            buffer.putInt(gameObject.getItemType().getId())
                    .putInt(gameObject.getId())
                    .putDouble(position.getX())
                    .putDouble(position.getY());
        }
        return buffer.array();
    }

    private int getPacketLength() {
        int packetIdSize = 1;
        int amountOfObjectsSize = 4;
        int objectsSize = gameObjects.size() * (2 * 4 + 2 * 8);
        return packetIdSize + amountOfObjectsSize + objectsSize;
    }
}
