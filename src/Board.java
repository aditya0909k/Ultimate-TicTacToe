//Aditya Kulkarni CS2336.003

/* Problem analysis

*/

/* Problem solution

*/

public class Board implements IBoard{
    private Box[] boxes;
    private String name;
    private int rowSize;
    private int colSize;
    private String winner = "";
    private int boardNumber;
    private boolean gameOver = false;
    
    
    public Board() {
        this(3, 3, "3x3 board");
    }
    public Board(int rowSize, int colSize, String name) {
        this.setName(name);
        this.setSize(rowSize, colSize);

    }
    public int getRowSize() {
        return this.rowSize;
    }
    public int getColSize() {
        return this.colSize;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSize(int rowSize, int colSize) {
        if (rowSize < 3 || colSize < 3) {
            System.out.println("Miniumum board size is 3x3.");
        }
        else {
            this.rowSize = rowSize;
            this.colSize = colSize;
            init();
        }
    }
    public void setBoardNumber(int boardNumber) {
        this.boardNumber = boardNumber;
    }
    public int getBoardNumber() {
        return boardNumber;
    }
    public void init() {
        boxes = new Box[rowSize*colSize];
        for (int i = 0; i < boxes.length; i++) {
            Box b = new Box(i/colSize, i%colSize);
            boxes[i] = b;
        }
        print();
    }
    public void reset() {
        
    }

    public void print() {
        System.out.println("Printing the " + this.name + "-" + this.rowSize + "*" + this.colSize + " board info...");
        for (int i = 0; i < boxes.length; i++) {
            if (i != 0 && i%colSize == 0) 
                System.out.println();
            boxes[i].print();
        }
        System.out.println("");
    }
    public boolean makeMove(String mark, int square) { 
        if (gameOver())
            return false;
        int index = square; 
        if (boxes[index].setPlaceHolder(mark)) 
                return true;
        return false; 
    }
    public boolean isFull() {
        for (Box b : boxes) {
            if (b.isAvailable())
                return false;
        }
        return true;
    }
    public String getMark(int row, int col) {
        return boxes[row * this.rowSize + col].getPlaceHolder();
    }
    public void setWinner(String winner) {
        this.winner = winner;
        this.gameOver = true;
    }
    public String getWinner() {
        return winner;
    }
    public boolean gameOver() {
        return gameOver;
    }
}
