//Aditya Kulkarni CS2336.003
/*
*
*
*
                            ***EVERY CLASS/FILE HAS A COMMENT SECTION FOR PROBLEM ANALYSIS & PROBLEM SOLUTION***
*
*
*
*/
/* Problem analysis 
This is where we create an instance of UltimateTTT and start it. We can also pass in any type of IBoard by initializing an array
of it and getting a different type of board.
*/

/* Problem solution
We will create an instance of TTTGame and start it. Optionally, we can create an array of IBoards to pass into setBoard.
*/

public class UltimateTTTRunner { //main runner
    public static void main(String[] args) { //main runner
        UltimateTTT TTTGame = new UltimateTTT(); //create instance
        IBoard[] boards = new IBoard[9];
        for (int i = 0; i < boards.length; i++) {
            boards[i] = new OtherBoardWrapper();
        }
        TTTGame.setBoard(boards);
        TTTGame.setPlayers(new HumanPlayer("Player1", "X"), new HumanPlayer("Player2", "O"));
        TTTGame.start(); //start
    }
}
