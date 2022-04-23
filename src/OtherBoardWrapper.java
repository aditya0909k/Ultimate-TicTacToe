//Aditya Kulkarni CS2336.003

/* Problem analysis

*/

/* Problem solution

*/

public class OtherBoardWrapper implements IBoard{
    OtherBoard otherboard;

    public OtherBoardWrapper() {
        otherboard = new OtherBoard();
    }
    public OtherBoardWrapper(int row, int col, String name) {
        otherboard = new OtherBoard(row, col, name);
    }
    @Override
    public void print() {
        otherboard.printMyBoard();
    }
    @Override
    public void reset() {
        otherboard.reset();
    }
    @Override
    public String getMark(int row, int col) {
        return otherboard.getMark(row, col);
    }
    @Override
    public boolean makeMove(String player, int square) {
        return otherboard.setMark(player, square);
    }
    @Override
    public void setSize(int row, int col) {
        otherboard.setSize(row, col);
    }
    @Override
    public int getRowSize() {
        return otherboard.getRowSize();
    }
    @Override
    public int getColSize() {
        return otherboard.getColSize();
    }
    @Override
    public String getName() {
        return otherboard.getName();
    }
    @Override
    public boolean isFull() {
        return (otherboard.emptyCells().length == 0);
    }
    @Override
    public String getWinner() {
        return otherboard.getWinner();
    }
    @Override
    public void setWinner(String winner) {
        otherboard.setWinner(winner);
    }
}
