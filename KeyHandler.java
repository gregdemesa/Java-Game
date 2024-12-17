/**
@author Gregory Miguell A. De Mesa (222017) Andrew M. Villorente (226782)
@version March 8, 2023

The provided code defines a class called "KeyHandler" that implements the KeyListener interface.
It handles keyboard input for controlling a game character or object by setting boolean variables
based on the pressed or released keys. The keyPressed method sets the corresponding boolean variable
to true when a key is pressed, while the keyReleased method sets it to false when a key is released.
This class can be used as a listener to handle keyboard events in a game or application.

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

public class KeyHandler implements KeyListener {

    public boolean up, down, left, right, northeast, shoot, reload;
    private long shootTime = 0;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {
            reload = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shootTime = System.currentTimeMillis();
            shoot = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {
            reload = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            shoot = false;
        }

    }

}