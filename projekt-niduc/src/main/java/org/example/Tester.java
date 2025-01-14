package org.example;

import java.util.HashSet;
import java.util.Random;

public class Tester {
    private int t=6;
    public Tester(){}

    //TEST DLA 1 BLEDU
    public void testLosowy1(Polynomial poly1, int samples) {
        Encoder encoder = new Encoder();
        MathPolynomials mathPolynomials = new MathPolynomials();

        int poprawione = 0;
        int niePoprawione = 0;
        int zlePoprawione = 0;

        //poprawne kodowanie
        System.out.println("Kodowany wielomian:");
        poly1.show_polynomial();
        Polynomial enc1 = encoder.encode(poly1);
        System.out.println("\nZakodowany wielomian:");
        enc1.show_polynomial();

        //wprowadzanie bledow
        Random random = new Random();
        HashSet<Integer> uniqueNumbers = new HashSet<>();

        for(int i=0;i<31;i++){
            Polynomial enc2 = new Polynomial(encoder.encode(poly1));
            System.out.println(i);        //pokazuje jakie indeksy zostaly zmienione

            int r = random.nextInt(31) + 1;
            enc2.getPolynomialSignal(i).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(i), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
            System.out.println("\nPrzekłamany:");
            enc2.show_polynomial();
//
            Polynomial dec1 = encoder.simpleDecoder(enc2);
            System.out.println("\nOdkodowany wielomian:");
            dec1.show_polynomial();

            if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                poprawione++;
                System.out.println("poprawione");
            } else if (mathPolynomials.comparePol(enc2, dec1) == 0) {
                niePoprawione++;
                System.out.println("niepoprawione");
            } else zlePoprawione++;
        }
        System.out.println("poprawione: " + poprawione + "\nZLEpoprawione: " + zlePoprawione + "\nNIEpoprawione: " + niePoprawione);


    }

    //TESTY PROSTEGO DEKODERA 3-6
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
                    System.out.println(x);        //pokazuje jakie indeksy zostaly zmienione

                    int r = random.nextInt(31) + 1;
                    enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                }
                System.out.println("\nPrzekłamany:");
                enc2.show_polynomial();
//
                Polynomial dec1 = encoder.simpleDecoder(enc2);
                System.out.println("\nOdkodowany wielomian:");
                dec1.show_polynomial();

                if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                    poprawione++;
                    System.out.println("poprawione");
                } else if (mathPolynomials.comparePol(enc2, dec1) == 0) {
                    niePoprawione++;
                    System.out.println("niepoprawione");
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
        for (int j = 1; j <= 31; j++) {

            System.out.println("\nIlość błędów: "+j);

            int poprawione = 0;
            int niePoprawione = 0;
            int zlePoprawione = 0;

            for (int i = 0; i < samples; i++) {
                Polynomial enc2 = new Polynomial(encoder.encode(poly1));

                //losowanie na kjakiej wiazce beda wprowadzonye bledy
                uniqueNumbers.clear();
                if(j==1){
                    int ri = random.nextInt(31);
                    uniqueNumbers.add(ri);
                } else if (j==31) {
                    for(int x=0;x<31;x++){
                        uniqueNumbers.add(x);
                    }
                } else if (j<5 || j==30) {           //długośc od 2 do 4 ma tylko 1 przerwe
                    int rb = random.nextInt(31-j);  //rb - wartość pierwszego bitu wiązki
                    while (uniqueNumbers.size()<j){
                        int ri = random.nextInt(j+1)+rb;
                        uniqueNumbers.add(ri);
                    }
                } else if (j<8 || j==29) {                   //długośc od 2 do 4 ma tylko 2 przerwy
                    int rb = random.nextInt(30-j);  //rb - wartość pierwszego bitu wiązki
                    while (uniqueNumbers.size()<j){
                        int ri = random.nextInt(j+2)+rb;
                        uniqueNumbers.add(ri);
                    }
                } else {                  //długośc od 2 do 4 ma tylko 3 przerwy (j<29)
                    int rb = random.nextInt(29-j);  //rb - wartość pierwszego bitu wiązki
                    while (uniqueNumbers.size()<j){
                        int ri = random.nextInt(j+3)+rb;
                        uniqueNumbers.add(ri);
                    }

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
                Polynomial dec1 = encoder.simpleDecoder(enc2);
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
