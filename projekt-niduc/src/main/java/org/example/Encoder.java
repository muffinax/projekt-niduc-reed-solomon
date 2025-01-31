package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Encoder {
    public Polynomial encode(Polynomial message){
        Generator generator = new Generator();
        Polynomial generator_poly = new Polynomial(generator.polynomial_generator());
        MathPolynomials mathPolynomials=new MathPolynomials();
        message=make19(message);
        int n = 31;
        int k = 19;

        //wielomian x^(n-k) * m(x), m(x) = x^13
        Polynomial xnkmx = mathPolynomials.mulPolynomials(
                new Polynomial(
                new String[]{"A32","A32","A32","A32","A32","A32","A32","A32","A32","A32","A32","A32","A00"},"element"),
                message);

        //uzyskanie r(x)
        Polynomial rx = mathPolynomials.moduloPol(xnkmx, generator_poly);

        //Otrzymanie ostatecznego wielomianu c(x):
        Polynomial cx = mathPolynomials.addPolynomials(xnkmx, rx);
        return cx;
    }

    //poprawny dekoder prosty
    public Polynomial simpleDecoder(Polynomial cyx){
        Generator generator = new Generator();
        Polynomial gx = new Polynomial(generator.polynomial_generator());
        MathPolynomials mathPolynomials=new MathPolynomials();
        cyx=make19(cyx);
        Polynomial cdx;
        //wielomian cy(x) - odebrany wielomian
        //wielomian g(x) - wielomian generujący
        //wielomian s(x) - syndrom, reszta dzielenia cy(x) przez g(x)
        //wielomian cd(x) - odkodowany wielomian
        //ws - waga Hamminga syndromu - liczba niezerowych współczynników

        int t = 6;
        int k = 19;
        int num = 0;

        while(true){
            Polynomial sx = mathPolynomials.moduloPol(cyx,gx);
            int ws = sx.hammingWeight();
            if(ws <= t){
                cdx = mathPolynomials.addPolynomials(cyx, sx);
                for(int j = 0; j < num; j++){
                    cdx.shiftPolynomial("L"); //obraca z powrotem wielomian o tyle, o ile trzeba
                }
                return cdx;
            }else if(ws > t){
                if(num == k){
                    //w przypadku braku możliwosci odkodowania metoda zwraca 0
                    return new Polynomial(new String[] {"A32"}, "element");
                }else if(num < k){
                    cyx.shiftPolynomial("R");//obraca o jeden w prawo
                    num++;
                }
            }
        }
    }

    //Dekoder pełny
    public Polynomial fullDecoder(Polynomial cx){

        // Krok 1: Oblicz syndrom s(x)
        Polynomial sx = calculateSyndrome(cx);
        System.out.println("Syndrom: ");
        sx.show_polynomial();

        // Jeśli syndrom jest zerowy, brak błędów
        if(sx.hammingWeight() == 0){
            return cx;
        }

        // Krok 2: Algorytm Berlekampa-Masseya (wyznaczanie wielomianu Lambda)
        Polynomial lambda = berlekampMassey(sx);
        System.out.println("Wielomian Lambda (lokalizujący błędy): ");
        lambda.show_polynomial();

        // Krok 3: Szukanie pozycji błędów
        ArrayList<Integer> errorPositions = findErrorPositions(lambda);

        // Debug: Wyświetl znalezione pozycje błędów
        System.out.print("Pozycje błędów: ");
        if (errorPositions.isEmpty()) {
            System.out.println("Brak znalezionych błędów.");
        } else {
            for (int pos : errorPositions) {
                System.out.print("x^" + pos + " ");
            }
            System.out.println();
        }

        // Krok 4: Obliczanie korekcji błędów
        Signal[] errorValues = computeErrorValues(sx, errorPositions, lambda);

        // Krok 5: Korekcja błędów
        Polynomial corrected = correctErrors(cx, errorValues);
        System.out.println("Odkodowany wielomian: ");
        corrected.show_polynomial();

        return corrected;
    }
    // Wyznacza Syndrom
    private Polynomial calculateSyndrome(Polynomial cx){
        MathPolynomials mathPolynomials = new MathPolynomials();
        final int t = 6;
        int n = cx.getTrueLength();
        Signal[] syndromes = new Signal[2 * t];

        for(int i = 1; i <= 2 * t; i++){
            Signal sum = new Signal("0", "decimal");
            for(int j = 0; j < n; j++){
                int pom = (i * j) % 31;
                Signal alphaPower = new Signal("A" + String.format("%02d", pom), "element"); // α^(i * j)
                Signal term = mathPolynomials.multiplication(cx.getPolynomialSignal(j), alphaPower); // r_j * α^(i * j)
                sum = mathPolynomials.addition(sum, term);
            }
            syndromes[i-1] = sum;
        }

        return new Polynomial(syndromes);
    }
    // Algorytm Berlekampa-Masseya
    private Polynomial berlekampMassey(Polynomial sx){
        MathPolynomials mathPolynomials = new MathPolynomials();
        int t = 6;
        int N = 12;
        Signal[] lambda = new Signal[N];
        Arrays.fill(lambda, new Signal("0", "decimal"));
        lambda[0] = new Signal("1", "decimal");
        Signal[] B = new Signal[N]; // Pomocniczy wielomian B
        Arrays.fill(B, new Signal("0", "decimal"));
        B[0] = new Signal("1", "decimal");
        Signal delta = new Signal("1", "decimal"); // Rozbieżność
        int L = 0; // Liczba błędów
        int m = 1; // Licznik kroków

        for(int k = 0; k < 2 * t; k++){
            delta = calculateDiscrepancy(new Polynomial(lambda), sx.getPolynomial(), k);
            if(!delta.getValueE().equals("A32")){
                Signal[] lambdaTemp = Arrays.copyOf(lambda, N);
                // Skalowanie B przez delta^(-1)
                Signal deltaInv = inverse(delta);
                for (int i = 0; i < N - m; i++) {
                    if(!B[i].getValueE().equals("A32")){
                        lambda[m + i] = mathPolynomials.addition(lambda[m + i],
                                mathPolynomials.multiplication(deltaInv, B[i]));
                    }
                }
                if(2 * L <= k){
                    L = k + 1 - L;
                    B = lambdaTemp;
                    m = 1;
                } else{
                    m++;
                }
            } else{
                m++;
            }
        }

        return new Polynomial(lambda);
    }
    //pomocnicza do B-M
    private Signal calculateDiscrepancy(Polynomial lambda, Signal[] syndromes, int k){
        MathPolynomials mathPolynomials = new MathPolynomials();
        Signal discrepancy = syndromes[k]; // Podstawowy syndrom S_k

        for(int i = 1; i < lambda.getPolynomial().length; i++){
            if(k - i >= 0){
                Signal lambdaCoeff = lambda.getPolynomialSignal(i); // Współczynnik Λ_i
                Signal syndromeCoeff = syndromes[k - i]; // Syndrom S_(k-i)
                Signal term = mathPolynomials.multiplication(lambdaCoeff, syndromeCoeff);
                discrepancy = mathPolynomials.addition(discrepancy, term);
            }
        }

        return discrepancy;
    }
    //metoda do odwracania sygnału
    public Signal inverse(Signal s){
        if(s.getValueE().equals("A32")) { // 0 w GF(2^m), brak odwrotności
            throw new ArithmeticException("Zero nie ma elementu odwrotnego w GF(2^5)");
        }
        int exponent = Integer.parseInt(s.getValueE().substring(1));
        int inverseExponent = (31 - exponent) % 31; // α^(-x) = α^(31-x)

        return new Signal("A" + String.format("%02d", inverseExponent), "element");
    }
    //Lokalizacja błędów
    private ArrayList<Integer> findErrorPositions(Polynomial lambda){
        ArrayList<Integer> errorPositions = new ArrayList<>();
        for(int i = 0; i < 31; i++){
            Signal alphaPower = new Signal("A" + String.format("%02d", i), "element");
            if(lambda.evaluate(alphaPower).getValueE().equals("A32")){
                errorPositions.add(31 - i);
            }
        }

        return errorPositions;
    }
    //Obliczanie wartości błędów
    private Signal[] computeErrorValues(Polynomial sx, ArrayList<Integer> errorPositions, Polynomial lambda){
        MathPolynomials mathPolynomials = new MathPolynomials();
        Signal[] errorValues = new Signal[31];
        Arrays.fill(errorValues, new Signal("0", "decimal"));
        Polynomial lambdaDerivative = lambda.derivative();
        for(int pos : errorPositions){
            Signal alphaPower = new Signal("A" + String.format("%02d", pos), "element");
            Signal errorValue = mathPolynomials.multiplication(sx.evaluate(alphaPower),
                    inverse(lambdaDerivative.evaluate(alphaPower)));
            errorValues[pos] = errorValue;
        }

        return errorValues;
    }
    //Koryguje błędy
    private Polynomial correctErrors(Polynomial cx, Signal[] errorValues){
        MathPolynomials mathPolynomials = new MathPolynomials();
        Signal[] corrected = Arrays.copyOf(cx.getPolynomial(), cx.getPolynomial().length);
        for(int i = 0; i < corrected.length; i++){
            corrected[i] = mathPolynomials.addition(corrected[i], errorValues[i]);
        }

        return new Polynomial(corrected);
    }

    //rozszerza wielomian o miejsca zerowe
    private Polynomial make19(Polynomial pol) {
        String[] r = new String[19];
        if (pol.getPolynomial().length < 16) {
            for (int i = 0; i < 19; i++) {
                if (i >= pol.getPolynomial().length) r[i] = "0";
                else r[i] = pol.getPolynomialSignal(i).getValueD();
            }
            return new Polynomial(r, "decimal");
        }
        else return pol;
    }
}
