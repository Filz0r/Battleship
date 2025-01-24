public class Destroyer extends Ship {
    final private int lenght = 2;

    public Destroyer(String[] coordinates) throws Exception {
        super(coordinates);
        super.type = "Destroyer";
        if (super.calculatedLength != lenght) {
            throw new Exception("Nobody is going to read this lel");
        }
    }
}
