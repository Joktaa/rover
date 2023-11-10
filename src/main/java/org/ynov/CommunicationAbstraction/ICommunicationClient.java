package org.ynov.CommunicationAbstraction;

import nl.pvdberg.pnet.client.Client;

public interface ICommunicationClient {
    void listening();
    void send(String mess);
    void setDataCallback(IDataCallback callback);
}
