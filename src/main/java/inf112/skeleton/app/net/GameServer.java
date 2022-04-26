package inf112.skeleton.app.net;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.net.packets.LoginCompletePacket;
import inf112.skeleton.app.net.packets.LoginPacket;
import inf112.skeleton.app.net.packets.Packet;
import inf112.skeleton.app.net.packets.PositionPacket;
import inf112.skeleton.app.objects.MultiplayerPlayer;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.attributes.Position;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class GameServer extends Thread {
    private DatagramSocket socket;
    private GameWorld gameWorld;
    private final List<MultiplayerPlayer> connectedPlayers = new ArrayList<MultiplayerPlayer>();

    public GameServer(GameWorld gameWorld, int port) {
        this.gameWorld = gameWorld;
        try {
            this.socket = new DatagramSocket(port);
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
            case LOGIN:
                LoginPacket packet = new LoginPacket(data);
                System.out.println("["+address.getHostAddress()+":"+port+"]" + packet.getUsername() + " has connected.");
                MultiplayerPlayer player = new MultiplayerPlayer(new Position(0, 0), address, port);
                this.gameWorld.addGameObject(player);
                addConnection(player, packet);
                LoginCompletePacket loginCompletePacket = new LoginCompletePacket();
                this.sendData(loginCompletePacket.getPacketData(), address, port);
                break;
            case DISCONNECT:
                break;
            case POSITION:
                PositionPacket positionPacket = new PositionPacket(data);
                var position = positionPacket.getPosition();
                System.out.println("["+address.getHostAddress()+":"+port+"] " +"[x: " + position.getX() + "] [y: " + position.getY() + "]");
                break;
        }
    }

    public void addConnection(MultiplayerPlayer player, LoginPacket loginPacket) {
        this.connectedPlayers.add(player);
        this.sendDataAll(loginPacket.getPacketData());
    }

    public void sendData(byte[] data, InetAddress ipAddress, int port) {
        DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendDataAll(byte[] packetData) {
        for(MultiplayerPlayer player : connectedPlayers) {
            sendData(packetData, player.getIpAddress(), player.getPort());
        }
    }
}
