package calculator;

import java.util.*;

/**
 * TODO
 * 8. “**inquiry”라는 문자열이 입력되면 저장된 연산 결과 전부를 출력합니다.**
 *      - foreach(향상된 for문)을 활용하여 구현 해봅니다.
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

            System.out.println("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회)");
            if (sc.nextLine().equals("inquiry")) {
                int index = 1;
                for (Integer i : opArr) {
                    System.out.println(index + "번째 결과는 = " + i);
                    index++;
                }
            }

            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            flag = sc.nextLine();
        }
    }
}