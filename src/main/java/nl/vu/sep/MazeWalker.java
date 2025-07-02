package nl.vu.sep;

import nl.vu.sep.engine.MazeEngine;
import nl.vu.sep.entities.enums.Direction;

import java.util.Calendar;
import java.util.Scanner;

public class MazeWalker {

    private static final String TERMINAL_STRING = "!quit";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MazeEngine engine = null;
        int moveCount = 0;

        System.out.println("Welcome to the Maze Walker!");

        while(engine == null) {
            try {
                System.out.println("Select your maze size: ");
                if(scanner.hasNext(TERMINAL_STRING)) {
                    System.out.println("Thanks for playing.");
                    return;
                }
                int size = scanner.nextInt();
                engine = new MazeEngine(Calendar.getInstance().getTimeInMillis(), size);
            } catch (NumberFormatException e) {
                System.out.println("You must input a number!");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input! Message: " + e.getMessage());
            }
        }

        System.out.println("Your board is: ");
        System.out.println(engine.getMaze());

        while (!engine.isPlayerInEndOfMaze()) {
            System.out.println("Select a direction to walk: ");
            boolean waitingToMove = true;

            while(waitingToMove) {
                if(scanner.hasNext(TERMINAL_STRING)) {
                    System.out.println("Thanks for playing.");
                    return;
                }
                char letter = scanner.next().charAt(0);
                try {
                    Direction direction = Direction.getDirectionFromLetter(letter);
                    engine.movePlayer(direction);
                    waitingToMove = false;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid input! Message: " + e.getMessage());
                }
            }

            moveCount++;

            System.out.println("\n=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("Your board is: \n");
            System.out.println(engine.getMaze());
            System.out.println("Move count: " + moveCount);
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        }

        System.out.println("Congrats on finishing the maze!");
        System.out.println("It took you " + moveCount + " moves!");

        scanner.close();
    }

}
