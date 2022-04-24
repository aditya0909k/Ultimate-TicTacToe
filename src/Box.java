//Aditya Kulkarni CS2336.003

/* Problem analysis

*/

/* Problem solution

*/

public class Box {
    private int row;
    private int col;
    private final static String DASH = "-";
    private String placeHolder = Box.DASH;

    public Box(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public String getPlaceHolder() {
        return this.placeHolder;
    }

    public boolean setPlaceHolder(String placeHolder) {
        if (isAvailable()) {
            this.placeHolder = placeHolder;
            return true;
        }
        return false;
    }
    public boolean isAvailableDash() {
        return this.placeHolder.equals(Box.DASH);
    }
    public boolean isAvailable() {
        if (this.placeHolder.equals(Box.DASH))
            return true;
        if (this.placeHolder.equals("*"))
            return true;
        return false;
    }
    public void print() {
        System.out.print(placeHolder + " ");
    }
}
