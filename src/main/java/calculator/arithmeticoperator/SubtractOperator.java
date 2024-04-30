package calculator.arithmeticoperator;

public class SubtractOperator implements Operator {
    @Override
    public double operate(double firstNumber, double secondNumber) {
        return firstNumber - secondNumber;
    }
}
