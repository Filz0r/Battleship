public class Battleship extends Ship {
    final private int lenght = 4;

    public Battleship(String[] coordinates) throws Exception {
        super(coordinates);
        super.type = "Battleship";
        if (super.calculatedLength != lenght) {
            throw new Exception("Nobody is going to read this lel");
        }
    }
}
