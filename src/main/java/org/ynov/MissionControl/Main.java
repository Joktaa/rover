package org.ynov.MissionControl;

import org.ynov.Rover.IRover;
import org.ynov.Rover.Rover;
import org.ynov.Topologie.Planet;

public class Main {
    public static void main(String args[])
    {
        // socket info before
        Planet planet= new Planet(5,5);
        final IRover myRover = new Rover(planet);
        Carte carte = new Carte(myRover, null);
    }
}
