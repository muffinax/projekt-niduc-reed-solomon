package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Tester tester=new Tester();
        Coder coder = new Coder();
        Encoder encoder = new Encoder();
        Polynomial poly1 = new Polynomial(new String[]{"A12", "A09", "A00"}, "element");
        Polynomial poly2 = new Polynomial(new String[]{/*"A02",*/"A03"}, "element");
        Polynomial poly3 = new Polynomial(new String[]{"A22", "A05", "A00"}, "element");
        Polynomial polyt = new Polynomial(new String[]{"A32", "A32", "A32", "A32", "A32", "A32", "A20"}, "element");
        Generator generator = new Generator();
        Polynomial generator_poly = new Polynomial(generator.polynomial_generator());
        MathPolynomials mathPolynomials = new MathPolynomials();

        //Coder.coder();

        //poly1.show_polynomial();
        //System.out.println();
        //poly2.show_polynomial();
        //System.out.println();


//        tester.testLosowy(randomPoly(),100);
        tester.testWiazka(randomPoly(),1);



//        //PRZYKLAD DLA NIKODEMA
//        //p(x) = x^2 + 2x + 3
//        Polynomial p = new Polynomial(new String[]{"3", "2", "1"}, "decimal");
//        System.out.print("Kodowany wielomian: ");
//        p.show_polynomial();
//        System.out.println();
//        //zakodowany wielomian p(x)
//        Polynomial encP = encoder.encode(p);
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

    }



    //Losowy Wielomian 19
    public static Polynomial randomPoly(){
        Random random=new Random();
        int r;
        String[] values = new String[19];
        for(int i=0;i<values.length-1;i++){
            r=random.nextInt(32);
            values[i]=String.valueOf(r);
        }

        //wartość przy najwyższej potędze zawsze większa od 0
        r=random.nextInt(31)+1;
        values[18]=String.valueOf(r);

        return new Polynomial(values,"decimal");
    }

}

