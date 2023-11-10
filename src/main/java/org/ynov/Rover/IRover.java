package org.ynov.Rover;

import org.ynov.Commandes.Direction;
import org.ynov.Commandes.Orientation;
import org.ynov.Commandes.Position;
import org.ynov.Commandes.Rotation;
import org.ynov.Topologie.Planet;

public interface IRover {
    void rotate(Rotation rotation);
    void move(Direction direction);
    Obstacles isObstacle(char nextMouv, Obstacles obstacles);
    void getStatus();

    Planet getPlanet();

    Orientation getOrientation();

    Position getPosition();
}
