package org.ynov.Socket;
import nl.pvdberg.pnet.client.Client;
import nl.pvdberg.pnet.client.util.PlainClient;
import nl.pvdberg.pnet.event.PNetListener;
import nl.pvdberg.pnet.packet.Packet;
import nl.pvdberg.pnet.packet.PacketReader;
import nl.pvdberg.pnet.server.Server;
import nl.pvdberg.pnet.server.util.PlainServer;
import org.ynov.CommunicationAbstraction.ICommunicationServer;

import java.io.IOException;


public class SocketServer implements ICommunicationServer {
    /*
    private Server server = null;
    private Client client = new PlainClient();

    public SocketServer() {
        try {
            server = new PlainServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        server.setListener(new PNetListener() {
            @Override
            public void onConnect(Client c) {
                System.out.println("Server connecté ! "+c.getInetAddress().getHostAddress());
            }

            @Override
            public void onDisconnect(Client c) {
                System.out.println("Server déconnecté ! "+c.getInetAddress().getHostName());
            }

            @Override
            public void onReceive(Packet p, Client c) throws IOException {
                PacketReader packetReader = new PacketReader(p);
                System.out.println("paquet recu du server : "+ packetReader.readString());
            }
        });
        }
        public void send(){
            server.start(1111);
        }
        */
}
