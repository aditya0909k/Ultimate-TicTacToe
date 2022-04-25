//Aditya Kulkarni CS2336.003

/* Problem analysis
For our UltimateTTT game, we will need a board class since the UltimateTTT is 3 boards x 3 boards. This board class will address 
everything in the board itself, making sure to store if the game is over, who the winner is, making a move on the board,
etc...
*/

/* Problem solution
First, we will have an array of Boxes from the Box class acting as our complete board. When we create the board,
it will default be a 3x3 but can be set differently if needed. We will need get and set methods for all our private
variables, and also. We will also need the makeMove method, which will check if a box at the specific square is available,
and if so, set the placeholder to that. Then we have our print and isFull methods, which will print the board and
check if the board is full by going through all boxes and seeing if any are available. We will also need a method to initialize 
our board, which is called by our setSize from the constructor, and will initialize all the boxes, giving it a row and column
number based on the Board's row and column sizes.  We will also add a isBoxAvailable method to see if a passed in box number
is available in the board, and finally a changeBoxes method, for when the game is over, we can change all the remaining DASHES in a
board to "*", to signify that our board is already won and the game winner can no longer be changed.
*/

public class Board implements IBoard{ //Board class implements the IBoard interface 
    private Box[] boxes; //array of boxes from Box class
    private String name;
    private int rowSize;
    private int colSize;
    private String winner = "";
    private int boardNumber; //for printing purposes, tells us which board this is
    private boolean gameOver = false;    
    
    public Board() { //default constructor passes in a 3x3 board to second constructor
        this(3, 3, "3x3 board");
    }
    public Board(int rowSize, int colSize, String name) { //takes in row, col, name and sets it
        this.setName(name);
        this.setSize(rowSize, colSize);
    }

    //iboard methods
    @Override
    public int getRowSize() { //return rowsize
        return this.rowSize;
    }
    @Override
    public int getColSize() { //return colsize
        return this.colSize;
    }
    @Override
    public String getName() { //return name
        return this.name;
    }
    public void setName(String name) { //set name
        this.name = name;
    }
    @Override
    public void setBoardNumber(int num) { //set boardnum
        this.boardNumber = num;
    }
    @Override
    public int getBoardNumber() { //get boardnum
        return boardNumber;
    }
    @Override
    public String getMark(int row, int col) { //get the mark at row and col
        return boxes[row * this.rowSize + col].getPlaceHolder();
    }
    @Override
    public void setWinner(String winner) { //set our winner. will also change gameover to true since if there is a winner, game is over. also changes boxes placeholders to indicate a won board
        this.winner = winner;
        this.gameOver = true;
        changeBoxes();
    }
    @Override
    public String getWinner() { //get winner
        return winner;
    }
    @Override
    public boolean gameOver() { //is the game over?
        return gameOver;
    }
    @Override
    public void setSize(int rowSize, int colSize) { //set size. make sure game is larger than 2x2
        if (rowSize < 3 || colSize < 3) { //if our board is less than 3x3, dont set it
            System.out.println("Miniumum board size is 3x3.");
        }
        else {
            this.rowSize = rowSize;
            this.colSize = colSize;
            init();
        }
    }
    @Override
    public boolean makeMove(String mark, int square) { //make a move on the board, return if it is possible
        if (boxes[square].setPlaceHolder(mark)) //set the box placeholder as the mark passed in
                return true;
        return false; 
    }
    @Override
    public boolean isBoxAvailable(int box) { //return if this box is available
        return boxes[box].isAvailable();
    }
    @Override
    public boolean isFull() { //is our board full?
        for (Box b : boxes) { //iterate through all boxes,
            if (b.isAvailable()) //if any is available, it is not full
                return false;
        }
        return true;
    }
    @Override
    public void print() { //print the board
        for (int i = 0; i < boxes.length; i++) { //for all the boxes in board
            if (i != 0 && i%colSize == 0) {  //based on if it is a starting box in a row
                System.out.println();
                System.out.print(" | "); 
            }
            if (i == 0) { //if its the first box
                System.out.println("BOARD #" + getBoardNumber());
                System.out.print(" | ");;
            }
            boxes[i].print();
            System.out.print( "| ");
        }
        System.out.println("");
    }
    //board specific methods
    private void init() { //initialize the board
        boxes = new Box[rowSize*colSize]; //array of boxes
        for (int i = 0; i < boxes.length; i++) {
            Box b = new Box(i/colSize, i%colSize); //make a new box and set it's row and col value
            boxes[i] = b;
        }
    }
    private void changeBoxes() { //change all DASH placeholder to * to indicate a won board
        if (gameOver()) { //make sure game is over
            for (int i = 0; i < boxes.length; i++) {
                if (boxes[i].isAvailableDash()) { //if its available and a DASH
                    boxes[i].setPlaceHolder("*"); //change it
                }
            }
        }
    }
}
