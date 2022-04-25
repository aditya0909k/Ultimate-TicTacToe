import java.util.Scanner;
//Aditya Kulkarni CS2336.003

/* Problem analysis
We should also have a way for humans to play the game. In this case, we can create a HumanPlayer class that is an extension of APlayer,
so that it can still be used in UltimateTTT class. We will also need to make sure our humans can pick a board/square, but within 
certain bounds.
*/

/* Problem solution
First, we need to make sure it extends APlayer. This is because our UltimateTTT takes in an APlayer, which could be computer,
human, robot???, etc. We will create our HumanPlayer through calling the APlayer constructor on name and mark, that we pass in.
For our selectBoard and selectBoardValue value, we will make sure what the user types in is a number in the specified range, and only 
then return it.
*/

public class HumanPlayer extends APlayer{ //HumanPlayer extends the APlayer class
    Scanner input = new Scanner(System.in);

    public HumanPlayer(String name, String mark) { //pass in the parameters to super's constructor
        super(name, mark);
    }
    @Override
    public int selectBoard(int range) { //select our board value
        range--; //decrement our range to account for indexing purposes
        System.out.println("Please select a valid board: "); 
        int boardNum = -1; //initialize our boardnum
        do { 
            System.out.print("Selected board: ");
            String boardNumString = input.nextLine(); //get the string value of what user inputs
            try { //try parsing the string
                boardNum = Integer.parseInt(boardNumString);
            } catch (Exception e) {System.out.println("Invalid character! Enter a number from 0 to " + range); continue;} //if it is not a string, sysout that its invalid, and use continue to repeat loop
            if (boardNum < 0 || boardNum > range) { //make sure boardNum is in our range
                System.out.println("Invalid board! Enter a number from 0 to " + range); //if it isn't sysout that its not
            }
        } while (boardNum < 0 || boardNum > range); //while boardnum isn't in range, keep doing this loop
        return boardNum;
    }
    @Override
    public int selectSquare(int range) { //select our square value
        range--; //decrement our range to account for indexing purposes
        System.out.println("Please select a valid square on the selected board: ");
        int boardSquare = -1; //initialize our boardsquare
        do {
            System.out.print("Selected square: ");
            String boardSquareString = input.nextLine(); //get the string value of what user inputs
            try { //try parsing the string
                boardSquare = Integer.parseInt(boardSquareString);
            } catch (Exception e) {System.out.println("Invalid character! Enter a number from 0 to " + range); continue;} //if it is not a string, sysout that its invalid, and use continue to repeat loop
            if (boardSquare < 0 || boardSquare > range) { //make sure boardSquare is in our range
                System.out.println("Invalid board! Enter a number from 0 to " + range); //if its isn't sysout that its not
            }
        } while (boardSquare < 0 || boardSquare > range); //while boardsquare isn't in range, keep doing this loop
        return boardSquare;
    }
}
