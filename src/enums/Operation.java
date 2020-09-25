package enums;

public enum Operation {
    PLUS("+"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    MINUS("-");

    private String str;

    Operation(String str) {
        this.str = str;
    }


    public String getStr() {
        return str;
    }

}
