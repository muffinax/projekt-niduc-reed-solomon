package org.example;

public class Generator {
    final int t=6;
    Polynomial polynomial_generator(){
        Polynomial result = new Polynomial(new String[] {"A01","A00"}, "element");
        Multiplication multiplication=new Multiplication();
        Polynomial gen=new Polynomial(new String[] {"A02","A00"},"element");

        for(int i=2;i<=2*t;i++){    //2*t<31, nie ma po co tego pilnować
            result=multiplication.mul_polynomials(result,gen);

            if(i<10)    gen.getPolynomialSignal(0).setValue("A0"+ i,"element");
            else    gen.getPolynomialSignal(0).setValue("A"+ i,"element");
        }

        return new Polynomial(new String[2],"element");
    }
}
