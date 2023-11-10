package org.ynov.Rover;

import org.ynov.Commandes.*;
import org.ynov.CommunicationAbstraction.ICommunication;
import org.ynov.CommunicationAbstraction.IDataCallback;
import org.ynov.Configuration.Configuration;
import org.ynov.Socket.Communication;
import org.ynov.Topologie.Planet;

import java.util.Random;
//Objet valeur
public class Rover implements IRover, IDataCallback{
    public Orientation orientation;
    public final Position position;
    public final Planet planet;
    //Server
    private ICommunication server = new Communication(Configuration.SERVER_PORT, Configuration.IP_SERVER_MUST_SEND_TO, Configuration.PORT_SERVER_MUST_SEND_TO);

    public Rover(final Planet planet) {
        this.orientation = Orientation.NORTH;
        this.position = new Position();
        this.planet = planet;
        //test socket serveur
        server.setDataCallback(this);
        server.listening();
    }
    @Override
    public void onDataReceived(String data) {
        System.out.println("Données reçues dans la rover : " + data);
    }
    public int getLatitude() {
        return this.position.getY();
    }

    public int getLongitude() {
        return this.position.getX();
    }

    public Planet getPlanet() {
        return planet;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public Position getPosition() {
        return position;
    }

    public void rotate(final Rotation rotation) {
        if (rotation.equals(Rotation.RIGHT)) {
            orientation = switch (orientation) {
                case NORTH -> Orientation.EST;
                case WEST -> Orientation.NORTH;
                case SOUTH -> Orientation.WEST;
                case EST -> Orientation.SOUTH;
            };
        } else if (rotation.equals(Rotation.LEFT)) {
            orientation = switch (orientation) {
                case NORTH -> Orientation.WEST;
                case WEST -> Orientation.SOUTH;
                case SOUTH -> Orientation.EST;
                case EST -> Orientation.NORTH;
            };
        } else {
            System.out.println("Error rotation");
        }
        this.getStatus();
    }

    public void move(final Direction direction) {
        if (direction.equals(Direction.FRONT)) {
            switch (this.orientation) {
                case NORTH -> position.checkOutOfBoundAndMove(MoveDirection.Y_plus, planet);
                case WEST -> position.checkOutOfBoundAndMove(MoveDirection.X_minus, planet);
                case SOUTH -> position.checkOutOfBoundAndMove(MoveDirection.Y_minus, planet);
                case EST -> position.checkOutOfBoundAndMove(MoveDirection.X_plus, planet);
            }
        } else if (direction.equals(Direction.BEHIND)) {
            switch (this.orientation) {
                case NORTH -> position.checkOutOfBoundAndMove(MoveDirection.Y_minus, planet);
                case WEST -> position.checkOutOfBoundAndMove(MoveDirection.X_plus, planet);
                case SOUTH -> position.checkOutOfBoundAndMove(MoveDirection.Y_plus, planet);
                case EST -> position.checkOutOfBoundAndMove(MoveDirection.X_minus, planet);
            }
        } else {
            System.out.println("Error direction");
        }
        this.getStatus();
    }

    public void getStatus() {
        System.out.println(this.position.toString());
        System.out.println("Je suis orienté à "+this.orientation);

    }

    public boolean isObstacle() {
        final Random rd = new Random();
        return rd.nextInt(5) == 4;
    }
}