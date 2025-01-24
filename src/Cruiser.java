public class Cruiser extends Ship {
    final private int lenght = 3;
    public Cruiser(String[] coordinates) throws Exception {
        super(coordinates);
        super.type = "Cruiser";
        if (super.calculatedLength != lenght) {
            throw new Exception("Nobody is going to read this lel");
        }
    }
}
