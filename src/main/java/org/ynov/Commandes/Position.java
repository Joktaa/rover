package org.ynov.Commandes;

import org.ynov.Topologie.Planet;

//Objet valeur
public class Position {
    private int X;
    private int Y;

    public Position() {
        X = 0;
        Y = 0;
    }

    public void checkOutOfBoundAndMove(final MoveDirection move, final Planet planet) {
        switch (move) {
            case Y_plus -> Y = (Y >= planet.y_size) ? planet.y_size * (-1) : Y+1;
            case Y_minus -> Y = (Y * (-1) >= planet.y_size) ? planet.y_size : Y-1;
            case X_plus -> X = (X >= planet.x_size) ? planet.x_size * (-1) : X+1;
            case X_minus -> X = (X * (-1) >= planet.x_size) ? planet.x_size : X-1;
        }
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public String toString(){
        return "Ma position est : X = " + this.X + " Y = " + this.Y;
    }
}