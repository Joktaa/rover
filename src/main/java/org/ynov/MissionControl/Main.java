package org.ynov.MissionControl;

import org.ynov.Rover.IRover;
import org.ynov.Rover.Rover;
import org.ynov.Topologie.Planet;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main{
    public static void main(String[] args) throws InterruptedException {
        final Planet planet ;
        final IRover myRover;
        final Console console = new Console();
        final Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        boolean wantMap = false;
        boolean wantDebug = false;

        // Pour avoir le temps de lancé le run du main du Rover
        TimeUnit.SECONDS.sleep(10);

        //si rover non déployer
        if(console.getData().isEmpty()){
            planet = new Planet(5, 5);
        }
        //sinon nous récuperons les données envoyées par le rover
        else{
            String[] data = console.getData().split("-");
            planet = new Planet(Integer.parseInt(data[0]) , Integer.parseInt(data[1]));
        }

        myRover = new Rover(planet);
        console.setMyRover(myRover);

        System.out.println("Imprimer une carte de la planet ? (oui/non)");
        final String carte = scanner.next().toLowerCase();
        if(carte.equals("oui")){
            wantMap = true;

            System.out.println("Debug? (oui/non)");
            final String debug = scanner.next().toLowerCase();
            wantDebug = debug.equals("oui");
        }

        if (wantMap) {
            new Carte(myRover, null, wantDebug);
        }

        while (isRunning) {
            System.out.println("Ecrire stop, une direction ou une suite de direction: (F, B, R, L)");
            final String commands = scanner.next().toLowerCase();

            isRunning = console.runCommand(commands, wantMap, wantDebug);
        }


    }
}
