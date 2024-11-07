package org.example;

public class Generator {
    final int t=6;
    Polynomial polynomial_generator(){
        Polynomial result = new Polynomial(new String[] {"A01","A00"}, "element");
        Multiplication multiplication=new Multiplication();
        Polynomial gen=new Polynomial(new String[] {"A02","A00"},"element");

        for(int i=2;i<=2*t;i++){
            if(i<10)    gen.getPolynomialSignal(0).setValue("A0"+ i,"element");
            else    gen.getPolynomialSignal(0).setValue("A"+ i,"element");

            result=multiplication.mul_polynomials(result,gen);
            result.show_polynomial();
        }

        return result;
    }
}
