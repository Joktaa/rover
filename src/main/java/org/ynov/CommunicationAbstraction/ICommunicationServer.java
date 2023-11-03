package org.ynov.CommunicationAbstraction;

public interface ICommunicationServer {
    public void send(String message);
    public String receive();
}
