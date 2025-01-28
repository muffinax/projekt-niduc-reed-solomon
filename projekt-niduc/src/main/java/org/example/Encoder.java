package org.example;

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
            return cx; // Brak błędów
        }

        // Krok 2: Algorytm Berlekampa-Masseya (wyznaczanie wielomianu Lambda)
        Polynomial lambda = berlekampMassey(sx);
        System.out.println("Wielomian Lambda (lokalizujący błędy): ");
        lambda.show_polynomial();

        // Krok 3: Wyznaczanie błędów za pomocą Lambda do wyrzucenia - służy tylko do wyświetlania
        Polynomial errorLocator = evaluateErrors(lambda, cx.getTrueLength());
        System.out.println("Błędy (pozycje): ");
        errorLocator.show_polynomial();

        // Krok 4: Korekcja błędów
        Polynomial corrected = correctErrors(cx, errorLocator);
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

        for(int i = 0; i < 2 * t; i++){
            Signal sum = new Signal("0", "decimal");
            for(int j = 0; j < n; j++){
                int pom = (i * j) % 31;
                Signal alphaPower = new Signal("A" + String.format("%02d", pom), "element"); // α^(i * j)
                Signal term = mathPolynomials.multiplication(cx.getPolynomialSignal(j), alphaPower); // r_j * α^(i * j)
                sum = mathPolynomials.addition(sum, term);
            }
            syndromes[i] = sum;
        }

        return new Polynomial(syndromes);
    }

    // Algorytm Berlekampa-Masseya
    private Polynomial berlekampMassey(Polynomial sx){
        MathPolynomials mathPolynomials=new MathPolynomials();
        Polynomial lambda = new Polynomial(new String[] {"A00"}, "element"); // Lambda(x) = 1
        Polynomial b = new Polynomial(new String[] {"A00"}, "element"); // B(x) = 1
        int l = 0; // Początkowy stopień lambda
        Signal delta = new Signal("A00", "element"); // Delta = 1

        for(int i = 0; i < sx.getPolynomial().length; i++){
            delta = calculateDiscrepancy(lambda, sx.getPolynomial(), i); // Delta(i) = s(i) - lambda(i)
            if(!delta.getValueE().equals("A32")){
                Polynomial t = lambda;
                Polynomial deltaB = mathPolynomials.mulPolynomials(b, new Polynomial(new String[] {delta.getValueE()}, "element"));
                deltaB.shiftPolynomial("L");
                lambda = mathPolynomials.addPolynomials(lambda, deltaB); // Lambda = Lambda + Delta * B
                if(2 * l <= i){
                    l = i + 1 - l; // Aktualizacja stopnia Lambda
                    b = mathPolynomials.mulPolynomials(t, new Polynomial(new String[] {delta.getValueE()}, "element"));
                }
            }
            b.shiftPolynomial("L");
        }
        return lambda;
    }

    // Wyznaczanie pozycji błędów
    private Polynomial evaluateErrors(Polynomial lambda, int length){
        MathPolynomials mathPolynomials=new MathPolynomials();
        Signal[] errorPositions = new Signal[length];
        for(int i = 0; i < length; i++){
            Signal xi = new Signal(String.valueOf(i), "decimal");
            Polynomial evaluation = mathPolynomials.mulPolynomials(lambda, new Polynomial(new String[] {xi.getValueE()}, "element"));
            if(evaluation.hammingWeight() == 0){
                errorPositions[i] = new Signal("1", "decimal"); // Błąd w pozycji i
            } else{
                errorPositions[i] = new Signal("0", "decimal");
            }
        }
        return new Polynomial(errorPositions);
    }
    /*  druga wersja                   kom może przydać się komenda inverse to odwracania sygnałów
    private Polynomial evaluateErrors(Polynomial lambda, int length){ //lenght = 31
        MathPolynomials mathPolynomials = new MathPolynomials();
        Signal[] errorPositions = new Signal[length];

        for(int i = 0; i < length; i++){
            int inverseIndex = (31 - i) % 31;
            Signal alphaInverse = new Signal("A" + String.format("%02d", inverseIndex), "element"); //α^(-i)

            // Oblicz lambda(α^(-i))          lambda(xi)        równe 1 lub 0

            if(evaluation.getValueE().equals("A32")){
                errorPositions[i] = new Signal("1", "decimal"); // Pozycja błędu
            } else{
                errorPositions[i] = new Signal("0", "decimal"); // Brak błędu
            }
    }

    return new Polynomial(errorPositions);
    }
    */

    // Korekcja błędów
    private Polynomial correctErrors(Polynomial cx, Polynomial errorLocator){
        int trueLength = cx.getTrueLength();
        Signal[] corrected = new Signal[trueLength];
        for(int i = 0; i < trueLength; i++){
            if(errorLocator.getPolynomialSignal(i).getValueD().equals("1")){
                corrected[i] = new Signal("A32", "element"); // Odwróć wartość w pozycji błędu
            } else{
                corrected[i] = cx.getPolynomialSignal(i); // Przepisz poprawny element
            }
        }
        return new Polynomial(corrected);
    }

    // pomocnicza do B-M
    private Signal calculateDiscrepancy(Polynomial lambda, Signal[] syndromes, int k){
        MathPolynomials mathPolynomials = new MathPolynomials();
        Signal discrepancy = syndromes[k]; // S_k jako baza
        for(int i = 1; i < lambda.getPolynomial().length; i++){
            if(k - i >= 0){
                Signal lambdaCoeff = lambda.getPolynomialSignal(i); // Współczynnik Λ_i
                Signal syndromeCoeff = syndromes[k - i]; // Syndrom S_(k-i)
                discrepancy = mathPolynomials.addition(discrepancy, mathPolynomials.multiplication(lambdaCoeff, syndromeCoeff));
            }
        }
        return discrepancy; // Wynikowa niezgodność δ
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
