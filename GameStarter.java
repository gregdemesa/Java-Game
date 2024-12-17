/**
 * @author Gregory Miguell A. De Mesa (222017) Andrew M. Villorente (226782)
 * @version March 8, 2023
 * 
 *          The provided code defines a class called "GameStarter" and it starts
 *          the code by instantiating a new GameFrame object and calling the
 *          GameFrame methods like connectToServer() and setUpGUI().
 * 
 **/

/*
 * I have not discussed the Java language code in my program
 * with anyone other than my instructor or the teaching assistants
 * assigned to this course.
 * I have not used Java language code obtained from another student,
 * or any other unauthorized source, either modified or unmodified.
 * If any Java language code or documentation used in my program
 * was obtained from another source, such as a textbook or website,
 * that has been clearly noted with a proper citation in the comments
 * of my program.
 */

public class GameStarter {

  public static void main(String[] args) {

    GameFrame gf = new GameFrame();

    gf.connectToServer();

    gf.setUpGUI();

  }
}