public class OtherBoard{

	private int boardRowSize;
	private int boardColSize;
	private char[][] board;
	private String name;
	private String winner;

	OtherBoard(){
		this(3, 3, "TTT 2D array of char");
	}

	OtherBoard(int row, int col, String name){
		this.setName(name);
		this.setSize(row, col);
	}

	private void init() {
		this.board = new char[boardRowSize][boardColSize];
		for(int i = 0 ; i < board.length; i++){
			for(int j = 0 ; j < board[i].length; j++){
				board[i][j] = Mark.DASH.getMark().charAt(0);
			}
		}
		printMyBoard()	;
	}

	public void printMyBoard() {
		System.out.println("printing the " + this.name + " - " +
				this.boardRowSize + "*" + this.boardColSize + " board info....");
		for(int i = 0 ; i < board.length; i++){
			for(int j = 0 ; j < board[i].length; j++){
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private int availability() {
		int count = 0;
		for(int i = 0 ; i < board.length; i++){
			for(int j = 0 ; j < board[i].length; j++){
				if(board[i][j] == Mark.DASH.getMark().charAt(0)) count++;
			}
		}
		return count;
	}

	public String getMark(int row, int col) {
		return board[row][col] + "";
	}

	public boolean setMark(int row, int col, String player) {
		if(board[row][col] == Mark.DASH.getMark().charAt(0)){
			board[row][col] = player.charAt(0);
			return true;
		}else return false;
	}

	public void setSize(int row, int col) {
		this.boardRowSize = row;
		this.boardColSize = col;
		this.init();
	}

	private void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void reset() {
		//implement

	}

	public int getColSize() {
		return this.boardColSize;
	}

	public int getRowSize() {
		return this.boardRowSize;
	}

	/*
	 * it will give us an array of all the empty spots
	 * by multiplying row and col
	 */
	public int[] emptyCells(){
		int[] emptyCells = new int[availability()];
		int index = 0;
		if(availability()>0)
			for(int i = 0 ; i < board.length; i++){
				for(int j = 0 ; j < board[i].length; j++){
					if(board[i][j] == Mark.DASH.getMark().charAt(0)) 
						emptyCells[index++] = i * this.boardRowSize + j;
				}
			}
		return emptyCells;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getWinner() {
		return winner;
	}
}