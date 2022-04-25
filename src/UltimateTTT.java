//Aditya Kulkarni CS2336.003

/* Problem analysis
The UltimateTTT game is an ultimate board that consists of 9 regular tictactoe boards. To win an ultiamte game, you must win
3 boards in a row, 3 boards in a column, or 3 boards in a diagonal. This makes ultiamteTTT a game of strategy rather than mostly
luck. For this UltimateTTT, we will need to have an array of the IBoard type, so that it can be played with any type of board
considering that maybe this is a product we are shipping to a customer. It will also need 2 APlayers for the tictactoe
game, which could be either computer or human...

There are a couple rules to the UltimateTTT. For instance, wherever the last player placed their square is the board you will play on.
The only exceptions are if the game has just started, or the last player sent you to a full board, in which case you pick your
own board. Also, you can still be sent to a board that has already been won, but this won't affect the outcome of the game.
*/

/* Problem solution
To start with, the basis of our UltimateTTT game will be an array of IBoards, and an array of APlayers. We will also need variables
to set our gameRowSize, and our gameColSize. We will need a variable to store the ultimateWinner, and the currentIndex of which
player is currently getting to play a move. 

First, our constructor: It will call setPlayers() and setBoard(), which will set our players to default as computers, or
humans if we pass in an APlayer, and will set our board to Board type by default, or any other type of IBoard if we pass
in an array of IBoards (in testing cases, OtherBoardWrapper()). 

Our setPlayers will be our method to set the APlayers in our game to computers, and the overloaded setPlayers method with parameters
will set the APlayers in our game to humans or any other type of player.

In our setBoard, by default we will create an IBoard array of rowsize*colsize, and loop through the boards array, setting each
board as a new Board type. In the overloaded method with parameters, we will set each board as a new IBoard type. Regardless of 
which method is ultimately used, we will also set the board number of each to its board number.

Our start function is where the game actually happens. To start off with, we will need a few variables to keep track of what is 
happening in the game. We will need "start", to see if the game just started, board, which is the user input board, square, which
is the user input square, and squareActingAsNextBoard, which is the square that the user inputted, which now acts as the next
board. We will also have prevPlayer to store so that when we state that the board is full, we can state which player just caused
the board to be full. Then, we will do a loop while the game isn't over. In this loop, we will first print out our current board, and then switchPlayer().
We will then check if it is a starting game where start == true, and if so, allow the user to select the board and square. We then
set start to false and use continue to repeat the loop. Next, we would want to check if the square that the last player played, which
is now the new board, is full. If so, we will tell the user that the board is full, and allow them to select a new board and square.
We will use a while loop to allow the user to continue picking a board until it is valid. Once the board is valid, We will ask them to
pick which square they want, and then use continue to repeat the loop. If neither of these conditions are satisfied, we will simply just
ask the user to pick a square on the already selected board, which is squareActingAsNextBoard. We will use a while loop to 
allow the user to continue picking a square until it is valid. Once the square is valid, we will makeMove on that square, and
set squareActingAsNewBoard to that square the user picked, to follow the rules of the game. We will also update prevPlayer.

In the gameOver function, we are checking for the method isWinnerOfUltimateBoard, and once it is returned as true, gameOver will
be returned as true, and our game will stop. In this method, we will also print who the winner of the game is, or if it is a tie.

In switchPlayer, we will switch the index based on the current index to change who is playing.

The print method will print out our boards and their rows and columns in the format that is required.

Our isFull will return if the UltimateTTT board is completely full, and will call the overloaded method isFull, and pass in the
Iboards array boards. The isFull(Iboard[] boards) will go through each board and call the isFull() method of that board. If any of
them are false, we know that the Ultimate Board is not full.

Now, we are at our logic part of the tic tac toe. First, we will check for the Ultimate Board Winner. This will check the
ultimate board's rows, columns, and diagonals. In these methods, we will call the isWinnerOfBoard method, passing in the board
that we are checking if it is won. In the isWinnerOfBoard method, we take in a parameter IBoard, and check this board's rows, 
columns, and diagonals. If any are satisfied, and when it is we change the board's gameOver to true and set its winner, we would
return this truthy value back to isWinnerOfBoard, which will return the truthy value to Ultimate Board's check row, check col,
or check diagonal. This will be how the game decides if there is a winner of the Ultimate TTTGame.
*/

