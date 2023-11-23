package org.ynov.Configuration;

public class Configuration {
    public static final String SERVER_IP = "localhost";
    public static final int SERVER_PORT = 8080;
    public static final String CLIENT_IP = "localhost";
    public static final int CLIENT_PORT = 2222;
    public static final String REPETEUR_IP = "localhost";
    public static final int REPETEUR_SERVER_PORT = 1111;
    public static final int REPETEUR_CLIENT_PORT = 8083;



    public static final String IP_SERVER_MUST_SEND_TO = REPETEUR_IP;
    public static final int PORT_SERVER_MUST_SEND_TO = REPETEUR_CLIENT_PORT;
    public static final String IP_CLIENT_MUST_SEND_TO = REPETEUR_IP;
    public static final int PORT_CLIENT_MUST_SEND_TO = REPETEUR_SERVER_PORT;
}
