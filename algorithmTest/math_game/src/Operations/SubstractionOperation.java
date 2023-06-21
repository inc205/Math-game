/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Operations;

import java.util.List;

public class SubstractionOperation extends Operation {

    public SubstractionOperation(List<Double> numbers) {
        super(numbers);
    }

    @Override
    public boolean checkAnswer(double value) {
        double result = this.getNumbers().get(0);

        for (int i = 1; i < this.getNumbers().size(); i++) {
            result -= this.getNumbers().get(i);
        }

        System.out.println("Respuesta" + result + "Persona" + value);
        return value == result;
    }

}
