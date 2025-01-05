package org.example;

public class Encoder {
    public Polynomial encode(Polynomial message){
        Generator generator = new Generator();
        Polynomial generator_poly = new Polynomial(generator.polynomial_generator());
        MathPolynomials mathPolynomials=new MathPolynomials();
        int n = 31;
        int k = 19;

        //wielomian x^(n-k) * m(x)
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
    public Polynomial simpleDecoder(Polynomial cyx){
        Generator generator = new Generator();
        Polynomial gx = new Polynomial(generator.polynomial_generator());
        System.out.println("generujący:");
        gx.show_polynomial();
        MathPolynomials mathPolynomials=new MathPolynomials();
        Polynomial cdx;
        //wielomian cy(x) - odebrany wielomian
        //wielomian g(x) - wielomian generujący
        //wielomian s(x) - syndrom, reszta dzielenia cy(x) przez g(x)
        //wielomian cd(x) - odkodowany wielomian
        //ws - waga Hamminga syndromu - liczba niezerowych współczynników

        int t = 6;
        int k = 19;
        for(int i = 0; i < k + 1; i++){
            Polynomial sx = mathPolynomials.moduloPol(cyx,gx);
            System.out.println("sx:");
            sx.show_polynomial();
            int ws = sx.hammingWeight();
            System.out.println("hamming: " + ws);
            if(ws <= t){
                cdx = mathPolynomials.addPolynomials(cyx, sx);
                System.out.println("cdx:");
                cdx.show_polynomial();
                for(int j = 0; j < i; j++){
                    cdx.shiftPolynomial("L");
                }
                return cdx;
            }else{
                if(i == k){
                    return new Polynomial(new String[] {"A32"}, "element");
                }else{
                    cyx.shiftPolynomial("R");
                }
            }
        }
        return new Polynomial(new String[] {"A32"}, "element");
    }
}
