//Aditya Kulkarni CS2336.003

/* Problem analysis

*/

/* Problem solution

*/

public abstract class APlayer {
    private String name;
    private String mark;
    
    public APlayer(String name, String mark) {
        setName(name);
        setMark(mark);
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMark() {
        return this.mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public abstract int selectBoard(int range);
    public abstract int selectBoardValue(int range);
}
