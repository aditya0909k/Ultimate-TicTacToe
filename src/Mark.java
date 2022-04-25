//Aditya Kulkarni CS2336.003

/* Problem analysis
Create an enumerated type of Mark where it can be either X O or - . 
*/

/* Problem solution
Constructor will set mark, and there will be a get method for mark.
*/

enum Mark {
    X("X"),
    O("O"),
    DASH("-");
    private String mark;

    Mark(String mark) { //set mark
        this.mark = mark;
    }
    public String getMark() { //return mark
        return mark;
    }
}