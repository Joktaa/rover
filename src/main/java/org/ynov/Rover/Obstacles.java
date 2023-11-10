package org.ynov.Rover;

import java.util.Map;

public class Obstacles {
    private boolean obstacle;
    private Map<Integer,Integer> coordonnee;

    public Obstacles(boolean obstacle, Map<Integer, Integer> coordonnee) {
        this.obstacle = obstacle;
        this.coordonnee = coordonnee;
    }

    public boolean isObstacle() {
        return obstacle;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    public Map<Integer, Integer> getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(Map<Integer, Integer> coordonnee) {
        this.coordonnee = coordonnee;
    }
}
