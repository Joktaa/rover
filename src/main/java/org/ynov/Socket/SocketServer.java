package org.ynov.Socket;
import nl.pvdberg.pnet.client.Client;
import nl.pvdberg.pnet.client.util.PlainClient;
import nl.pvdberg.pnet.event.PNetListener;
import nl.pvdberg.pnet.packet.Packet;
import nl.pvdberg.pnet.packet.PacketBuilder;
import nl.pvdberg.pnet.packet.PacketReader;
import nl.pvdberg.pnet.server.Server;
import nl.pvdberg.pnet.server.util.PlainServer;
import org.ynov.CommunicationAbstraction.ICommunicationServer;
import org.ynov.CommunicationAbstraction.IDataCallback;
import org.ynov.Configuration.Configuration;

import java.io.IOException;


public class SocketServer implements ICommunicationServer {
    private Client sendingServer = new PlainClient();
    private Server listeningServer;
    private final int listeningPort = 2222;
    private final int sendingPort = 1111;
    private IDataCallback dataCallback;

    public SocketServer() {
        //new instance
        try {
            listeningServer = new PlainServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void listening() {
        listeningServer.start(Configuration.SERVER_PORT);
        listeningServer.setListener(new PNetListener() {
            @Override
            public void onConnect(Client c) {

            }

            @Override
            public void onDisconnect(Client c) {
            }

            @Override
            public void onReceive(Packet p, Client c) throws IOException {PacketReader packetReader = new PacketReader(p);
                String res = packetReader.readString();
                if (dataCallback != null) {
                    dataCallback.onDataReceived(res);
                }
            }
        });
    }
    public void send(String mess){
        sendingServer.connect(Configuration.CLIENT_IP, Configuration.CLIENT_PORT);
        Packet packet = new PacketBuilder(Packet.PacketType.Request).withString(mess).build();
        sendingServer.send(packet);
        sendingServer.close();
    }

    public void setDataCallback(IDataCallback dataCallback) {
            this.dataCallback = dataCallback;
        }
}
