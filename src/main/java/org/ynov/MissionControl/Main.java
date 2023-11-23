package org.ynov.MissionControl;

import org.ynov.CommunicationAbstraction.ICommunication;
import org.ynov.CommunicationAbstraction.IDataCallback;
import org.ynov.Rover.IRover;
import org.ynov.Rover.Rover;
import org.ynov.Socket.Communication;
import org.ynov.Topologie.Planet;

import java.io.IOException;
import java.util.Scanner;
import java.net.*;

public class Main implements IDataCallback {
    private String data;
    public void main(String[] args) {
        final Planet planet ;
        final IRover myRover;
        final Console console;
        final Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        boolean wantMap;
        boolean wantDebug;
        ICommunication communication = new Communication(2222,"localhost",1111);
        communication.listening();
        communication.setDataCallback(this);

        //si rover non déployer
        if(data.equals("blabla")){
            planet = new Planet(5, 5);
            myRover = new Rover(planet);
            console = new Console(myRover);
            wantMap = false;
            wantDebug = false;
        }else{
            planet = new Planet(5, 5);
            myRover = new Rover(planet);
            console = new Console(myRover);
            wantMap = false;
            wantDebug = false;
        }


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
    @Override
    public void onDataReceived(String data) {
        this.data = data;
    }
}
