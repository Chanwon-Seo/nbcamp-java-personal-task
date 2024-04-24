package calculator;

import java.util.Scanner;

/**
 * TODO
 * 5. 연산 결과 10개를 저장할 수 있는 배열을 선언 및 생성하고 연산의 결과를 저장합니다.
 *      - 연산의 결과를 저장할 수 있도록 적합한 타입의 배열을 생성합니다.
 *      - 연산의 결과를 비어있는 곳에 저장하기 위해 저장할 때마다 count 합니다.
 */

public class App {

    public static void main(String[] args) {
        // Scanner 객체 생성
        Scanner sc = new Scanner(System.in);
        //연산 결과를 저장
        int[] opArr = new int[10];
        //연산 횟수
        int count = 0;

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

            int result = 0;
            //연산 기호에 따라 계산을 수행
            try {
                result = switch (operator) {
                    case '+' -> firstNum + secondNum;
                    case '-' -> firstNum - secondNum;
                    case '*' -> firstNum * secondNum;
                    case '/' -> {
                        if (secondNum == 0) {
                            throw new IllegalArgumentException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                        }
                        yield firstNum / secondNum;
                    }
                    default -> throw new IllegalArgumentException("[ +, -, /, * ] 이외에 입력되었습니다.");
                };
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            opArr[count] = result;
            System.out.println("결과 = " + opArr[count]);
            count++;

            if (count > 9) {
                System.out.println("더 이상 저장 공간이 없습니다.");
                System.out.println("종료합니다.");
                break;
            }
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            flag = sc.nextLine();
        }
    }

}