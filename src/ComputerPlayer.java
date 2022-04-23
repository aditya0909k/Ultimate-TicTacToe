//Aditya Kulkarni CS2336.003

/* Problem analysis

*/

/* Problem solution

*/

public class ComputerPlayer extends APlayer{
    public ComputerPlayer(String name, String mark) {
        super(name, mark);
    }
    private int randomNumber(int range) { 
        return (int) (Math.random() * range);
    }
    @Override
    public int selectBoard(int range) {
        int board = randomNumber(range);
        System.out.println("Computer selected board: " + board);
        return board;
    }
    @Override
    public int selectBoardValue(int range) {
        int square = randomNumber(range);
        System.out.println("Computer selected square: " + square);
        return square;
    }
}
