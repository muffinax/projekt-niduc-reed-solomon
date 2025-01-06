package org.example;

import java.util.HashSet;
import java.util.Random;

public class Tester {
    private int t=6;
    public Tester(){}

    //TESTY PROSTEGO DEKODERA
    public void testLosowy(Polynomial poly1, int samples) {
        Encoder encoder = new Encoder();
        MathPolynomials mathPolynomials = new MathPolynomials();


        //poprawne kodowanie
        System.out.println("Kodowany wielomian:");
        poly1.show_polynomial();
        Polynomial enc1 = encoder.encode(poly1);
        System.out.println("\nZakodowany wielomian:");
        enc1.show_polynomial();

        //wprowadzanie bledow
        Random random = new Random();
        HashSet<Integer> uniqueNumbers = new HashSet<>();
        for (int j = 1; j <= t; j++) {

            System.out.println("\nIlość błędnych bitów: "+j);

            int poprawione = 0;
            int niePoprawione = 0;
            int zlePoprawione = 0;

            for (int i = 0; i < samples; i++) {
                Polynomial enc2 = new Polynomial(encoder.encode(poly1));

                //losowanie na których bitach będzie wprowadzony blad
                uniqueNumbers.clear();
                while (uniqueNumbers.size() < j) {
                    int ri = random.nextInt(31);
                    uniqueNumbers.add(ri);
                }

                //wprowadzanie bledow na bity
                for (int x : uniqueNumbers) {
//                    System.out.println(x);        //pokazuje jakie indeksy zostaly zmienione

                    int r = random.nextInt(31) + 1;
                    enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                }

//                System.out.println("\nPrzekłamany:");
//                enc2.show_polynomial();
//
                Polynomial dec1 = encoder.decode(enc2);
//                System.out.println("\nOdkodowany wielomian:");
//                dec1.show_polynomial();

                if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                    poprawione++;
                } else if (mathPolynomials.comparePol(enc2, dec1) == 0) {
                    niePoprawione++;
                } else zlePoprawione++;

            }
            System.out.println("poprawione: " + poprawione + "\nZLEpoprawione: " + zlePoprawione + "\nNIEpoprawione: " + niePoprawione);
        }
    }

    public void testWiazka(Polynomial poly1, int samples){
        Encoder encoder = new Encoder();
        MathPolynomials mathPolynomials = new MathPolynomials();


        //poprawne kodowanie
        System.out.println("Kodowany wielomian:");
        poly1.show_polynomial();
        Polynomial enc1 = encoder.encode(poly1);
        System.out.println("\nZakodowany wielomian:");
        enc1.show_polynomial();

        //wprowadzanie bledow
        Random random = new Random();
        HashSet<Integer> uniqueNumbers = new HashSet<>();
        for (int j = 1; j <= t; j++) {

            System.out.println("\nIlość błędnych bitów: "+j);

            int poprawione = 0;
            int niePoprawione = 0;
            int zlePoprawione = 0;

            for (int i = 0; i < samples; i++) {
                Polynomial enc2 = new Polynomial(encoder.encode(poly1));

                //losowanie na których bitach będzie wprowadzony blad
                uniqueNumbers.clear();
                while (uniqueNumbers.size() < j) {
                    int ri = random.nextInt(31);
                    uniqueNumbers.add(ri);
                }

                //wprowadzanie bledow na bity
                for (int x : uniqueNumbers) {
//                    System.out.println(x);        //pokazuje jakie indeksy zostaly zmienione

                    int r = random.nextInt(31) + 1;
                    enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                }

//                System.out.println("\nPrzekłamany:");
//                enc2.show_polynomial();
//
                Polynomial dec1 = encoder.decode(enc2);
//                System.out.println("\nOdkodowany wielomian:");
//                dec1.show_polynomial();

                if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                    poprawione++;
                } else if (mathPolynomials.comparePol(enc2, dec1) == 0) {
                    niePoprawione++;
                } else zlePoprawione++;

            }
            System.out.println("poprawione: " + poprawione + "\nZLEpoprawione: " + zlePoprawione + "\nNIEpoprawione: " + niePoprawione);
        }
    }
}
