package inf112.skeleton.app.net.packets;

import inf112.skeleton.app.net.GameClient;
import inf112.skeleton.app.net.GameServer;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class LoginPacket extends Packet{

    private String username;

    public String getUsername() {
        return username;
    }

    public LoginPacket(String username) {
        super(PacketType.LOGIN.getId());
        this.username = username;
    }

    public LoginPacket(byte[] data) {
        super(PacketType.LOGIN.getId());
        readData(data);
    }

    private static String readUsername(byte[] data) {
        return data.toString();
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
        var usernameLength = buffer.getInt();
        this.username = new String(Arrays.copyOfRange(buffer.array(), buffer.position(), buffer.position() + usernameLength), StandardCharsets.UTF_8);
    }

    @Override
    public byte[] getPacketData() {
        ByteBuffer buffer = ByteBuffer.allocate(getPacketLength());
        buffer.put(this.packetId).putInt(username.getBytes(StandardCharsets.UTF_8).length).put(username.getBytes());
        return buffer.array();
    }

    public int getPacketLength() {
        int packetIdSize = 1;
        int stringLengthSize = 4;
        int nameSize = username.getBytes(StandardCharsets.UTF_8).length;
        return packetIdSize + stringLengthSize + nameSize;
    }
}
