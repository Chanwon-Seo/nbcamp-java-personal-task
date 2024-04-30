package calculator.arithmeticoperator;

public class DivideOperator implements Operator {

    @Override
    public double operate(double firstNumber, double secondNumber) {
        if (secondNumber == 0) {
            throw new IllegalArgumentException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
        }
        return firstNumber / secondNumber;
    }
}
