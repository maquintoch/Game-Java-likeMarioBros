package inf112.skeleton.app.net.packets;

import inf112.skeleton.app.net.GameClient;
import inf112.skeleton.app.net.GameServer;
import inf112.skeleton.app.objects.IGameObject;

import java.nio.ByteBuffer;

public class RemoveGameObjectPacket extends Packet {

    int gameObjectId;

    public RemoveGameObjectPacket(byte[] data) {
        super(PacketType.REMOVE_GAMEOBJECT.getId());
        readData(data);
    }

    public int getGameObjectId() {
        return gameObjectId;
    }

    public RemoveGameObjectPacket() {
        super(PacketType.REMOVE_GAMEOBJECT.getId());
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
        gameObjectId = buffer.getInt();
    }

    @Override
    public byte[] getPacketData() {
        ByteBuffer buffer = ByteBuffer.allocate(5);
        buffer.put(this.packetId)
                .putInt(gameObjectId);
        return buffer.array();
    }

}
