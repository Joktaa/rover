package org.ynov.Topologie;

import java.util.Random;

//Object valeur
public class Planet {
    private final int x_size;
    private final int y_size;

    private final String [][] obstacle;

    public Planet(int x, int y) {
       x_size = x;
       y_size = y;
       obstacle = this.setObstacle();
    }

    public String[][] getObstacle() {
        return obstacle;
    }

    public int getX_size() {
        return x_size;
    }

    public int getY_size() {
        return y_size;
    }

    public String[][] setObstacle() {
        String rock = "x";
        String ground = "o";
        int planetX = this.getX_size()*2+1;
        int planetY = this.getY_size()*2+1;
        Random rd = new Random();
        String [][] obstacle = new String[planetX][planetY];

        for (int i = 0; i < planetY; i++) {
            for (int j = 0; j < planetX; j++) {

                if(rd.nextInt(5) == 4){
                    obstacle[i][j] = rock;
                }else{
                    obstacle[i][j] = ground;
                }
            }
        }
        return obstacle;
    }
}
