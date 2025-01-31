package org.example;

import java.util.HashSet;
import java.util.Random;

public class Tester {
    private int t = 6;

    public Tester() {
    }

    //TESTY LOSOWE
    public void testLosowy(Polynomial poly1, int samples) {
        System.out.println();
        System.out.println("Testy losowe dla dekodera prostego:");
        testLosowy1(poly1);
        testLosowy2(poly1);
        testLosowy36(poly1, samples);
    }

    //TEST DLA 1 BLEDU
    private void testLosowy1(Polynomial poly1) {
        Encoder encoder = new Encoder();
        MathPolynomials mathPolynomials = new MathPolynomials();

        int poprawione = 0;
        int niePoprawione = 0;

        //poprawne kodowanie
        Polynomial enc1 = encoder.encode(poly1);

        //wprowadzanie bledow
        Random random = new Random();
        for (int i = 0; i < 31; i++) {
            Polynomial enc2 = new Polynomial(encoder.encode(poly1));

            int r = random.nextInt(31) + 1;
            enc2.getPolynomialSignal(i).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(i), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");

            Polynomial dec1 = encoder.simpleDecoder(enc2);

            if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                poprawione++;
            } else niePoprawione++;
        }
        System.out.println("\nIlość błędów: 1\npoprawione: " + poprawione + "\nNIEpoprawione: " + niePoprawione);
    }


    //TEST DLA 2 BLEDOW
    private void testLosowy2(Polynomial poly1) {
        Encoder encoder = new Encoder();
        MathPolynomials mathPolynomials = new MathPolynomials();

        int poprawione = 0;
        int niePoprawione = 0;

        //poprawne kodowanie
        Polynomial enc1 = encoder.encode(poly1);

        //wprowadzanie bledow
        Random random = new Random();
        for (int i = 0; i < 30; i++) {
            for (int j = i + 1; j < 31; j++) {
                Polynomial enc2 = new Polynomial(encoder.encode(poly1));

                //dawanie losowej wartości na odpowiednie indeksy
                enc2.getPolynomialSignal(i).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(i), new Signal(String.valueOf(random.nextInt(31) + 1), "decimal")).getValueD(), "decimal");
                enc2.getPolynomialSignal(j).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(i), new Signal(String.valueOf(random.nextInt(31) + 1), "decimal")).getValueD(), "decimal");

                Polynomial dec1 = encoder.simpleDecoder(enc2);

                if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                    poprawione++;
                } else niePoprawione++;
            }
        }
        System.out.println("\nIlość błędów: 2\npoprawione: " + poprawione + "\nNIEpoprawione: " + niePoprawione);
    }


    //TESTY PROSTEGO DEKODERA 3-6
    private void testLosowy36(Polynomial poly1, int samples) {
        Encoder encoder = new Encoder();
        MathPolynomials mathPolynomials = new MathPolynomials();

        //poprawne kodowanie
        Polynomial enc1 = encoder.encode(poly1);

        //wprowadzanie bledow
        Random random = new Random();
        HashSet<Integer> uniqueNumbers = new HashSet<>();

        for (int j = 3; j <= t; j++) {  //Ilość błędów badanych

            int poprawione = 0;
            int niePoprawione = 0;

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
                    int r = random.nextInt(31) + 1;
                    enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                }

                Polynomial dec1 = encoder.simpleDecoder(enc2);

                if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                    poprawione++;
                } else niePoprawione++;

            }
            System.out.println("\nIlość błędów: " + j + "\npoprawione: " + poprawione + "\nNIEpoprawione: " + niePoprawione);
        }
    }


    public void testWiazka(Polynomial poly1, int samples) {
        Random random = new Random();
        System.out.println();
        System.out.println("Testy grupowe dla dekodera prostego:");

        Encoder encoder = new Encoder();
        MathPolynomials mathPolynomials = new MathPolynomials();

        //poprawne kodowanie;
        Polynomial enc1 = encoder.encode(poly1);

        //wprowadzanie bledow

        testLosowy1(poly1);  //test wiązki dla 1 błędu jest taki sam jak test losowy dla 1 błędu

        testWiazka23(poly1);

        testWiazka456789(poly1, samples);

        testWiazka9_10(poly1);

        for (int i = 11; i <= 12; i++) {

            int poprawione = 0;
            int niePoprawione = 0;

            if (i == 12) {
                for (int h = 0; h < 20; h++) {
                    Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                    for (int x = h; x < 12 + h; x++) {
                        int r = random.nextInt(31) + 1;
                        enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                    }

                    Polynomial dec1 = encoder.simpleDecoder(enc2);

                    if (mathPolynomials.comparePol(dec1, enc1) == 0) poprawione++;
                    else niePoprawione++;
                }
            } else if (i == 11) {
                for (int y = 0; y < 20; y++) {
                    for (int p = 1 + y; p < 12 + y; p++) {
                        Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                        for (int x = y; x < 12 + y; x++) {
                            if (x != p) {
                                int r = random.nextInt(31) + 1;
                                enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                            }
                        }

                        Polynomial dec1 = encoder.simpleDecoder(enc2);

                        if (mathPolynomials.comparePol(dec1, enc1) == 0) poprawione++;
                        else niePoprawione++;

                    }

                }
                Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                for (int x = 20; x < 31; x++) {
                    int r = random.nextInt(31) + 1;
                    enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                }

                Polynomial dec1 = encoder.simpleDecoder(enc2);

                if (mathPolynomials.comparePol(dec1, enc1) == 0) poprawione++;
                else niePoprawione++;

            }
            System.out.println("\nIlość błędów: " + i + "\npoprawione: " + poprawione + "\nNIEpoprawione: " + niePoprawione);
        }
    }

    private void testWiazka23(Polynomial poly1) {
        Random random = new Random();
        MathPolynomials mathPolynomials = new MathPolynomials();
        Encoder encoder = new Encoder();

        Polynomial enc1 = encoder.encode(poly1);

        for (int w = 0; w < 2; w++) {
            int poprawione = 0;
            int niePoprawione = 0;

            for (int a = 0; a < 30 - w; a++) {
                int gb;
                if (a < 20) gb = 12 - w + a;
                else gb = 31 - w;
                for (int b = a + 1; b < gb; b++) {
                    if (w == 1) {
                        int gc = gb + 1;
                        for (int c = b + 1; c < gc; c++) {
                            Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                            for (int x = 0; x < 31; x++) {
                                int r = random.nextInt(31) + 1;
                                enc2.getPolynomialSignal(a).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(a), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                                enc2.getPolynomialSignal(b).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(b), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                                enc2.getPolynomialSignal(c).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(c), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");

                            }

                            Polynomial dec1 = encoder.simpleDecoder(enc2);

                            if (mathPolynomials.comparePol(dec1, enc1) == 0) poprawione++;
                            else niePoprawione++;
                        }
                    } else {
                        Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                        for (int x = 0; x < 31; x++) {
                            int r = random.nextInt(31) + 1;
                            enc2.getPolynomialSignal(a).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(a), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                            enc2.getPolynomialSignal(b).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(b), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                        }

                        Polynomial dec1 = encoder.simpleDecoder(enc2);

                        if (mathPolynomials.comparePol(dec1, enc1) == 0) poprawione++;
                        else niePoprawione++;
                    }
                }
            }
            System.out.println("\nIlość błędów: " + (w + 2) + "\npoprawione: " + poprawione + "\nNIEpoprawione: " + niePoprawione);

        }
    }

    private void testWiazka456789(Polynomial poly1, int samples) {
        Random random = new Random();
        MathPolynomials mathPolynomials = new MathPolynomials();
        Encoder encoder = new Encoder();

        Polynomial enc1 = encoder.encode(poly1);

        HashSet<Integer> bityBledow = new HashSet<>();

        for (int w = 0; w < 6; w++) {
            int poprawione = 0;
            int niePoprawione = 0;

            for (int i = 0; i < samples; i++) {
                Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                bityBledow.clear();
                while (bityBledow.size() < w + 4) {
                    int ri = random.nextInt(12);
                    bityBledow.add(ri);
                }

                int rb = random.nextInt(20);

                for (int x : bityBledow) {
                    int r = random.nextInt(31) + 1;
                    enc2.getPolynomialSignal(x + rb).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x + rb), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                }

                Polynomial dec1 = encoder.simpleDecoder(enc2);

                if (mathPolynomials.comparePol(dec1, enc1) == 0) poprawione++;
                else niePoprawione++;

            }
            System.out.println("\nIlość błędów: " + (w + 4) + "\npoprawione: " + poprawione + "\nNIEpoprawione: " + niePoprawione);

        }
    }

    private void testWiazka9_10(Polynomial poly1) {
        Random random = new Random();
        MathPolynomials mathPolynomials = new MathPolynomials();
        Encoder encoder = new Encoder();

        int poprawione = 0;
        int niePoprawione = 0;

        Polynomial enc1 = encoder.encode(poly1);
        for (int y = 0; y < 20; y++) {
            for (int p = 1 + y; p < 12 + y; p++) {
                Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                for (int x = y; x < 12 + y; x++) {
                    if (x != p|| x!=y) {
                        int r = random.nextInt(31) + 1;
                        enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                    }
                }

                Polynomial dec1 = encoder.simpleDecoder(enc2);

                if (mathPolynomials.comparePol(dec1, enc1) == 0) poprawione++;
                else niePoprawione++;

            }

        }
        Polynomial enc2 = new Polynomial(encoder.encode(poly1));
        for (int i = 20; i < 30; i++) {
            for( int b=i+1;b<31;b++){
                for (int x = i; x < 31; x++) {
                    if (x != b|| x!=i) {
                        int r = random.nextInt(31) + 1;
                        enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                    }
                }
            }
        }

        Polynomial dec1 = encoder.simpleDecoder(enc2);

        if (mathPolynomials.comparePol(dec1, enc1) == 0) poprawione++;
        else niePoprawione++;

        System.out.println("\nIlość błędów: 10\npoprawione: " + poprawione + "\nNIEpoprawione: " + niePoprawione);

    }
}