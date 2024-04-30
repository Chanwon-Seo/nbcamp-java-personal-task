package calculator;


import java.util.NoSuchElementException;
import java.util.Queue;

public class CircleCalculator extends Calculator {
    private final static double PI = 3.14159265358979323846;
    private double radius;

    public CircleCalculator() {
    }

    //필드값 주입
    public void toCircleCalculator(double radius) {
        this.radius = radius;
    }

    //연산 수행
    @Override
    double calculate() {

        if (radius < 0) {
            throw new IllegalArgumentException("반지름은 양수여야 합니다.");
        }

//      return radius * radius * Math.PI;
        return radius * radius * PI;
    }

    //저장된 결과값 전체 조회
    Queue<Double> opInquiryResults() {
        try {
            if (getResults().isEmpty()) {
                throw new NoSuchElementException("조회할 연산 결과가 없습니다.");
            }
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        }
            return getResults();
    }

}
