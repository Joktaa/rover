package org.ynov.Repeteur;

import org.ynov.CommunicationAbstraction.ICommunicationClient;
import org.ynov.CommunicationAbstraction.ICommunicationServer;
import org.ynov.CommunicationAbstraction.IDataCallback;

public class Repeteur {
    private final ICommunicationClient client;
    private final ICommunicationServer server;
    private final IDataCallback clientDataCallback;
    private final IDataCallback serverDataCallback;

    public Repeteur(ICommunicationClient client, ICommunicationServer server) {
        this.client = client;
        this.server = server;
        this.clientDataCallback = new IDataCallback() {
            @Override
            public void onDataReceived(String data) {
                server.send(data);
            }
        };
        this.serverDataCallback = new IDataCallback() {
            @Override
            public void onDataReceived(String data) {
                client.send(data);
            }
        };
    }

    public void start() {
        client.setDataCallback(clientDataCallback);
        client.listening();
        server.setDataCallback(serverDataCallback);
        server.listening();
    }
}
