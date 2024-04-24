package calculator;

import lombok.Getter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public abstract class Calculator {
    private final Queue<Double> results;

    public Calculator() {
        results = new LinkedList<>();
    }

    public void addCalculation(Double result) {
        results.offer(result);
    }

    abstract double calculate();
    abstract void opInquiryResults();
}