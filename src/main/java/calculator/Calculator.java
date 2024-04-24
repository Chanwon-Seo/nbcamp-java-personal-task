package calculator;

import lombok.Getter;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

@Getter
public class Calculator {
    private final Queue<Integer> opArr;
    private final Queue<Double> areaArr;
    private final static double PI = 3.141592;

    public Calculator() {
        this.opArr = new LinkedList<>();
        this.areaArr = new LinkedList<>();
    }

    //사칙연산 수행
    public int calculate(int firstNum, int secondNum, char operator) {
        int result = 0;
        if (operator == '+') {
            result = firstNum + secondNum;
        } else if (operator == '-') {
            result = firstNum - secondNum;
        } else if (operator == '*') {
            result = firstNum * secondNum;
        } else if (operator == '/') {
            if (secondNum == 0) {
                throw new IllegalArgumentException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
            }
            result = firstNum / secondNum;
        }
        return result;
    }

    public double calculateCircleArea(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("반지름은 양수여야 합니다.");
        }
//        return radius * radius * Math.PI;
        return radius * radius * PI;
    }

    //[사칙연산]가장 먼저 저장된 결과값 삭제
    public void opRemoveResult() {
        if (opArr.isEmpty()) {
            throw new NoSuchElementException("삭제할 연산 결과가 없습니다.");
        }
        System.out.println("remove = " + opArr.poll());
    }

    //[사칙연산]저장된 결과값 전체 조회
    public void opInquiryResults() {
        if (opArr.isEmpty()) {
            throw new NoSuchElementException("조회할 연산 결과가 없습니다.");
        }
        int index = 1;
        for (Integer i : opArr) {
            System.out.println(index + "번째 결과는 = " + i);
            index++;
        }
    }

    public void areaInquiryResults() {
        if (areaArr.isEmpty()) {
            throw new NoSuchElementException("조회할 연산 결과가 없습니다.");
        }
        int index = 1;
        for (Double v : areaArr) {
            System.out.println(index + "번째 결과는 = " + v);
            index++;
        }
    }
}