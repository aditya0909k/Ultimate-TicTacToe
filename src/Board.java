//Aditya Kulkarni CS2336.003

/* Problem analysis
First I must implement the makeMove method. In this, I will have to take in a mark, row, and column and set the piece in the row
and column to the mark given.

For the checkRow, I will go through each row and check if there are rowSize-1 marks directly next to the starting mark of the 
same row, with them all being the same mark.

For checkCol, I will go through each column and check if there are colSize-1 marks directly next to the starting mark of the same
column, with them all being the same mark.

For checkDiagLR, I will go through the diagonals directly bottom right of the top left mark, rowSize-1 times, and make sure that 
all of these marks are the same.

For checkDiagRL, I will go through the diagonals directly bottom left of the top right mark, rowSize-1 times, and make sure that 
all of these marks are the same.
*/

/* Problem solution
For the makeMove method, I will first determine the index using the equation (row*colSize) + col. With this, I will check if it is
possible to make a move and at the same time setPlaceHolder at the index to the mark parameter. If it is possible and the placeHolder 
is changed, I will return true. Else, false.

For the checkRow, I will set a counter variable to see how many times I get a match from one space on a row to another. I will
then loop through each row, and for each row every column. I will check to see that the mark isn't a "-" and then see if the mark
next to it is the same one. If so, I can increment counter and set the winner variable to the current box I am checking.
If counter is equal to rowSize-1, that means that there is a complete line with a single type of mark, and that there is a winner.

For the checkCol, I will set a counter variable to see how many times I get a match from one space on a column to another. I will
then loop through each column, and for each column every row. I will check to see that the mark isn't a "-" and then see if the mark
below it is the same one. If so, I can increment counter and set the winner variable to the current box I am checking.
If counter is equal to colSize-1, that means that there is a complete line with a single type of mark, and that there is a winner.

For checkDiagLR, I will start on the top left box and check for the middle one(s) and the bottom right edge using a loop. I will make
a counter variable. No matter what NxN board it is, the box directly bottom right to the current box will always be rowSize+1. 
I got this through just experimenting with values. I will keep checking for these places in a loop, and if I see that the box 
directly bottom right to the current box is the same mark, I will increment counter. If counter is equal to rowSize-1, that means
that there is a diagonal line of a single type of mark, and that there is a winner.

For checkDiagLR, I will start on the top right box and check for the middle one(s) and the bottom left edge using a loop. I will make
a counter variable. No matter what NxN board it is, the box directly bottom left to the current box will always be rowSize+1. 
I got this through just experimenting with values. I will keep checking for these places in a loop, and if I see that the box 
directly bottom left to the current box is the same mark, I will increment counter. If counter is equal to rowSize-1, that means
that there is a diagonal line of a single type of mark, and that there is a winner.
*/
public class Board implements IBoard{
    private Box[] boxes;
    private String name;
    private int rowSize;
    private int colSize;
    String winner = "";
    
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
    public boolean makeMove(String mark, int row, int col) { //Take in a player's mark, row, and column, and place their "piece" on the board at the specified location
        int index = (row*colSize) + col; //equation to find index position based on row and col given
        if (boxes[index].setPlaceHolder(mark)) //through if statement, set placeholder of index to mark and return true
                return true;
        return false; //if doesnt work return false
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
    }
    public String getWinner() {
        return winner;
    }
    // public boolean checkRow() { //Go through each row of the array and check to see if (rowSize) in a row are the same player mark.
    //     int counter = 0; 
    //     String currentPlayer = "";
    //     for (int row = 0; row <= (rowSize*colSize)-rowSize; row+=rowSize) { //start at 0, ensure it is less than or equal to the number that holds the last row, first column, and increase by rowSize to get to next row
    //         for (int col = 0; col < colSize-1; col++) { //start at 0, ensure it only happens colSize-1 times because of adding to the row for indexing, and increase by 1
    //             if (!boxes[col+row].getPlaceHolder().equals("-") && (boxes[col+row].getPlaceHolder().equals(boxes[col+row+1].getPlaceHolder()))) { //if the mark is not "-" and it equals the mark directly next to it
    //                 counter++; //increase counter
    //                 currentPlayer = boxes[row+col].getPlaceHolder(); //change the current player
    //             }
    //             if (counter == rowSize-1) { //if there are rowSize marks next to each other
    //                 winner = currentPlayer; //change winner to current player
    //                 return true;
    //             }
    //         }
    //         counter = 0; //reset counter after done checking one single row
    //     }
    //     return false;
    // }
    // public boolean checkCol() { //Go through each column of the array and check to see if (colSize) in a row are the same player mark
    //     int counter = 0;
    //     String currentPlayer = "";
    //     for (int col = 0; col < colSize; col++) { //start at 0, go through all columns, increase by one each time
    //         for (int row = 0; row < (rowSize*colSize)-rowSize; row+=rowSize) { //in one column, start at row 0, ensure it is less than the number that holds the last row, first column, and increase by rowSize to get to the next row 
    //             if (!boxes[col+row].getPlaceHolder().equals("-") && (boxes[col+row].getPlaceHolder().equals(boxes[(col+row)+colSize].getPlaceHolder()))) { //if the mark is not "-" and it equals the mark directly below it
    //                 counter++; //increase counter
    //                 currentPlayer = boxes[row+col].getPlaceHolder(); //change the current player
    //             }
    //         }
    //         if (counter == colSize-1) { //if there are colSize marks next to each other
    //             winner = currentPlayer; //change winner to current player
    //             return true;
    //         }
    //         counter = 0; //reset counter after done checking one single column
    //     }
    //     return false;
    // }
    // public boolean checkDiagLR() { //Go through the diagonal of the array from top left to bottom right and see if they are the same player mark
    //     int counter = 0;
    //     String currentPlayer = "";
    //     for (int row = 0; row < (rowSize*colSize)-1; row+=rowSize+1) { //start at top left, ensure it is less than the bottom right number, increase by rowSize+1 to get the next diagonal index
    //         if (!boxes[row].getPlaceHolder().equals("-") && (boxes[row].getPlaceHolder().equals(boxes[(row+(rowSize+1))].getPlaceHolder()))) { //if the mark is not "-" and it is equal to the mark directly diagonal to it going from top left to bottom right
    //             counter++; //increase counter
    //             currentPlayer = boxes[row].getPlaceHolder(); //change current player
    //         }
    //     }
    //     if (counter == rowSize-1) { //if there are rowSize marks next to each other
    //         winner = currentPlayer; //change winner to current player
    //         return true;
    //     }
    //     return false;
    // }
    // public boolean checkDiagRL() { //Go through the diagonal of the array from bottom left to top right and see if they are the same player mark
    //     int counter = 0;
    //     String currentPlayer = "";
    //     for (int row = rowSize-1; row < (rowSize*colSize)-rowSize; row+=rowSize-1) { //start at top right, ensure it is less than bottom left number, increase by rowSize-1 to get the next diagonal index
    //         if (!boxes[row].getPlaceHolder().equals("-") && (boxes[row].getPlaceHolder().equals(boxes[(row+(rowSize-1))].getPlaceHolder()))) { //if the mark is not "-" and it is equal to the mark directly diagonal to it going from top right to bottom left
    //             counter++; //increase counter
    //             currentPlayer = boxes[row].getPlaceHolder(); //change current player
    //         }
    //     }
    //     if (counter == rowSize-1) { //if there are rowSize marks next to each other
    //         winner = currentPlayer; //change winner to current player
    //         return true;
    //     }
    //     return false;
    // }
}
