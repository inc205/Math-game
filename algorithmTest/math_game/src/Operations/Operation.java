/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operations;

import java.util.List;

/**
 *
 * @author LSyaRe <lsyare.luminesway.com>
 */
public abstract class Operation {

    private List<Double> numbers;
    private String answer;

    public Operation(List<Double> numbers) {
        this.numbers = numbers;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    public boolean isAnswered() {
        return answer.isEmpty();
    }

    public List<Double> getNumbers() {
        return numbers;
    }

    public abstract boolean checkAnswer(double value);

}
