package org.ynov.Repeteur;

import org.ynov.CommunicationAbstraction.ICommunication;
import org.ynov.CommunicationAbstraction.IDataCallback;

public class Repeteur {
    private final ICommunication client;
    private final ICommunication server;
    private final IDataCallback clientDataCallback;
    private final IDataCallback serverDataCallback;

    public Repeteur(ICommunication client, ICommunication server) {
        this.client = client;
        this.server = server;
        this.clientDataCallback = server::send;
        this.serverDataCallback = client::send;
    }

    public void start() {
        client.setDataCallback(clientDataCallback);
        client.listening();
        server.setDataCallback(serverDataCallback);
        server.listening();
    }
}
