public class Pair<F, S> {
    private F x; //x member of pair
    private S y; //y member of pair

    public Pair(F x, S y) {
        this.x = x;
        this.y = y;
    }

    public void setx(F x) {
        this.x = x;
    }

    public void sety(S y) {
        this.y = y;
    }

    public F getx() {
        return x;
    }

    public S gety() {
        return y;
    }

    public Pair x_change(){
        F temp = x;
        x = (F)y;
        y = (S)temp;
        return this;
    }
}


