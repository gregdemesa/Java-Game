/**
@author Gregory Miguell A. De Mesa (222017) Andrew M. Villorente (226782)
@version March 8, 2023

This is the class for the heart
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

public class Heart {

    private int x, y;
    Image heart;

    public Heart(int x, int y) {

        this.x = x;
        this.y = y;
        heart = new ImageIcon("Assets/Heart.png").getImage();

    }

    public void draw(Graphics2D g2d) {

        g2d.drawImage(heart, x, y, null);

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