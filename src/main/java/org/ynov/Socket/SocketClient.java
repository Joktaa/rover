package org.ynov.Socket;

import jdk.jfr.Unsigned;
import nl.pvdberg.pnet.client.Client;
import nl.pvdberg.pnet.client.util.PlainClient;
import nl.pvdberg.pnet.event.PNetListener;
import nl.pvdberg.pnet.packet.Packet;
import nl.pvdberg.pnet.packet.PacketBuilder;
import nl.pvdberg.pnet.packet.PacketReader;
import nl.pvdberg.pnet.server.Server;
import nl.pvdberg.pnet.server.util.PlainServer;
import org.ynov.CommunicationAbstraction.ICommunicationClient;

import java.io.Console;
import java.io.IOException;

public class SocketClient implements ICommunicationClient {
    private Client client;
    private Server server;
    private final int port = 1111;

    public SocketClient() {
        //new instance
        try {
            server = new PlainServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        client = new PlainClient();
        server.start(port);
        this.serverListener();
    }
    private void serverListener() {
        server.setListener(new PNetListener() {
            @Override
            public void onConnect(Client c) {

            }

            @Override
            public void onDisconnect(Client c) {
                server.stop();
            }

            @Override
            public void onReceive(Packet p, Client c) throws IOException {
                PacketReader packetReader = new PacketReader(p);
                String res = packetReader.readString();
                System.out.println("res : "+res);
            }
        });
    }
    public void send(String mess){
        client.connect("localhost",port);
        Packet packet = new PacketBuilder(Packet.PacketType.Request).withString(mess).build();
        client.send(packet);
        client.close();
    }
}
