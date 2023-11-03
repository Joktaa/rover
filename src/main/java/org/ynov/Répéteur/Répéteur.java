package org.ynov.Répéteur;

import org.ynov.CommunicationAbstraction.ICommunicationClient;
import org.ynov.CommunicationAbstraction.ICommunicationServer;
import org.ynov.Socket.SocketClient;
import org.ynov.Socket.SocketServer;

public class Répéteur {
    private final ICommunicationClient client;
    private final ICommunicationServer server;

    public Répéteur(ICommunicationClient client, ICommunicationServer server) {
        this.client = client;
        this.server = server;
    }

    public void start() {
        //server.getServer().start(1234);
        //client.getClient().connect("localhost",1234);
    }
}
