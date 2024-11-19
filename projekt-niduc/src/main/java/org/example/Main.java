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

        Encoder encoder = new Encoder();
        Polynomial gen_test = new Polynomial(new String[] {"A16","A09","A13","A03","A10","A25","A13","A02","A10","A00","A22","A06","A00"}, "element");
        Polynomial poly1 = new Polynomial(new String[] {"A12","A09","A00"}, "element");
        Polynomial poly2 = new Polynomial(new String[] {"A03","A12","A00"}, "element");
        Polynomial generator_poly = new Polynomial();
        Generator generator= new Generator();
        MathPolynomials mathPolynomials=new MathPolynomials();

        poly1.show_polynomial();
        System.out.println();
        poly2.show_polynomial();
        System.out.println();

//        System.out.println(multi.multiplication(first,second).getValueE());
//        System.out.println(addi.addition(first,second).getValueE());
        mathPolynomials.addPolynomials(poly1,poly2).show_polynomial();
        System.out.println();
        //mathPolynomials.mulPolynomials(poly1,poly2).show_polynomial();
        //System.out.println();
        //generator_poly= generator.polynomial_generator();
        //generator_poly.show_polynomial();
        //System.out.println();

//        System.out.println(comparator.compare_pol(poly1,poly1));

//        String[] poly1coded = encoder.encode(poly1);
        //for(int i = 0; i < poly1coded.length; i++){
        //    System.out.println(poly1coded[i]);
        //}
    }
}