package org.ynov.rover;

import org.ynov.shared.Direction;
import org.ynov.shared.Rotation;

public interface IRover {
    void rotate(Rotation rotation);
    void move(Direction direction);
    boolean isObstacle();
    void getStatus();
}
