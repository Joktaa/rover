package org.ynov.Rover;

import org.ynov.CommunicationAbstraction.ICommunication;
import org.ynov.MissionControl.Carte;
import org.ynov.MissionControl.Console;
import org.ynov.Socket.Communication;
import org.ynov.Topologie.Planet;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final Planet planet = new Planet(5, 5);
        final IRover myRover = new Rover(planet);
        ICommunication communication = new Communication(1111,"localhost",2222);
        communication.send(planet.getX_size() + "-" + planet.getY_size());
    }
}
