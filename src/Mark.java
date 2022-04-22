enum Mark {
    X("X"),
    O("O"),
    DASH("-");

    private String mark;
    Mark(String mark) {
        this.mark = mark;
    }
    public String getMark() {
        return mark;
    }
}