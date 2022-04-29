package inf112.skeleton.app.net.packets;

import inf112.skeleton.app.net.GameClient;
import inf112.skeleton.app.net.GameServer;
import inf112.skeleton.app.objects.attributes.Position;

import java.nio.ByteBuffer;

public class PositionPacket extends Packet {

    private int entityId;
    private Position position;

    public int getEntityId() {
        return entityId;
    }

    public Position getPosition() {
        return position;
    }

    public PositionPacket(int entityId, Position position) {
        super(PacketType.POSITION.getId());
        this.entityId = entityId;
        this.position = position;
    }

    public PositionPacket(byte[] data) {
        super(PacketType.LOGIN.getId());
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
        entityId = buffer.getInt();
        var positionX = buffer.getDouble();
        var positionY = buffer.getDouble();
        position = new Position(positionX, positionY);
    }

    @Override
    public byte[] getPacketData() {
        ByteBuffer buffer = ByteBuffer.allocate(getPacketLength());
        buffer.put(this.packetId).putInt(entityId).putDouble(position.getX()).putDouble(position.getY());
        return buffer.array();
    }

    public int getPacketLength() {
        int packetIdSize = 1;
        int entityIdSize = 4;
        var xPositionSize = 8;
        var yPositionSize = 8;
        return packetIdSize + entityIdSize + xPositionSize + yPositionSize;
    }

}
