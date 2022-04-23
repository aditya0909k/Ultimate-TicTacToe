import java.util.Scanner;
//Aditya Kulkarni CS2336.003

/* Problem analysis

*/

/* Problem solution

*/

public class HumanPlayer extends APlayer{
    Scanner input = new Scanner(System.in);

    public HumanPlayer(String name, String mark) {
        super(name, mark);
    }
    @Override
    public int selectBoard(int range) {
        range-=1;
        System.out.println("Please select a valid board: ");
        int boardNum = -1;
        do {
            System.out.print("Selected board: ");
            String boardNumString = input.nextLine();
            if (boardNumString.length() == 1 && boardNumString.matches("[0-8]")) {
                boardNum = Integer.parseInt(boardNumString);
            }
            if (boardNum < 0 || boardNum > range) {
                System.out.println("Invalid board! Enter a number from 0 to " + range);
            }
        } while (boardNum < 0 || boardNum > range);
        return boardNum;
    }
    @Override
    public int selectBoardValue(int range) {
        range-=1;
        System.out.println("Please select a valid square on the selected board: ");
        int boardNumValue = -1;
        do {
            System.out.print("Selected square: ");
            String boardNumValueString = input.nextLine();
            if (boardNumValueString.length() == 1 && boardNumValueString.matches("[0-8]")) {
                boardNumValue = Integer.parseInt(boardNumValueString);
            }
            if (boardNumValue < 0 || boardNumValue > range) {
                System.out.println("Invalid board! Enter a number from 0 to " + range);
            }
        } while (boardNumValue < 0 || boardNumValue > range);
        return boardNumValue;
    }
}
