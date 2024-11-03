package org.example;

public class Multiplication {
    public Signal multiplication (Signal first, Signal second){

        int fir = Integer.parseInt(String.valueOf(first.getValueE().charAt(2))) + 10 *Integer.parseInt(String.valueOf(first.getValueE().charAt(1)));
        int sec = Integer.parseInt(String.valueOf(second.getValueE().charAt(2))) + 10 *Integer.parseInt(String.valueOf(second.getValueE().charAt(1)));
        if (fir == 32 || sec == 32) return new Signal("A32","element");
        String wynik;
        if((fir + sec)%31 < 10)
            wynik = "0" + String.valueOf((fir + sec)%31);
        else
            wynik = String.valueOf((fir + sec)%31);
        return new Signal("A" + wynik, "element");
    }

    Polynomial mul_polynomials(Polynomial p1, Polynomial p2){
        Addition add = new Addition();
        String[] value = new String[p1.getPolynomial().length+p2.getPolynomial().length-1];
        Polynomial result = new Polynomial(value,"element");
        //zerowanie wyniku
        for(int i=0;i<result.getPolynomial().length;i++) result.getPolynomialSignal(i).setValue("0","decimal");

        for(int i=0;i<p1.getPolynomial().length;i++){
            for(int j=0;j<p2.getPolynomial().length;j++) {
                result.getPolynomialSignal(i+j).setValue(add.addition(result.getPolynomialSignal(i+j),multiplication(p1.getPolynomialSignal(i),p2.getPolynomialSignal(j))).getValueE(),"element");
            }
        }
        return result;
    }
}
