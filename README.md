# 🚢 Battleship Game ⚓

This is a command-line Battleship game developed in Java as a project for the Hyperskill learning academy. It showcases several OOP and general programming concepts.

## 🕹️ How to Run

1.  **Make sure you have Java Development Kit (JDK) installed on your machine.**
2.  **Clone the repository to your local machine.**
3.  Navigate to the `src` directory.
4.  Compile the Java files using a Java compiler (e.g. from your IDE):
    ```bash
    javac *.java
    ```
5.  Run the `Main` class:
    ```bash
    java Main
    ```

## 💡 Key Concepts Used

### Object-Oriented Programming (OOP)

*   **Classes and Objects:** The game utilizes classes to represent various game entities such as `Ship`, `Carrier`, `Battleship`, `Cruiser`, `Destroyer`, `Submarine`, `Field`, and `Game`. Each class is a blueprint for objects.
    *  `Ship` serves as a base class, providing common properties such as coordinates, length, and life status.
    *  Specific ship types (`Carrier`, `Battleship`, etc.) inherit from `Ship` and define their unique lengths.
    *  `Field` manages the game board, ship placement, and shooting.
    * `Game` handles the game flow, player turns and validation.
*   **Inheritance:** The game uses inheritance to create a hierarchy of ship classes, inheriting the common properties and methods from the `Ship` base class.
*   **Polymorphism:** Though not explicitly used in the form of interface implementation, the `Field` class `isSunk` method works with all the ships through `Ship` parent class.
*   **Encapsulation:** Class fields are made `protected` or `private`, to protect data.
*   **Abstraction:** The `Ship` class is an abstract representation of any ship in the game, and the specific ship types are concrete implementations of that abstraction.

### General Programming

*   **Arrays:** The game board is represented using a 2D array `int[][] field`.
*   **Input Handling:** The `Scanner` class is used for reading user input.
*   **Error Handling:** `try-catch` blocks are used to handle potential errors (e.g., `NumberFormatException`, `NoSuchElementException`, and custom exceptions for invalid input).
*  **Game Logic:** The `Game` class manages the main game logic, including player turns, ship placement, and win conditions.
*   **String Manipulation:** String methods like `split()`, `substring()`, `toUpperCase()`, and `charAt()` are used for input validation and processing.
*   **Loops and Conditional Statements:** The game uses loops (`while`, `for`) for game flow, input validation, and conditional statements (`if`, `else if`) for game logic and error checking.

## 🖼️  Visuals

*  **Game Board:** The game uses '~' for water, 'O' for intact ship pieces (only visible when the player is placing their ships or without `fogOfWar`), 'X' for hit ships and 'M' for missed shots on the game board.
*  **Player Prompts:** When requesting coordinates, the game prints descriptive messages.