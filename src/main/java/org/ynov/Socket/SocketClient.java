package org.ynov.Socket;

import nl.pvdberg.pnet.client.Client;
import nl.pvdberg.pnet.client.util.PlainClient;
import nl.pvdberg.pnet.event.PNetListener;
import nl.pvdberg.pnet.packet.Packet;
import nl.pvdberg.pnet.packet.PacketBuilder;
import nl.pvdberg.pnet.packet.PacketReader;
import nl.pvdberg.pnet.server.Server;
import nl.pvdberg.pnet.server.util.PlainServer;
import org.ynov.CommunicationAbstraction.ICommunicationClient;
import org.ynov.CommunicationAbstraction.IDataCallback;
import org.ynov.Configuration.Configuration;

import java.io.IOException;

public class SocketClient implements ICommunicationClient {
    private Client sendingClient = new PlainClient();
    private Server listeningClient;
    private IDataCallback dataCallback;

    public SocketClient() {
        //new instance
        try {
            listeningClient = new PlainServer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listening() {
        listeningClient.start(Configuration.CLIENT_PORT);
        listeningClient.setListener(new PNetListener() {
            @Override
            public void onConnect(Client c) {
            }

            @Override
            public void onDisconnect(Client c) {
            }

            @Override
            public void onReceive(Packet p, Client c) throws IOException {
                PacketReader packetReader = new PacketReader(p);
                String res = packetReader.readString();
                if (dataCallback != null) {
                    dataCallback.onDataReceived(res);
                }
            }
        });
    }

    public void send(String mess) {
        sendingClient.connect(Configuration.SERVER_IP, Configuration.SERVER_PORT);
        Packet packet = new PacketBuilder(Packet.PacketType.Request).withString(mess).build();
        sendingClient.send(packet);
        sendingClient.close();
    }

    public void setDataCallback(IDataCallback dataCallback) {
        this.dataCallback = dataCallback;
    }
}
