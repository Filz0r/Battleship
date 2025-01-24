public class Carrier extends Ship {
    final private int lenght = 5;
    public Carrier(String[] coordinates) throws Exception {
        super(coordinates);
        super.type = "Aircraft Carrier";
        if (super.calculatedLength != lenght) {
            throw new Exception("Nobody is going to read this lel");
        }
    }
}
