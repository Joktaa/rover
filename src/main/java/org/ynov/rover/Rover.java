package org.ynov.rover;

import org.ynov.planet.Planet;
import org.ynov.shared.Direction;
import org.ynov.shared.Orientation;
import org.ynov.shared.Position;
import org.ynov.shared.Rotation;

public class Rover {
    public Orientation orientation;
    public Position position;
    public Planet planet;

    public Rover() {
        this.orientation = Orientation.NORTH;
        this.position = new Position();
        this.planet = new Planet();
    }

    public void rotate(Rotation rotation) {
        if (rotation.equals(Rotation.RIGHT)) {
            orientation = switch (orientation) {
                case NORTH -> Orientation.EST;
                case WEST -> Orientation.NORTH;
                case SOUTH -> Orientation.WEST;
                case EST -> Orientation.SOUTH;
            };
        } else {
            orientation = switch (orientation) {
                case NORTH -> Orientation.WEST;
                case WEST -> Orientation.SOUTH;
                case SOUTH -> Orientation.EST;
                case EST -> Orientation.NORTH;
            };
        }
        this.getStatus();
    }

    public void move(Direction direction) {
        if (direction.equals(Direction.FRONT)) {
            switch (this.orientation) {
                case NORTH -> this.checkOutOfBoundAndMove(Move.Y_plus);
                case WEST -> this.checkOutOfBoundAndMove(Move.X_minus);
                case SOUTH -> this.checkOutOfBoundAndMove(Move.Y_minus);
                case EST -> this.checkOutOfBoundAndMove(Move.X_plus);
            }
        } else if (direction.equals(Direction.BEHIND)) {
            switch (this.orientation) {
                case NORTH -> this.checkOutOfBoundAndMove(Move.Y_minus);
                case WEST -> this.checkOutOfBoundAndMove(Move.X_plus);
                case SOUTH -> this.checkOutOfBoundAndMove(Move.Y_plus);
                case EST -> this.checkOutOfBoundAndMove(Move.X_minus);
            }
        } else {
            System.out.println("Error direction");
        }
        this.getStatus();
    }

    private void checkOutOfBoundAndMove(Move move) {
        switch (move) {
            case Y_plus -> {
                if (this.position.Y >= this.planet.y_size) {
                    this.position.Y = this.planet.y_size * (-1);
                } else {
                    this.position.Y++;
                }
            }
            case Y_minus -> {
                if (this.position.Y * (-1) >= this.planet.y_size) {
                    this.position.Y = this.planet.y_size;
                } else {
                    this.position.Y--;
                }
            }
            case X_plus -> {
                if (this.position.X >= this.planet.x_size) {
                    this.position.X = this.planet.x_size * (-1);
                } else {
                    this.position.X++;
                }
            }
            case X_minus -> {
                if (this.position.X * (-1) >= this.planet.x_size) {
                    this.position.X = this.planet.x_size;
                } else {
                    this.position.X--;
                }
            }
        }
    }

    private void getStatus() {
        System.out.println(this.position);
        System.out.println(this.orientation);
    }

    private enum Move {
        Y_plus,
        Y_minus,
        X_plus,
        X_minus
    }
}