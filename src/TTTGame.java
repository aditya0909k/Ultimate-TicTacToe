//DO NOT USE
//DO NOT USE
//DO NOT USE
//DO NOT USE
//DO NOT USE
//DO NOT USE
//DO NOT USE
//DO NOT USE
//DO NOT USE
//DO NOT USE
//DO NOT USE
//DO NOT USE

//FOR TESTING DRIVERMAIN ONLY



//Aditya Kulkarni CS2336.003

/* Problem analysis
First, I must implement a gameOver method. This method will need to check if there is a winner, and if so, return the winner of the
game and end the game. Otherwise, I should return that there is a tie and end the game.

Second, I will need to implement a switchPlayer method. This method will check what the current player index is, and based off of it,
change the current player index.

Lastly, I will need to implement a isWinner method. This method will call the board class' checkRow, checkCol, and checkDiag's methods
to see if there is a winner. If none of these pass and the game is full, it can be safely assumed that there isWinner is true,
although there is no actual winner player, and rather it is a tie.
*/

/* Problem solution
For the gameOver method, I will set the winner to whatever board.winner is, so that I can display who has won the game.
I will then check if there is a winner by invoking the isWinner() method, and if the board.winner is set to Tie, I will print out
that the game ended in a tie. Otherwise, I will say that the game is over, and output who the winner is. Either of these will set
the gameOver method to true, which will end the start() method.

For switchPlayer, to start off the game I will switch the currentIndex to 0, which will be player 1. Since the currentIndex 
can only be 0 or 1, since there are 2 players, if currentIndex is 0, I will increase it. If currentIndex is 1, I will decrease it.

For the isWinner method, I will 
*/
public class TTTGame {
    private APlayer[] players = new APlayer[2];
    private IBoard board;
    
    private String[] marks = {"X", "O"};
    private String name = "TicTacToe";
    
    private int gameRowSize = 3;
    private int gameColSize = 3;
    private String winner = "";

    private int currentIndex = -1;

    public TTTGame() {
        setPlayers();
        setBoard();
    }
    private void setPlayers() {
        for (int i = 0; i < players.length; i++) {
            ComputerPlayer p = new ComputerPlayer("Player " + i+1, marks[i]);
            players[i] = p;
        }
    }
    public void setPlayers(APlayer player1, APlayer player2) {
        players[0] = player1;
        players[1] = player2;
    }
    private void setBoard() {
        this.board = new Board(gameRowSize, gameColSize, "Ultimate TTTGame");
    }
    public void setBoard(IBoard board) {
        this.board = board;
    }
    public void start() {
        System.out.println("Game has started...");
        do {
            switchPlayer();
            while (!board.makeMove(players[this.currentIndex].getMark(), 
                players[this.currentIndex].selectRowValue(gameRowSize), players[this.currentIndex].selectColValue(gameColSize))); //didnt understnad but asked, now i understand. this is just confirming that players are still able to make a move, meaning the game is not over
            board.print();
        } while(!gameOver());
    }
    public boolean gameOver() { //Check if there is a winner of the game
        if (isWinner()) {
            if (winner.equals("Tie")) 
                System.out.println("Game ended in a tie!");
            else
                System.out.println("Game over! " + players[currentIndex].getMark() + " wins!"); //print out Game over, and winner
            return true;
        }
        return false;
    }
    public void switchPlayer() { //Modify currentIndex to change the player who is playing. 
        if (currentIndex == -1) //since starting at -1, increase to zero to start game with player 1 playing.
            currentIndex++;
        else if (currentIndex == 0) { //if it is player one's turn done, change to player 2
            currentIndex++;
        }
        else if (currentIndex == 1) { //if it is player two's turn done, change to player 1
            currentIndex--;
        }
    }
    private boolean isWinner() { //Invokes logical methods from the board class to check for a winner in any row, column, or diagonal. If none of these are true and the board is filled, this confirms that the game is tied.
        if (checkRows()) { //check through all rows for a winner 
            return true;
        }
        if (checkCols()) { //check through all columns for a winner
            return true;
        }
        if (checkDiagLR()) { //check the top left diagonal for winner
            return true;
        }
        if (checkDiagRL()) { //check the bottom left diagonal for a winner
            return true;
        }
        if (board.isFull()) { //if all checks are false, and the board is full, this means that the game is tied.
            winner = "Tie";
            return true;
        }
        return false;
    }
    public boolean checkRows() {  //check each row for a winner
        for (int i = 0; i < gameRowSize; i++)  { //go through each row
            if (checkRow(i))
                return true;
        }
        return false;
    }
    public boolean checkRow(int row) { //Go through each row of the array and check to see if (rowSize) in a row are the same player mark.
        int counter = 0; 
            for (int col = 0; col < gameColSize; col++) { //start at 0, ensure it only happens colSize-1 times because of adding to the row for indexing, and increase by 1
                if (board.getMark(row, col).equals(players[currentIndex].getMark())) { //if the mark is not "-" and it equals the mark directly next to it
                    counter++; //increase counter
                }    
        }
        if (counter == gameRowSize) { //if there are rowSize marks next to each other
            return true;
        }
        return false;
    }
    public boolean checkCols() { //check each column for a winner
        for (int i = 0; i < gameColSize; i++) { //go through each column
            if (checkCol(i)) 
                return true;
        }
        return false;
    }
    public boolean checkCol(int col) { //Go through each column of the array and check to see if (colSize) in a row are the same player mark
        int counter = 0;
        for (int row = 0; row < gameRowSize; row++) { //start at 0, go through all columns, increase by one each time
            if (board.getMark(row, col).equals(players[currentIndex].getMark())) { //if the mark is not "-" and it equals the mark directly below it
                counter++; //increase counter
            }
        }
        if (counter == gameColSize) { //if there are colSize marks next to each other
            return true;
        }
        return false;
    }
    public boolean checkDiagLR() { //Go through the diagonal of the array from top left to bottom right and see if they are the same player mark
        int counter = 0;
        int row = 0;
        int col = 0;
        for (int i = 0; i < gameRowSize; i++) { //start at top left, ensure it is less than the bottom right number, increase by rowSize+1 to get the next diagonal index
            if (board.getMark(row, col).equals(players[currentIndex].getMark())) { //if the mark is not "-" and it is equal to the mark directly diagonal to it going from top left to bottom right
                counter++; //increase counter
            }
            row++; 
            col++; //increment for diagonal
        }
        if (counter == gameRowSize) { //if there are rowSize marks next to each other
            return true;
        }
        return false;
    }
    public boolean checkDiagRL() { //Go through the diagonal of the array from bottom left to top right and see if they are the same player mark
        int counter = 0;
        int row = 0;
        int col = gameColSize-1;
        for (int i = 0; i < gameRowSize; i++) { //start at top right, ensure it is less than bottom left number, increase by rowSize-1 to get the next diagonal index
            if (board.getMark(row, col).equals(players[currentIndex].getMark())) { //if the mark is not "-" and it is equal to the mark directly diagonal to it going from top right to bottom left
                counter++; //increase counter
            }
            row++; //start top right corner, increase row, decrease column
            col--;
        }
        if (counter == board.getRowSize()) { //if there are rowSize marks next to each other
            return true;
        }
        return false;
    }
}
