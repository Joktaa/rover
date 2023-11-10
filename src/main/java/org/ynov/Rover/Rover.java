package org.ynov.Rover;

import org.ynov.Commandes.*;
import org.ynov.CommunicationAbstraction.ICommunication;
import org.ynov.CommunicationAbstraction.IDataCallback;
import org.ynov.Configuration.Configuration;
import org.ynov.Socket.Communication;
import org.ynov.Topologie.Planet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Objet valeur
public class Rover implements IRover, IDataCallback {
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
        System.out.println("Je suis orienté à " + this.orientation);

    }

    public Obstacles isObstacle(char nextMouv, Obstacles obstacles) {

        int x = this.position.getX() + this.planet.getX_size();
        int y = 0;
        if(this.position.getY()>0){
            y = this.planet.getY_size() - this.position.getY();
        }else{
            y = (this.position.getY() * -1) + this.planet.getY_size();
        }


        switch (nextMouv) {
            case 'F' -> {
                switch (this.orientation) {
                    case NORTH -> y = y-1;
                    case WEST -> x = x-1 ;
                    case SOUTH -> y = y+1;
                    case EST -> x = x+1;
                }
            }
            case 'B' -> {
                switch (this.orientation) {
                    case NORTH -> y = y+1 ;
                    case WEST -> x= x+1 ;
                    case SOUTH -> y = y-1;
                    case EST -> x = x-1;
                }
            }
        }

        String [][] obst = this.planet.getObstacle();



        if(obst[y][x].equals("x")){
            System.out.println("Obstacle rencontré à la position");
            obstacles.setObstacle(true);
            Map<Integer, List<Integer>> obstacle = obstacles.getCoordonnee();
            if(obstacle.get(y) == null){
                List<Integer> list = new ArrayList<>();
                obstacle.put(y,list);

            }
            obstacle.get(y).add(x);


            obstacles.setCoordonnee(obstacle);
            return obstacles;
        }else{
            obstacles.setObstacle(false);
            return obstacles;
        }
    }
}