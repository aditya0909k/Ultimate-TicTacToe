//Aditya Kulkarni CS2336.003

/* Problem analysis
This boxes class is essential to the ultimate tic tac toe, because it will consits the members of a board. Each board will have
rowsize * colsize boxes.
*/

/* Problem solution
We will have our constructor to set where our box is in a specific board through row and col, and use the constructor to initialize
these. We will also have our get and set method for placeHolder, and our set method will only set if that placeHolder is 
available, since we do not want to overwrite a box when it is marked. We will also have an isAvailableDash method to check if the
current placeHolder is a DASH, which will be used in Board method when changing boxes. Then we also have a simple isAvailable method
that will check if the placeHolder is a DASH or "*", in which case it can be overwritten. Finally. our print method will simply 
print out the placeholder 
*/

public class Box { //box is the basis of every board. consists of a row and col of where the box is located inside a board, and the box's placeholder
    private int row;
    private int col;
    private final static String DASH = "-";
    private String placeHolder = Box.DASH;

    public Box(int row, int col) { //set row and col
        this.row = row;
        this.col = col;
    }
    public String getPlaceHolder() { //return placeholder
        return this.placeHolder;
    }
    public boolean setPlaceHolder(String placeHolder) { //set placeholder if it is a dash or "*". else, dont.
        if (isAvailable()) {
            this.placeHolder = placeHolder;
            return true;
        }
        return false;
    }
    public boolean isAvailable() { //is the box available
        if ((!this.placeHolder.equals("O") && !this.placeHolder.equals("X")) || (this.placeHolder.equals("*"))) //if its not a O or X, or it is a star
            return true;
        return false;
    }
    public void print() { //print it
        System.out.print(placeHolder + " ");
    }
}
