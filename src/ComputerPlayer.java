//Aditya Kulkarni CS2336.003
public class ComputerPlayer extends APlayer{
    private String name;
    private String mark;

    public ComputerPlayer(String name, String mark) {
        super(name, mark);
    }
    private int randomNumber(int range) { 
        return (int) (Math.random() * range);
    }
    @Override
    public int selectRowValue(int range) {
        return randomNumber(range);
    }
    @Override
    public int selectColValue(int range) {
        return randomNumber(range);
    }
}
