package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        double x = readDouble("Enter variable x from (-1,1): ");
        int n = readInt("Enter sequence member number n: ");
        double e = readDouble("Enter precision e: ");

        if (!checkEnteredValueIncludedInInterval(x)) {
            x = readDouble("Entered x does not belong to (-1; 1). Try again: ");
        }

        double sumOfNTermsOfSequences = findTheSumOfElements(x, n);
        System.out.printf("The sum of %d members of a sequence is %f\n", n, sumOfNTermsOfSequences);

        SumAndIteration sumWithEpsilon = findSumAndIteration(x, e);
        printResultWithPrecision(sumWithEpsilon, e);

        SumAndIteration SumOfTermsGreaterThanEpsilonDividedByTen = findSumAndIteration(x, e / 10);
        printResultWithPrecision(SumOfTermsGreaterThanEpsilonDividedByTen, e / 10);

        double functionValue = calculateFunctionValue(x);
        System.out.printf("The resulting function value ln(1 + x) is %f\n", functionValue);
    }

    private static double readDouble(String phrase) {
        System.out.print(phrase);
        Scanner scn = new Scanner(System.in);
        return scn.nextDouble();
    }

    private static int readInt(String phrase) {
        System.out.print(phrase);
        Scanner scn = new Scanner(System.in);
        return scn.nextInt();
    }

    private static boolean checkEnteredValueIncludedInInterval(double x) {
        return x >= -1 && x <= 1;
    }

    private static double findElement(double x, int num) {
        return Math.pow(x, num) / num;
    }

    private static double findTheSumOfElements(double x, int num) {
        double sum = 0;
        for (int n = 1; n <= num; n++) {
            if (n % 2 != 0) {
                sum += findElement(x, n);
            } else {
                sum -= findElement(x, n);
            }
        }
        return sum;
    }

    private static SumAndIteration findSumAndIteration(double precision, double e) {
        int iteration = 1;
        double sum = 0;
        while (Math.abs(e) > precision) {
            if (iteration % 2 != 0) {
                sum += e;
            } else {
                sum -= e;
            }
            iteration++;
        }
        return new SumAndIteration(sum, iteration);
    }

    private static double calculateFunctionValue(double x) {
        return Math.log(x + 1);
    }

    private static void printResultWithPrecision(SumAndIteration sumAndIteration, double precision) {
        System.out.printf("The sum of the first %d elements with precision %f is %f\n" +
                sumAndIteration.getIteration() + precision + sumAndIteration.getSum());
    }
}


