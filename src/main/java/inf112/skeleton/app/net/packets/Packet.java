package inf112.skeleton.app.net.packets;

import inf112.skeleton.app.net.GameClient;
import inf112.skeleton.app.net.GameServer;

public abstract class Packet {
    public static enum PacketType {
        INVALID(0x00),
        LOGIN(0x01),
        DISCONNECT(0x02),
        LOGIN_COMPLETE(0x03),
        POSITION(0x04);

        private int packetId;
        private PacketType(int packetId) {
            this.packetId = packetId;
        }

        public int getId() {
            return packetId;
        }
    }

    public byte packetId;

    public Packet(int packetId) {
        this.packetId = (byte) packetId;
    }

    public abstract void writeData(GameClient client);
    public abstract void writeData(GameServer client);

    public abstract void readData(byte[] data);

    public abstract byte[] getPacketData();

    public static PacketType getPacketType(int id) {
        for(PacketType type : PacketType.values()) {
            if(type.getId() == id) { return type; }
        }
        return PacketType.INVALID;
    }
}
