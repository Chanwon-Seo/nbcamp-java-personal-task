package calculator;

import calculator.enums.OperatorType;
import lombok.NoArgsConstructor;

import java.util.NoSuchElementException;
import java.util.Objects;

@NoArgsConstructor
public class ArithmeticCalculator<T> extends Calculator {

    private T firstNumber;
    private T secondNumber;
    private char operator;

    //연산 수행
    @Override
    double calculate() {
        double result = 0;
        if (Objects.equals(OperatorType.OPERATOR_ADD.getOperatorName(), operator)) {
            result = getAddOperator().operate((double) firstNumber, (double) secondNumber);
        } else if (Objects.equals(OperatorType.OPERATOR_SUBTRACT.getOperatorName(), operator)) {
            result = getSubtractOperator().operate((double) firstNumber, (double) secondNumber);
        } else if (Objects.equals(OperatorType.OPERATOR_MULTIPLY.getOperatorName(), operator)) {
            result = getMultiplyOperator().operate((double) firstNumber, (double) secondNumber);
        } else if (Objects.equals(OperatorType.OPERATOR_DIVIDE.getOperatorName(), operator)) {
            result = getDivideOperator().operate((double) firstNumber, (double) secondNumber);
        } else if (Objects.equals(OperatorType.OPERATOR_MOD.getOperatorName(), operator)) {
            result = getModOperator().operate((double) firstNumber, (double) secondNumber);
        } else {
            throw new IllegalArgumentException("[ +, -, /, *, % ] 이외에 입력되었습니다.");
        }
        return result;
    }

    //필드값 주입
    public void toArithmeticCalculator(T firstNumberInput, T secondNumberInput, char operatorInput) {
        this.firstNumber = firstNumberInput;
        this.secondNumber = secondNumberInput;
        this.operator = operatorInput;
    }

    //가장 먼저 저장된 결과값 삭제
    public void opRemoveResult() {
        if (getResults().isEmpty()) {
            throw new NoSuchElementException("삭제할 연산 결과가 없습니다.");
        }
        System.out.println("remove = " + getResults().poll());
    }

    //저장된 결과값 전체 조회
    public void opInquiryResults() {
        if (getResults().isEmpty()) {
            throw new NoSuchElementException("조회할 연산 결과가 없습니다.");
        }

        int index = 1;
        for (Double result : getResults()) {
            System.out.println(index + "번째 결과는 = " + result);
            index++;
        }
    }


}
