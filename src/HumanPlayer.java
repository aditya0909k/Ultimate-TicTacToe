import java.util.Scanner;

public class HumanPlayer extends APlayer{
    Scanner input = new Scanner(System.in);

    public HumanPlayer(String name, String mark) {
        super(name, mark);
    }

    @Override
    public int selectRowValue(int range) {
        range-=1;
        System.out.println("Please enter a valid row number from 0 to " + range);
        int row = -1;
        do {
            String rowString = input.nextLine();
            if (rowString.length() == 1 && rowString.matches("[0-9]")) {
                row = Integer.parseInt(rowString);
            }
            if (row < 0 || row > range) {
                System.out.println("Invalid move! Enter a number from 0 to " + range);
            }
        } while (row < 0 || row > range);
        return row;
    }
    @Override
    public int selectColValue(int range) {
        range-=1;
        System.out.println("Please enter a valid column number from 0 to " + range);
        int col = -1;
        do {
            String colString = input.nextLine();
            if (colString.length() == 1 && colString.matches("[0-9]")) {
                col = Integer.parseInt(colString);
            }
            if (col < 0 || col > range) {
                System.out.println("Invalid move! Enter a number from 0 to " + range);
            }
        } while (col < 0 || col > range);
        return col;
    }
}
