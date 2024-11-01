package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String value;
        System.out.println("Podaj wartość pierwszego wielomianu(dziesiętnie)");
        value= in.nextLine();
        Signal first = new Signal(value, "decimal");
        System.out.println("Podaj wartość drugiego wielomianu(dziesiętnie)");
        value= in.nextLine();
        Signal second = new Signal(value, "decimal");

        Multiplication multi=new Multiplication();
        Addition addi=new Addition();
        Polynomial polynomial=new Polynomial(new int[] {5,3, 0,-2,9});
        Polynomial poly2 = new Polynomial(new int[] {2,-5,1});

        System.out.println(multi.multiplication(first,second).value);
        System.out.println(addi.addition(first,second).value);
        //System.out.println(polynomial.add_polynomials(pol1,pol2));

        polynomial.add_polynomials(poly2.getPolynomial());
        polynomial.show_polynomial();
        //mnożenie

    }
}