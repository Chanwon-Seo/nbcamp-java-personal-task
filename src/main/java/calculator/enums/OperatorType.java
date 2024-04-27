package calculator.enums;

import lombok.Getter;

@Getter
public enum OperatorType {
    OPERATOR_ADD('+'),
    OPERATOR_SUBTRACT('-'),
    OPERATOR_MULTIPLY('*'),
    OPERATOR_DIVIDE('/'),
    OPERATOR_MOD('%');


    private final char operatorName;

    OperatorType(char c) {
        this.operatorName = c;
    }

}
