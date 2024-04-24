package calculator;

import java.util.*;

/**
 * TODO
 * 5. Calculator 클래스에 저장된 연산 결과들을 조회하는 기능을 가진 메서드를 구현한 후
 *    App 클래스의 main 메서드에 조회 메서드가 활용될 수 있도록 수정합니다.
 */
public class App {
    //사칙연산에 필요한 기호 Set
    private static final Set<Character> operatorSet = new HashSet<>();

    static {
        operatorSet.add('+');
        operatorSet.add('-');
        operatorSet.add('*');
        operatorSet.add('/');
    }

    public static void main(String[] args) {
        // Scanner 객체 생성
        Scanner sc = new Scanner(System.in);
        //Calculator 생성
        Calculator calculator = new Calculator();

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
            char operator;
            try {
                operator = sc.nextLine().charAt(0);
                if (!operatorSet.contains(operator)) {
                    throw new InputMismatchException("[ +, -, /, * ] 이외에 입력되었습니다.");
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                continue;
            }

            //사칙연산 후 calculator.opArr에 "직접 호출" 후 저장
            try {
                int result = calculator.calculate(firstNum, secondNum, operator);
                calculator.getOpArr().add(result);
                System.out.println("결과 = " + calculator.getOpArr().peek() + "\n");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            //삭제 여부 입력
            System.out.println("가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제)");
            try {
                if (sc.nextLine().equals("remove")) {
                    calculator.removeResult();
                }
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }

            //전체 조회 여부 입력
            System.out.println("저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회)");
            try {
                if (sc.nextLine().equals("inquiry")) {
                    calculator.inquiryResults();
                }
            } catch (NoSuchElementException e) {
                System.out.println(e.getMessage());
            }


            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            flag = sc.nextLine();
        }
    }
}