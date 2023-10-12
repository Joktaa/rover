import org.junit.jupiter.api.Test;
import org.ynov.rover.Rover;
import org.ynov.shared.Direction;
import org.ynov.shared.Orientation;
import org.ynov.shared.Rotation;

import static org.junit.jupiter.api.Assertions.*;

public class RoverTests {

    @Test
    void moveRover() {
        Rover rover = new Rover();

        assertEquals(0, rover.position.X);
        assertEquals(0, rover.position.Y);
        assertEquals(Orientation.NORTH, rover.orientation);

        rover.move(Direction.FRONT);

        assertEquals(0, rover.position.X);
        assertEquals(1, rover.position.Y);
        assertEquals(Orientation.NORTH, rover.orientation);

        rover.move(Direction.BEHIND);

        assertEquals(0, rover.position.X);
        assertEquals(0, rover.position.Y);
        assertEquals(Orientation.NORTH, rover.orientation);
    }

    @Test
    void rotateRover(){
        Rover rover = new Rover();

        rover.rotate(Rotation.RIGHT);
        assertEquals(0, rover.position.X);
        assertEquals(0, rover.position.Y);
        assertEquals(Orientation.EST, rover.orientation);


        rover.rotate(Rotation.RIGHT);
        assertEquals(0, rover.position.X);
        assertEquals(0, rover.position.Y);
        assertEquals(Orientation.SOUTH, rover.orientation);

        rover.rotate(Rotation.RIGHT);
        assertEquals(0, rover.position.X);
        assertEquals(0, rover.position.Y);
        assertEquals(Orientation.WEST, rover.orientation);

        rover.rotate(Rotation.LEFT);
        assertEquals(0, rover.position.X);
        assertEquals(0, rover.position.Y);
        assertEquals(Orientation.SOUTH, rover.orientation);
    }

    @Test
    void checkOutOfBoundAndMoveFrontRover(){
        Rover rover = new Rover();

        for (int i = 0; i < 100; i++) {
            rover.move(Direction.FRONT);
        }
        assertEquals(100, rover.position.Y);

        rover.move(Direction.FRONT);
        assertEquals(-100, rover.position.Y);

        rover.move(Direction.FRONT);
        assertEquals(-99, rover.position.Y);
    }

    @Test
    void checkOutOfBoundAndMoveBehindRover(){
        Rover rover = new Rover();

        for (int i = 0; i < 100; i++) {
            rover.move(Direction.BEHIND);
        }
        assertEquals(-100, rover.position.Y);

        rover.move(Direction.BEHIND);
        assertEquals(100, rover.position.Y);

        rover.move(Direction.BEHIND);
        assertEquals(99, rover.position.Y);
    }

    @Test
    void checkOutOfBoundAndMoveRightFrontRover(){
        Rover rover = new Rover();

        rover.rotate(Rotation.RIGHT);

        for (int i = 0; i < 100; i++) {
            rover.move(Direction.FRONT);
        }
        assertEquals(100, rover.position.X);

        rover.move(Direction.FRONT);
        assertEquals(-100, rover.position.X);

        rover.move(Direction.FRONT);
        assertEquals(-99, rover.position.X);
    }

    @Test
    void checkOutOfBoundAndMoveRightBehindRover(){
        Rover rover = new Rover();

        rover.rotate(Rotation.RIGHT);

        for (int i = 0; i < 100; i++) {
            rover.move(Direction.BEHIND);
        }
        assertEquals(-100, rover.position.X);

        rover.move(Direction.BEHIND);
        assertEquals(100, rover.position.X);

        rover.move(Direction.BEHIND);
        assertEquals(99, rover.position.X);
    }
}
