package inf112.skeleton.app.net;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.net.packets.LoginPacket;
import inf112.skeleton.app.net.packets.Packet;
import inf112.skeleton.app.objects.MultiplayerPlayer;
import inf112.skeleton.app.objects.attributes.Position;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;

public class GameClient extends Thread {

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
            byte[] data = new byte[1024];
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
            case LOGIN_COMPLETE:
                LoginPacket packet = new LoginPacket(data);
                System.out.println("["+address.getHostAddress()+":"+port+"]" + packet.getUsername() + " has joined the game.");
                MultiplayerPlayer player = new MultiplayerPlayer(new Position(0, 0), address, port, gameWorld.getInputHandler());
                gameWorld.addTargetPlayer(player);
                break;
            case DISCONNECT:
                break;
            case POSITION:
                break;
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
}
