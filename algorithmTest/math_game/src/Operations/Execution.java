/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author LSyaRe <lsyare.luminesway.com>
 */
public class Execution {

    private int limit;

    public Execution(int limit) {
        this.limit = limit;
    }

    public List<Operation> generateOperations(OperationType type) {

        List<Operation> operations = new ArrayList();

        for (int i = 0; i < this.limit; i++) {
            List<Double> numbers = new ArrayList();

            while (numbers.size() < 2) {
                int randomNumber = this.getRandomNumber(1, 12);
                numbers.add(Double.valueOf(String.valueOf(randomNumber)));
            }

            switch (type) {
                case SUM ->
                    operations.add(new AdditionOperation(numbers));
                case SUBSTRACT -> {
                    numbers.sort(Collections.reverseOrder());
                    operations.add(new SubstractionOperation(numbers));
                }
                case MULTIPLY ->
                    operations.add(new MultiplyOperation(numbers));
                default ->
                    throw new AssertionError(type.name());
            }

        }

        return operations;
    }

    private int getRandomNumber(int min, int max) {
        if (min > max) {
            throw new AssertionError("The value max must not less to min");
        }

        return (int) ((Math.random() * (max - min)) + min);
    }
}
