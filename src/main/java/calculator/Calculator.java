package calculator;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public abstract class Calculator {
    private final Queue<Double> results;

    private final AddOperator addOperator;
    private final SubtractOperator subtractOperator;
    private final MultiplyOperator multiplyOperator;
    private final DivideOperator divideOperator;

    public Calculator() {
        this.addOperator = new AddOperator();
        this.subtractOperator = new SubtractOperator();
        this.multiplyOperator = new MultiplyOperator();
        this.divideOperator = new DivideOperator();
        this.results = new LinkedList<>();
    }

    public void addCalculation(Double result) {
        results.offer(result);
    }

    abstract double calculate();
    abstract void opInquiryResults();
}