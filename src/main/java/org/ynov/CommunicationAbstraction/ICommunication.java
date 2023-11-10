package org.ynov.CommunicationAbstraction;


public interface ICommunication {
    void listening();
    void send(String mess);
    void setDataCallback(IDataCallback callback);
}