public class UltimateTTT {
    private IBoard[] boards; //IBoard array
    private int gameRowSize = 3;
    private int gameColSize = 3;
    private APlayer[] players = new APlayer[2]; //our game players
    private String[] marks = {"X", "O"};
    private String ultimateWinner = "";
    private int currentIndex = -1;

    public UltimateTTT() { //initialize our board
        setPlayers();
        setBoard();
    }
    private void setPlayers() { //default method, computer players
        for (int i = 0; i < players.length; i++) {
            ComputerPlayer p = new ComputerPlayer("Player " + i+1, marks[i]);
            players[i] = p;
        }
    }
    public void setPlayers(APlayer player1, APlayer player2) { //set our players if game wants something other than computers playing
        players[0] = player1; 
        players[1] = player2;
    }
    private void setBoard() { //by default, our boards are the Board type
        boards = new IBoard[gameRowSize*gameColSize]; //create an IBoard array size of rowsize*colsize
        for (int i = 0; i < boards.length; i++) {
            this.boards[i] = new Board(gameRowSize, gameColSize, "TTTGame"); //for every IBoard in the array, create a new Board and place it there
            this.boards[i].setBoardNumber(i); //set the board number
        }
    }
    public void setBoard(IBoard[] boards) { //set our board if we are given a differnt type of board
        for (int i = 0; i < boards.length; i++) {
            this.boards[i] = boards[i]; //for each board in UltimateTTT boards, set it as other type of board
            this.boards[i].setBoardNumber(i); //set the board number
        }
    }
    public void start() {
        System.out.println("==== WELCOME TO THE ULTIMATE TIC-TAC-TOE GAME!! ==== \n");
        boolean start = true; //variable that indicates if we have just started the game
        int board; //our user input board
        int square; //our user input square
        int squareActingAsNextBoard = -1; //our square that is the next board number
        APlayer prevPlayer = null; //store previous player for whenever board is full
        do {
            print(); //print out the board
            switchPlayer(); //switch the player
            System.out.println("Current Player is: " + players[currentIndex].getMark()); //state current player
            if (start) { //if its the start of game
                board = players[currentIndex].selectBoard(gameRowSize*gameRowSize); //let player choose board
                squareActingAsNextBoard = players[currentIndex].selectSquare(gameColSize*gameColSize); //let player choose square that will act as next board
                boards[board].makeMove(players[currentIndex].getMark(), squareActingAsNextBoard); //make the move
                start = false; //set start to false
                continue; //continue to repeat loop
            }
            if (boards[squareActingAsNextBoard].isFull()) { //if the board that was just made a move on is full now
                System.out.println("-----------------------------------------------------------------------------------------------");
                System.out.println("The board that player " + prevPlayer.getMark() + " tried to send you to is full! You get to pick any board you would like.");
                System.out.println("-----------------------------------------------------------------------------------------------");
                board = players[currentIndex].selectBoard(gameRowSize*gameRowSize); //let player choose board
                while (boards[board].isFull()) { //make sure it isnt a full board
                    System.out.println("---------------------------");
                    System.out.println("That board is already full!");
                    System.out.println("---------------------------");
                    board = players[currentIndex].selectBoard(gameRowSize*gameColSize); //let player choose board
                }
                squareActingAsNextBoard = players[currentIndex].selectSquare(gameColSize*gameColSize); //let player choose square that will act as next board
                boards[board].makeMove(players[currentIndex].getMark(), squareActingAsNextBoard); //make the move
                continue; //continue to repeat loop
            }
            System.out.println("Selected board: " + squareActingAsNextBoard); //state the board that you will be playing on
            square = players[currentIndex].selectSquare(gameColSize*gameColSize); //let player choose square
            while (!boards[squareActingAsNextBoard].isBoxAvailable(square)) { //make sure this square isnt already taken
                System.out.println("-----------------------------");
                System.out.println("That square is already taken!");
                System.out.println("-----------------------------");
                square = players[currentIndex].selectSquare(gameColSize*gameColSize); //let player choose square
            }
            boards[squareActingAsNextBoard].makeMove(players[currentIndex].getMark(), square); //make the move
            squareActingAsNextBoard = square; //square that will act as next board is set to the player choose square
            prevPlayer = players[currentIndex]; //set prevplayer to our current player, so that next iteration it will be prevplayer
        } while(!gameOver()); //continue this loop while the game is not over
    }
    private boolean gameOver() { //Check if there is a winner of the game
        if (isWinnerOfUltimateBoard()) { //if there is an ultiamte winner
            print(); //print the final board
            if (ultimateWinner.equals("Tie")) { //if it is a tie
                System.out.println();
                System.out.println("Game ended in a tie!");
            }
            else { //if there is an actual player winner
                System.out.println();
                System.out.println("Game over! " + players[currentIndex].getMark() + " wins!"); //print out Game over, and winner
            }
            return true;
        }
        return false;
    }
    private void switchPlayer() { //Modify currentIndex to change the player who is playing. 
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
        for (int i = 0; i < boards.length; i++) {
            if (boards[i].gameOver()) {
                System.out.println("Board #" + i + " winner is " + boards[i].getWinner());
            }
        }
        System.out.println();
    }
    //
    //
    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    //
    //

