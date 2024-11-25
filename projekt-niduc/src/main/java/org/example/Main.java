package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Coder coder = new Coder();
        Encoder encoder = new Encoder();
        Polynomial poly1 = new Polynomial(new String[] {"A12","A09","A00"}, "element");
        Polynomial poly2 = new Polynomial(new String[] {"A02","A03"}, "element");
        Polynomial poly3 = new Polynomial(new String[] {"A22","A05","A00"}, "element");
        Polynomial polyt = new Polynomial(new String[] {"A32","A32","A32","A32","A32","A32","A20"}, "element");
        Generator generator= new Generator();
        Polynomial generator_poly = new Polynomial(generator.polynomial_generator());
        MathPolynomials mathPolynomials=new MathPolynomials();

        coder.coder();

        //poly1.show_polynomial();
        //System.out.println();
        //poly2.show_polynomial();
        //System.out.println();
        System.out.println("Kodowany wielomian:");
        poly1.show_polynomial();
        Polynomial enc1 = encoder.encode(poly1);
        System.out.println("Zakodowany wielomian:");
        enc1.show_polynomial();
        Polynomial dec1 = encoder.decode(enc1);
        System.out.println("Odkodowany wielomian:");
        dec1.show_polynomial();


        //mathPolynomials.moduloPol(generator_poly,poly2).show_polynomial();
        //System.out.println();

        //generator.polynomial_generator().show_polynomial();
        //System.out.println();

//        Signal s20 =new Signal("A20","element");
//        Signal s00 =new Signal("A32","element");
//        System.out.println(mathPolynomials.addition(s00,s20).getValueE());
//
//        mathPolynomials.mulPolynomials(poly2,polyt).show_polynomial();

//        String[] poly1coded = encoder.encode(poly1);
        //for(int i = 0; i < poly1coded.length; i++){
        //    System.out.println(poly1coded[i]);
        //}
    }
}