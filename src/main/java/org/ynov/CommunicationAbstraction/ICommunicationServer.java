package org.ynov.CommunicationAbstraction;


public interface ICommunicationServer {
    void listening();
    void send(String mess);
    void setDataCallback(IDataCallback callback);
}
