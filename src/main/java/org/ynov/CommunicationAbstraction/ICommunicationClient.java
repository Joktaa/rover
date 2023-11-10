package org.ynov.CommunicationAbstraction;

public interface ICommunicationClient {
    void listening();
    void send(String mess);
    void setDataCallback(IDataCallback callback);
}
