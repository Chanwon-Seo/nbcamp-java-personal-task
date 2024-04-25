package calculator;

import lombok.NoArgsConstructor;

import java.util.NoSuchElementException;

@NoArgsConstructor
public class ArithmeticCalculator extends Calculator {

    private double firstNumber;
    private double secondNumber;
    private char operator;

    //연산 수행
    @Override
    double calculate() {
        double result = 0;
        if (operator == '+') {
            result = getAddOperator().operate(firstNumber,secondNumber);
        } else if (operator == '-') {
            result = getSubtractOperator().operate(firstNumber, secondNumber);
        } else if (operator == '*') {
            result = getMultiplyOperator().operate(firstNumber, secondNumber);
        } else if (operator == '/') {
            result = getDivideOperator().operate(firstNumber, secondNumber);
        } else if (operator == '%') {
            result = getModOperator().operate(firstNumber, secondNumber);
        }
        return result;
    }

    //필드값 주입
    public void toArithmeticCalculator(double firstNumberInput, double secondNumberInput, char operatorInput) {
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
