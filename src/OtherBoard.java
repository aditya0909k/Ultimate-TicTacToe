//Aditya Kulkarni CS2336.003

/* Problem analysis
This is simply just for showing how my UltimateTTT accepts any type of board. This board class will address 
everything in the board itself, making sure to store if the game is over, who the winner is, making a move on the board,
etc...
*/

/* Problem solution
This otherboard will be a 2 dimensional char array. The constructor will either default to a 3x3 array or based on user input.
It will have all the standard variables like the row size, col size, board's name, its winner, if the game is over, its boardNumber,
and all the set/get methods required for each of these variables. Additionally, it will have setMark, which is where a move on the
board is made. It will also need to be initialized, where the init() function will set all the char's to DASH. I will also have
an isFull method to see if the board is full, and this will be using the board's isBoxAvailable. I will have 2 isBoxAvailable. One
will be public and accept square for IBoard, one will be private and accept row/col for dealing inside the otherboard only. 
I will have changeBoxes method to change DASH to * if a board has a winner, and finally a print method to just print out the board
as normal.
*/

public class OtherBoard{
	private char[][] board; //our board
	private int boardRowSize;
	private int boardColSize;
	private String name;
	private String winner;
	private boolean gameOver = false;
	private int boardNumber;

	OtherBoard(){ //constructor defaults 3x3 board
		this(3, 3, "TTT 2D array of char");
	}
	OtherBoard(int row, int col, String name){ //pass in row, col, name and will set it
		this.setName(name);
		this.setSize(row, col);
	}
	//all iboard methods
	public String getWinner() { //return winner
		return winner;
	}
	public void setBoardNumber(int num) { //set boardnum
		this.boardNumber = num;
	}
	public int getBoardNumber() { //return boardnum
		return boardNumber;
	}
	public String getName() { //return name
		return this.name;
	}
	public int getColSize() { //return col size
		return this.boardColSize;
	}
	public int getRowSize() { //return row size
		return this.boardRowSize;
	}
	public String getMark(int row, int col) { //get the mark at specific row and col
		return board[row][col] + "";
	}
	public boolean gameOver() { //is game over?
		return gameOver;
	}
	private void setName(String name) { //set name
		this.name = name;
	}
	public void setWinner(String winner) { //set winner, and also set gameover as true, and change boxes
		this.winner = winner;
		this.gameOver = true;
		changeBoxes();
	}
	public boolean setMark(String player, int square) { //set mark on the board
		int row = square/getRowSize(); //use this formula to get the row
		int col = square - (row*getRowSize()); //use this formula to get the col
		if (isBoxAvailable(row, col)) { //if the box is available here
			board[row][col] = player.charAt(0); //set it to our new mark
			return true;
		}
		return false;
	}
	public void setSize(int row, int col) { //set board size and initialize our board
		this.boardRowSize = row;
		this.boardColSize = col;
		this.init(); 
	}
	public boolean isFull(){ //is our board full?
		int count = 0; //counter
		for (int row = 0; row < getRowSize(); row++) { //loop through every single box
			for (int col = 0; col < getColSize(); col++) {
				if (!isBoxAvailable(row, col)) //if its not available
					count++; //incrememnt count
			}
		}
		if (count == (boardRowSize*boardColSize)) { //if count == the XxX size of board, our board is full
			return true;
		}
		return false;
	}
	public boolean isBoxAvailable(int box) { //is the box available?
		int row = box/getRowSize(); //use formula to get row
		int col = box - (row*getRowSize()); //use formula to get col
		if (isBoxAvailable(row, col)) //if its available
			return true;
		return false;
	}
	public void printMyBoard() { //print out board
		System.out.println("printing the " + this.name + " - " + this.boardRowSize + "*" + this.boardColSize + " board info....");
		for(int row = 0 ; row < getRowSize(); row++) {
			for(int col = 0 ; col < getColSize(); col++) {
				System.out.print(board[row][col] + " "); //loop through all boxes, print each one
			}
			System.out.println();
		}
	}
	//all board specific methods
	private void init() { //initialize our board
		this.board = new char[boardRowSize][boardColSize]; //create a 2d array XxX size
		for(int i = 0 ; i < board.length; i++){
			for(int j = 0 ; j < board[i].length; j++){
				board[i][j] = Mark.DASH.getMark().charAt(0); //set all boxes to DASH at start
			}
		}
	}
	public boolean isBoxAvailable(int row, int col) { //is the box available?
		if(board[row][col] == Mark.DASH.getMark().charAt(0)) //if its a dash
			return true;
		if (board[row][col] == '*') //or a star
			return true; //return true
		return false;
	}
	public void changeBoxes() { //change our boxes
		if (gameOver()) { //make sure game is over
			for (int row = 0; row < getRowSize(); row++) {
				for (int col = 0; col < getColSize(); col++) {
					if (isBoxAvailable(row, col)) { //for every box, if its still available,
						board[row][col] = '*'; //make it a start to indicate an already won board
					}
				}
			}
		}
	}
}