import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ynov.Topologie.Planet;
import org.ynov.Rover.Rover;
import org.ynov.Commandes.Direction;
import org.ynov.Commandes.Orientation;
import org.ynov.Commandes.Rotation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoverTests {

    @Test
    @DisplayName("Test la fonction move(avancer)")
    void moveFrontRover() {
        Planet planet = new Planet();
        Rover rover = new Rover(planet);

        assertEquals(0, rover.getLatitude());
        rover.move(Direction.FRONT);
        assertEquals(1, rover.getLatitude());
    }

    @Test
    @DisplayName("Test la fonction move(reculer)")
    void moveBehindRover() {
        Planet planet = new Planet();
        Rover rover = new Rover(planet);

        assertEquals(0, rover.getLatitude());

        rover.move(Direction.BEHIND);

        assertEquals(-1, rover.getLatitude());
    }

    @Test
    @DisplayName("Test la fonction rotate")
    void rotateRover(){
        Planet planet = new Planet();
        Rover rover = new Rover(planet);

        rover.rotate(Rotation.RIGHT);
        assertEquals(Orientation.EST, rover.orientation);

        rover.rotate(Rotation.RIGHT);
        assertEquals(Orientation.SOUTH, rover.orientation);

        rover.rotate(Rotation.RIGHT);
        assertEquals(Orientation.WEST, rover.orientation);

        rover.rotate(Rotation.LEFT);
        assertEquals(Orientation.SOUTH, rover.orientation);
    }

    @Test
    @DisplayName("Test la fonction checkOutOfBound, doit aller à l'opposé de la planette si le robot sort des limites")
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
    @DisplayName("Test la fonction checkOutOfBound, doit aller à l'opposé de la planette si le robot sort des limites")
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
    @DisplayName("Test la fonction checkOutOfBound, doit aller à l'opposé de la planette si le robot sort des limites")
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
    @DisplayName("Test la fonction checkOutOfBound, doit aller à l'opposé de la planette si le robot sort des limites")
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


//    @Test
//    @DisplayName("Test les commandes pour le robot")
//    void command(){
//        Planet planet = new Planet();
//        Position position = new Position();
//
//        roverMock = mock(Rover.class);
//        when(roverMock.getPlanet()).thenReturn(planet);
//        when(roverMock.position).thenReturn(position);
//        when(roverMock.getLongitude()).thenReturn(0);
//        when(roverMock.isObstacle()).thenReturn(false);
//        Console console = new Console(roverMock);
//
//        console.runCommand("FFRBFFL");
//
//
//        assertEquals(2, roverMock.getLongitude());
//        assertEquals(1, roverMock.getLatitude());
//
////        Planet planet = new Planet();
////        Rover rover = new Rover(planet);
////        Console console = new Console(rover);
//
//
//
//    }
}
