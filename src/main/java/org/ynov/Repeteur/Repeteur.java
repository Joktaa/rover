package org.ynov.Repeteur;

import org.ynov.CommunicationAbstraction.ICommunicationClient;
import org.ynov.CommunicationAbstraction.ICommunicationServer;

public class Repeteur {
    private final ICommunicationClient client;
    private final ICommunicationServer server;

    public Repeteur(ICommunicationClient client, ICommunicationServer server) {
        this.client = client;
        this.server = server;
    }

    public void start() {
        //server.getServer().start(1234);
        //client.getClient().connect("localhost",1234);
    }
}
