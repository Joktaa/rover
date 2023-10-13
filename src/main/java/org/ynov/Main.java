package org.ynov;

import org.ynov.planet.Planet;
import org.ynov.rover.IRover;
import org.ynov.rover.Rover;
import org.ynov.shared.Direction;
import org.ynov.shared.Rotation;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello rover");
        Planet planet = new Planet();
        IRover myRover = new Rover(planet);
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while(isRunning){
            System.out.println("Ecrire une direction: (front, behind, right, left)");
            String direction = scanner.next();
            switch (direction){
                case "front" -> myRover.move(Direction.FRONT);
                case "behind" -> myRover.move(Direction.BEHIND);
                case "right" -> myRover.rotate(Rotation.RIGHT);
                case "left" -> myRover.rotate(Rotation.LEFT);
                default -> System.out.println("Commande incorrecte, le rover n'a pas bougé de position");
            }
            isRunning = stopGame(scanner);
        }
    }

    private static boolean stopGame(Scanner scanner){
        System.out.println("Continuer ? (oui/non)");
        return !scanner.next().equals("non");
    }
}