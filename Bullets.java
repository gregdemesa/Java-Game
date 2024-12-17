/**
@author Gregory Miguell A. De Mesa (222017) Andrew M. Villorente (226782)
@version March 8, 2023

This is the class for the Bullets. It is what draws the actual bullet which is 2 pixels long, 1 pixel high.
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
import java.awt.geom.*;

public class Bullets {
    private double x;
    private double y;
    private double angle, dx, dy;

    public Bullets(double x, double y, GameCanvas game, double mouseX, double mouseY) {
        this.x = x;
        this.y = y;
        this.dx = mouseX - x;
        this.dy = mouseY - y;

        angle = Math.toDegrees(Math.atan2(dy, dx));

    }

    public void movement() {

        double vx = 5 * Math.cos(Math.toRadians(angle));
        double vy = 5 * Math.sin(Math.toRadians(angle));
        x += vx;
        y += vy;
    }

    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        Rectangle2D.Double bullet = new Rectangle2D.Double(x, y, 2, 1);
        g2d.setColor(Color.YELLOW);
        g2d.fill(bullet);
        g2d.draw(bullet);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getMouseX() {
        return dx;
    }

    public double getMouseY() {
        return dy;
    }
}
