/**
@author Gregory Miguell A. De Mesa (222017) Andrew M. Villorente (226782)
@version March 8, 2023

The provided code defines a class called "GameServer" that acts as a server for a multiplayer game.
It uses sockets and streams to establish connections with multiple clients. The server has a maximum
player limit of 2. It initializes variables related to player positions, health, rotations, mouse
coordinates, bullet shots, and player status. In the constructor, it creates a ServerSocket on port 49501.
The method "acceptConnections()" accepts incoming connections from clients and assigns them player numbers.
It creates separate threads for reading and writing data to/from each player. The inner class "ReadFromClient"
implements the Runnable interface and handles reading player data from the client. The inner class "WriteToClient"
implements the Runnable  interface and handles writing game data to the client. The "sendStartMsg()" method sends
a start message to the clients when both players are connected. The "main" method creates an instance of GameServer
and starts accepting connections.

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

import java.net.*;
import java.io.*;

public class GameServer {
    private ServerSocket ss;
    private int numPlayers;
    private int maxPlayers;

    private Socket p1Socket;
    private Socket p2Socket;
    private ReadFromClient p1ReadRunnable;
    private ReadFromClient p2ReadRunnable;
    private WriteToClient p1WriteRunnable;
    private WriteToClient p2WriteRunnable;
    private int p1x, p2x, p1y, p2y, p1health, p2health;
    private double p1Rotation, p2Rotation, bullet1x, bullet2x, bullet1y, bullet2y;
    private int mouse1x, mouse1y, mouse2x, mouse2y;
    private boolean bulletShotP1, bulletShotP2, player1Dead, player2Dead;

    public GameServer() {
        numPlayers = 0;
        maxPlayers = 2;
        p1x = 20;
        p1y = 20;
        p2x = 350;
        p2y = 350;
        p1health = 200;
        p2health = 200;
        try {
            ss = new ServerSocket(49501);

        } catch (IOException e) {
            System.out.println("Server Error!");

        }

    }

    public void acceptConnections() {
        try {
            System.out.println("Waiting for connections...");

            while (numPlayers < maxPlayers) {
                Socket s = ss.accept();
                DataInputStream in = new DataInputStream(s.getInputStream());
                DataOutputStream out = new DataOutputStream(s.getOutputStream());
                numPlayers++;
                out.writeInt(numPlayers);
                System.out.println("Player #" + numPlayers + "has connected");

                ReadFromClient rfc = new ReadFromClient(numPlayers, in);
                WriteToClient wtc = new WriteToClient(numPlayers, out);

                if (numPlayers == 1) {
                    p1Socket = s;
                    p1ReadRunnable = rfc;
                    p1WriteRunnable = wtc;
                } else {
                    p2Socket = s;
                    p2ReadRunnable = rfc;
                    p2WriteRunnable = wtc;
                    p1WriteRunnable.sendStartMsg();
                    p2WriteRunnable.sendStartMsg();
                    Thread readThread1 = new Thread(p1ReadRunnable);
                    Thread readThread2 = new Thread(p2ReadRunnable);
                    readThread1.start();
                    readThread2.start();
                    Thread writeThread1 = new Thread(p1WriteRunnable);
                    Thread writeThread2 = new Thread(p2WriteRunnable);
                    writeThread1.start();
                    writeThread2.start();
                }

            }
            System.out.println("No longer accepting connections");
        } catch (IOException e) {
            System.out.println("IOException from acceptConnections");
        }
    }

    private class ReadFromClient implements Runnable {
        private int playerID;
        private DataInputStream dataIn;

        public ReadFromClient(int pId, DataInputStream in) {
            playerID = pId;
            dataIn = in;
            System.out.println("RFC" + playerID + " Runnable created");
        }

        public void run() {
            try {
                while (true) {
                    if (playerID == 1) {
                        p1x = dataIn.readInt();
                        p1y = dataIn.readInt();
                        mouse1x = dataIn.readInt();
                        mouse1y = dataIn.readInt();
                        p1Rotation = dataIn.readDouble();
                        bulletShotP1 = dataIn.readBoolean();
                        player1Dead = dataIn.readBoolean();
                        p1health = dataIn.readInt();

                    } else {
                        p2x = dataIn.readInt();
                        p2y = dataIn.readInt();
                        mouse2x = dataIn.readInt();
                        mouse2y = dataIn.readInt();
                        p2Rotation = dataIn.readDouble();
                        bulletShotP2 = dataIn.readBoolean();
                        player2Dead = dataIn.readBoolean();
                        p2health = dataIn.readInt();

                    }

                }
            } catch (IOException ex) {
                System.out.println("IOException from RFC run()");
            }
        }
    }

    private class WriteToClient implements Runnable {
        private int playerID;
        private DataOutputStream dataOut;

        public WriteToClient(int pId, DataOutputStream out) {
            playerID = pId;
            dataOut = out;
            System.out.println("WTC" + playerID + " Runnable created");
        }

        public void run() {
            try {
                while (true) {
                    if (playerID == 1) {
                        dataOut.writeInt(p2x);
                        dataOut.writeInt(p2y);
                        dataOut.writeInt(mouse2x);
                        dataOut.writeInt(mouse2y);
                        dataOut.writeDouble(p2Rotation);
                        dataOut.writeBoolean(bulletShotP2);
                        dataOut.writeBoolean(player2Dead);
                        dataOut.writeInt(p2health);

                        dataOut.flush();
                    } else {
                        dataOut.writeInt(p1x);
                        dataOut.writeInt(p1y);
                        dataOut.writeInt(mouse1x);
                        dataOut.writeInt(mouse1y);
                        dataOut.writeDouble(p1Rotation);
                        dataOut.writeBoolean(bulletShotP1);
                        dataOut.writeBoolean(player1Dead);
                        dataOut.writeInt(p1health);

                        dataOut.flush();
                    }
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException ex) {
                        System.out.println("InterruptedException from WriteToClient");
                    }
                }

            } catch (IOException ex) {
                System.out.println("IOException from WriteToClient");
            }
        }

        public void sendStartMsg() {
            try {
                dataOut.writeUTF("We now have 2 players. GGS!");
            } catch (IOException ex) {
                System.out.println("IOException from sendStartMsg()");
            }
        }

    }

    public static void main(String[] args) {
        GameServer gs = new GameServer();
        gs.acceptConnections();
    }
}
