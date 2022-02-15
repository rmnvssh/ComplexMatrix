package com.company;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
            // initial complex numbers
            Scanner scan = new Scanner(System.in);
            try {
                double xReal = scan.nextDouble();
                double xImage = scan.nextDouble();
                double yReal = scan.nextDouble();
                double yImage = scan.nextDouble();
                Complex x = new Complex(xReal, xImage);
                Complex y = new Complex(yReal, yImage);

                // arithmetic operations on complex numbers
                System.out.println(x.sub(y));
                System.out.println(x.module());
                System.out.println(x.sum(y));
                System.out.println(x.mul(y));
                try {
                    System.out.println(x.div(y));
                }
                catch (ArithmeticException e) {
                    System.out.println("Invalid input, division by zero. Denominator should not be zero");
                    System.exit(1);
                }

            }
            catch (InputMismatchException e){
                System.out.println("Input numbers only!");
                System.exit(1);
            }

            // initial matrix of complex numbers
            System.out.println("Input size of matrix.");
            try {
                int size = scan.nextInt();
                Matrix matrix_a = new Matrix(size);
                Matrix matrix_b = new Matrix(size);

                Complex[][] data_a = new Complex[size][size];
                Complex[][] data_b = new Complex[size][size];

                Random random = new Random();

                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        data_a[i][j] = new Complex(random.nextDouble() * 10, random.nextDouble() * 10);
                        data_b[i][j] = new Complex(random.nextDouble() * 10, random.nextDouble() * 10);
                    }
                }

                matrix_a.setMatrix(data_a);
                matrix_a.print();
                matrix_b.setMatrix(data_b);
//          matrix_b.print();

                // arithmetic operations on complex numbers
                Matrix sum = matrix_a.sum(matrix_b);
//        sum.print();
                Matrix sub = matrix_a.sub(matrix_b);
//        sub.print();
                Matrix mul = matrix_a.mul(matrix_b);
//        mul.print();
                Matrix trans = matrix_a.transponse();
//        trans.print();
                Complex det = new Complex();
                det = matrix_a.determinate();
                System.out.println(det);
            }
            catch (InputMismatchException e){
                System.out.println("Input only numbers!");
                System.exit(1);
            }



    }
}
