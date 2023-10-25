package org.ynov;

import org.ynov.planet.Planet;
import org.ynov.rover.IRover;
import org.ynov.rover.Rover;
import org.ynov.shared.Console;
import java.util.Scanner;

public class Main {
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