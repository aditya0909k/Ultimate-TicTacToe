//Aditya Kulkarni CS2336.003

/* Problem analysis

*/

/* Problem solution

*/

public class UltimateTTT {
    private IBoard[] boards;
    private int gameRowSize = 3;
    private int gameColSize = 3;
    private APlayer[] players = new APlayer[2];
    private String[] marks = {"X", "O"};
    private String name = "Ultimate TTTGame";
    private String ultimateWinner = "";
    private int currentIndex = -1;

    public UltimateTTT() {
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
        boards = new Board[gameRowSize*gameColSize];
        for (int i = 0; i < boards.length; i++) {
            this.boards[i] = new Board(gameRowSize, gameColSize, "TTTGame");
        }
    }
    public void setBoard(IBoard board) {
        boards = new IBoard[gameRowSize*gameColSize];
        for (int i = 0; i < boards.length; i++) {
            this.boards[i] = board;
        }
    }

    //
    //
    // need to fix Board class makeMove logic to implement the specific rules
    //
    //
    public void start() {
        System.out.println("==== WELCOME TO THE ULTIMATE TIC-TAC-TOE GAME!! ====");
        print();
        do {
            switchPlayer();
            System.out.println("Current Player is: " + players[currentIndex].getMark());
            int board = players[this.currentIndex].selectBoard(gameRowSize*gameColSize);
            int square = players[this.currentIndex].selectBoardValue(gameColSize*gameColSize);
            boards[board].makeMove(players[this.currentIndex].getMark(), square);
            print();
        } while(!gameOver());
    }
    //
    //
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    //
    //

