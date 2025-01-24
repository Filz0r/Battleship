import java.util.Scanner;

public class Field {
    final protected int[][] field = new int[10][10];
    protected Carrier carrier = null;
    protected Battleship battleship = null;
    protected Submarine submarine = null;
    protected Cruiser cruiser = null;
    protected Destroyer destroyer = null;
    private boolean shipWasSank = false;

    public boolean allShipsAreSank() {
        return !(carrier.isAlive() && battleship.isAlive() && submarine.isAlive() && cruiser.isAlive() && destroyer.isAlive());
    }

    public void shoot(Scanner sc) {
        String sankMessage = "You sank a ship! Specify a new target:\n> ";
        String normalMessage = "Take a shot!\n> ";
        int[] input = Game.getAttackCoordinate(sc, shipWasSank ? sankMessage : normalMessage);
        int x = input[0] - 1;
        int y = input[1];

        if (field[y][x] == 1) {
            field[y][x] = -1;
            if (checkIfSunk()) {
                shipWasSank = true;
            } else {
                shipWasSank = false;
                System.out.println("You hit a ship!");
            }
        } else {
            field[y][x] = -2;
            System.out.println("You missed!");
            this.printField();
            System.exit(0);
        }
    }

    private boolean checkIfSunk() {
        if (carrier.isAlive() && isSunk(carrier)) {
            return true;
        } else if (battleship.isAlive() && isSunk(battleship)) {
            return true;
        } else if (submarine.isAlive() && isSunk(submarine)) {
            return true;
        } else if (cruiser.isAlive() && isSunk(cruiser)) {
            return true;
        } else if (destroyer.isAlive() && isSunk(destroyer)) {
            return true;
        }
        return false;
    }

    private boolean isSunk(Ship ship) {
        if (ship == null) return false;
        for (int i = ship.getStartY(); i <= ship.getEndY(); i++) {
            for (int j = ship.getStartX(); j <= ship.getEndX(); j++) {
                if (field[i][j] != -1) {
                    return false;
                }
            }
        }
        ship.killShip();
        return true;
    }

    public void setField(Scanner sc) {
        if (carrier == null) {
            this.setCarrier(sc);
        } else if (battleship == null) {
            this.setBattleship(sc);
        } else if (submarine == null) {
            this.setSubmarine(sc);
        } else if (cruiser == null) {
            this.setCruiser(sc);
        } else if (destroyer == null) {
            this.setDestroyer(sc);
        }
        if (this.isFieldSet()) {
            Game.start = true;
        }
    }

    private void placeShip(Ship ship) {

        if (!ship.isVertical) {
            for(int i = ship.getStartX(); i <= ship.getEndX(); i++) {
                field[ship.getStartY()][i] = 1;
            }
        } else {
            for(int i = ship.getStartY(); i <= ship.getEndY(); i++) {
                field[i][ship.getStartX()] = 1;
            }
        }
    }

