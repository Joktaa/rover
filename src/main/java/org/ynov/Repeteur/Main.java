package org.ynov.Repeteur;

import org.ynov.CommunicationAbstraction.ICommunication;
import org.ynov.Configuration.Configuration;
import org.ynov.Socket.Communication;

public class Main {
    public static void main(String[] args) {
        final ICommunication client = new Communication(Configuration.REPETEUR_CLIENT_PORT, Configuration.CLIENT_IP, Configuration.CLIENT_PORT);
        final ICommunication server = new Communication(Configuration.REPETEUR_SERVER_PORT, Configuration.SERVER_IP, Configuration.SERVER_PORT);

        final Repeteur repeteur = new Repeteur(client, server);
        repeteur.start();
    }
}
