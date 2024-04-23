package calculator;

import java.util.Scanner;

/**
 * TODO
 * 3. 입력받은 양의 정수 2개와 사칙연산 기호를 사용하여 연산을 진행한 후 결과값을 출력합니다.
 *      - 사칙연산 기호에 맞는 연산자를 사용하여 연산을 진행합니다.
 *      - 입력받은 연산 기호를 구분하기 위해 제어문을 사용합니다. (e.g.if, switch)
 *      - 연산 오류가 발생할 경우 해당 오류에 대한 내용을 정제하여 출력합니다.
 *      - e.g. “ 나눗셈 연산에서 분모(두번째 정수)에 0이 입력될 수 없습니다. “
 */
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        //사용자로부터 첫 번째 숫자 입력
        System.out.print("첫 번째 숫자를 입력하세요: ");
        String firstStr = sc.nextLine();
        //사용자로부터 두 번째 숫자 입력
        System.out.print("두 번째 숫자를 입력하세요: ");
        String secondStr = sc.nextLine();

        //firstStr과 secondStr이 정수인지 판별
        int firstNum, secondNum;
        try {
            firstNum = Integer.parseInt(firstStr);
            secondNum = Integer.parseInt(secondStr);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return;
        }

        //사용자로부터 사칙연산 기호 입력
        System.out.print("사칙연산 기호를 입력하세요: ");
        char operator = sc.nextLine().charAt(0);

        //연산 기호에 따라 계산을 수행 및 결과 출력
        try {
            switch (operator) {
                case '+':
                    System.out.println(firstNum + secondNum);
                    break;
                case '-':
                    System.out.println(firstNum - secondNum);
                    break;
                case '*':
                    System.out.println(firstNum * secondNum);
                    break;
                case '/':
                    if (secondNum == 0) {
                        throw new IllegalArgumentException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                    }
                    System.out.println(firstNum / secondNum);
                    break;
                default:
                    throw new IllegalArgumentException("[ +, -, /, * ] 이외에 입력되었습니다.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}