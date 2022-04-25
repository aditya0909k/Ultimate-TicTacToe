//Aditya Kulkarni CS2336.003

/* Problem analysis
My game will take in any type of player, whether that be human, computer, etc... This abstract class will be the parent of any
class that is a player of the game.
*/

/* Problem solution
Make an abstract class with values of a name and mark. Simple constructor, get and set methods, and also abstract
methods for selecting the board value for UltimateTTT and square value for UltimateTTT
*/

public abstract class APlayer { //Abstract class for the UltimateTTT game to use, so no matter which type of player, game still runs.
    private String name;
    private String mark;
    
    public APlayer(String name, String mark) { //constructor that takes in name and mark and sets
        setName(name);
        setMark(mark);
    }
    public String getName() { //return name
        return this.name;
    }
    public void setName(String name) { //set name
        this.name = name;
    }
    public String getMark() { //get mark
        return this.mark;
    }
    public void setMark(String mark) { //set mark
        this.mark = mark;
    }
    public abstract int selectBoard(int range); //abstract class specific to each instance of a class that extends APlayer. this will select the board a player wants to play on
    public abstract int selectBoardValue(int range); //abstract class specific to each instance of a class that extends APlayer. this will select the square a player wants to play on
}
