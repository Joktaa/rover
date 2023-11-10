package org.ynov.MissionControl;

import org.ynov.Commandes.Direction;
import org.ynov.Commandes.Rotation;
import org.ynov.CommunicationAbstraction.ICommunicationClient;
import org.ynov.CommunicationAbstraction.IDataCallback;
import org.ynov.Rover.IRover;
import org.ynov.Socket.SocketClient;
import org.ynov.Rover.Obstacles;

import java.util.ArrayList;
import java.util.HashMap;

// Entité
public class Console  implements IDataCallback {
    private ArrayList<Character> commands = new ArrayList<>();
    private final IRover myRover;

    private Obstacles obstacles;
    //client
    ICommunicationClient mcClient = new SocketClient();

    public Console(final IRover _myRover) {
        this.myRover = _myRover;
        obstacles = new Obstacles(false, new HashMap<>());
//        //test client socket
        mcClient.setDataCallback(this);
        mcClient.listening();
    }

    @Override
    public void onDataReceived(String data) {
        System.out.println("Données reçues dans la console : " + data);
    }

    private boolean run(boolean wantMap) {
        for (final Character direction : commands) {
            switch (direction) {
                case 'F' -> {
                    obstacles = myRover.isObstacle('F',obstacles);
                    if(!obstacles.isObstacle()){
                        myRover.move(Direction.FRONT);
                    }
                }
                case 'B' -> {
                    obstacles = myRover.isObstacle('B',obstacles);
                    if(!obstacles.isObstacle()){
                        myRover.move(Direction.BEHIND);
                    }
                }
                case 'R' -> myRover.rotate(Rotation.RIGHT);

                case 'L' -> myRover.rotate(Rotation.LEFT);

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
