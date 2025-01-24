public class Submarine extends Ship {
    final private int lenght = 3;
    public Submarine(String[] coordinates) throws Exception {
        super(coordinates);
        super.type = "Submarine";
        if (super.calculatedLength != lenght) {
            throw new Exception("Nobody is going to read this lel");
        }
    }
}
