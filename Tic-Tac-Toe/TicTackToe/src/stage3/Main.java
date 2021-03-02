package stage3;

/*
	Objectives
	In this stage, your program should:
	
	Take a string entered by the user and print the game grid as in the previous stage.
	Analyze the game state and print the result. Possible states:
	Game not finished when neither side has three in a row but the grid still has empty cells.
	Draw when no side has a three in a row and the grid has no empty cells.
	X wins when the grid has three X¡¯s in a row.
	O wins when the grid has three O¡¯s in a row.
	Impossible when the grid has three X¡¯s in a row as well as three O¡¯s in a row, or there are a lot more X's than O's or vice versa (the difference should be 1 or 0; if the difference is 2 or more, then the game state is impossible).
	In this stage, we will assume that either X or O can start the game.
	
	You can choose whether to use a space or underscore _ to print empty cells.
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Enter cells
        System.out.print("Enter cells: ");
        char[] cells = sc.nextLine().toCharArray();
        sc.close();

        // Create board
        char[][] board = new char[3][3];

        // count X, O, -
        int x = 0;
        int o = 0;
        int blank = 0;

        // Put cells to board
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j++) {
                char current = cells[3*i+j];
                board[i][j] = current;

                // count cells for future use
                if (current == 'X') {
                    x++;
                } else if (current == 'O') {
                    o++;
                } else {
                    blank++;
                }
            }
        }

        // print game table
        printTable(board);

        // check for IMPOSSIBLE (unequal counts of X and O)
        if (!checkIfPossible(x, o, blank)) {
            System.out.println("Impossible");
            System.exit(0);
        }

        // check for winner
        String result = checkForWinner(blank, board);
        System.out.println(result);

    }

    // PRINT GAME TABLE
    public static void printTable(char[][] board) {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("|");
            for (char c : board[i]) {
                System.out.print(" "+c);
            }
            System.out.print(" |\n");
        }
        System.out.println("---------");
    }

    // CHECK FOR IMPOSSIBLE
    // if true => impossible
    public static boolean checkIfPossible(int x, int o, int blank) {
        if (Math.abs(x-o) > 1) return false;
        return true;
    }

    // CHECK FOR WINNER
    public static String checkForWinner(int blank, char[][] board) {
        int xWins = 0;
        int oWins = 0;

        for (int i = 0; i < 3; i++) {
            // vertical
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2]) {
                if (board[i][0] == 'X') xWins++;
                if (board[i][0] == 'O') oWins++;
            }

            // horizontal
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i]) {
                if (board[0][i] == 'X') xWins++;
                if (board[0][i] == 'O') oWins++;
            }
        }

        // diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'X') xWins++;
            else if (board[0][0] == 'O') oWins++;
        }
        if (board[0][2] == board[1][1] && board[0][2] == board[2][0]) {
            if (board[0][0] == 'X') xWins++;
            else if (board[0][0] == 'O') oWins++;
        }

        // check winner
        if (xWins > 0 && oWins > 0) return "Impossible";
        else if (xWins == 0 && oWins == 0) {
            if (blank > 0) return "Game not finished";
            else return "Draw";
        }
        else if (xWins > 0) return "X wins";
        else return "O wins";

    }
}
