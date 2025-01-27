package org.example;

import java.util.HashSet;
import java.util.Random;

public class Tester {
    private int t = 6;

    public Tester() {
    }

    //TEST DLA 1 BLEDU
    public void testLosowy1(Polynomial poly1) {
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
    public void testLosowy2(Polynomial poly1) {
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
    public void testLosowy36(Polynomial poly1, int samples) {
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


    public void testWiazka(Polynomial poly1) {
        Encoder encoder = new Encoder();
        MathPolynomials mathPolynomials = new MathPolynomials();

        //poprawne kodowanie;
        Polynomial enc1 = encoder.encode(poly1);

        enc1.show_polynomial();

        //wprowadzanie bledow
        Random random = new Random();
//            HashSet<Integer> bityBledow = new HashSet<>();
//            HashSet<Integer> uniqueNumbers = new HashSet<>();
        for (int i = 1; i <= 12; i++) {

            int poprawione = 0;
            int niePoprawione = 0;

            int g;

            if (i < 7) g = 13 - i;
            else g = i+1;

            if (i == 12) {
                Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                for (int x = 0; x < 12; x++) {
                    int r = random.nextInt(31) + 1;
                    enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                }

                Polynomial dec1 = encoder.simpleDecoder(enc2);

                if (mathPolynomials.comparePol(dec1, enc1) == 0)    poprawione++;
                else niePoprawione++;

            } else {

                for (int p = 0; p < g; p++) {
                    if (i == 11) {
                        Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                        for (int x = 0; x < 12; x++) {
                            if (x != p) {
                                int r = random.nextInt(31) + 1;
                                enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                            }
                        }

                        Polynomial dec1 = encoder.simpleDecoder(enc2);

                        if (mathPolynomials.comparePol(dec1, enc1) == 0)    poprawione++;
                        else niePoprawione++;

                    } else if (i == 1) {
                        Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                        int r = random.nextInt(31) + 1;
                        enc2.getPolynomialSignal(p).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(p), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");

                        Polynomial dec1 = encoder.simpleDecoder(enc2);

                        if (mathPolynomials.comparePol(dec1, enc1) == 0)    poprawione++;
                        else niePoprawione++;

                    } else {
                        for (int j = p + 1; j < g + 1; j++) {
                            if (i == 10) {
                                Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                                for (int x = 0; x < 12; x++) {
                                    if (x != p || x != j) {
                                        int r = random.nextInt(31) + 1;
                                        enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                                    }
                                }
                                Polynomial dec1 = encoder.simpleDecoder(enc2);

                                if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                                    poprawione++;
                                } else niePoprawione++;

                            } else if (i == 2) {
                                Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                                int r = random.nextInt(31) + 1;
                                enc2.getPolynomialSignal(p).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(p), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                                r = random.nextInt(31) + 1;
                                enc2.getPolynomialSignal(j).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(j), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");

                                Polynomial dec1 = encoder.simpleDecoder(enc2);

                                if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                                    poprawione++;
                                } else niePoprawione++;

                            } else {

                                for (int k = j + 1; k < g + 2; k++) {

                                    if (i == 9) {
                                        Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                                        for (int x = 0; x < 12; x++) {
                                            if (x != p || x != j || x != k) {
                                                int r = random.nextInt(31) + 1;
                                                enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                                            }
                                        }
                                        Polynomial dec1 = encoder.simpleDecoder(enc2);

                                        if (mathPolynomials.comparePol(dec1, enc1) == 0)    poprawione++;
                                        else niePoprawione++;

                                    } else if (i == 3) {
                                        Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                                        for (int x = 0; x < 12; x++) {
                                            if (x == p || x == j || x == k) {
                                                int r = random.nextInt(31) + 1;
                                                enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                                            }
                                        }

                                        Polynomial dec1 = encoder.simpleDecoder(enc2);

                                        if (mathPolynomials.comparePol(dec1, enc1) == 0)    poprawione++;
                                        else niePoprawione++;

                                    } else {

                                        for (int a = k + 1; a < g + 3; a++) {

                                            if (i == 8) {
                                                Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                                                for (int x = 0; x < 12; x++) {
                                                    if (x != p || x != j || x != k || x != a) {
                                                        int r = random.nextInt(31) + 1;
                                                        enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                                                    }
                                                }
                                                Polynomial dec1 = encoder.simpleDecoder(enc2);

                                                if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                                                    poprawione++;
                                                } else niePoprawione++;

                                            } else if (i == 4) {
                                                Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                                                for (int x = 0; x < 12; x++) {
                                                    if (x == p || x == j || x == k || x == a) {
                                                        int r = random.nextInt(31) + 1;
                                                        enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                                                    }
                                                }

                                                Polynomial dec1 = encoder.simpleDecoder(enc2);

                                                if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                                                    poprawione++;
                                                } else niePoprawione++;

                                            } else {

                                                for (int b = a + 1; b < g + 4; b++) {

                                                    if (i == 7) {
                                                        Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                                                        for (int x = 0; x < 12; x++) {
                                                            if (x != p || x != j || x != k || x != a || x != b) {
                                                                int r = random.nextInt(31) + 1;
                                                                enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                                                            }
                                                        }
                                                        Polynomial dec1 = encoder.simpleDecoder(enc2);

                                                        if (mathPolynomials.comparePol(dec1, enc1) == 0) {
                                                            poprawione++;
                                                        } else niePoprawione++;

                                                    } else if (i == 5) {
                                                        Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                                                        for (int x = 0; x < 12; x++) {
                                                            if (x == p || x == j || x == k || x == a || x == b) {
                                                                int r = random.nextInt(31) + 1;
                                                                enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                                                            }
                                                        }

                                                        Polynomial dec1 = encoder.simpleDecoder(enc2);

                                                        if (mathPolynomials.comparePol(dec1, enc1) == 0)    poprawione++;
                                                        else niePoprawione++;

                                                    } else {
                                                        for (int c = b + 1; c < g + 5; c++) {
                                                            Polynomial enc2 = new Polynomial(encoder.encode(poly1));
                                                            for (int x = 0; x < 12; x++) {
                                                                if (x == p || x == j || x == k || x == a || x == b || x==c) {
                                                                    int r = random.nextInt(31) + 1;
                                                                    enc2.getPolynomialSignal(x).setValue(mathPolynomials.addition(enc2.getPolynomialSignal(x), new Signal(String.valueOf(r), "decimal")).getValueD(), "decimal");
                                                                }
                                                            }
                                                            Polynomial dec1 = encoder.simpleDecoder(enc2);

                                                            if (mathPolynomials.comparePol(dec1, enc1) == 0)    poprawione++;
                                                            else niePoprawione++;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("\nIlość błędów: " + i + "\npoprawione: " + poprawione + "\nNIEpoprawione: " + niePoprawione);
        }
    }
}