package org.example;

import java.util.HashSet;
import java.util.Random;

public class Tester {
    private int t=6;
    public Tester(){}

    //TEST DLA 1 BLEDU
    public void testLosowy1(Polynomial poly1) {
        Encoder encoder = new Encoder();
        MathPolynomials mathPolynomials = new MathPolynomials();

        int poprawione = 0;
        int niePoprawione = 0;
        int zlePoprawione = 0;

        //poprawne kodowanie
        Polynomial enc1 = encoder.encode(poly1);
        System.out.println("\nZakodowany wielomian:");
        enc1.show_polynomial();

        //wprowadzanie bledow
        Random random = new Random();
        for(int i=0;i<31;i++){
            Polynomial enc2 = new Polynomial(encoder.encode(poly1));

            int r = random.nextInt(31) + 1;
            enc2.getPolynomialSignal(i).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(i), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
            System.out.println("\nPrzekłamany:");
            enc2.show_polynomial();

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
        System.out.println("\nIlość błędów: 1\npoprawione: " + poprawione + "\nZLEpoprawione: " + zlePoprawione + "\nNIEpoprawione: " + niePoprawione);
    }


    //TEST DLA 2 BLEDOW
    public void testLosowy2(Polynomial poly1) {
        Encoder encoder = new Encoder();
        MathPolynomials mathPolynomials = new MathPolynomials();

        int poprawione = 0;
        int niePoprawione = 0;
        int zlePoprawione = 0;

        //poprawne kodowanie
        Polynomial enc1 = encoder.encode(poly1);
        System.out.println("\nZakodowany wielomian:");
        enc1.show_polynomial();

        //wprowadzanie bledow
        Random random = new Random();
        for(int i=0;i<30;i++){
            for (int j=i+1;j<31;j++){
                Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                System.out.println(i);        //pokazuje jakie indeksy zostaly zmienione
                System.out.println(j);        //pokazuje jakie indeksy zostaly zmienione

                //dawanie losowej wartości na odpowiednie indeksy
                enc2.getPolynomialSignal(i).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(i), new Signal(String.valueOf(random.nextInt(31) + 1), "decimal")).getValueD(), "decimal");
                enc2.getPolynomialSignal(j).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(i), new Signal(String.valueOf(random.nextInt(31) + 1), "decimal")).getValueD(), "decimal");

//                System.out.println("\nPrzekłamany:");
//                enc2.show_polynomial();

                Polynomial dec1 = encoder.simpleDecoder(enc2);
//                System.out.println("\nOdkodowany wielomian:");
//                dec1.show_polynomial();

                if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                    poprawione++;
                    System.out.println("poprawione");
                } else if (mathPolynomials.comparePol(enc2, dec1) == 0) {
                    niePoprawione++;
                    System.out.println("niepoprawione");
                } else zlePoprawione++;
            }
        }
        System.out.println("\nIlość błędów: 2\npoprawione: " + poprawione + "\nZLEpoprawione: " + zlePoprawione + "\nNIEpoprawione: " + niePoprawione);
    }


    //TESTY PROSTEGO DEKODERA 3-6
    public void testLosowy36(Polynomial poly1, int samples) {
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
        for (int j = 3; j <= t; j++) {

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
            System.out.println("\nIlość błędów: "+j+"\npoprawione: " + poprawione + "\nZLEpoprawione: " + zlePoprawione + "\nNIEpoprawione: " + niePoprawione);
        }
    }





    public void testWiazka(Polynomial poly1,int max, int samples){
        if(max<2 || max>31) System.out.println("ERROR");
        else{
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
            HashSet<Integer> bityBledow = new HashSet<>();
            HashSet<Integer> uniqueNumbers = new HashSet<>();
            for (int j = 2; j <= max; j++) {

                int poprawione = 0;
                int niePoprawione = 0;
                int zlePoprawione = 0;

                for (int i = 0; i < samples; i++) {
                    Polynomial enc2 = new Polynomial(encoder.encode(poly1));

                    //losowanie na kjakiej wiazce beda wprowadzonye bledy
                    bityBledow.clear();
                    if (j==31) {
                        for(int x=0;x<31;x++){
                            bityBledow.add(x);
                        }

                    } else{
                        int rb, p;
                        uniqueNumbers.clear();

                        if (j<6) {           //długośc od 2 do 5 ma max 3 przerwy
                            p=3;
                            rb = random.nextInt(32-p-j);  //rb - wartość pierwszego bitu wiązki

                        } else if (j<13) {                   //długośc od 6 do 12 ma max 4 przerwy
                            p=4;
                            rb = random.nextInt(32-p-j);  //rb - wartość pierwszego bitu wiązki
                        } else if (j<26) {                   //długośc od 13 do 25 ma max 5 przerwy
                            p=5;
                            rb = random.nextInt(32-p-j);  //rb - wartość pierwszego bitu wiązki
                        } else {                  //długośc od 26 do 30
                            p=31-j;
                            rb = 0;  //rb - wartość pierwszego bitu wiązki
                        }

                        while (uniqueNumbers.size()<=p){
                            int ri = random.nextInt(j+p+1)+rb;
                            uniqueNumbers.add(ri);
                        }

                        for(int x=rb;x<j+p+1;x++){
                            if(!uniqueNumbers.contains(x))    bityBledow.add(x);
                        }
                    }

                    //wprowadzanie bledow na bity
                    for (int x : bityBledow) {
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
                    } else if (mathPolynomials.comparePol(enc2, dec1) == 0) {
                        niePoprawione++;
                    } else zlePoprawione++;

                }
                System.out.println("\nIlość błędów: "+j+"\npoprawione: " + poprawione + "\nZLEpoprawione: " + zlePoprawione + "\nNIEpoprawione: " + niePoprawione);
            }
        }

    }
}
