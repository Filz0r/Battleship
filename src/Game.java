import java.util.NoSuchElementException;
import java.util.Scanner;


public class Game {
    private  Field player1 = new Field();
    private  Field player2 = new Field();
    private boolean keepRunning = true;
    static public boolean stop = false;
    static public boolean start = false;
    static public boolean fogOfWar = false;
    private boolean player1Turn = true;

    public void run(Scanner sc) {
        setupPlayer(player1, sc, "Player 1");
        setupPlayer(player2, sc, "Player 2");

        while (this.keepRunning) {
            if (player1Turn) {
                takeTurn(player1, player2, sc, "Player 1");
            } else {
                takeTurn(player2, player1, sc, "Player 2");
            }
            player1Turn = !player1Turn;
            if (player1.allShipsAreSank() || player2.allShipsAreSank()) {
                this.keepRunning = false;
            }
        }
        String winningPlayer = player1.allShipsAreSank() ? "Player2" : "Player1";
        System.out.println("Congratulations " + winningPlayer + "! You have won the game!");
    }

    private void setupPlayer(Field player, Scanner sc, String name) {
        System.out.println(name + ", place your ships on the game field");
        player.printField();
        while (!player.isFieldSet()) {
            player.setField(sc);
            player.printField();
            System.out.println();
        }
        System.out.println("Press Enter and pass the move to another player");
        sc.nextLine();
        clearScreen();
    }

    private void takeTurn(Field currentPlayer, Field opponent, Scanner sc, String playerName) {
        System.out.println(playerName + ", it's your turn:");
        opponent.printOpponentField();
        System.out.println("---------------------");
        currentPlayer.printField();
        currentPlayer.shoot(sc);
        if (currentPlayer.allShipsAreSank()) {
            this.keepRunning = false;
            System.out.println(playerName + " sank the last ship. You won. Congratulations!");
        } else {
            System.out.println("Press Enter and pass the move to another player");
            sc.nextLine();
            clearScreen();
        }
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static boolean validateInput(String[] input) {
        boolean result = false;

        if (input == null) {
            return false;
        }

        for (String elem : input) {
            if (!isValidCoordinate(elem)) {
                result = false;
                break;
            }
            result = true;
        }

        return result;
    }

    public static String[] getInput(Scanner sc, String message) {
        String[] result = null;
        System.out.print(message);
        try {
            String temp = sc.nextLine().toUpperCase();
            result = temp.split(" ");
            if (result.length != 2)
                return null;
        } catch (NoSuchElementException e) {
            System.out.println("CTRL + D is a no go!");
            System.exit(1);
        }
        catch (Exception e) {
            System.out.println("fuuuk");
        }

        return result;
    }

    public static int[] getAttackCoordinate(Scanner sc, String message) {
        System.out.println(message);
        int[] result = new int[2];
        while (true) {
            try {
                String temp = sc.nextLine().toUpperCase().trim();
                if (isValidCoordinate(temp)) {
                    result[0] = Integer.parseInt(temp.substring(1));
                    result[1] = temp.charAt(0) - 'A';
                    break ;
                } else {
                    System.out.print("Error! You entered the wrong coordinates! Try again:\n> ");
                }
            } catch (NoSuchElementException e) {
                System.out.println("CTRL + D is a no go!");
                System.exit(1);
            } catch (NumberFormatException e) {
                System.out.println("Error! Invalid input!");
            }
        }
        return result;
    }

    private static boolean isValidCoordinate(String coord) {
        char row = coord.charAt(0);
        if (row < 'A' || row > 'J') {
            return false;
        }
        try {
            int col = Integer.parseInt(coord.substring(1));
            if (col <= 0 || col > 10) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
