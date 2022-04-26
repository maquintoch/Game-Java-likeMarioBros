package inf112.skeleton.app.net.packets;

import inf112.skeleton.app.net.GameClient;
import inf112.skeleton.app.net.GameServer;

import java.nio.ByteBuffer;

public class LoginCompletePacket extends Packet {
    public LoginCompletePacket() {
        super(PacketType.LOGIN_COMPLETE.getId());
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
    }

    @Override
    public byte[] getPacketData() {
        return new byte[] { this.packetId };
    }
}
