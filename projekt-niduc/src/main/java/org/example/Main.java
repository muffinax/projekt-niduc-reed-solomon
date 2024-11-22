package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Encoder encoder = new Encoder();
        Polynomial gen_test = new Polynomial(new String[] {"A16","A09","A13","A03","A10","A25","A13","A02","A10","A00","A22","A06","A00"}, "element");
        Polynomial poly1 = new Polynomial(new String[] {"A12","A09","A00"}, "element");
        Polynomial poly2 = new Polynomial(new String[] {"A03","A12","A00"}, "element");
        Polynomial poly3 = new Polynomial(new String[] {"A22","A05","A00"}, "element");
        Polynomial polyt = new Polynomial(new String[] {"A32","A32","A32","A32","A32","A32","A20"}, "element");
        Polynomial generator_poly = new Polynomial();
        Generator generator= new Generator();
        MathPolynomials mathPolynomials=new MathPolynomials();

        //poly1.show_polynomial();
        //System.out.println();
        //poly2.show_polynomial();
        //System.out.println();
//        Polynomial enc1 = encoder.encode(poly1);
//        enc1.show_polynomial();

//        mathPolynomials.moduloPol(gen_test,poly2).show_polynomial();

        Signal s20 =new Signal("A20","element");
        Signal s00 =new Signal("A32","element");
        System.out.println(mathPolynomials.addition(s00,s20).getValueE());

        mathPolynomials.mulPolynomials(poly2,polyt).show_polynomial();

//        String[] poly1coded = encoder.encode(poly1);
        //for(int i = 0; i < poly1coded.length; i++){
        //    System.out.println(poly1coded[i]);
        //}
    }
}