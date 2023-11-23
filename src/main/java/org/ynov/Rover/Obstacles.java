package org.ynov.Rover;

import java.util.List;
import java.util.Map;

public class Obstacles {
    private boolean obstacle;
    private Map<Integer,List<Integer>> coordonnee;

    public Obstacles(boolean obstacle, Map<Integer, List<Integer>> coordonnee) {
        this.obstacle = obstacle;
        this.coordonnee = coordonnee;
    }

    public boolean isObstacle() {
        return obstacle;
    }

    public void setObstacle(boolean obstacle) {
        this.obstacle = obstacle;
    }

    public Map<Integer, List<Integer>> getCoordonnee() {
        return coordonnee;
    }

    public void setCoordonnee(Map<Integer, List<Integer>> coordonnee) {
        this.coordonnee = coordonnee;
    }
}
