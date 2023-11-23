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
        final Console console = new Console(myRover);
        final Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        boolean wantMap = false;
        boolean wantDebug = false;
        ICommunication communication = new Communication(1111,"localhost",2222);
        communication.send("blabla");
    }
}
