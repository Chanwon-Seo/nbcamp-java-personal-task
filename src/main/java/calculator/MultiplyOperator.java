package calculator;

public class MultiplyOperator implements Operator {

    @Override
    public double operate(double firstNumber, double secondNumber) {
        return firstNumber * secondNumber;
    }
}
