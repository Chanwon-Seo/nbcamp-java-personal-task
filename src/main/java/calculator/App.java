package calculator;

import java.util.Scanner;

/**
 * TODO
 * 2. Scanner를 사용하여 사칙연산 기호를 전달 받을 수 있습니다.
 *      - 사칙연산 기호를 적합한 타입으로 선언한 변수에 저장합니다. (`charAt(0)`)
 */
public class App {

    public static void main(String[] args) {
        // Scanner 객체 생성
        Scanner sc = new Scanner(System.in);

        //사용자로부터 첫 번째 숫자 입력
        System.out.print("첫 번째 숫자를 입력하세요: ");
        int firstNum = Integer.parseInt(sc.nextLine());
        //사용자로부터 두 번째 숫자 입력
        System.out.print("두 번째 숫자를 입력하세요: ");
        int secondNum = Integer.parseInt(sc.nextLine());

        //사용자로부터 사칙연산 기호 입력
        System.out.print("사칙연산 기호를 입력하세요: ");
        char operator = sc.nextLine().charAt(0);
    }
}