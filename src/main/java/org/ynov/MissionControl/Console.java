package org.ynov.MissionControl;

import org.ynov.Commandes.Direction;
import org.ynov.Commandes.Rotation;
import org.ynov.CommunicationAbstraction.ICommunication;
import org.ynov.CommunicationAbstraction.IDataCallback;
import org.ynov.Configuration.Configuration;
import org.ynov.Rover.IRover;
import org.ynov.Socket.Communication;

import java.util.ArrayList;
// Entité
public class Console implements IDataCallback {
    private ArrayList<Character> commands = new ArrayList<>();
    private final IRover myRover;
    //client
    ICommunication client = new Communication(Configuration.CLIENT_PORT, Configuration.IP_CLIENT_MUST_SEND_TO, Configuration.PORT_CLIENT_MUST_SEND_TO);

    public Console(final IRover _myRover) {
        this.myRover = _myRover;
        //test client socket
        client.setDataCallback(this);
        client.listening();
    }

    @Override
    public void onDataReceived(String data) {
        System.out.println("Données reçues dans la console : " + data);
    }

    private boolean run() {
        for(final Character direction : commands) {
            if(!myRover.isObstacle()){
                switch (direction){
                    case 'F' -> myRover.move(Direction.FRONT);
                    case 'B' -> myRover.move(Direction.BEHIND);
                    case 'R' -> myRover.rotate(Rotation.RIGHT);
                    case 'L' -> myRover.rotate(Rotation.LEFT);
                    default ->System.out.println("Commande incorrecte, le rover n'a pas bougé de position");
                }
            } else {
                System.out.println("Obstacle rencontré à la position");
                myRover.getStatus();
                return false;
            }
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

    public boolean runCommand(final String command) {
        if(command.equals("stop")){
            return false;
        }
        this.setCommand(command);
        return this.run();
    }
}
