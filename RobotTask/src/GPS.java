public class GPS {
    private int lr = 5;
    private int cr = 5;
    private final int l = 11;
    private final int c = 11;

    public GPS() {
    }

    public int getRow(int id) {
        return this.lr;
    }

    public int getColumn(int id) {
        return this.cr;
    }

    public void move(Move m1) {
        if (m1 == Move.UP && this.lr > 1) {
            --this.lr;
        }

        if (m1 == Move.LEFT && this.cr > 1) {
            --this.cr;
        }

        if (m1 == Move.DOWN && this.lr < 11) {
            ++this.lr;
        }

        if (m1 == Move.RIGHT && this.cr < 11) {
            ++this.cr;
        }

    }
}
