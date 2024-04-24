package calculator;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.Queue;

@NoArgsConstructor
@Getter
public class Calculator {
    private final Queue<Integer> opArr = new LinkedList<>();

    //연산 수행
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