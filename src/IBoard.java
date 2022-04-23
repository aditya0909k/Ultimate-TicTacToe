public interface IBoard {
    void print();
    void reset();
    String getMark(int row, int col);
    boolean makeMove(String player, int row, int col);
    void setSize(int row, int col);
    int getRowSize();
    int getColSize();
    String getName();
    boolean isFull();
    String getWinner();
    void setWinner(String winner);
}
