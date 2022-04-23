//Aditya Kulkarni CS2336.003

/* Problem analysis

*/

/* Problem solution

*/

public interface IBoard {
    void print();
    String getMark(int row, int col);
    boolean makeMove(String player, int square);
    void setSize(int row, int col);
    int getRowSize();
    int getColSize();
    String getName();
    boolean isFull();
    String getWinner();
    void setWinner(String winner);
    boolean gameOver();
}
