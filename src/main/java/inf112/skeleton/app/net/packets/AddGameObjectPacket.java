package inf112.skeleton.app.net.packets;

import inf112.skeleton.app.net.GameClient;
import inf112.skeleton.app.net.GameServer;
import inf112.skeleton.app.objects.IGameObject;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.Tile;
import inf112.skeleton.app.objects.Trampoline;
import inf112.skeleton.app.objects.attributes.Position;

import java.nio.ByteBuffer;

public class AddGameObjectPacket extends Packet {


    private IGameObject gameObject;

    public IGameObject getGameObject() {
        return gameObject;
    }

    public AddGameObjectPacket(IGameObject gameObject) {
        super(PacketType.ADD_GAMEOBJECT.getId());
        this.gameObject = gameObject;
    }

    public AddGameObjectPacket(byte[] data) {
        super(PacketType.ADD_GAMEOBJECT.getId());
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
        var itemType = buffer.getInt();
        var entityId = buffer.getInt();
        var positionX = buffer.getDouble();
        var positionY = buffer.getDouble();
        var position = new Position(positionX, positionY);
        gameObject = new Player(position, null);
        gameObject.setId(entityId);
    }

    @Override
    public byte[] getPacketData() {
        ByteBuffer buffer = ByteBuffer.allocate(1 + 2 * 4 + 2 * 8);
        buffer.put(this.packetId);
        buffer.putInt(gameObject.getItemType().getId());
        buffer.putInt(gameObject.getId());
        buffer.putDouble(gameObject.getPosition().getX());
        buffer.putDouble(gameObject.getPosition().getY());
        return buffer.array();
    }
}
