package calculator;

import java.util.Scanner;

/** TODO
 * 1. Scanner를 사용하여 양의 정수 2개(0 포함)를 전달 받을 수 있다.
 *      - 양의 정수는 각각 하나씩 전달 받는다.
 *      - 양의 정수는 적합한 타입으로 선언한 변수에 저장한다.
 */
public class App {

    public static void main(String[] args) {
        // Scanner 객체 생성
        Scanner sc = new Scanner(System.in);
        // 사용자로부터 첫 번째 숫자 입력
        System.out.print("첫 번째 숫자를 입력하세요: ");
        int firstNum = Integer.parseInt(sc.nextLine());
        // 사용자로부터 두 번째 숫자 입력
        System.out.print("두 번째 숫자를 입력하세요: ");
        int secondNum = Integer.parseInt(sc.nextLine());
    }
}