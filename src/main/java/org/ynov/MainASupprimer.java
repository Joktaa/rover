package org.ynov;

import org.ynov.Topologie.Planet;
import org.ynov.Rover.IRover;
import org.ynov.Rover.Rover;
import org.ynov.MissionControl.Console;
import java.util.Scanner;

public class MainASupprimer {
    public static void main(String[] args) {
        final Planet planet = new Planet();
        final IRover myRover = new Rover(planet);
        final Console console = new Console(myRover);
        final Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while(isRunning){
            System.out.println("Ecrire stop, une direction ou une suite de direction: (F, B, R, L)");
            final String commands = scanner.next();
            isRunning = console.runCommand(commands);
        }
    }
}