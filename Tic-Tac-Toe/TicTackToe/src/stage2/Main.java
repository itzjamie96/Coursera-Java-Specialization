package stage2;

import java.util.*;

/**
 * TicTacToe - computer's coordinates chosen randomly
 *
 * X = player = 1
 * O = computer = -1
 * _ = 0
 *
 * rather than comparing Strings to check for a bingo, sum of each row/column/diagonal was found
 * ex) if the sum of a row is 3, X wins
 * ex) if the sum of a diagonal is -3, O wins
 */


public class Main {
    public static void main(String[] args) {

        // initialize table
        int[][] table = {{0,0,0},{0,0,0},{0,0,0}};
        printTable(table);

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int playerX, playerY;
        String result;


        // arrayList to keep visited coordinates
        // the coordinates were represented with a "Coordinates" class
        List<Coordinate> visited = new ArrayList<Coordinate>();


        // variable to count number of moves
        // this cnt will be used to find out when to start checking for a winner
        int cnt = 0;


        // game starts
        while (true) {

            // get coordinates of player
            System.out.print("Enter the coordinates: ");

            // try - catch phrase used to check that the player entered valid coordinates
            try {

                Coordinate playerXY = new Coordinate(sc.nextInt() - 1, sc.nextInt() - 1);

                if (!contains(visited, playerXY)) {
                    table[playerXY.getX()][playerXY.getY()] = 1;
                    visited.add(playerXY);
                    cnt++;
                    printTable(table);
                } else {
                    continue;
                }

                // check for a winner after the player's turn
                // fyi: there can be no winner before the first player makes a third move (with 2 moves by the opponent)
                if (cnt > 4) {

                    // no more moves left = game over
                    if (cnt == 9) {
                        System.out.println(checkWinner(table));
                        break;
                    }

                    // if there still are empty coordinates left and the result of the game is draw,
                    // the game can still go on
                    result = checkWinner(table);
                    if (!result.equals("Draw")) {
                        System.out.println(result);
                        break;
                    }
                }

                // computer
                System.out.println("Making move level \"easy\"");

                // get a random Coordinate that has not been visited yet
                while (true) {
                    Coordinate randomXY = new Coordinate(random.nextInt(3), random.nextInt(3));
                    if (!contains(visited, randomXY)) {
                        table[randomXY.getX()][randomXY.getY()] = -1;
                        visited.add(randomXY);
                        cnt++;
                        printTable(table);
                        break;
                    }
                }

                // check for a winner after the computer's turn
                if (cnt > 4) {
                    if (cnt == 9) {
                        System.out.println(checkWinner(table));
                        break;
                    }

                    result = checkWinner(table);
                    if (!result.equals("Draw")) {
                        System.out.println(result);
                        break;
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                sc.nextLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sc.close();

    }

    public static void printTable(int[][] table) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == 0) {
                    System.out.print(" ");
                } else if (table[i][j] == 1){
                    System.out.print("X");
                } else {
                    System.out.print("O");
                }
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }

    public static boolean contains(List<Coordinate> visited, Coordinate xy) {

        for (Coordinate c : visited) {
            if (c.getX() == xy.getX() && c.getY() == xy.getY()) {
                return true;
            }
        }
        return false;
    }

    public static String checkWinner(int[][] table) {

        int leftSum = 0, rightSum = 0;
        for (int i = 0; i < 3; i++) {
            int rowSum = 0, colSum = 0;
            for (int j = 0; j < 3; j++) {
                rowSum += table[i][j];
                colSum += table[j][i];
                rightSum += table[j][2 - j];
            }
            rightSum = 0;
            leftSum += table[i][i];

            if (rowSum == 3 || colSum == 3 || leftSum == 3 || rightSum == 3) {
                return "X wins";
            } else if (rowSum == -3 || colSum == -3 || leftSum == -3 || rightSum == -3) {
                return "O wins";
            }
            rightSum = 0;
        }
        return "Draw";

    }

}

// coordinates class : acts like a tuple
class Coordinate {
    private int x;
    private int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}

