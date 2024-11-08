package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        String value;
//        System.out.println("Podaj wartość pierwszego wielomianu(element)");
//        value= in.nextLine();
//        Signal first = new Signal(value, "element");
//        System.out.println("Podaj wartość drugiego wielomianu(element)");
//        value= in.nextLine();
//        Signal second = new Signal(value, "element");

        Multiplication multi=new Multiplication();
        Addition addi=new Addition();
        Encoder encoder = new Encoder();
        Polynomial gen_test = new Polynomial(new String[] {"A16","A09","A13","A03","A10","A25","A13","A02","A10","A00","A22","A06","A00"}, "element");
        Polynomial poly1 = new Polynomial(new String[] {"A12","A00"}, "element");
        Polynomial poly2 = new Polynomial(new String[] {"A26","A10","A15","A28","A00"}, "element");
        Polynomial generator_poly = new Polynomial();
        Generator generator= new Generator();

//        System.out.println(multi.multiplication(first,second).getValueE());
//        System.out.println(addi.addition(first,second).getValueE());
//        addi.add_polynomials(poly1,poly2).show_polynomial();
//        System.out.println();
        //multi.mul_polynomials(poly1,poly2).show_polynomial();
        //System.out.println();
        //generator_poly= generator.polynomial_generator();
        //generator_poly.show_polynomial();
        //System.out.println();

        //tudidu
//        polynomial.add_polynomials(poly2.getPolynomial());
//        polynomial.show_polynomial();
//        poly1.mul_polynomials(poly2.getPolynomial());
//        poly1.show_polynomial();

        String[] poly1coded = encoder.encode(poly1);
        //for(int i = 0; i < poly1coded.length; i++){
        //    System.out.println(poly1coded[i]);
        //}
    }
}