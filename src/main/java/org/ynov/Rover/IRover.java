package org.ynov.Rover;

import org.ynov.Commandes.Direction;
import org.ynov.Commandes.Rotation;

public interface IRover {
    void rotate(Rotation rotation);
    void move(Direction direction);
    boolean isObstacle();
    void getStatus();
}
