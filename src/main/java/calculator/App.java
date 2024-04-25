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
                    printMessage("잘못된 입력입니다. 1또는 2를 입력해주세요\n");
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
//        calculator = new ArithmeticCalculator();
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
        printMessage(message);
        char operatorInput = inputNextLine().charAt(0);
        if (!operatorSet.contains(operatorInput)) {
            throw new InputMismatchException("[ +, -, /, * ] 이외에 입력되었습니다.");
        }
        return operatorInput;
    }

    //사용자로부터 값을 입력 받음
    private static double getNumberInput(String message) {
        printMessage(message);

        return Double.parseDouble(inputNextLine());
    }
}