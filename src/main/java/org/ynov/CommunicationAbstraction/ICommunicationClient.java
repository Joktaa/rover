package org.ynov.CommunicationAbstraction;

import nl.pvdberg.pnet.client.Client;

public interface ICommunicationClient {
    void send(String mess);
}
