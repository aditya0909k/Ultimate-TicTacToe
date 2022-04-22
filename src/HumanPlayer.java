import java.util.Scanner;

public class HumanPlayer extends APlayer{
    Scanner input = new Scanner(System.in);

    public HumanPlayer(String name, String mark) {
        super(name, mark);
    }

    @Override
    public int selectRowValue(int range) {
        System.out.println("Please enter a valid row number from 0 to " + range);
        int row;
        do {
            row = input.nextInt();
        } while (row < 0 && row > range);
        return row;
    }
    @Override
    public int selectColValue(int range) {
        System.out.println("Please enter a valid column number from 0 to " + range);
        int col;
        do {
            col = input.nextInt();
        } while (col < 0 && col > range);
        return col;
    }
}
