package com.company;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public enum Roman {
        I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
        private int weight;

        private Roman(int weight) {
            this.weight = weight;
        }

        public int getWeight() {
            return weight;
        }
    }

    public static void main(String[] args) {
        Scanner example = new Scanner(System.in);
        String[] variables = separation(example.nextLine());

        float num;
        if (variables == null)
            System.out.print("throws Exception");
        else {
            num = calculator(variables);
            if (num == 0)
                System.out.print("throws Exception");
            else if (variables[3] == "Roman") {
                romanOutputRecursion((int) num);
                if (output == "") System.out.print("throws Exception");
                else System.out.print("result = " + output);
            } else System.out.print("result = " + num);

        }

    }

    public static String output = "";

    public static String romanOutputRecursion(int num) {
        Roman[] numbers = Roman.values();
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i].getWeight() <= num && numbers[i + 1].getWeight() >= num) {

                if (numbers[i + 1].getWeight() - numbers[i].getWeight() == num) {
                    output += numbers[i].toString() + numbers[i + 1].toString();
                } else {
                    if (num == numbers[i + 1].getWeight()) {
                        num = num - numbers[i + 1].getWeight();
                        output += numbers[i + 1].toString();
                    } else {
                        num = num - numbers[i].getWeight();
                        output += numbers[i].toString();
                    }
                    romanOutputRecursion(num);
                }
            }
        }
        return output;
    }

    private static String[] separation(String str) {

        String[] variables = str.split(" ");

        Character[] romeNumbers = new Character[]{'I', 'V', 'X', 'L', 'C', 'D', 'M'};
        if (variables.length == 3) {
            if (Arrays.asList(romeNumbers).contains(variables[0].charAt(0)))
                if (Arrays.asList(romeNumbers).contains(variables[2].charAt(0))) {
                    variables[0] = String.valueOf(romanNumberCalc(variables[0]));
                    variables[2] = String.valueOf(romanNumberCalc(variables[2]));
                    variables = Arrays.copyOf(variables, 4);
                    variables[3] = "Roman";
                    return variables;
                } else return null;
            else {
                variables = Arrays.copyOf(variables, 4);
                variables[3] = "Arabic";
                return variables;
            }
        } else return null;


    }

    public static int romanNumberCalc(String variable) {
        char[] numbers = variable.toCharArray();
        int sum = 0;
        if (numbers.length == 1) return Integer.valueOf(Roman.valueOf(String.valueOf(numbers[0])).getWeight());
        else {
            for (int i = numbers.length - 1; i > 0; i--) {
                Roman number1 = Roman.valueOf(String.valueOf(numbers[i]));
                Roman number2 = Roman.valueOf(String.valueOf(numbers[i - 1]));
                if (i == numbers.length - 1) sum += Integer.valueOf(number1.getWeight());
                if (Integer.valueOf(number1.getWeight()) > Integer.valueOf(number2.getWeight())) {
                    sum -= Integer.valueOf(number2.getWeight());
                } else sum += Integer.valueOf(number2.getWeight());


            }
        }


        return sum;
    }

    private static float calculator(String[] variables) {

        switch (variables[1]) {
            case "*":
                return Float.parseFloat(variables[0]) * Float.parseFloat(variables[2]);
            case "/":
                return Float.parseFloat(variables[0]) / Float.parseFloat(variables[2]);
            case "-":
                return Float.parseFloat(variables[0]) - Float.parseFloat(variables[2]);
            case "+":
                return Float.parseFloat(variables[0]) + Float.parseFloat(variables[2]);
            default:
                return 0;
        }


    }
}
