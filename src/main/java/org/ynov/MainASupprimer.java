package org.ynov;

import org.ynov.MissionControl.Carte;
import org.ynov.MissionControl.Console;
import org.ynov.Rover.IRover;
import org.ynov.Rover.Rover;
import org.ynov.Topologie.Planet;

import java.util.Scanner;

public class MainASupprimer {
    public static void main(String[] args) {
        final Planet planet = new Planet(5, 5);
        final IRover myRover = new Rover(planet);
        final Console console = new Console(myRover);
        final Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        boolean wantMap;
        boolean wantDebug;

        System.out.println("Imprimer une carte de la planet ? (oui/non)");
        final String carte = scanner.next().toLowerCase();
        wantMap = carte.equals("oui");

        System.out.println("Debug? (oui/non)");
        final String debug = scanner.next().toLowerCase();
        wantDebug = debug.equals("oui");

        if (wantMap) {
            new Carte(myRover, null);
        }

        while (isRunning) {
            System.out.println("Ecrire stop, une direction ou une suite de direction: (F, B, R, L)");
            final String commands = scanner.next().toLowerCase();

            isRunning = console.runCommand(commands, wantMap);
        }
    }
}