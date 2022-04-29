//Aditya Kulkarni CS2336.003

/* Problem analysis
Our UltimateTTT should be able to take in any type of board. To do this, we will have to create an interface type of board,
IBoard.
*/

/* Problem solution
Create an interface IBoard with multiple methods that any board would need.
*/

public interface IBoard { //interface called IBoard
    int getRowSize();
    int getColSize();
    String getName();
    String getWinner();
    String getMark(int row, int col);
    void setWinner(String winner);
    void setSize(int row, int col);
    boolean makeMove(String player, int square);
    boolean isBoxAvailable(int box);
    boolean isFull();
    boolean gameOver();
    void printBoard(int index);
}
