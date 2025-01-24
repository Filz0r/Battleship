public class Ship {
    protected int calculatedLength;
    protected int startX;
    protected int startY;
    protected int endX;
    protected int endY;
    private boolean alive;
    public boolean isVertical;
    protected String type;

    public Ship() {
        this.type = "None";
        this.endX = 0;
        this.startX = 0;
        this.startY = 0;
        this.endY = 0;
        this.calculatedLength = 0;
        this.alive = true;
    }

    public void killShip() {
        this.alive = false;
    }

    public boolean isAlive() { return this.alive; }

    public Ship(String[] coordinates) throws Exception {
        this();

        char startCol = coordinates[0].charAt(0), endCol = coordinates[1].charAt(0);
        int startRow = Integer.parseInt(coordinates[0].substring(1));
        int endRow = Integer.parseInt(coordinates[1].substring(1));

        if (startCol == endCol) {
            this.createHorizontalShip(new char[] { startCol, endCol}, new int[] {startRow, endRow});
        } else if (startRow == endRow) {
            this.createVerticalShip(new char[] { startCol, endCol}, new int[] {startRow, endRow});
        } else {
            throw new Exception("Error! Invalid Ship placement!");
        }
    }

    public int getStartX() {
        return startX;
    }

    public int getEndX() {
        return endX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndY() {
        return endY;
    }

    public int getCalculatedLength() {
        return calculatedLength;
    }

    public void print() {
        String message = String.format("""
                CalculatedLength: %d
                X: %d - %d
                Y: %d - %d
                Type: %s
                Alive: %s
                """, this.calculatedLength, startX, endX, startY, endY, type, alive);
        System.out.println(message);
    }

    private void createHorizontalShip(char[] cols, int[] rows) {
        this.calculatedLength = Math.abs(rows[1] - rows[0]) + 1;
        if (rows[0] > rows[1]) {
            int temp = rows[0];
            rows[0] = rows[1];
            rows[1] = temp;
        }
        this.startX = rows[0] - 1;
        this.endX = rows[1] - 1;
        this.startY = this.endY = cols[0] - 'A';
        this.isVertical = false;
    }

    private void createVerticalShip(char[] cols, int[] rows) {
        this.calculatedLength = Math.abs(cols[1] - cols[0]) + 1;
        if (cols[0] > cols[1]) {
            char temp = cols[0];
            cols[0] = cols[1];
            cols[1] = temp;
        }
        this.startX = this.endX = rows[0] - 1;
        this.startY = cols[0] - 'A';
        this.endY = cols[1] - 'A';
        this.isVertical = true;
    }
}

