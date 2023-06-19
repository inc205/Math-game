/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operations;

import java.util.List;


public class AdditionOperation extends Operation {

    public AdditionOperation(List<Double> numbers) {
        super(numbers);
    }

    @Override
    public boolean checkAnswer(double value) {
        double result = 0;

        for (double number : this.getNumbers()) {
            result += number;
        }

        
        return value == result;
    }

}


