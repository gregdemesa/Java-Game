/**
@author Gregory Miguell A. De Mesa (222017) Andrew M. Villorente (226782)
@version March 8, 2023

The given code defines a class called "GameOver" that extends JPanel. It sets the preferred size of 
the panel to 400x420 pixels and sets the background color to black.
It loads two images, "Map2.png" and "GameOver.png", using the ImageIcon class. 
The "draw" method is responsible for rendering the images on the panel using the 
provided Graphics2D object. The background image is drawn at the coordinates (0, 0),
and the "gameOver" image is drawn at the coordinates (0, 100).
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
import java.awt.image.ImageObserver;

import javax.swing.*;
import java.util.*;

public class GameOver extends JPanel {
    Image bg, gameOver;

    public GameOver() {
        this.setPreferredSize(new Dimension(400, 420));
        this.setBackground(Color.black);
        bg = new ImageIcon("Assets/Map2.png").getImage();
        gameOver = new ImageIcon("Assets/GameOver.png").getImage();

    }

    public void draw(Graphics2D g) {
        super.paint(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(bg, 0, 0, null);
        g2D.drawImage(gameOver, 0, 0, null);
    }

}