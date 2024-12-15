package org.example;

public class Encoder {
    public Polynomial encode(Polynomial message){
        Generator generator = new Generator();
        Polynomial generator_poly = new Polynomial(generator.polynomial_generator());
        MathPolynomials mathPolynomials=new MathPolynomials();

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
        Generator generator = new Generator();
        Polynomial gx = new Polynomial(generator.polynomial_generator());
        MathPolynomials mathPolynomials=new MathPolynomials();

        //wielomian cy(x) - odebrany wielomian
        //wielomian g(x) - wielomian generujący
        //wielomian s(x) - syndrom, reszta dzielenia cy(x) przez g(x)
        //wielomian cd(x) - odkodowany wielomian
        //ws - waga Hamminga syndromu - liczba niezerowych współczynników

        int numberOfShifts = 0; //licznik, ile razy zostanie przesunięty wielomian
        while(true){
            Polynomial sx = mathPolynomials.moduloPol(cyx,gx);
            int ws = sx.hammingWeight();
            if(ws <= 6){
                Polynomial cdx = mathPolynomials.addPolynomials(cyx, sx);
                for(int i = 0; i < numberOfShifts; i++){
                    cdx.shiftPolynomial("L");
                }
                return cdx;
            }else{
                if(numberOfShifts >= 5){
                    System.out.println("Na tym etapie dekoder nie jest w stanie odkodowac tego wielomianu, " +
                            "zwrocony zostanie wielomian wejsciowy.");
                    for(int i = 0; i < numberOfShifts; i++){
                        cyx.shiftPolynomial("L");
                    }
                    return cyx;
                }else{
                    cyx.shiftPolynomial("R");
                    numberOfShifts++;
                }
            }
        }
    }
}
