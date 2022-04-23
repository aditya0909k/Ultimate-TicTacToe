//Aditya Kulkarni CS2336.003
public class DriverMain {
    public static void main(String[] args) {
        TTTGame TTTGame = new TTTGame();
        TTTGame.setBoard(new OtherBoardWrapper());
        TTTGame.setPlayers(new HumanPlayer("Player1", "X"), new HumanPlayer("Player2", "O"));
        TTTGame.start();
    }
}
