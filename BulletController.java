/**
@author Gregory Miguell A. De Mesa (222017) Andrew M. Villorente (226782)
@version March 8, 2023

This is the class for the BulletController. It is what we use to act as a middleman between the GameFrame/GameCanvas and the actual bullets. Because it is
a linked list, it gives us a lot more flexibility to make changes to our overall program.
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

import java.util.ArrayList;
import java.awt.*;

public class BulletController {
    private ArrayList<Bullets> b = new ArrayList<Bullets>();

    Bullets TempBullets;
    GameCanvas game;
    Walls wall;
    double mouseX, mouseY;
    int counter, bullets;
    int currentBullets;

    public BulletController(GameCanvas game, MouseActionHandler mouse) {
        this.game = game;
        counter = 0;
        currentBullets = 0;
    }

    public void render(Graphics2D g) {
        for (int i = 0; i < b.size(); i++) {
            TempBullets = b.get(i);
            if (TempBullets.getY() < 20 || TempBullets.getY() > 380) {
                removeBullet(TempBullets);
            } else if (TempBullets.getX() < 20 || TempBullets.getX() > 380) {
                removeBullet(TempBullets);
            }

            TempBullets.movement();
            TempBullets.render(g);
        }
    }

    public void addbullet(Bullets block) {
        if (counter < 31) {
            b.add(block);
            System.out.println("Current Number of Bullets Drawn: " + counter);
            counter++;
        }

    }

    public void removeBullet(Bullets block) {
        b.remove(block);
    }

    public int counter() {

        if (counter == 0) {
            currentBullets = 30;

        } else {
            int bullets = 30 - counter;
            currentBullets = bullets;

        }

        return currentBullets;

    }

    public void resetCounter() {
        counter = 0;
    }

    public void isCollidingWest(Walls wall) {

        for (int i = 0; i < b.size(); i++) {

            TempBullets = b.get(i);

            if (TempBullets.getX() + 2 <= wall.getCoordinate(0) || TempBullets.getX() >= wall.getCoordinate(0) + 7
                    || TempBullets.getY() + 2 <= wall.getCoordinate(1)
                    || TempBullets.getY() >= wall.getCoordinate(1) + 62) {

            } else {
                removeBullet(TempBullets);
            }

            if (TempBullets.getX() + 2 <= wall.getCoordinate(2) || TempBullets.getX() >= wall.getCoordinate(2) + 7
                    || TempBullets.getY() + 2 <= wall.getCoordinate(3)
                    || TempBullets.getY() >= wall.getCoordinate(3) + 62) {

            } else {
                removeBullet(TempBullets);
            }
            if (TempBullets.getX() + 2 <= wall.getCoordinate(4) || TempBullets.getX() >= wall.getCoordinate(4) + 62
                    || TempBullets.getY() + 2 <= wall.getCoordinate(5)
                    || TempBullets.getY() >= wall.getCoordinate(5) + 7) {

            } else {
                removeBullet(TempBullets);
            }
            if (TempBullets.getX() + 2 <= wall.getCoordinate(6) || TempBullets.getX() >= wall.getCoordinate(6) + 62
                    || TempBullets.getY() + 2 <= wall.getCoordinate(7)
                    || TempBullets.getY() >= wall.getCoordinate(7) + 7) {

            } else {
                removeBullet(TempBullets);
            }

        }

    }

    public void isHit(Player player) {

        for (int i = 0; i < b.size(); i++) {
            TempBullets = b.get(i);

            if (TempBullets.getX() + 2 <= Double.valueOf(player.getX())
                    || TempBullets.getX() >= Double.valueOf(player.getX() + 16)
                    || TempBullets.getY() + 2 <= Double.valueOf(player.getY())
                    || TempBullets.getY() >= Double.valueOf(player.getY() + 16)) {

            } else {
                removeBullet(TempBullets);
                player.gotShot();
            }

        }

    }

}
