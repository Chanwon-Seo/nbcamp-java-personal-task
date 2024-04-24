package calculator;


import java.util.*;

/**
 * TODO
 * 8. 사칙연산을 수행하는 계산기 ArithmeticCalculator 클래스와 원과 관련된 연산을 수행하는 계산기 CircleCalculator 클래스 2개를 구현합니다.
 *     - 기존에 만들어둔 Calculator 클래스를 수정합니다
 *     - 수정한 Calculator 클래스를 활용하여 ArithmeticCalculator, CircleCalculator 클래스를 구현 해봅니다. (상속)
 *     - 위 요구사항을 구현하게되면 App 클래스의 main 메서드에 오류가 발생할 겁니다.
 *         - 구현한 클래스들을 활용하여 오류가 발생하지 않고 활용될 수 있도록 수정 해보세요!
 *         - 기존에 사칙연산을 저장하던 컬렉션 필드의 타입을 Double로 변경해도 괜찮습니다.
 *         - 필드의 접근 제어자를 변경해도 괜찮습니다.
 */
public class App {
    //사칙연산에 필요한 기호 Set
    private static final Set<Character> operatorSet = new HashSet<>();
    static Scanner sc = new Scanner(System.in);

    static {
        operatorSet.add('+');
        operatorSet.add('-');
        operatorSet.add('*');
        operatorSet.add('/');
    }

    public static void main(String[] args) {
        //Calculator 생성
        Calculator arithmeticCalculator = new ArithmeticCalculator();
        Calculator circleCalculator = new CircleCalculator();

        String flag = "";
        while (!flag.equals("exit")) {
            //작업 수행 입력
            printMessage("어떤 작업을 수행하시겠습니까? [사칙연산], [원의 넓이]");
            boolean isValid = true;
            switch (inputNextLine()) {
                case "사칙연산":
                    isValid = toArithmetic(arithmeticCalculator);
                    break;
                case "원의 넓이":
                    isValid = toCircle(circleCalculator);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 1또는 2를 입력해주세요\n");
                    break;
            }

            if (isValid) {
                printMessage("계산하시겠습니까? (exit 입력 시 종료)");
                flag = inputNextLine();
            }
        }

    }

    //[사칙연산]
    private static boolean toArithmetic(Calculator calculator) {
        printMessage("사칙연산 계산기\n");
        ArithmeticCalculator arithmeticCalculator = (ArithmeticCalculator) calculator;
        //사칙연산에 필요한 입력
        double firstNumberInput, secondNumberInput;
        char operatorInput;
        try {
            //첫 번째 숫자
            firstNumberInput = getNumberInput("첫 번째 숫자를 입력하세요: ");
            //두 번째 숫자
            secondNumberInput = getNumberInput("두 번째 숫자를 입력하세요: ");

            //사용자로부터 사칙연산 기호 입력
            operatorInput = getOperatorInput("사칙연산 기호를 입력하세요: ");
        } catch (NumberFormatException e) {
            printMessage("잘못된 입력입니다. 숫자를 입력하세요.");
            return false;
        } catch (InputMismatchException e) {
            printMessage(e.getMessage());
            return false;
        }

        arithmeticCalculator.toArithmeticCalculator(firstNumberInput, secondNumberInput, operatorInput);

        //연산 결과값 출력 및 저장
        try {
            double calculate = arithmeticCalculator.calculate();
            arithmeticCalculator.addCalculation(calculate);
            printMessage("[사칙연산] 결과 = " + calculate + "\n");
        } catch (IllegalArgumentException e) {
            printMessage(e.getMessage());
        }

        //삭제 여부 입력
        printMessage("[사칙연산]가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제)");
        try {
            String removeInput = inputNextLine();
            if (removeInput.equals("remove")) {
                arithmeticCalculator.opRemoveResult();
            }
        } catch (NoSuchElementException e) {
            printMessage(e.getMessage());
        }

        //전체 조회 여부 입력
        printMessage("[사칙연산]저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회)");
        try {
            String removeInput = inputNextLine();
            if (removeInput.equals("inquiry")) {
                arithmeticCalculator.opInquiryResults();
            }
        } catch (NoSuchElementException e) {
            printMessage(e.getMessage());
        }
        return true;
    }

    //[원의 넓이]
    private static boolean toCircle(Calculator calculator) {
        printMessage("원의 넓이 계산기\n");
        CircleCalculator circleCalculator = (CircleCalculator) calculator;

        double radius;
        try {
            //사용자로부터 반지름 입력
            radius = getNumberInput("반지름을 입력하세요: ");
        } catch (NumberFormatException e) {
            printMessage("잘못된 입력입니다. 숫자를 입력하세요.");
            return false;
        }

        circleCalculator.toCircleCalculator(radius);

        //원의 넓이 구한 후 출력 및 저장 후 전체 조회
        try {
            double area = circleCalculator.calculate();
            circleCalculator.addCalculation(area);
            printMessage("[원의 넓이]결과 = " + area + "\n");
            circleCalculator.opInquiryResults();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NoSuchElementException e) {
            printMessage(e.getMessage());
        }
        return true;
    }

    private static String inputNextLine() {
        return sc.nextLine().trim();
    }

    private static void printMessage(String message) {
        System.out.println(message);
    }

    private static char getOperatorInput(String message) {
        System.out.print(message);
        char operatorInput = inputNextLine().charAt(0);
        if (!operatorSet.contains(operatorInput)) {
            throw new InputMismatchException("[ +, -, /, * ] 이외에 입력되었습니다.");
        }
        return operatorInput;
    }

    //사용자로부터 값을 입력 받음
    private static double getNumberInput(String message) {
        System.out.print(message);

        return Double.parseDouble(inputNextLine());
    }
}