//Aditya Kulkarni CS2336.003

/* Problem analysis
This is a Wrapper class that implements the IBoard interface. It takes methods from the otherboard class and wraps it as an
Iboard board.
*/

/* Problem solution
Simply just wraps otherboard methods as IBoard methods.
*/

public class OtherBoardWrapper implements IBoard{
    OtherBoard otherboard;

    public OtherBoardWrapper() { //construct an otherboard
        otherboard = new OtherBoard();
    }
    public OtherBoardWrapper(int row, int col, String name) { //construct an otherboard based on parameters
        otherboard = new OtherBoard(row, col, name);
    }
    @Override
    public void print() { //get otherboard's print method
        otherboard.printMyBoard();
    }
    @Override
    public String getMark(int row, int col) { //return otherboard's getmark
        return otherboard.getMark(row, col);
    }
    @Override
    public boolean makeMove(String player, int square) { //return otherboard's setmark
        return otherboard.setMark(player, square);
    }
    @Override
    public void setSize(int row, int col) { //return otherboard's setsize
        otherboard.setSize(row, col);
    }
    @Override
    public int getRowSize() { //return otherboard's row size
        return otherboard.getRowSize();
    }
    @Override
    public int getColSize() { //return otherboard's col size
        return otherboard.getColSize();
    }
    @Override
    public String getName() { //return otherboard's name
        return otherboard.getName();
    }
    @Override
    public boolean isFull() { //return if otherboard is full
        return otherboard.isFull();
    }
    @Override
    public String getWinner() { //return otherboard's winner
        return otherboard.getWinner();
    }
    @Override
    public void setWinner(String winner) { //set otherboard's winner
        otherboard.setWinner(winner);
    }
    @Override
    public boolean gameOver() { //return if otherboard game is over
        return otherboard.gameOver();
    }
    @Override
    public void setBoardNumber(int num) { //set otherboard's board number
        otherboard.setBoardNumber(num);
    }
    @Override
    public int getBoardNumber() { //return otherboard's baord number
        return otherboard.getBoardNumber();
    }
    @Override
    public boolean isBoxAvailable(int box) { //return if otherboard's box is available
        return otherboard.isBoxAvailable(box);
    }
}
