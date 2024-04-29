package calculator.arithmeticoperator;

import calculator.Operator;

public class AddOperator implements Operator {
    @Override
    public double operate(double firstNum, double secondNum) {
        return firstNum + secondNum;
    }
}
