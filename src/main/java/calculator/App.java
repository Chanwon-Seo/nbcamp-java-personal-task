package calculator;


import java.util.*;

public class App {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        //Calculator 생성
        ArithmeticCalculator<Double> arithmeticCalculator = new ArithmeticCalculator<>();
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
        }

        arithmeticCalculator.toArithmeticCalculator(firstNumberInput, secondNumberInput, operatorInput);

        //연산 결과값 출력 및 저장
        double calculate;
        try {
            calculate = arithmeticCalculator.calculate();
            arithmeticCalculator.addCalculation(calculate);
            System.out.println("[사칙연산] 결과 = " + calculate + "\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return false;
        }

        //삭제 여부 입력
        System.out.println("[사칙연산]가장 먼저 저장된 연산 결과를 삭제하시겠습니까? (remove 입력 시 삭제)");
        String removeInput = sc.nextLine().trim();
        if (removeInput.equals("remove")) {
            arithmeticCalculator.opRemoveResult();
        }

        //전체 조회 여부 입력
        System.out.println("[사칙연산]저장된 연산결과를 조회하시겠습니까? (inquiry 입력 시 조회)");
        String inquiryInput = sc.nextLine().trim();
        if (inquiryInput.equals("inquiry")) {
            arithmeticCalculator.opInquiryResults(calculate);
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
        }
        return true;
    }


}