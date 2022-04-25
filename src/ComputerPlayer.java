//Aditya Kulkarni CS2336.003

/* Problem analysis
To test our UltimateTTT game without all the manual labor, we should have a computer player to see how the game plays itself out 
when automated. This class will extend APlayer since it is a type of player.
*/

/* Problem solution
The only thing we need to do in this class would be to set the name and mark we pass into when creating ComputerPlayer, and also
select our board and square value. To do this, we will use a randomNumber class that accepts a range and uses Math.random. We will
pass this back into the selectBoard or selectBoardValue to get our computer generated values.
*/

public class ComputerPlayer extends APlayer{ //computerplayer extends aplayer class.
    public ComputerPlayer(String name, String mark) { //set its name and mark using APlayer's constructor
        super(name, mark);
    }
    private int randomNumber(int range) { //get a randomnumber in the range using Math.random
        return (int) (Math.random() * range);
    }
    @Override
    public int selectBoard(int range) { //select our board value
        int board = randomNumber(range);
        System.out.println("Computer selected board: " + board);
        return board;
    }
    @Override
    public int selectSquare(int range) { //select our square value
        int square = randomNumber(range);
        System.out.println("Computer selected square: " + square);
        return square;
    }
}
