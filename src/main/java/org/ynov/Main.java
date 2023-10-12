package org.ynov;

import org.ynov.rover.Rover;
import org.ynov.shared.Direction;
import org.ynov.shared.Rotation;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello rover");
        Rover myRover = new Rover();

        Scanner scanner = new Scanner(System.in);
        boolean run = true;
        while(run){
            System.out.println("Ecrire une direction: (front,behind,right,left)");
            String direction = scanner.next();
            if(direction.equals("front")){
                myRover.move(Direction.FRONT);
            }
            else if (direction.equals("behind")){
                myRover.move(Direction.BEHIND);
            }
            else if (direction.equals("right")){
                myRover.rotate(Rotation.RIGHT);
            }
            else if (direction.equals("left")){
                myRover.rotate(Rotation.LEFT);
            }
            else{
                System.out.println("Commande incorrecte, le rover n'a pas bougé de position");
            }
            run = stopGame(scanner);
        }
    }
    
    private static boolean stopGame(Scanner scanner){
        System.out.println("Stop (oui/non)");
        if(scanner.next().equals("non")){
            return false;
        }
        return true;
    }
}