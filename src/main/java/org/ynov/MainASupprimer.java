package org.ynov;

import org.ynov.CommunicationAbstraction.ICommunicationClient;
import org.ynov.MissionControl.Carte;
import org.ynov.Socket.SocketClient;
import org.ynov.Topologie.Planet;
import org.ynov.Rover.IRover;
import org.ynov.Rover.Rover;
import org.ynov.MissionControl.Console;
import java.util.Scanner;

public class MainASupprimer {
    public static void main(String[] args) {
        final Planet planet = new Planet(5,5);
        final IRover myRover = new Rover(planet);
        final Console console = new Console(myRover);
        final Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        boolean wantMap;
//        ICommunicationClient com = new SocketClient();
//        com.send("hello there");

        System.out.println("Imprimer une carte de la planet ? (oui/non)");
        final String carte = scanner.next();
        wantMap = carte.equals("oui");
        if(wantMap){
            new Carte(myRover);
        }


        while(isRunning){
            System.out.println("Ecrire stop, une direction ou une suite de direction: (F, B, R, L)");
            final String commands = scanner.next();

            isRunning = console.runCommand(commands,wantMap);
        }
    }
}