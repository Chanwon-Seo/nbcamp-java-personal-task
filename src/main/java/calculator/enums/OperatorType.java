package calculator.enums;

import calculator.arithmeticoperator.Operator;
import lombok.Getter;

@Getter
public enum OperatorType {
    PLUS((a, b) -> a + b),
    MINUS((a, b) -> a - b),
    MULTIPLY((a, b) -> a * b),
    DIVIDE((a, b) -> {
        if (b == 0) {
            throw new IllegalArgumentException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
        }
        return a / b;
    }),
    MOD((a, b) -> a % b),
    ;

    private final Operator operator;

    OperatorType(Operator operator) {
        this.operator = operator;
    }

    public double calculate(double a, double b) {
        return operator.operate(a, b);
    }

}
