/**
@author Gregory Miguell A. De Mesa (222017) Andrew M. Villorente (226782)
@version March 8, 2023

This is the class for the BulletCounter, it is what we use to draw how many bullets are left, serving as an indicator to the player.
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

public class BulletCounter {
    private int x, y, number;
    Image image;

    public BulletCounter(int x, int y) {

        this.x = x;
        this.y = y;

    }

    public void images(int number) {

        switch (number) {
            case 0:
                image = new ImageIcon("Assets/bulletcount/0.png").getImage();
                break;
            case 1:
                image = new ImageIcon("Assets/bulletcount/1.png").getImage();
                break;
            case 2:
                image = new ImageIcon("Assets/bulletcount/2.png").getImage();
                break;
            case 3:
                image = new ImageIcon("Assets/bulletcount/3.png").getImage();
                break;
            case 4:
                image = new ImageIcon("Assets/bulletcount/3.png").getImage();
                break;
            case 5:
                image = new ImageIcon("Assets/bulletcount/5.png").getImage();
                break;
            case 6:
                image = new ImageIcon("Assets/bulletcount/6.png").getImage();
                break;
            case 7:
                image = new ImageIcon("Assets/bulletcount/7.png").getImage();
                break;
            case 8:
                image = new ImageIcon("Assets/bulletcount/8.png").getImage();
                break;
            case 9:
                image = new ImageIcon("Assets/bulletcount/9.png").getImage();
                break;
            case 10:
                image = new ImageIcon("Assets/bulletcount/10.png").getImage();
                break;
            case 11:
                image = new ImageIcon("Assets/bulletcount/11.png").getImage();
                break;
            case 12:
                image = new ImageIcon("Assets/bulletcount/12.png").getImage();
                break;
            case 13:
                image = new ImageIcon("Assets/bulletcount/13.png").getImage();
                break;
            case 14:
                image = new ImageIcon("Assets/bulletcount/14.png").getImage();
                break;
            case 15:
                image = new ImageIcon("Assets/bulletcount/15.png").getImage();
                break;
            case 16:
                image = new ImageIcon("Assets/bulletcount/15.png").getImage();
                break;
            case 17:
                image = new ImageIcon("Assets/bulletcount/17.png").getImage();
                break;
            case 18:
                image = new ImageIcon("Assets/bulletcount/18.png").getImage();
                break;
            case 19:
                image = new ImageIcon("Assets/bulletcount/19.png").getImage();
                break;
            case 20:
                image = new ImageIcon("Assets/bulletcount/20.png").getImage();
                break;
            case 21:
                image = new ImageIcon("Assets/bulletcount/21.png").getImage();
                break;
            case 22:
                image = new ImageIcon("Assets/bulletcount/22.png").getImage();
                break;
            case 23:
                image = new ImageIcon("Assets/bulletcount/23.png").getImage();
                break;
            case 24:
                image = new ImageIcon("Assets/bulletcount/24.png").getImage();
                break;
            case 25:
                image = new ImageIcon("Assets/bulletcount/25.png").getImage();
                break;
            case 26:
                image = new ImageIcon("Assets/bulletcount/26.png").getImage();
                break;
            case 27:
                image = new ImageIcon("Assets/bulletcount/27.png").getImage();
                break;
            case 28:
                image = new ImageIcon("Assets/bulletcount/28.png").getImage();
                break;
            case 29:
                image = new ImageIcon("Assets/bulletcount/29.png").getImage();
                break;
            case 30:
                image = new ImageIcon("Assets/bulletcount/30.png").getImage();
                break;

        }
    }

    public void draw(Graphics2D g2d) {

        g2d.drawImage(image, x, y, null);

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

}