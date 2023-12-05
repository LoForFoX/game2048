package game;

public class Key{
    private int i;
    private int j;
    public Key(int i, int j) {
        this.i = i;
        this.j = j;
    }
    public void setI(int value) { i = value; }
    public void setJ(int value) { j = value; }
    public int getI() { return i; }
    public int getJ() { return j; }

    @Override
    public String toString(){
        return ("Key:{i=" + i + ", j=" + j + "}");
    }

    public boolean equals(Key k) {
        return ( (this.j==k.getJ()) && (this.i==k.getI()) );
    }
    public boolean equals(int ki, int kj) {
        return ( (this.i==ki) && (this.j==kj) );
    }
}
