package calculator;

import java.util.Scanner;

/**
 * TODO
 * 4. 반복문을 사용하여 반복의 종료를 알려주는 “exit” 문자열을 입력하기 전까지 무한으로 계산을 진행할 수 있도록 소스 코드를 수정합니다.
 *      - 반복문을 사용합니다. (e.g. for, while …)
 */

public class App {

    public static void main(String[] args) {
        // Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        String flag = "";
        while (!flag.equals("exit")) {
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
                continue;
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
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            flag = sc.nextLine();
        }
    }

}