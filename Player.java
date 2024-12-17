/**
@author Gregory Miguell A. De Mesa (222017) Andrew M. Villorente (226782)
@version March 8, 2023

The provided code defines a class called "Player" that renders player and provides the methods player has.
**/

/*
I have not discussed the Java language code in my program 
with anyone other than my instructor or the teaching assistants 
assigned to this course.
I have not used Java language code obtained from another student, 
or any other unauthorized source, either modified or unmodified.
If any Java language code or documentation used in my program 
was obtained from another source, such as a textbook or website, 
that has been clearly noted with a proper citation in the comments 
of my program.
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Player implements PositionInterface {

    private int x, y, og;
    private double angle; // New variable to store the rotation angle
    Image player;
    private double rotationAngle, whereItsFacing;
    private int whichPlayer;
    private int health;

    public Player(int x, int y, int whichPlayer) {

        this.x = x;
        this.y = y;
        if (whichPlayer == 1) {
            player = new ImageIcon("Assets/Player.png").getImage();
        } else if (whichPlayer == 2) {
            player = new ImageIcon("Assets/Player2.png").getImage();
        }
        og = 0;
        whereItsFacing = 0;
        health = 200;
        this.whichPlayer = whichPlayer;

    }

    public void draw(Graphics2D g2d) {

        g2d.rotate(rotationAngle, x + 16 / 2.0f, y + 16 / 2.0f);
        whereItsFacing = rotationAngle;
        g2d.drawImage(player, x, y, null);
        g2d.rotate(-rotationAngle, x + 16 / 2.0f, y + 16 / 2.0f);

    }

    public boolean isNotHittingTheWall() {
        return !(this.getY() < 20 || this.getY() > 370 || this.getX() < 20 || this.getX() > 370);

    }

    public boolean isNotColliding(Walls wall, String whichWall) {
        if (whichWall == "WEST") {
            return (this.getX() + 16 <= wall.getCoordinate(0) || // west wall
                    this.getX() >= wall.getCoordinate(0) + 7 ||
                    this.getY() + 16 <= wall.getCoordinate(1) ||
                    this.getY() >= wall.getCoordinate(1) + 62);
        }
        if (whichWall == "EAST") {
            return (this.getX() + 16 <= wall.getCoordinate(2) || // east wall
                    this.getX() >= wall.getCoordinate(2) + 7 ||
                    this.getY() + 16 <= wall.getCoordinate(3) ||
                    this.getY() >= wall.getCoordinate(3) + 62);

        }
        if (whichWall == "NORTH") {
            return (this.getX() + 16 <= wall.getCoordinate(4) || // north wall
                    this.getX() >= wall.getCoordinate(4) + 62 ||
                    this.getY() + 16 <= wall.getCoordinate(5) ||
                    this.getY() >= wall.getCoordinate(5) + 7);
        }
        if (whichWall == "SOUTH") {
            return (this.getX() + 16 <= wall.getCoordinate(6) || // south wall
                    this.getX() >= wall.getCoordinate(6) + 62 ||
                    this.getY() + 16 <= wall.getCoordinate(7) ||
                    this.getY() >= wall.getCoordinate(7) + 7);
        }
        return false;
    }

    public boolean isNotCollidingWest(Walls wall) {
        return (this.getX() + 16 <= wall.getCoordinate(0) || // west wall
                this.getX() >= wall.getCoordinate(0) + 7 ||
                this.getY() + 16 <= wall.getCoordinate(1) ||
                this.getY() >= wall.getCoordinate(1) + 62);
    }

    public boolean isNotCollidingEast(Walls wall) {
        return (this.getX() + 16 <= wall.getCoordinate(2) || // east wall
                this.getX() >= wall.getCoordinate(2) + 7 ||
                this.getY() + 16 <= wall.getCoordinate(3) ||
                this.getY() >= wall.getCoordinate(3) + 62);
    }

    public boolean isNotCollidingNorth(Walls wall) {
        return (this.getX() + 16 <= wall.getCoordinate(4) || // north wall
                this.getX() >= wall.getCoordinate(4) + 62 ||
                this.getY() + 16 <= wall.getCoordinate(5) ||
                this.getY() >= wall.getCoordinate(5) + 7);
    }

    public boolean isNotCollidingSouth(Walls wall) {
        return (this.getX() + 16 <= wall.getCoordinate(6) || // south wall
                this.getX() >= wall.getCoordinate(6) + 62 ||
                this.getY() + 16 <= wall.getCoordinate(7) ||
                this.getY() >= wall.getCoordinate(7) + 7);
    }

    public void faceDirection(double degrees) {

        if (degrees == 0) {
            rotationAngle = 0; // facing east
        } else if (degrees == 90) {
            rotationAngle = 1.5; // facing south
        } else if (degrees == 180) {
            rotationAngle = 3.0;
        } else if (degrees == 270) {
            rotationAngle = 4.5;
        }
    }

    public void setPosition(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int n) {
        x = n;
    }

    public void setY(int n) {
        y = n;
    }

    public void setHealth(int n) {
        health = n;
    }

    public void setRotationAngle(double n) {
        rotationAngle = n;
    }

    public void adjustX(double distance) { // Moves the x position to the right
        x += distance;
    }

    public void adjustXleft(double distance) { // Moves the x position to the left
        x -= distance;
    }

    public void adjustY(double distance) { // Moves the y position up
        y -= distance;
    }

    public void adjustYdown(double distance) { // Moves the y position down
        y += distance;
    }

    public void rotate(int mouseX, int mouseY, boolean whichMode, double degrees) {

        if (whichMode) {
            if (degrees == 0) {
                rotationAngle = 0; // facing east
            } else if (degrees == 90) {
                rotationAngle = 1.5; // facing south
            } else if (degrees == 180) {
                rotationAngle = 3.0;
            } else if (degrees == 270) {
                rotationAngle = 4.5;
            }
        } else {

            float xDistance = mouseX - x;
            float yDistance = mouseY - y;
            rotationAngle = Math.toDegrees(Math.atan2(yDistance, xDistance));
            rotationAngle *= 0.25;

            /*
             * 
             * if (rotationAngle > 16.3) {
             * rotationAngle = 16.3; // Set the angle to 6 if it exceeds 6
             * } else if (rotationAngle < 0) {
             * rotationAngle = 0; // Set the angle to 0 if it is less than 0
             * }
             * 
             */

        }

    }

    public double whatsRotationAngle() {
        return rotationAngle;
    }

    public void gotShot() {
        health -= 10;
        System.out.println("Current health of Player " + whichPlayer + " is " + health);
    }

    public int whichPlayer() {
        return whichPlayer;
    }

    public int getHealth() {
        return health;
    }

}