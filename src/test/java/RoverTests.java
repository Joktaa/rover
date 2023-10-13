import org.junit.jupiter.api.Test;
import org.ynov.planet.Planet;
import org.ynov.rover.Rover;
import org.ynov.shared.Direction;
import org.ynov.shared.Orientation;
import org.ynov.shared.Rotation;

import static org.junit.jupiter.api.Assertions.*;

public class RoverTests {

    @Test
    void moveRover() {
        Planet planet = new Planet();
        Rover rover = new Rover(planet);

        assertEquals(0, rover.getLongitude());
        assertEquals(0, rover.getLatitude());
        assertEquals(Orientation.NORTH, rover.orientation);

        rover.move(Direction.FRONT);

        assertEquals(0, rover.getLongitude());
        assertEquals(1, rover.getLatitude());
        assertEquals(Orientation.NORTH, rover.orientation);

        rover.move(Direction.BEHIND);

        assertEquals(0, rover.getLongitude());
        assertEquals(0, rover.getLatitude());
        assertEquals(Orientation.NORTH, rover.orientation);
    }

    @Test
    void rotateRover(){
        Planet planet = new Planet();
        Rover rover = new Rover(planet);

        rover.rotate(Rotation.RIGHT);
        assertEquals(0, rover.getLongitude());
        assertEquals(0, rover.getLatitude());
        assertEquals(Orientation.EST, rover.orientation);

        rover.rotate(Rotation.RIGHT);
        assertEquals(0, rover.getLongitude());
        assertEquals(0, rover.getLatitude());
        assertEquals(Orientation.SOUTH, rover.orientation);

        rover.rotate(Rotation.RIGHT);
        assertEquals(0, rover.getLongitude());
        assertEquals(0, rover.getLatitude());
        assertEquals(Orientation.WEST, rover.orientation);

        rover.rotate(Rotation.LEFT);
        assertEquals(0, rover.getLongitude());
        assertEquals(0, rover.getLatitude());
        assertEquals(Orientation.SOUTH, rover.orientation);
    }

    @Test
    void checkOutOfBoundAndMoveFrontRover(){
        Planet planet = new Planet();
        Rover rover = new Rover(planet);

        for (int i = 0; i < 100; i++) {
            rover.move(Direction.FRONT);
        }
        assertEquals(100, rover.getLatitude());

        rover.move(Direction.FRONT);
        assertEquals(-100, rover.getLatitude());

        rover.move(Direction.FRONT);
        assertEquals(-99, rover.getLatitude());
    }

    @Test
    void checkOutOfBoundAndMoveBehindRover(){
        Planet planet = new Planet();
        Rover rover = new Rover(planet);

        for (int i = 0; i < 100; i++) {
            rover.move(Direction.BEHIND);
        }
        assertEquals(-100, rover.getLatitude());

        rover.move(Direction.BEHIND);
        assertEquals(100, rover.getLatitude());

        rover.move(Direction.BEHIND);
        assertEquals(99, rover.getLatitude());
    }

    @Test
    void checkOutOfBoundAndMoveRightFrontRover(){
        Planet planet = new Planet();
        Rover rover = new Rover(planet);

        rover.rotate(Rotation.RIGHT);

        for (int i = 0; i < 100; i++) {
            rover.move(Direction.FRONT);
        }
        assertEquals(100, rover.getLongitude());

        rover.move(Direction.FRONT);
        assertEquals(-100, rover.getLongitude());

        rover.move(Direction.FRONT);
        assertEquals(-99, rover.getLongitude());
    }

    @Test
    void checkOutOfBoundAndMoveRightBehindRover(){
        Planet planet = new Planet();
        Rover rover = new Rover(planet);

        rover.rotate(Rotation.RIGHT);

        for (int i = 0; i < 100; i++) {
            rover.move(Direction.BEHIND);
        }
        assertEquals(-100, rover.getLongitude());

        rover.move(Direction.BEHIND);
        assertEquals(100, rover.getLongitude());

        rover.move(Direction.BEHIND);
        assertEquals(99, rover.getLongitude());
    }
}