    public boolean gameOver() { //Check if there is a winner of the game
        if (isWinnerOfUltimateBoard()) {
            if (ultimateWinner.equals("Tie")) 
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

    //
    //
    // need to change print method... a lot
    //
    //
    public void print() {         
        for (int i = 0; i < boards.length; i++) {
            boards[i].print();
        }
    }
    //
    //
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    //
    //
    public boolean isFull() {
        return isFull(boards);
    }
    private boolean isFull(IBoard[] boards) {
        for (int i = 0; i < boards.length; i++) {
            if (!boards[i].isFull())
                return false;
        }
        return true;
    }
    //check if there is a winner of ultimate
    public boolean isWinnerOfUltimateBoard() {
        if (checkBoardRows()) {
            return true;
        }
        if (checkBoardCols()) {
            return true;
        }
        if (checkBoardDiagLR()) {
            return true;
        }
        if (checkBoardDiagRL()) {
            return true;
        }
        if (isFull()) {
            ultimateWinner = "Tie";
            return true;
        }
        return false;
    }
    //row checking
    public boolean checkBoardRows() { //check all rows for 3 boards in a row won
        for (int i = 0; i < gameRowSize; i++) {
            if (checkBoardRow(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkBoardRow(int row) { //check an individual row for 3 boards in a row
        int counter = 0;
        int currentRow = -1;
        for (int i = 0; i < gameRowSize-1; i++) {
            currentRow = row * gameRowSize + i;
            if ((isWinnerOfBoard(boards[currentRow]) && isWinnerOfBoard(boards[currentRow+1]))) {
                if (boards[currentRow].getWinner().equals(boards[currentRow+1].getWinner()))
                    counter++;
            }
        }
        if (counter == gameRowSize-1) {
            ultimateWinner = boards[currentRow].getWinner();
            return true;
        }
        return false;
    }
    //column checking
    public boolean checkBoardCols() { //check all cols for 3 boards in a row won
        for (int i = 0; i < gameColSize; i++) {
            if (checkBoardCol(i)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkBoardCol(int col) { //check an individual row for 3 boards in a row
        int counter = 0;
        int currentCol = -1;
        for (int i = 0; i < gameColSize-1; i++) {
            currentCol = i * gameRowSize + col; 
            if ((isWinnerOfBoard(boards[currentCol]) && isWinnerOfBoard(boards[currentCol+1]))) {
                if (boards[currentCol].getWinner().equals(boards[currentCol+1].getWinner()))
                    counter++;
            }
        }
        if (counter == gameColSize-1) {
            ultimateWinner = boards[currentCol].getWinner();
            return true;
        }
        return false;
    }
    //LR checking
    public boolean checkBoardDiagLR() { //check top left to bottom right diagonal for 3 boards in a row won
        int counter = 0;
        for (int i = 0; i < (gameRowSize*gameColSize)-1; i+=gameRowSize+1) { 
            if ((isWinnerOfBoard(boards[i]) && isWinnerOfBoard(boards[i+gameRowSize+1]))) {
                if (boards[i].getWinner().equals(boards[i+gameRowSize+1].getWinner()))
                    counter++;
            }
            if (counter == gameRowSize-1) { 
                ultimateWinner = boards[i].getWinner();
                return true;
            }
        }
        return false;
    }
    //RL checking
    public boolean checkBoardDiagRL() { //check top right to bottom left diagonal for 3 boards in a row won
        int counter = 0;
        for (int i = gameRowSize-1; i < (gameRowSize*gameColSize)-2; i+=gameRowSize-1) { 
            if ((isWinnerOfBoard(boards[i]) && isWinnerOfBoard(boards[i+gameRowSize-1]))) {
                if (boards[i].getWinner().equals(boards[i+gameRowSize-1].getWinner()))
                    counter++;
            }
            if (counter == gameRowSize-1) { 
                ultimateWinner = boards[i].getWinner();
                return true;
            }
        }
        return false;
    }
    public boolean isWinnerOfBoard(IBoard board) { //Invokes logical methods from the board class to check for a winner in any row, column, or diagonal. If none of these are true and the board is filled, this confirms that the game is tied.
            if (checkRows(board)) { //check through all rows for a winner 
                return true;
            }
            if (checkCols(board)) { //check through all columns for a winner
                return true;
            }
            if (checkDiagLR(board)) { //check the top left diagonal for winner
                return true;
            }
            if (checkDiagRL(board)) { //check the bottom left diagonal for a winner
                return true;
            }
            if (board.isFull()) { //if all checks are false, and the board is full, this means that the game is tied.
                board.setWinner("Tie");
                return true;
            }
            return false;
    }
    public boolean checkRows(IBoard board) {  //check each row for a winner
        for (int i = 0; i < gameRowSize; i++)  { //go through each row
            if (checkRow(board, i))
                return true;
        }
        return false;
    }
    public boolean checkRow(IBoard board, int row) { //Go through each row of the array and check to see if (rowSize) in a row are the same player mark.
        int counter = 0; 
            for (int col = 0; col < gameColSize; col++) { //start at 0, ensure it only happens colSize-1 times because of adding to the row for indexing, and increase by 1
                if (board.getMark(row, col).equals(players[currentIndex].getMark())) { //if the mark is not "-" and it equals the mark directly next to it
                    counter++; //increase counter
                }    
        }
        if (counter == gameRowSize) { //if there are rowSize marks next to each other
            board.setWinner(players[currentIndex].getMark());
            return true;
        }
        return false;
    }
    public boolean checkCols(IBoard board) { //check each column for a winner
        for (int i = 0; i < gameColSize; i++) { //go through each column
            if (checkCol(board, i)) 
                return true;
        }
        return false;
    }
    public boolean checkCol(IBoard board, int col) { //Go through each column of the array and check to see if (colSize) in a row are the same player mark
        int counter = 0;
        for (int row = 0; row < gameRowSize; row++) { //start at 0, go through all columns, increase by one each time
            if (board.getMark(row, col).equals(players[currentIndex].getMark())) { //if the mark is not "-" and it equals the mark directly below it
                counter++; //increase counter
            }
        }
        if (counter == gameColSize) { //if there are colSize marks next to each other
            board.setWinner(players[currentIndex].getMark());
            return true;
        }
        return false;
    }
    public boolean checkDiagLR(IBoard board) { //Go through the diagonal of the array from top left to bottom right and see if they are the same player mark
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
            board.setWinner(players[currentIndex].getMark());
            return true;
        }
        return false;
    }
    public boolean checkDiagRL(IBoard board) { //Go through the diagonal of the array from bottom left to top right and see if they are the same player mark
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
            board.setWinner(players[currentIndex].getMark());
            return true;
        }
        return false;
    }
}
