import javax.print.attribute.standard.MediaSize.Other;

public class OtherBoardWrapper implements IBoard{
    OtherBoard otherboard = new OtherBoard();

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
    public boolean makeMove(String player, int row, int col) {
        return otherboard.setMark(row, col, player);
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
