package org.ynov.Repeteur;

import org.ynov.Socket.SocketClient;
import org.ynov.Socket.SocketServer;

public class Main {
    public static void main(String[] args) {
        final SocketClient client = new SocketClient();
        final SocketServer server = new SocketServer();
        final Repeteur repeteur = new Repeteur(client, server);
        repeteur.start();
    }
}
