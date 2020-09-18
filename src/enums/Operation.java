package enums;

public enum Operation {
    PLUS("+"),

    MINUS("-");

    private String str;

    private Operation(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

}
