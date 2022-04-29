package inf112.skeleton.app.net.packets;

import inf112.skeleton.app.net.GameClient;
import inf112.skeleton.app.net.GameServer;
import inf112.skeleton.app.objects.attributes.Position;

import java.nio.ByteBuffer;

public class PlayerPositionPacket extends Packet {

    private Position position = new Position(0, 0);

    public Position getPosition() {
        return position;
    }

    public PlayerPositionPacket(Position position) {
        super(PacketType.PLAYER_POSITION.getId());
        this.position = position;
    }

    public PlayerPositionPacket(byte[] data) {
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
        var positionX = buffer.getDouble();
        var positionY = buffer.getDouble();
        position = new Position(positionX, positionY);
    }

    @Override
    public byte[] getPacketData() {
        ByteBuffer buffer = ByteBuffer.allocate(getPacketLength());
        buffer.put(this.packetId).putDouble(position.getX()).putDouble(position.getY());
        return buffer.array();
    }

    public int getPacketLength() {
        int packetIdSize = 1;
        var xPositionSize = 8;
        var yPositionSize = 8;
        return packetIdSize + xPositionSize + yPositionSize;
    }

}
