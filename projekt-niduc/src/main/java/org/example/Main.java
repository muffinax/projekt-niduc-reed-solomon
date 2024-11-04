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
        //Polynomial polynomial=new Polynomial(new String[] {"5","3", "0","-2","9"},"decimal");
        Polynomial poly2 = new Polynomial(new String[] {"2","5","1"}, "decimal");
        Polynomial poly1 = new Polynomial(new String[] {"2","1"}, "decimal");

        System.out.println(multi.multiplication(first,second).getValueE());
        System.out.println(addi.addition(first,second).getValueV());
        addi.add_polynomials(poly1,poly2).show_polynomial();
        multi.mul_polynomials(poly1,poly2).show_polynomial();

        //tudidu
//        polynomial.add_polynomials(poly2.getPolynomial());
//        polynomial.show_polynomial();
//        poly1.mul_polynomials(poly2.getPolynomial());
//        poly1.show_polynomial();
    }
}