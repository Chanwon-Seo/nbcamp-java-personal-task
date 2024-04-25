package calculator.enums;

public enum OperatorType {
    OPERATOR_ADD('+'),
    OPERATOR_SUBTRACT('-'),
    OPERATOR_MULTIPLY('*'),
    OPERATOR_DIVIDE('/'),
    OPERATOR_MOD('%');


    private char operatorName;

    OperatorType(char c) {
        this.operatorName = c;
    }

    public char getOperatorName() {
        return operatorName;
    }
}
