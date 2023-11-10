package org.ynov.MissionControl;

import org.ynov.Commandes.Direction;
import org.ynov.Commandes.Rotation;
import org.ynov.CommunicationAbstraction.ICommunication;
import org.ynov.CommunicationAbstraction.IDataCallback;
import org.ynov.Configuration.Configuration;
import org.ynov.Rover.IRover;
import org.ynov.Rover.Obstacles;
import org.ynov.Socket.Communication;

import java.util.ArrayList;
import java.util.HashMap;

// Entité
public class Console implements IDataCallback {
    private ArrayList<Character> commands = new ArrayList<>();
    private final IRover myRover;

    private Obstacles obstacles;
    //client
    private final ICommunication client = new Communication(Configuration.CLIENT_PORT, Configuration.IP_CLIENT_MUST_SEND_TO, Configuration.PORT_CLIENT_MUST_SEND_TO);

    public Console(final IRover _myRover) {
        this.myRover = _myRover;
        obstacles = new Obstacles(false, new HashMap<>());
        //test client socket
        client.setDataCallback(this);
        client.listening();
    }

    @Override
    public void onDataReceived(String data) {
        System.out.println("Données reçues dans la console : " + data);
    }

    private boolean run(boolean wantMap) {
        for (final Character direction : commands) {
            switch (direction) {
                case 'f' -> {
                    obstacles = myRover.isObstacle('f',obstacles);
                    if(!obstacles.isObstacle()){
                        myRover.move(Direction.FRONT);
                    }
                }
                case 'b' -> {
                    obstacles = myRover.isObstacle('b',obstacles);
                    if(!obstacles.isObstacle()){
                        myRover.move(Direction.BEHIND);
                    }
                }
                case 'r' -> myRover.rotate(Rotation.RIGHT);

                case 'l' -> myRover.rotate(Rotation.LEFT);

                default -> System.out.println("Commande incorrecte, le rover n'a pas bougé de position");
            }
        }

        if (wantMap) {
            this.printMap(myRover, obstacles);
        }
        return true;
    }

    private void setCommand(final String command) {
        ArrayList<Character> result = new ArrayList<>();
        for (final char currentChar : command.toCharArray()) {
            if (Character.isLetter(currentChar)) {
                result.add(currentChar);
            }
        }
        this.commands = result;
    }

    public boolean runCommand(final String command, boolean wantMap) {
        if (command.equals("stop")) {
            return false;
        }
        this.setCommand(command);
        return this.run(wantMap);
    }

    private void printMap(IRover myRover, Obstacles obstacles) {
        Carte carte = new Carte(myRover, obstacles);
    }
}
