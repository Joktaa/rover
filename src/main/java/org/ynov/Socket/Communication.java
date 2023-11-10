package org.ynov.Socket;

import org.ynov.CommunicationAbstraction.ICommunication;
import org.ynov.CommunicationAbstraction.IDataCallback;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication implements ICommunication {
    private ServerSocket serverSocket;
    private final int listeningPort;
    private final int sendingPort;
    private final String sendingIp;
    private IDataCallback dataCallback;

    public Communication(final int listeningPort, final String sendingIp, final int sendingPort) {
        this.listeningPort = listeningPort;
        this.sendingPort = sendingPort;
        this.sendingIp = sendingIp;
        try {
            serverSocket = new ServerSocket(this.listeningPort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listening() {
        new Thread(() -> {
            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String receivedData = in.readLine();
                    if (dataCallback != null) {
                        dataCallback.onDataReceived(receivedData);
                    }
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void send(String message) {
        try (Socket sendingSocket = new Socket(sendingIp, sendingPort)) {
            PrintWriter out = new PrintWriter(sendingSocket.getOutputStream(), true);
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDataCallback(IDataCallback dataCallback) {
        this.dataCallback = dataCallback;
    }
}
