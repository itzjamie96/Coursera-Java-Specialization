package stage1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Initialize a 3x3 table
        String[][] table = new String[3][3];

        Scanner sc = new Scanner(System.in);

        // Ask the user to provide the initial state
        System.out.print("Enter the cells: ");
        String[] initialStatus = sc.nextLine().split("");

        // set table
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                table[i][j] = initialStatus[j+3*i];
            }
        }

        // print table
        printTable(table);

        // find out user
        String user = user(table);

        // start game
        while (true) {

            // get coordinates
            System.out.print("Enter the coordinates: ");
            try {
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;

                if (x < 0 || x > 2 || y < 0 || y > 2) {
                    System.out.println("Coordinates should be from 1 to 3!");

                } else if (!table[x][y].equals("_")){
                    System.out.println("This cell is occupied! Choose another one!");
                }

                // valid x, y
                else {
                    if (user.equals("X")) {
                        table[x][y] = "X";
                        user = "O";
                    } else {
                        table[x][y] = "O";
                        user = "X";
                    }

                    // check for winner
                    String result = checkWinner(table);

                    int cnt = printTable(table);
                    if (cnt > 0 && result.equals("Draw")) {
                        System.out.println("Game not finished");
                    } else {
                        System.out.println(result);
                    }
                    break;

                }
                sc.nextLine();

            } catch (InputMismatchException e) {
                System.out.println("You should enter numbers!");
                sc.nextLine();
            }
        }
    }

    public static int printTable(String[][] table) {

        int cnt = 0;

        System.out.println("---------");
        for (int i = 0; i < 3; i++){
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (table[i][j].equals("_")) {
                    cnt++;
                    System.out.printf(" ");
                } else {
                    System.out.printf("%s",table[i][j]);
                }
                System.out.print(" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

        return cnt;
    }

    public static String checkWinner(String[][] table) {

        String currentUser;
        // flag to check if there is a winner
        boolean flag;

        // check horizontally
        for (int i = 0; i < 3; i++){

            // flag to check if there is a winner
            flag = true;

            // check with user at index 0 of each row
            if (!table[i][0].equals("_")) {
                currentUser = table[i][0];
                for (int j = 1; j < 3; j++) {
                    if (!currentUser.equals(table[i][j])) {
                        flag = false;
                        break;
                    }
                }
                // if there is a winner, return the winner
                if (flag) {
                    return currentUser + " wins";
                }

            }
        }

        // check diagonally
        // top left -> down right
        if (!table[0][0].equals("_")) {
            currentUser = table[0][0];
            if (currentUser.equals(table[1][1]) && currentUser.equals(table[2][2])) {
                return currentUser + " wins";
            }
        }
        if (!table[0][2].equals("_")) {
            currentUser = table[0][2];
            if (currentUser.equals(table[1][1]) && currentUser.equals(table[2][0])) {
                return currentUser + " wins";
            }
        }

        // check vertically
        for (int i = 0; i < 3; i++){

            // flag to check if there is a winner
            flag = true;

            // check with user at index 0 of each col
            if (!table[0][i].equals("_")) {
                currentUser = table[0][i];

                for (int j = 1; j < 3; j++) {
                    if (!currentUser.equals(table[j][i])) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    return currentUser + " wins";
                }
            }
        }


        // there was no winner, return draw
        return "Draw";
    }

    // finding out user
    public static String user(String[][] table) {

        int x = 0, o = 0;

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++) {
                if (table[i][j].equals("X")) {
                    x++;
                } else if (table[i][j].equals("O")) {
                    o++;
                }
            }
        }

        if (x < o) {
            return "X";
        } else if (o < x) {
            return "O";
        } else {
            return "X";
        }

    }
}
