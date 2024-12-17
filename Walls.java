/**
@author Gregory Miguell A. De Mesa (222017) Andrew M. Villorente (226782)
@version March 8, 2023

This is the class for the Walls of the map, the coordinates of the walls are placed
inside an Arraylist of type Walls.
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

public class Walls {
    private int x, y;
    Image walls;
    private ArrayList<Integer> wallsOne = new ArrayList<Integer>();

    public Walls(int x, int y) {
        this.x = x;
        this.y = y;
        walls = new ImageIcon("Assets//Walls.png").getImage();
        wallsOne.addAll(Arrays.asList(60, 168, 332, 168, 166, 56, 165, 332)); // In Order: WestWallX, WestWallY,
                                                                              // EastWallX, EastWallY, NorthWallX,
                                                                              // NorthWallY, SouthWallX, SouthWallY;
    }

    public void draw(Graphics2D g) {
        g.drawImage(walls, x, y, null);
    }

    public int getCoordinate(int indexValue) {
        return wallsOne.get(indexValue);
    }

}
