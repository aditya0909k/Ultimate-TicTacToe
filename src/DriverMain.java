//Aditya Kulkarni CS2336.003

/* Problem analysis

*/

/* Problem solution

*/

public class DriverMain {
    public static void main(String[] args) {
        UltimateTTT TTTGame = new UltimateTTT();
        // IBoard[] boards = new IBoard[9];
        // for (int i = 0; i < boards.length; i++) {
        //     boards[i] = new OtherBoardWrapper();
        // }
        //TTTGame.setBoard(boards);
        TTTGame.setPlayers(new HumanPlayer("Player1", "X"), new HumanPlayer("Player2", "O"));
        TTTGame.start();
    }
}
