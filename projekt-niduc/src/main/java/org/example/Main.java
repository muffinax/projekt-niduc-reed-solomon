package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Tester tester=new Tester();
        Encoder encoder = new Encoder();
        Polynomial poly1 = new Polynomial(new String[]{"A12", "A09", "A22","A00","A32"}, "element");
        Polynomial poly12 = new Polynomial(new String[]{"A32", "A01", "A00","A32"}, "element");
        Polynomial poly2 = new Polynomial(new String[]{/*"A02",*/"A03"}, "element");
        Polynomial poly3 = new Polynomial(new String[]{"A22", "A05", "A00"}, "element");
        Polynomial polyt = new Polynomial(new String[]{"A32", "A32", "A32", "A32", "A32", "A32", "A20"}, "element");
        Generator generator = new Generator();
        Polynomial generator_poly = new Polynomial(generator.polynomial_generator());
        MathPolynomials mathPolynomials = new MathPolynomials();
        FullTester fullTester=new FullTester();



        Polynomial randomP =new Polynomial(randomPoly());
        randomP.show_polynomial();
//        encoder.fullDecoder(randomPoly());

        tester.testLosowy(randomP,800);
        tester.testWiazka(randomP);

        fullTester.testLosowy(randomP,800);
        fullTester.testWiazka(randomP);



//        //p(x) = x^2 + 2x + 3
//        Polynomial p = new Polynomial(new String[]{"3", "2", "1"}, "decimal");
//        Polynomial po = new Polynomial(generator.polynomial_generator());
//        System.out.print("Kodowany wielomian: ");
//        po.show_polynomial();
//        System.out.println();
//        //zakodowany wielomian p(x)
//        Polynomial encP = encoder.encode(po);
//        System.out.print("Zakodowany wielomian: ");
//        encP.show_polynomial();
//        System.out.println();
//        //zakłamanie wielomianu - dodanie bita w częsci informacyjnej
//        encP = mathPolynomials.addPolynomials(
//                encP,
//                new Polynomial(
//                        new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "1"}, "decimal"));
//        System.out.print("Zakłamany wielomian: ");
//        encP.show_polynomial();
//        System.out.println();
//        //dekodowanie wielomianu
//        Polynomial decP = encoder.simpleDecoder(p);
//        System.out.print("Odkodowany wielomian: ");
//        decP.show_polynomial();
//        System.out.println();


        /* test dekodera pełnego
        Polynomial po = new Polynomial(generator.polynomial_generator());
        System.out.println("Kodowany wielomian: ");
        po.show_polynomial();
        Polynomial encP = encoder.encode(po);
        System.out.println("Zakodowany wielomian: ");
        encP.show_polynomial();
        encP = mathPolynomials.addPolynomials(encP,
                new Polynomial(
                        new String[]{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "1"}, "decimal"));
        System.out.println("Zakłamany wielomian: ");
        encP.show_polynomial();
        encoder.fullDecoder(encP);
        System.out.println();
         */

    }



    //Losowy Wielomian 19
    public static Polynomial randomPoly(){
        Random random=new Random();
        int r;
        String[] values = new String[19];

        for(int i=0;i<values.length;i++){
            r=random.nextInt(32);
            values[i]=String.valueOf(r);
        }

        return new Polynomial(values,"decimal");
    }

}

