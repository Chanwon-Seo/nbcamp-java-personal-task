package calculator;

import calculator.enums.OperatorType;

import java.util.NoSuchElementException;
import java.util.Queue;


public class ArithmeticCalculator<T extends Number> extends Calculator {

    private T firstNumber;
    private T secondNumber;
    private String operator;

    //연산 수행
    @Override
    double calculate() {
        return switch (operator) {
            case "+" -> OperatorType.PLUS.calculate((Double) firstNumber, (Double) secondNumber);
            case "-" -> OperatorType.MINUS.calculate((Double) firstNumber, (Double) secondNumber);
            case "*" -> OperatorType.MULTIPLY.calculate((Double) firstNumber, (Double) secondNumber);
            case "/" -> OperatorType.DIVIDE.calculate((Double) firstNumber, (Double) secondNumber);
            case "%" -> OperatorType.MOD.calculate((Double) firstNumber, (Double) secondNumber);
            default -> throw new IllegalArgumentException("[ +, -, /, *, % ] 이외에 입력되었습니다.");
        };
    }

    //필드값 주입
    public void toArithmeticCalculator(T firstNumberInput, T secondNumberInput, String operatorInput) {
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
    Queue<Double> opInquiryResults() {
        try {
            if (getResults().isEmpty()) {
                throw new NoSuchElementException("조회할 연산 결과가 없습니다.");
            }
//            getResults().stream()
//                    .filter(result -> result > )
//                    .forEach(System.out::println);
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return getResults();

    }

}
