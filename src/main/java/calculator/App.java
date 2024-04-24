package calculator;

import java.util.*;

/**
 * TODO
 * 7. Calculator 클래스에 반지름을 매개변수로 전달받아 원의 넓이를 계산하여 반환해주는 메서드를 구현합니다.
 *     - APP 클래스의 main 메서드에 Scanner를 활용하여 사칙연산을 진행할지 원의 넓이를 구할지 명령어를 입력 받은 후 원의 넓이를 구하는 것을 선택했을 때 원의 반지름을 입력 받아 원의 넓이를 구한 후 출력되도록 구현합니다.
 *         - 기존에 구현되어있던 사칙연산 기능은 수정 후에도 반드시 이전과 동일하게 동작해야합니다.
 *     - 이때, static, final 키워드를 활용할 수 있는지 고민한 후 활용 해봅니다.
 *         - 반드시 static, final 키워드에 대한 설명과 활용한 이유에 대해 주석으로 작성합니다.
 *     - 원의 넓이 결과를 저장하는 컬렉션 타입의 필드 선언 및 생성
 *         - 계산된 원의 넓이를 저장합니다.
 *         - 생성자로 초기화됩니다.
 *         - 외부에서 직접 접근할 수 없습니다.
 *         - Getter, Setter 메서드를 구현합니다.
 *         - 원의 넓이 결과값들을 조회하는 메서드를 구현합니다.
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
        Calculator calculator = new Calculator();

        String flag = "";
        while (!flag.equals("exit")) {
            //작업 수행 입력
            System.out.println("어떤 작업을 수행하시겠습니까? [사칙연산], [원의 넓이]");
            switch (sc.nextLine().trim()) {
                case "사칙연산":
                    toOperation(calculator);
                    break;
                case "원의 넓이":
                    toCircle(calculator);
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 1또는 2를 입력해주세요\n");
                    break;
            }
            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            flag = sc.nextLine().trim();
        }
    }

    private static void toCircle(Calculator calculator) {
        System.out.println("원의 넓이 계산기\n");

        double radius = 0;
        try {
            //사용자로부터 반지름 입력
            System.out.print("반지름을 입력하세요: ");
            radius = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
        }

        //원의 넓이 구한 후 출력 및 저장
        try {
            double area = calculator.calculateCircleArea(radius);
            calculator.getAreaArr().add(area);
            System.out.println("[원의 넓이]결과 = " + calculator.getAreaArr().peek() + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        //저장된 원의 넓이 값들 바로 전체 조회
        try {
            calculator.areaInquiryResults();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void toOperation(Calculator calculator) {
        System.out.println("사칙연산 계산기\n");

        //firstStr과 secondStr이 정수인지 판별
        int firstNum = 0, secondNum = 0;
        try {
            //사용자로부터 첫 번째 숫자 입력
            System.out.print("첫 번째 숫자를 입력하세요: ");
            firstNum = Integer.parseInt(sc.nextLine().trim());
            //사용자로부터 두 번째 숫자 입력
            System.out.print("두 번째 숫자를 입력하세요: ");
            secondNum = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력하세요.");
        }

        //사용자로부터 사칙연산 기호 입력
        System.out.print("사칙연산 기호를 입력하세요: ");
        char operator = 0;
        try {
            operator = sc.nextLine().trim().charAt(0);
            if (!operatorSet.contains(operator)) {
                throw new InputMismatchException("[ +, -, /, * ] 이외에 입력되었습니다.");
            }
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }

        //연산 결과값 출력 및 저장
        try {
            int result = calculator.calculate(firstNum, secondNum, operator);
            calculator.getOpArr().add(result);
            System.out.println("[사칙연산] 결과 = " + calculator.getOpArr().peek() + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        //삭제 여부 입력
        System.out.println("[사칙연산]가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제)");
        try {
            if (sc.nextLine().trim().equals("remove")) {
                calculator.opRemoveResult();
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }

        //전체 조회 여부 입력
        System.out.println("[사칙연산]저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회)");
        try {
            if (sc.nextLine().trim().equals("inquiry")) {
                calculator.opInquiryResults();
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
    }
}