    private boolean canPlaceShip(Ship ship) {
        int startX = ship.getStartX();
        int endX = ship.getEndX();
        int startY = ship.getStartY();
        int endY = ship.getEndY();

        for (int i = startY - 1; i <= endY + 1; i++) {
            for (int j = startX - 1; j <= endX + 1; j++) {
                if (i >= 0 && i < 10 && j >= 0 && j < 10 && field[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void setCarrier(Scanner sc) {
        final String startMessage = "Enter the coordinates of Aircraft Carrier (5 cells):\n> ";
        String message = startMessage;
        String[] input= Game.getInput(sc, message);
        while (this.carrier == null) {
            try {
                if (!Game.validateInput(input)) {
                    System.out.println("Error! Invalid input");
                    message = startMessage;
                    input= Game.getInput(sc, message);
                    continue ;
                }
                carrier = new Carrier(input);
                if (canPlaceShip(this.carrier)) {
                    this.placeShip(this.carrier);
                } else {
                    this.carrier = null;
                    message = "\nError! You placed it too close to another one! Try again:\n> ";
                    input = Game.getInput(sc, message);
                }
            } catch (Exception e) {
                if (e.getMessage().equals("Error! Invalid Ship placement!")) {
                    System.out.println(e.getMessage());
                    message = startMessage;
                    input= Game.getInput(sc, message);
                    continue ;
                }
                message = "\nError! Wrong length of the Aircraft Carrier! Try again:\n> ";
                input = Game.getInput(sc, message);
            }
        }
    }

    private void setBattleship(Scanner sc) {
        final String startMessage = "Enter the coordinates of Battleship (4 cells):\n> ";
        String message = startMessage;
        String[] input= Game.getInput(sc, message);
        while (this.battleship == null) {
            try {
                if (!Game.validateInput(input)) {
                    System.out.println("Error! Invalid input");
                    message = startMessage;
                    input= Game.getInput(sc, message);
                    continue ;
                }
                battleship = new Battleship(input);
                if (canPlaceShip(this.battleship)) {
                    this.placeShip(this.battleship);
                } else {
                    this.battleship = null;
                    message = "\nError! You placed it too close to another one! Try again:\n> ";
                    input = Game.getInput(sc, message);
                }
            } catch (Exception e) {
                if (e.getMessage().equals("Error! Invalid Ship placement!")) {
                    System.out.println(e.getMessage());
                    message = startMessage;
                    input= Game.getInput(sc, message);
                    continue ;
                }
                message = "\nError! Wrong length of the Battleship! Try again:\n> ";
                input = Game.getInput(sc, message);
            }
        }
    }

    private void setSubmarine(Scanner sc) {
        final String startMessage = "Enter the coordinates of Submarine (3 cells):\n> ";
        String message = startMessage;
        String[] input= Game.getInput(sc, message);
        while (this.submarine == null) {
            try {
                if (!Game.validateInput(input)) {
                    System.out.println("Error! Invalid input");
                    message = startMessage;
                    input= Game.getInput(sc, message);
                    continue ;
                }
                submarine = new Submarine(input);
                if (canPlaceShip(this.submarine)) {
                    this.placeShip(this.submarine);
                } else {
                    this.submarine = null;
                    message = "\nError! You placed it too close to another one! Try again:\n> ";
                    input = Game.getInput(sc, message);
                }
            } catch (Exception e) {
                if (e.getMessage().equals("Error! Invalid Ship placement!")) {
                    System.out.println(e.getMessage());
                    message = startMessage;
                    input= Game.getInput(sc, message);
                    continue ;
                }
                message = "\nError! Wrong length of the Submarine! Try again:\n> ";
                input = Game.getInput(sc, message);
            }
        }
    }

    private void setCruiser(Scanner sc) {
        final String startMessage = "Enter the coordinates of Cruiser (3 cells):\n> ";
        String message = startMessage;
        String[] input= Game.getInput(sc, message);
        while (this.cruiser == null) {
            try {
                if (!Game.validateInput(input)) {
                    System.out.println("Error! Invalid input");
                    message = startMessage;
                    input= Game.getInput(sc, message);
                    continue ;
                }
                cruiser = new Cruiser(input);
                if (canPlaceShip(this.cruiser)) {
                    this.placeShip(this.cruiser);
                } else {
                    this.cruiser = null;
                    message = "\nError! You placed it too close to another one! Try again:\n> ";
                    input = Game.getInput(sc, message);
                }
            } catch (Exception e) {
                if (e.getMessage().equals("Error! Invalid Ship placement!")) {
                    System.out.println(e.getMessage());
                    message = startMessage;
                    input= Game.getInput(sc, message);
                    continue ;
                }
                message = "\nError! Wrong length of the Cruiser! Try again:\n> ";
                input = Game.getInput(sc, message);
            }
        }
    }

    private void setDestroyer(Scanner sc) {
        final String startMessage = "Enter the coordinates of Destroyer (2 cells):\n> ";
        String message = startMessage;
        String[] input= Game.getInput(sc, message);
        while (this.destroyer == null) {
            try {
                if (!Game.validateInput(input)) {
                    System.out.println("Error! Invalid input");
                    message = startMessage;
                    input= Game.getInput(sc, message);
                    continue ;
                }
                destroyer = new Destroyer(input);
                if (canPlaceShip(this.destroyer)) {
                    this.placeShip(this.destroyer);
                } else {
                    this.destroyer = null;
                    message = "\nError! You placed it too close to another one! Try again:\n> ";
                    input = Game.getInput(sc, message);
                }
            } catch (Exception e) {
                if (e.getMessage().equals("Error! Invalid Ship placement!")) {
                    System.out.println(e.getMessage());
                    message = startMessage;
                    input= Game.getInput(sc, message);
                    continue ;
                }
                message = "\nError! Wrong length of the Destroyer! Try again:\n> ";
                input = Game.getInput(sc, message);
            }
        }
    }

    public boolean isFieldSet() {
        return carrier != null && battleship != null && submarine != null && cruiser != null && destroyer != null;
    }

    public void printShips() {
        System.out.println("Aircraft Carrier");
        if (this.carrier != null) {
            this.carrier.print();
        } else {
            System.out.println("Null");
        }

        System.out.println("Battleship");
        if (this.battleship != null) {
            this.battleship.print();
        } else {
            System.out.println("Null");
        }

        System.out.println("Submarine");
        if (this.submarine != null) {
            this.submarine.print();
        } else {
            System.out.println("Null");
        }

        System.out.println("Cruiser");
        if (this.cruiser != null) {
            this.cruiser.print();
        } else {
            System.out.println("Null");
        }

        System.out.println("Destroyer");
        if (this.destroyer != null) {
            this.destroyer.print();
        } else {
            System.out.println("Null");
        }
    }

    public void printField() {
        System.out.print("  1 2 3 4 5 6 7 8 9 10\n");
        char height = 'A';
        for (int i = 0; i < field.length; i++) {
            System.out.print(height + " ");
            for (int k = 0; k < field[i].length; k++) {
                if (field[i][k] == 0) {
                    System.out.print("~");
                } else if (field[i][k] == 1){
                    if (Game.fogOfWar){
                        System.out.print("~");
                    } else {
                        System.out.print("O");
                    }
                } else if (field[i][k] == -1) {
                    System.out.print("X");
                } else if (field[i][k] == -2) {
                    System.out.print("M");
                }
                if (k < field[i].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
            height++;
        }
    }

    public void printOpponentField() {
        System.out.print("  1 2 3 4 5 6 7 8 9 10\n");
        char height = 'A';
        for (int i = 0; i < field.length; i++) {
            System.out.print(height + " ");
            for (int k = 0; k < field[i].length; k++) {
                if (field[i][k] == 0 || field[i][k] == 1) {
                    System.out.print("~");
                } else if (field[i][k] == -1) {
                    System.out.print("X");
                } else if (field[i][k] == -2) {
                    System.out.print("M");
                }
                if (k < field[i].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
            height++;
        }
    }
}