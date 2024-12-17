/**
@author Gregory Miguell A. De Mesa (222017) Andrew M. Villorente (226782)
@version March 8, 2023

This is a class called "GameFrame" that extends the JFrame class and implements the Runnable interface. 
It sets up a game frame with various components such as a canvas, players, walls, and bullet controllers. 
The class includes methods for setting up the GUI, connecting to a server, handling game over conditions, 
reading and writing data to the server, and running the game loop. The game loop updates the game state, 
including player movement, bullet shooting, collision detection, and health management. 
It also plays background music during the game.
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
import java.net.*;
import java.io.*;
import javax.swing.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.sound.sampled.*;

public class GameFrame extends JFrame implements Runnable {

  GameCanvas panel;
  GameOver exit;
  int playerID;
  int FPS;
  private Thread gameThread;
  private Player player1, player2;
  private Walls wall;
  private KeyHandler keyH = new KeyHandler();
  private MouseActionHandler mouseH = new MouseActionHandler();
  private BulletController controlBullet, controlBullet2;
  private ReadFromServer rfsRunnable;
  private WriteToServer wtsRunnable;
  private boolean bulletShot, playerDead;
  private Bullets bullet;
  private BulletController bulletsControl;
  private String bullets;
  private JLabel bulletLabel;
  private long bulletHit;
  private Long reloadHit;
  int p2health;
  int other;

  GameFrame() {

    FPS = 60;
    bulletShot = false;
    playerDead = false;
    bulletHit = 0;
    panel = new GameCanvas(playerID);
    exit = new GameOver();
  }

  public void setUpGUI() {
    panel = new GameCanvas(playerID);
    player1 = panel.getPlayer();
    player2 = panel.getPlayer2();
    wall = panel.getWalls();

    controlBullet = panel.getBulletController();
    controlBullet2 = panel.GetGBPLayer2();

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(panel);

    this.startGameThread();
    this.pack();
    this.setTitle("Player #" + playerID);
    this.setLocationRelativeTo(null);
    this.setVisible(true);

    this.addKeyListener(keyH);
    this.setFocusable(true);
    this.addMouseMotionListener(mouseH);
    try {
      playMusic();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public void gameOver() {
    exit = new GameOver();
    this.add(exit);
    this.setTitle("Player #" + playerID);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(false);
  }

  public void connectToServer() {

    try {
      Scanner console = new Scanner(System.in);
      System.out.print("IP Address: ");
      String ipAddress = console.nextLine();
      System.out.print("Port Number: ");
      int portNumber = Integer.parseInt(console.nextLine());
      Socket s = new Socket(ipAddress, portNumber);
      DataInputStream in = new DataInputStream(s.getInputStream());
      DataOutputStream out = new DataOutputStream(s.getOutputStream());
      playerID = in.readInt();
      System.out.println("You are player#" + playerID);

      if (playerID == 0) {
        System.out.println("Waiting for Player#2 to connect...");

      }
      rfsRunnable = new ReadFromServer(in);
      wtsRunnable = new WriteToServer(out);
      rfsRunnable.waitForStartMsg();
    } catch (IOException e) {
      System.out.println("IOException from connectToServer()");
    }

  }

  private class ReadFromServer implements Runnable {
    private DataInputStream dataIn;

    public ReadFromServer(DataInputStream in) {
      dataIn = in;
      System.out.println("RFS Runnable Created");
    }

    public void run() {
      try {
        while (true) {
          int player2X = dataIn.readInt();
          int player2Y = dataIn.readInt();
          int mouseX2 = dataIn.readInt();
          int mouseY2 = dataIn.readInt();

          double player2Rotation = dataIn.readDouble();
          Boolean shootingBullet = dataIn.readBoolean();
          Boolean player2Dead = dataIn.readBoolean();
          p2health = dataIn.readInt();

          // System.out.println(player2X);
          // System.out.println(player2Y);
          if (player2 != null) {
            player2.setX(player2X);
            player2.setY(player2Y);
            player2.setRotationAngle(player2Rotation);
            player2.setHealth(p2health);
            double bullet_x2 = player2X + 2;
            double bullet_y2 = player2Y + 5;
            if (shootingBullet == true) {
              controlBullet2.addbullet(new Bullets(bullet_x2, bullet_y2, panel, mouseX2, mouseY2)); // print the bullets
              // of the enemy
            }

          }

        }
      } catch (IOException ex) {
        System.out.println("IOException from RFS run()");
      }

    }

    public void waitForStartMsg() {
      try {
        String startMsg = dataIn.readUTF();
        System.out.println("Message from server: " + startMsg);
        Thread readThread = new Thread(rfsRunnable);
        Thread writeThread = new Thread(wtsRunnable);
        readThread.start();
        writeThread.start();
      } catch (IOException ex) {
        System.out.println("IOException from waitForStartMsg()");
      }
    }
  }

  private class WriteToServer implements Runnable {
    private DataOutputStream dataOut;

    public WriteToServer(DataOutputStream out) {
      dataOut = out;
      System.out.println("WTS Runnable created");
    }

    public void run() {
      try {
        while (true) {
          if (player1 != null) {
            dataOut.writeInt(player1.getX());
            dataOut.writeInt(player1.getY());
            dataOut.writeInt(mouseH.mouseX());
            dataOut.writeInt(mouseH.mouseY());
            dataOut.writeDouble(player1.whatsRotationAngle());
            dataOut.writeBoolean(bulletShot);
            dataOut.writeBoolean(playerDead);
            dataOut.writeInt(player1.getHealth());

            dataOut.flush();
            bulletShot = false;
          }
          try {
            Thread.sleep(25);
          } catch (InterruptedException ex) {
            System.out.println("InterruptedException from WriteToServer.run()");
          }
        }
      } catch (IOException ex) {
        System.out.println("IOException from WriteToServer()");
      }

    }
  }

  // Below is new

  public void startGameThread() {
    gameThread = new Thread(this);
    gameThread.start();
  }

  @Override
  public void run() {
    double drawInterval = 1000000000 / FPS;
    double nextDrawTime = System.nanoTime() + drawInterval;

    while (gameThread != null) {

      // System.out.println(x);

      try {
        update();
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

      System.out.println("My Player Number is: " + playerID);

      if (playerID == 1) {
        other = 2;
      } else {
        other = 1;
      }

      System.out.println("Current Health of Player " + other + " : " + p2health);

      repaint();

      try {
        double remainingTime = nextDrawTime - System.nanoTime();
        remainingTime = remainingTime / 1000000;

        if (remainingTime < 0) {
          remainingTime = 0;
        }

        Thread.sleep((long) remainingTime);

        nextDrawTime += drawInterval;

      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }

  }

  public void update() throws Exception {

    if (player1.getHealth() < 0 || p2health < 0) {
      panel.setStatus(false);
    }

    else {

      // mouseH.currentCoordinates();
      int oldX = player1.getX();
      int oldY = player1.getY();

      File file = new File("Assets/gunsound.wav"); // https://www.youtube.com/watch?v=IMzsk1MUwo8
      AudioInputStream bgm = AudioSystem.getAudioInputStream(file);
      Clip clip = AudioSystem.getClip();

      if (player1.isNotCollidingWest(wall) &&
          player1.isNotCollidingNorth(wall) &&
          player1.isNotCollidingEast(wall) &&
          player1.isNotCollidingSouth(wall) && player1.isNotHittingTheWall()) {

        if (keyH.northeast == true) { // not working
          player1.adjustX(1);
          player1.adjustY(1);
        } else if (keyH.up == true) {
          player1.adjustY(1);
          player1.rotate(0, 0, true, 270);
        } else if (keyH.down == true) {
          player1.adjustYdown(1);
          player1.rotate(0, 0, true, 90);
        } else if (keyH.left == true) {
          player1.adjustXleft(1);
          player1.rotate(0, 0, true, 180);
        } else if (keyH.reload == true) {
          if (reloadInterval()) {
            controlBullet.resetCounter();
            reloadHit = System.currentTimeMillis();
          }
        } else if (keyH.right == true) {
          player1.adjustX(1);
          player1.rotate(0, 0, true, 0);
        } else if (keyH.shoot == true) {
          clip.open(bgm);
          if (shotInterval()) {

            double bullet_x = player1.getX() + 2;
            double bullet_y = player1.getY() + 5;
            bulletShot = true;
            double mouseX = mouseH.mouseX();
            double mouseY = mouseH.mouseY();

            clip.start();
            controlBullet.addbullet(new Bullets(bullet_x, bullet_y, panel, mouseX, mouseY));

            bulletHit = System.currentTimeMillis();

          }

        } else {
          player1.rotate(mouseH.mouseX(), mouseH.mouseY(), false, 0);
        }

      }

      else {
        if (player1.whatsRotationAngle() == 0) {
          player1.adjustXleft(1);
        }
        if (player1.whatsRotationAngle() == 3.0) {
          player1.adjustX(1);
        }
        if (player1.whatsRotationAngle() == 1.5) {
          player1.adjustY(1);
        }
        if (player1.whatsRotationAngle() == 4.5) {
          player1.adjustYdown(1);
        }
      }
      controlBullet2.isCollidingWest(wall);
      controlBullet.isCollidingWest(wall);
      controlBullet2.isHit(player1);
      controlBullet.isHit(player2);

      panel.setHealthP1(player1.getHealth());

      panel.setHealthP2(p2health);

    }

  }

  public void playMusic() throws Exception { // https://www.youtube.com/watch?v=SyZQVJiARTQ
    File file = new File("Assets/bgm.wav");
    AudioInputStream bgm = AudioSystem.getAudioInputStream(file);
    Clip clip = AudioSystem.getClip();
    clip.open(bgm);
    clip.start();
    clip.loop(Clip.LOOP_CONTINUOUSLY);
  }

  private boolean shotInterval() {
    long currentTime = System.currentTimeMillis();
    long fireRate = TimeUnit.MILLISECONDS.toSeconds(100000);

    return currentTime - bulletHit > fireRate;
  }

  private boolean reloadInterval() {
    long currentTime = System.currentTimeMillis();
    long fireRate = TimeUnit.MILLISECONDS.toSeconds(300000);

    return currentTime - bulletHit > fireRate;
  }

}