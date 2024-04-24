package calculator;

import java.util.*;

/**
 * TODO
 * 7. 연산 결과가 10개로 고정되지 않고 무한이 저장될 수 있도록 소스 코드를 수정합니다.
 *      - JCF(Java Collection Framework)를 사용합니다. (e.g. List, Set …)
 *      - “remove”라는 문자열을 입력받으면 가장 먼저 저장된 결과가 삭제될 수 있도록 구현합니다.
 */
public class App {

    public static void main(String[] args) {
        // Scanner 객체 생성
        Scanner sc = new Scanner(System.in);
        //연산 결과를 저장 v1
        Queue<Integer> opArr = new LinkedList<>();

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

            opArr.add(result);
            System.out.println("결과 = " + opArr.peek() + "\n");

            System.out.println("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제)");

            if (sc.nextLine().equals("remove")) {
                if (!opArr.isEmpty()) {
                    System.out.println("remove = " + opArr.poll());
                }
            }

            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            flag = sc.nextLine();
        }
    }

}