package org.example;

public class Encoder {
    //chyba poprawny (już zwątpiłam) koder:
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
//            System.out.println("syndrom: ");
//            sx.show_polynomial();
            int ws = sx.hammingWeight();
//            System.out.println("waga: " + ws);
            if(ws <= t){
                cdx = mathPolynomials.addPolynomials(cyx, sx);
                for(int j = 0; j < num; j++){
                    cdx.shiftPolynomial("L"); //obraca z powrotem wielomian o tyle, o ile trzeba
                }
                return cdx;
            }else if(ws > t){
                if(num == k){
                    //na razie w przypadku braku możliwosci odkodowania metoda zwraca 0
                    return new Polynomial(new String[] {"A32"}, "element");
                }else if(num < k){
                    cyx.shiftPolynomial("R");//obraca o jeden w prawo
                    num++;
                }
            }
        }
        /*for(int i = 0; i < k + 1; i++){
            Polynomial sx = mathPolynomials.moduloPol(cyx,gx);
            System.out.println("syndrom: ");
            sx.show_polynomial();
            int ws = sx.hammingWeight();
            System.out.println("waga: " + ws);
            if(ws <= t){
                cdx = mathPolynomials.addPolynomials(cyx, sx);
                for(int j = 0; j < i; j++){
                    cdx.shiftPolynomial("L"); //obraca z powrotem wielomian o tyle, o ile trzeba
                }
                return cdx;
            }else{
                if(i == k){
                    //na razie w przypadku braku możliwosci odkodowania metoda zwraca 0
                    return new Polynomial(new String[] {"A32"}, "element");
                }else{
                    cyx.shiftPolynomial("R");//obraca o jeden w prawo
                }
            }
        }
        return new Polynomial(new String[] {"A32"}, "element");*/
    }

    //tej metody dekodera nie wysyłaj, jest troche bardiej zła chyba
    public Polynomial decode(Polynomial cyx){
        //System.out.println("WEWNATRZ DEKODERA:::::");
        Generator generator = new Generator();
        //System.out.println("GENERUJACY::::");
        Polynomial gx = new Polynomial(generator.polynomial_generator());
        //gx.show_polynomial();
        MathPolynomials mathPolynomials=new MathPolynomials();

        //wielomian cy(x) - odebrany wielomian
        //wielomian g(x) - wielomian generujący
        //wielomian s(x) - syndrom, reszta dzielenia cy(x) przez g(x)
        //wielomian cd(x) - odkodowany wielomian
        //ws - waga Hamminga syndromu - liczba niezerowych współczynników

        int numberOfShifts = 0; //licznik, ile razy zostanie przesunięty wielomian
        int maxShifts = 5;
        //System.out.println("MAX SHIFTS::::" + maxShifts);
        int t = 6;
        while(true){
            Polynomial sx = mathPolynomials.moduloPol(cyx,gx);
            //System.out.println("SX NUMBER :::::" + numberOfShifts);
            //sx.show_polynomial();
            int ws = sx.hammingWeight();
            //System.out.println("WS::::" + ws);
            if(ws <= t){
                //System.out.println("WENT INTO WS<=T");
                Polynomial cdx = mathPolynomials.addPolynomials(cyx, sx);
                //System.out.println("CDX::::::");
                //cdx.show_polynomial();
                for(int i = 0; i < numberOfShifts; i++){
                    cdx.shiftPolynomial("L");
                    //System.out.println("CDX SHIFTED TIMES " + i);
                    //cdx.show_polynomial();
                }
                return cdx;
            }else{
                //System.out.println("WENT INTO THE ELSEEEEE");
                if(numberOfShifts >= maxShifts){
                    //System.out.println("WENT INTO NUMOFSH >= MAXSH");
                    //System.out.println("Na tym etapie dekoder nie jest w stanie odkodowac tego wielomianu, " +
                    //"zwrocony zostanie wielomian wejsciowy.");
                    for(int i = 0; i < numberOfShifts; i++){
                        cyx.shiftPolynomial("L");
                    }
                    return cyx;
                }else{
                    //System.out.println("PRZESUWAAAAAAAA");
                    cyx.shiftPolynomial("R");
                    //System.out.println("PRZESUNIETY CYX::::::");
                    //cyx.show_polynomial();
                    numberOfShifts++;
                }
                //System.out.println("CURRENT NUM OF SHIFTS::::::" + numberOfShifts);
            }
        }
    }
    //Dekoder pełny
    public Polynomial fullDecoder(Polynomial cyx){
        Generator generator = new Generator();
        Polynomial gx = new Polynomial(generator.polynomial_generator());
        MathPolynomials mathPolynomials = new MathPolynomials();
        //cyx = make19(cyx); // Dopasowuje długość wielomianu do 19
        //Polynomial cdx;

        // Krok 1: Oblicz syndrom s(x)
        Polynomial sx = mathPolynomials.moduloPol(cyx, gx);
        System.out.println("Syndrom: ");
        sx.show_polynomial();

        // Jeśli syndrom jest zerowy, brak błędów
        if (sx.hammingWeight() == 0) {
            return cyx; // Brak błędów
        }

        // Krok 2: Algorytm Berlekampa-Masseya (wyznaczanie wielomianu Lambda)
        Polynomial lambda = berlekampMassey(sx);
        System.out.println("Wielomian Lambda (lokalizujący błędy): ");
        lambda.show_polynomial();

        // Krok 3: Wyznaczanie błędów za pomocą Lambda
        Polynomial errorLocator = evaluateErrors(lambda, cyx.getPolynomial().length);
        System.out.println("Błędy (pozycje): ");
        errorLocator.show_polynomial();

        // Krok 4: Korekcja błędów
        Polynomial corrected = correctErrors(cyx, errorLocator);
        System.out.println("Odkodowany wielomian: ");
        corrected.show_polynomial();

        return corrected;
    }

    // Algorytm Berlekampa-Masseya
    private Polynomial berlekampMassey(Polynomial sx){
        Polynomial lambda = new Polynomial(new String[] {"A32"}, "element"); // Lambda(x) = 1
        Polynomial b = new Polynomial(new String[] {"A32"}, "element"); // B(x) = 1
        int l = 0; // Początkowy stopień lambda
        Signal delta = new Signal("A32", "element"); // Delta = 1 w GF(2^5)

        for(int i = 0; i < sx.getPolynomial().length; i++){
            delta = calculateDiscrepancy(lambda, sx, i); // Delta(i) = s(i) - lambda(i)
            if(!delta.getValueE().equals("A32")){ // Jeśli delta != 0
                Polynomial t = lambda; // Kopia lambda
                Polynomial deltaB = mathPolynomials.mulPolynomials(b, new Polynomial(new String[] {delta.getValueE()}, "element"));
                lambda = mathPolynomials.addPolynomials(lambda, deltaB); // Lambda = Lambda + Delta * B
                if(2 * l <= i){
                    l = i + 1 - l; // Aktualizacja stopnia Lambda
                    b = mathPolynomials.mulPolynomials(t, new Polynomial(new String[] {delta.getValueE()}, "element"));
                }
            }
            b.shiftPolynomial("L"); // Przesunięcie B w lewo
        }
        return lambda;
    }

    // Wyznaczanie pozycji błędów (poprawiona wersja bez Chien Search)
    private Polynomial evaluateErrors(Polynomial lambda, int length){
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
        return new Polynomial(errorPositions, "decimal");
    }

    // Korekcja błędów
    private Polynomial correctErrors(Polynomial cyx, Polynomial errorLocator){
        Signal[] corrected = new Signal[cyx.getPolynomial().length];
        for(int i = 0; i < cyx.getPolynomial().length; i++){
            if(errorLocator.getPolynomialSignal(i).getValueD().equals("1")){
                // Odwróć wartość w pozycji błędu
                corrected[i] = new Signal("A32", "element"); // Przykład: A32 oznacza 0 w GF(2^m)
            } else{
                corrected[i] = cyx.getPolynomialSignal(i); // Przepisz poprawny element
            }
        }
        return new Polynomial(corrected, "element");
    }
}
