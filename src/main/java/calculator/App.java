package calculator;


import java.util.*;

/**
 * TODO
 * 10. ArithmeticCalculator 클래스에 추가로 나머지 연산(%) 기능을 추가하기 위해 ModOperator 클래스를 만들어 추가합니다.
 *     - 추가하려고 하니 앞으로 계속 기능이 추가되면 여러 부분의 소스코드를 수정해야 한다는 생각이 들었고 “현재 비효율적인 구조가 아닌가?” 라는 의구심이 들었습니다.
 *         - 따라서 소스 코드의 변경은 최소화하면서 기능을 쉽게 추가(확장)할 수 있는 방법을 고민 해봅니다. (OCP)
 *     - 방법을 고민 및 학습하여 적용했을 때 전체적인 소스 코드와 구조의 변경이 발생 했을 겁니다.
 *         - 최대한 생각한 방법으로 구현 해보세요. 틀린 답은 없습니다. 컴파일에 문제가 없고 기능이 정상적으로 동작 하면 모두 정답입니다.
 *         - 포기하는 것 보다 본인이 생각한데로 구현해보고 다른 개발자들과 공유 하면서 여러 가지 방법들을 확인 했을 때 실력이 가장 많이 향상됩니다.
 *     - 마찬가지로 수정 후에도 이전과 똑같이 동작해야합니다.
 */
public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //Calculator 생성
        ArithmeticCalculator arithmeticCalculator = new ArithmeticCalculator();
        CircleCalculator circleCalculator = new CircleCalculator();

        String flag = "";
        while (!flag.equals("exit")) {
            //작업 수행 입력
            System.out.println("어떤 작업을 수행하시겠습니까? [사칙연산], [원의 넓이]");
            boolean isValid = switch (sc.nextLine().trim()) {
                case "사칙연산" -> toArithmetic(arithmeticCalculator);
                case "원의 넓이" -> toCircle(circleCalculator);
                default -> {
                    System.out.println("잘못된 입력입니다. \"사칙연산\" 또는 \"원의 넓이\" 를 입력해주세요\n");
                    yield false;
                }
            };

            if (isValid) {
                System.out.println("계산하시겠습니까? (exit 입력 시 종료)");
                flag = sc.nextLine().trim();
            }
        }
    }

    //[사칙연산]
    private static boolean toArithmetic(ArithmeticCalculator arithmeticCalculator) {
        System.out.println("사칙연산 계산기\n");
        //사칙연산에 필요한 입력
        double firstNumberInput, secondNumberInput;
        char operatorInput;
        try {
            //첫 번째 숫자
            System.out.print("첫 번째 숫자를 입력하세요: ");
            firstNumberInput = Double.parseDouble(sc.nextLine().trim());
            //두 번째 숫자
            System.out.print("두 번째 숫자를 입력하세요: ");
            secondNumberInput = Double.parseDouble(sc.nextLine().trim());

            //사용자로부터 사칙연산 기호 입력
            System.out.print("사칙연산 기호를 입력하세요: ");
            operatorInput = sc.nextLine().trim().charAt(0);
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return false;
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            return false;
        }

        arithmeticCalculator.toArithmeticCalculator(firstNumberInput, secondNumberInput, operatorInput);

        //연산 결과값 출력 및 저장
        try {
            double calculate = arithmeticCalculator.calculate();
            arithmeticCalculator.addCalculation(calculate);
            System.out.println("[사칙연산] 결과 = " + calculate + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        //삭제 여부 입력
        System.out.println("[사칙연산]가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제)");
        try {
            String removeInput = sc.nextLine().trim();
            if (removeInput.equals("remove")) {
                arithmeticCalculator.opRemoveResult();
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        //전체 조회 여부 입력
        System.out.println("[사칙연산]저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회)");
        try {
            String removeInput = sc.nextLine().trim();
            if (removeInput.equals("inquiry")) {
                arithmeticCalculator.opInquiryResults();
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    //[원의 넓이]
    private static boolean toCircle(CircleCalculator circleCalculator) {
        System.out.println("원의 넓이 계산기\n");

        double radius;
        try {
            //사용자로부터 반지름 입력
            System.out.print("반지름을 입력하세요: ");
            radius = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
            return false;
        }

        circleCalculator.toCircleCalculator(radius);

        //원의 넓이 구한 후 출력 및 저장 후 전체 조회
        try {
            double area = circleCalculator.calculate();
            circleCalculator.addCalculation(area);
            System.out.println("[원의 넓이]결과 = " + area + "\n");
            circleCalculator.opInquiryResults();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }


}