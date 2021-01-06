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

        int playerX, playerY, randomIndex;
        int[] computerChoice;
        String result;

        // array to keep possible coordinates for the computer
        // coordinates were represented with an int array
        ArrayList<int[]> possibleCoordinates = new ArrayList<int[]>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                possibleCoordinates.add(new int[]{i,j});
            }
        }

        // variable to count number of moves
        // this cnt will be used to find out when to start checking for a winner
        int cnt = 0;
        
        
        // game starts
        while (true) {

            // get coordinates of player
            System.out.print("Enter the coordinates: ");
            
            // try - catch phrase used to check that the player entered valid coordinates
            try {
                playerX = sc.nextInt() - 1;
                playerY = sc.nextInt() - 1;

                if (table[playerX][playerY] == 0) {
                    table[playerX][playerY] = 1;
                    
                    // the player's coordinates must be removed from the possibleCoordinates list
                    for (int i = 0; i < possibleCoordinates.size(); i++) {
                        int[] tmp = possibleCoordinates.get(i);
                        if (tmp[0] == playerX && tmp[1] == playerY) {
                            possibleCoordinates.remove(i);
                        }
                    }
                    cnt++;
                    printTable(table);
                }

                // check for a winner after the player's turn
                // fyi: there can be no winner before the first player makes a third move (with 2 moves by the opponent)
                if (cnt > 4) {
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
                
                // get a random number within the size of the possibleCoordinates list
                // = computer's coordinates
                randomIndex = random.nextInt(possibleCoordinates.size());
                computerChoice = possibleCoordinates.get(randomIndex);
                table[computerChoice[0]][computerChoice[1]] = -1;
                cnt++;
                printTable(table);
                possibleCoordinates.remove(randomIndex);

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

    public static String checkWinner(int[][] table) {
        
        int rowSum = 0, colSum = 0, leftSum = 0, rightSum = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rowSum += table[i][j];
                colSum += table[j][i];
                rightSum += table[j][2 - j];
            }
            leftSum += table[i][i];

            if (rowSum == 3 || colSum == 3 || leftSum == 3 || rightSum == 3) {
                return "X wins";
            } else if (rowSum == -3 || colSum == -3 || leftSum == -3 || rightSum == -3) {
                return "O wins";
            }
        }
        return "Draw";

    }


}
