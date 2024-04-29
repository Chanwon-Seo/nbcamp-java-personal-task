package calculator;

import calculator.arithmeticoperator.*;

import java.util.NoSuchElementException;
import java.util.Objects;

import static calculator.enums.OperatorType.*;

public class ArithmeticCalculator<T extends Number> extends Calculator {

    private T firstNumber;
    private T secondNumber;
    private char operator;

    //연산 수행
    @Override
    double calculate() {
        double result = 0;
        if (Objects.equals(OPERATOR_ADD.getOperatorName(), operator)) {
            result = new AddOperator().operate((double) firstNumber, (double) secondNumber);
        } else if (Objects.equals(OPERATOR_SUBTRACT.getOperatorName(), operator)) {
            result = new SubtractOperator().operate((double) firstNumber, (double) secondNumber);
        } else if (Objects.equals(OPERATOR_MULTIPLY.getOperatorName(), operator)) {
            result = new MultiplyOperator().operate((double) firstNumber, (double) secondNumber);
        } else if (Objects.equals(OPERATOR_DIVIDE.getOperatorName(), operator)) {
            result = new DivideOperator().operate((double) firstNumber, (double) secondNumber);
        } else if (Objects.equals(OPERATOR_MOD.getOperatorName(), operator)) {
            result = new ModOperator().operate((double) firstNumber, (double) secondNumber);
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
        try {
            if (getResults().isEmpty()) {
                throw new NoSuchElementException("삭제할 연산 결과가 없습니다.");
            }
            System.out.println("remove = " + getResults().poll());
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

    }

    //저장된 결과값 전체 조회
    public void opInquiryResults(double num) {
        try {
            if (getResults().isEmpty()) {
                throw new NoSuchElementException("조회할 연산 결과가 없습니다.");
            }
            getResults().stream()
                    .filter(result -> result > num)
                    .forEach(System.out::println);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

    }

}
