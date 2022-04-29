package inf112.skeleton.app.net;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.net.packets.*;
import inf112.skeleton.app.objects.MultiplayerPlayer;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.attributes.Position;
import javafx.animation.AnimationTimer;

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

        AnimationTimer timer = new AnimationTimer() {

            final int TICKS_PER_SECOND = 10;
            final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
            double next_game_tick = System.currentTimeMillis();

            @Override
            public void handle(long now) {
                if (System.currentTimeMillis() > next_game_tick) {
                    for(var gameObject : gameWorld.getGameObjects()) {
                        var positionPacket = new PositionPacket(gameObject.getId(), gameObject.getPosition());
                        sendDataAll(positionPacket.getPacketData());
                    }
                }
            }
        };
        timer.start();
    }

    @Override
    public void run() {
        while(true) {
            byte[] data = new byte[1024 * 16];
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
                MultiplayerPlayer player = new MultiplayerPlayer(new Position(10, 10), address, port);
                this.gameWorld.addGameObject(player);
                addConnection(player, packet);
                LoginCompletePacket loginCompletePacket = new LoginCompletePacket();
                this.sendData(loginCompletePacket.getPacketData(), address, port);
                AddGameObjectPacket addGameObjectPacket = new AddGameObjectPacket(player);
                this.sendDataAll(addGameObjectPacket.getPacketData());
                var loadPacket = new LoadMapPacket(this.gameWorld.getGameObjects(), null);
                this.sendData(loadPacket.getPacketData(), address, port);
                break;
            case DISCONNECT:
                break;
            case PLAYER_POSITION:
                PlayerPositionPacket playerPositionPacket = new PlayerPositionPacket(data);
                var position = playerPositionPacket.getPosition();
                System.out.println("["+address.getHostAddress()+":"+port+"] " +"[x: " + position.getX() + "] [y: " + position.getY() + "]");
                for(MultiplayerPlayer multiplayerPlayer : connectedPlayers) {
                    if(multiplayerPlayer.getIpAddress() == address && multiplayerPlayer.getPort() == port) {
                        multiplayerPlayer.setPosition(position);
                    }
                }

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