    private boolean isFull() { //is our board full?
        return isFull(boards); //call helper method on boards array
    }
    private boolean isFull(IBoard[] boards) { //check if all boards in IBoard array are full
        for (int i = 0; i < boards.length; i++) {
            if (!boards[i].isFull()) //if any of the boards isnt full
                return false; //return false
        }
        return true;
    }
    private boolean isWinnerOfUltimateBoard() { //check if there is a winner of ultimate board
        if (checkBoardRows()) { //check board's rows
            return true;
        }
        if (checkBoardCols()) { //check board's cols
            return true;
        }
        if (checkBoardDiagLR()) { //check the top left to bottom right diagonal
            return true;
        }
        if (checkBoardDiagRL()) { //check the top right to bottom left diagonal
            return true;
        }
        if (isFull()) { //if its full and no winner yet, it is tied
            ultimateWinner = "Tie";
            return true;
        }
        return false;
    }
    //row checking
    private boolean checkBoardRows() { //check all rows for 3 boards in a row won
        for (int i = 0; i < gameRowSize; i++) { //for each row
            if (checkBoardRow(i)) { //check that row
                return true;
            }
        }
        return false;
    }
    private boolean checkBoardRow(int row) { //check an individual row for 3 boards in a row
        int counter = 0; //start at 0 counter
        int currentSquare = -1;
        for (int i = 0; i < gameRowSize-1; i++) {
            currentSquare = row * gameRowSize + i; //get currentsquare using formula
            if ((isWinnerOfBoard(boards[currentSquare]) && isWinnerOfBoard(boards[currentSquare+1]))) { //if there is a winner at currentsquare and the index+1 (since it is a row)
                if (!boards[currentSquare].getWinner().equals("Tie") && boards[currentSquare].getWinner().equals(boards[currentSquare+1].getWinner())) //make sure the winner isnt Tie and if both boards winner are same
                    counter++; //increment counter
            }
        }
        if (counter == gameRowSize-1) { //if counter is equal to rowSize-1 because we are only checking if the board NEXT to another board is same winner
            ultimateWinner = boards[currentSquare].getWinner(); //set our ultimate winner
            return true;
        }
        return false;
    }
    //column checking
    private boolean checkBoardCols() { //check all cols for 3 boards in a row won
        for (int i = 0; i < gameColSize; i++) { //for each col
            if (checkBoardCol(i)) { //check that col
                return true;
            }
        }
        return false;
    }
    private boolean checkBoardCol(int col) { //check an individual row for 3 boards in a row
        int counter = 0; //start at 0 counter
        int currentSquare = -1;
        for (int i = 0; i < gameColSize-1; i++) {
            currentSquare = i * gameRowSize + col; //get currentsquare using formula
            if ((isWinnerOfBoard(boards[currentSquare]) && isWinnerOfBoard(boards[currentSquare+gameColSize]))) { //if there is a winner at currentsquare and the index+gameColSize (since it is a column)
                if (!boards[currentSquare].getWinner().equals("Tie") && boards[currentSquare].getWinner().equals(boards[currentSquare+gameColSize].getWinner())) //make sure the winner isnt Tie and if both boards winner are same
                    counter++;
            }
        }
        if (counter == gameColSize-1) {  //if counter is equal to rowSize-1 because we are only checking if the board BELOW another board is same winner
            ultimateWinner = boards[currentSquare].getWinner(); //set our ultimate winner
            return true;
        }
        return false;
    }
    //LR checking
    private boolean checkBoardDiagLR() { //check top left to bottom right diagonal for 3 boards in a row won
        int counter = 0; //start at counter 0
        for (int currentSquare = 0; currentSquare < (gameRowSize*gameColSize)-1; currentSquare+=gameRowSize+1) { //start at 0, make sure currentsquare is less than rowsize*colsize-1 because the last square will be rowsize*colsize, add rowsize+1 each time to get the next diagonal spot
            if ((isWinnerOfBoard(boards[currentSquare]) && isWinnerOfBoard(boards[currentSquare+gameRowSize+1]))) { //if there is a winner of currentsquare and its diagonal
                if (!boards[currentSquare].getWinner().equals("Tie") && boards[currentSquare].getWinner().equals(boards[currentSquare+gameRowSize+1].getWinner())) //make sure winner isnt Tie and also make sure they are both the same winner
                    counter++; //increment counter
            }
            if (counter == gameRowSize-1) { //if counter is rowsize-1 because we are looking for the diagonal NEXT to currentsquare
                ultimateWinner = boards[currentSquare].getWinner(); //set our ultimate winner
                return true;
            }
        }
        return false;
    }
    //RL checking
    private boolean checkBoardDiagRL() { //check top right to bottom left diagonal for 3 boards in a row won
        int counter = 0;
        for (int i = gameRowSize-1; i < (gameRowSize*gameColSize)-2; i+=gameRowSize-1) {  //start at rowsize-1 which is the top right square, go until rowsize*colsize-2 because that is the bottom left square, add rowsize-1 each time to get the next diagonal square
            if ((isWinnerOfBoard(boards[i]) && isWinnerOfBoard(boards[i+gameRowSize-1]))) {  //if there is a winner of currentsquare and its diagonal
                if (!boards[i].getWinner().equals("Tie") && boards[i].getWinner().equals(boards[i+gameRowSize-1].getWinner()))  //make sure winner isnt Tie and also make sure they are both the same winner
                    counter++;
            }
            if (counter == gameRowSize-1) { //if counter is rowsize-1 because we are looking for the diagonal NEXT to currentsquare
                ultimateWinner = boards[i].getWinner(); //set our ultimate winner
                return true;
            }
        }
        return false;
    }
    private boolean isWinnerOfBoard(IBoard board) { //Pass in the passed in board to each check method. Invokes logical methods to check for a winner in any row, column, or diagonal. If none of these are true and the board is filled, this confirms that the game is tied.
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
            if (!board.gameOver() && board.isFull()) { //if all checks are false, and the board is full, and the game isnt over yet, this means that the game is tied.
                board.setWinner("Tie");
                return true;
            }
            return false;
    }
    //single board row checking
    private boolean checkRows(IBoard board) {  //check each row for a winner based on the board
        for (int i = 0; i < gameRowSize; i++)  { //go through each row
            if (checkRow(board, i))
                return true;
        }
        return false;
    }
    private boolean checkRow(IBoard board, int row) { //Go through each row of the array and check to see if (rowSize) in a row are the same player mark.
        int counter = 0; 
            for (int col = 0; col < gameColSize; col++) { //start at 0, go through each column of the board
                if (board.getMark(row, col).equals(players[currentIndex].getMark())) { //if the mark is not "-" and it equals the mark directly next to it
                    counter++; //increase counter
                }    
        }
        if (counter == gameRowSize && !board.gameOver()) { //if there are rowSize marks next to each other and there is no winner of the board AKA the game isnt over
            board.setWinner(players[currentIndex].getMark()); //set our winner to currentplayer
        }
        if (counter == gameRowSize) { //if there are rowSize marks next to each other
            return true;
        }
        return false;
    }
    //single board col checking
    private boolean checkCols(IBoard board) { //check each column for a winner based on the board
        for (int i = 0; i < gameColSize; i++) { //go through each column
            if (checkCol(board, i))
                return true;
        }
        return false;
    }
    private boolean checkCol(IBoard board, int col) { //Go through each column of the array and check to see if (colSize) in a row are the same player mark
        int counter = 0;
        for (int row = 0; row < gameRowSize; row++) { //start at 0, go through all columns, increase by one each time
            if (board.getMark(row, col).equals(players[currentIndex].getMark())) { //if the mark is not "-" and it equals the mark directly below it
                counter++; //increase counter
            }
        }
        if (counter == gameColSize && !board.gameOver()) { //if there are colSize marks next to each other and there is no winner of the board AKA the game isnt over
            board.setWinner(players[currentIndex].getMark()); //set our winner to current player
        }
        if (counter == gameColSize) { //if there are colSize marks next to each other
            return true;
        }
        return false;
    }
    //single board LR checking
    private boolean checkDiagLR(IBoard board) { //Go through the diagonal of the array from top left to bottom right and see if they are the same player mark
        int counter = 0;
        int row = 0;
        int col = 0;
        for (int i = 0; i < gameRowSize; i++) {//go gameRowSize times
            if (board.getMark(row, col).equals(players[currentIndex].getMark())) { //if the mark is not "-" and it is equal to the mark directly diagonal to it going from top left to bottom right
                counter++; //increase counter
            }
            row++; //increment for diagonal
            col++; //increment for diagonal
        }
        if (counter == gameColSize && !board.gameOver()) { //if there are colSize marks next to each other and there is no winner of the board AKA the game isnt over
            board.setWinner(players[currentIndex].getMark()); //set our winner to the current player's mark
        }
        if (counter == gameColSize) { //if there are colSize marks next to each other
            return true;
        }

        return false;
    }
    //single board RL checking
    private boolean checkDiagRL(IBoard board) { //Go through the diagonal of the array from bottom left to top right and see if they are the same player mark
        int counter = 0;
        int row = 0;
        int col = gameColSize-1;
        for (int i = 0; i < gameRowSize; i++) { //go through this loop gameRowSize times
            if (board.getMark(row, col).equals(players[currentIndex].getMark())) { //if the mark is not "-" and it is equal to the mark directly diagonal to it going from top right to bottom left
                counter++; //increase counter
            }
            row++; //start top right corner, increase row, decrease column
            col--;
        }
        if (counter == gameColSize && !board.gameOver()) { //if there are colSize marks next to each other and there is no winner of the board AKA the game isnt over
            board.setWinner(players[currentIndex].getMark()); //set our winner to the current player's mark
        }
        if (counter == gameColSize) { //if there are colSize marks next to each other
            return true;
        }
        return false;
    }
}
