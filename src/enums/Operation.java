package enums;

public enum Operation {
    PLUS("+", 1),
    MULTIPLICATION("*", 1),
    DIVISION("/", 2),
    MINUS("-", 2);

    private String str;
    private int priority;

    Operation(String str, int priority) {
        this.str = str;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public String getStr() {
        return str;
    }

}
