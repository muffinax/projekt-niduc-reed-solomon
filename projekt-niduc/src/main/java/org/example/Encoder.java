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
}
