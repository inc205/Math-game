/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Test;

import Operations.Execution;
import Operations.Operation;
import Operations.OperationType;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author LSyaRe <lsyare.luminesway.com>
 */
public class LogicTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Scanner sc = new Scanner(System.in);

        Execution ex = new Execution(5);

        List<Operation> ops = ex.generateOperations(OperationType.SUBSTRACT);

        System.out.println(ops.get(0).getNumbers());
        System.out.println(ops.get(1).getNumbers());
        System.out.println(ops.get(2).getNumbers());
        System.out.println(ops.get(3).getNumbers());
        System.out.println(ops.get(4).getNumbers());
        
        //double answer = sc.nextDouble();
        
        System.out.println(ops.get(0).checkAnswer(5));
        System.out.println(ops.get(1).checkAnswer(2));
        System.out.println(ops.get(2).checkAnswer(10));
        System.out.println(ops.get(3).checkAnswer(0));
        System.out.println(ops.get(4).checkAnswer(8));

        
      
//        List<Double> numbers = new ArrayList();
//        
//        numbers.add(20.0);
//        numbers.add(20.0);
//        numbers.add(20.0);
//        
//        Operation add = new AdditionOperation(numbers);
//        
//        boolean answer = add.checkAnswer(40);
//        
//        System.out.println(answer);
    }



}
