package org.ynov.MissionControl;
import org.ynov.Rover.IRover;
import org.ynov.Rover.Obstacles;

import java.util.List;
import java.util.Map;

public class Carte {

    public Carte(IRover rover, Obstacles obstacles, boolean wantDebug) {
        this.UI(rover,obstacles,wantDebug);
    }

    public void UI(IRover rover, Obstacles obstacles, boolean wantDebug){

        //Init
        String right = ">";
        String left = "<";
        String up = "↑";
        String down = "↓";

        String border = " | ";
        String rock = "x";
        String ground = "o";

        int planetX = rover.getPlanet().getX_size()*2+1;
        int planetY = rover.getPlanet().getY_size()*2+1;

        int roverX = rover.getPosition().getX() + rover.getPlanet().getX_size();
        int roverY = -rover.getPosition().getY() + rover.getPlanet().getY_size();

        String [][] map = new String[planetX][planetY];

        //Génération de map
        for (int i = 0; i < planetY; i++) {
            for (int j = 0; j < planetX; j++) {

                if(i == (roverY) && j == (roverX)){
                    switch (rover.getOrientation()){
                        case WEST -> map[i][j] = left;
                        case EST -> map[i][j] = right;
                        case NORTH -> map[i][j] = up;
                        case SOUTH -> map[i][j] = down;
                    }
                }else{
                    map[i][j] = ground;
                }
            }
        }

        if(obstacles !=null){
            // Ajout des obstacles
            for (Map.Entry<Integer, List<Integer>> obstacle : obstacles.getCoordonnee().entrySet()) {
                for (Integer x: obstacle.getValue()) {
                    map[obstacle.getKey()][x] = rock;
                }
            }
        }


        // Affichage
        for (int i = 0; i < planetX; i++) {
            for (int j = 0; j < planetY; j++) {
                System.out.print(border +  map[i][j]);
            }
            System.out.println(border);
        }

        if(wantDebug){
            System.out.println();
            System.out.println();

            // Affichage Obstacle
            for (int i = 0; i < planetX; i++) {
                for (int j = 0; j < planetY; j++) {
                    System.out.print(border +  rover.getPlanet().getObstacle()[i][j]);
                }
                System.out.println(border);
            }
        }
    }
}
