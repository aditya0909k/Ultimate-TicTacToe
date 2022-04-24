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
    @Override
    public int getRowSize() {
        return this.rowSize;
    }
    @Override
    public int getColSize() {
        return this.colSize;
    }
    @Override
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
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
    @Override
    public void setBoardNumber(int num) {
        this.boardNumber = num;
    }
    @Override
    public int getBoardNumber() {
        return boardNumber;
    }
    public void init() {
        boxes = new Box[rowSize*colSize];
        for (int i = 0; i < boxes.length; i++) {
            Box b = new Box(i/colSize, i%colSize);
            boxes[i] = b;
        }
    }
    @Override
    public void print() {
        for (int i = 0; i < boxes.length; i++) {
            if (i != 0 && i%colSize == 0) { 
                System.out.println();
                System.out.print(" | "); 
            }
            if (i == 0) {
                System.out.println("BOARD #" + getBoardNumber());
                System.out.print(" | ");;
            }
            boxes[i].print();
            System.out.print( "| ");
        }
        System.out.println("");
    }
   
    // public void print(int boardNum) {
    //     for (int i = 0; i < boxes.length; i++) {
    //         if (i != 0 && i%colSize == 0) { 
    //             System.out.println();
    //             System.out.print("BOARD#" + boardNum + " | "); 
    //         }
    //         if (i == 0) {
    //             System.out.print("BOARD#" + boardNum + " | ");;
    //         }
    //         boxes[i].print();
    //         System.out.print("| ");
    //     }
    //     System.out.println("");
    // }
    @Override
    public boolean makeMove(String mark, int square) {
        int index = square; 
        if (boxes[index].setPlaceHolder(mark)) 
                return true;
        return false; 
    }
    @Override
    public boolean isFull() {
        for (Box b : boxes) {
            if (b.isAvailable())
                return false;
        }
        return true;
    }
    public boolean isBoxAvailable(int box) {
        return boxes[box].isAvailable();
    }
    @Override
    public String getMark(int row, int col) {
        return boxes[row * this.rowSize + col].getPlaceHolder();
    }
    @Override
    public void setWinner(String winner) {
        this.winner = winner;
        this.gameOver = true;
        changeBoxes();
    }
    @Override
    public String getWinner() {
        return winner;
    }
    @Override
    public boolean gameOver() {
        return gameOver;
    }
    public void changeBoxes() {
        if (gameOver()) {
            for (int i = 0; i < boxes.length; i++) {
                if (boxes[i].isAvailableDash()) {
                    boxes[i].setPlaceHolder("*");
                }
            }
        }
    }
}
