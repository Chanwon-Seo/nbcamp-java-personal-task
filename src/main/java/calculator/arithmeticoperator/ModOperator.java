package calculator.arithmeticoperator;

public class ModOperator implements Operator {
    @Override
    public double operate(double firstNum, double secondNum) {
        if (secondNum == 0) {
            throw new IllegalArgumentException("나머지 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
        }
        return firstNum % secondNum;
    }
}
