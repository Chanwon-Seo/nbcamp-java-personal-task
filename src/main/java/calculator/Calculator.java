package calculator;

import java.util.LinkedList;
import java.util.Queue;

public class Calculator {
    public Queue<Integer> opArr = new LinkedList<>();

    public Calculator() {
    }

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
}