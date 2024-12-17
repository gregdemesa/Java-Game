/**
@author Gregory Miguell A. De Mesa (222017) Andrew M. Villorente (226782)
@version March 8, 2023

This is the class for the GameCanvas, it works as the "Canvas" where we draw our different objects like the walls, floors, and actual players.
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
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import javax.swing.*;

public class GameCanvas extends JPanel {

	private static final ImageObserver ull = null;
	final int PANEL_WIDTH = 400;
	final int PANEL_HEIGHT = 420;
	Thread gameThread;
	Player player, player2;
	Image backgroundImage, bulletImage;
	Walls wall;
	GameOver gameOver;
	KeyHandler keyH = new KeyHandler();
	MouseActionHandler mouseH = new MouseActionHandler();
	BulletController controlBullet, controlBulletP2;
	private ArrayList<Heart> heart = new ArrayList<Heart>();
	private ArrayList<Heart> heartP2 = new ArrayList<Heart>();
	int x = 0;
	int y = 0;
	int FPS = 60;
	int x2, y2;
	int counter;
	int heartX, heartY, heartP2X, heartP2Y;
	int heartsToDraw, bulletcounter, heartsToDrawP2;
	boolean gameStatus;

	String bullets;
	BulletCounter count;
	int correctHealthP1, correctHealthP2;

	private boolean collisionON;

	GameCanvas(int playerID) {
		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.setBackground(Color.black);

		if (playerID == 1) {
			player = new Player(20, 20, 1);
			player2 = new Player(350, 350, 2);
		} else {
			player2 = new Player(20, 20, 1);
			player = new Player(350, 350, 2);
		}
		backgroundImage = new ImageIcon("Assets/Map2.png").getImage();
		wall = new Walls(0, 0);
		controlBullet = new BulletController(this, mouseH);
		controlBulletP2 = new BulletController(this, mouseH);
		bulletImage = new ImageIcon("Assets/ammoCount.png").getImage();
		bulletcounter = controlBullet.counter();

		count = new BulletCounter(135, 391);

		counter = 0;
		heartX = 10;
		heartY = 394;
		heartP2X = 280;
		heartP2Y = 394;

		for (int i = 0; i < 20; i++) {
			if (i == 10) {
				heartX = 10;
				heartY = 404;
				heartP2X = 280;
				heartP2Y = 404;
			}

			heart.add(new Heart(heartX, heartY));
			heartX += 11;
			heartP2.add(new Heart(heartP2X, heartP2Y));
			heartP2X += 11;
		}

		gameOver = new GameOver();

		gameStatus = true;

	}

	public void paint(Graphics g) {
		super.paint(g); // paint background

		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(backgroundImage, 0, 0, null);

		if (gameStatus) {

			player.draw(g2D);
			player2.draw(g2D); // this is where you add the if conditional, if(health < 0): remove player and
								// draw game over bar
			wall.draw(g2D);
			controlBullet.render(g2D);
			controlBulletP2.render(g2D);
			g2D.drawImage(bulletImage, 115, 388, null);

			bulletcounter = controlBullet.counter();
			count.images(bulletcounter);
			count.draw(g2D);

			heartsToDraw = correctHealthP1 / 10;
			for (int i = 0; i < heartsToDraw; i++) {
				heart.get(i).draw(g2D);
			}

			heartsToDrawP2 = correctHealthP2 / 10;
			for (int i = 0; i < heartsToDrawP2; i++) {
				heartP2.get(i).draw(g2D);
			}

		}

		if (gameStatus == false) {
			gameOver.draw(g2D);
		}

	}

	public void setHealthP1(int correctHealth) {
		correctHealthP1 = correctHealth;
	}

	public void setHealthP2(int correctHealth) {
		correctHealthP2 = correctHealth;
	}

	public Player getPlayer() {
		return player;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Walls getWalls() {
		return wall;
	}

	public BulletController getBulletController() {
		return controlBullet;
	}

	public BulletController GetGBPLayer2() {
		return controlBulletP2;
	}

	public void setStatus(boolean correct) {
		gameStatus = correct;
	}

}