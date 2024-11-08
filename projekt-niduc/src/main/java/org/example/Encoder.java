package org.example;

public class Encoder {
    public String[] encode(Polynomial message){
        Generator generator = new Generator();
        Multiplication multiplication = new Multiplication();
        String[] encodedMessage = new String[message.getPolynomial().length];
        String[] rx = new String[message.getPolynomial().length];
        Polynomial xnkxm = new Polynomial(message);
        //zrobienie wielomianu x^(n-k) * m(x)
        for(int i = 0; i < xnkxm.getPolynomial().length; i++){
            for(int j = 0; j < 12; j++){
                xnkxm.getPolynomialSignal(i).setValue(xnkxm.getPolynomialSignal(i).getValueD() + "0", "decimal");
            }
        }
        //uzyskanie r(x)
        Polynomial mxg = multiplication.mul_polynomials(xnkxm, generator.polynomial_generator());
        for(int i = 0; i < message.getPolynomial().length;i++){
            rx[i] = mxg.getPolynomialSignal(i).getValueD();
            //dopełnienie r(x) do odpowiedniej długosci
            if(mxg.getPolynomialSignal(i).getValueD().length() < 12)
                while(rx[i].length() < 12){
                    rx[i] = "0" + rx[i];
                }
        }
        //dodanie rozszerzonego do odpowiedniej dlugosci r(x)
        for(int i = 0; i < message.getPolynomial().length;i++){
            encodedMessage[i] = message.getPolynomialSignal(i).getValueD() + rx[i];
        }
        return encodedMessage;
    }
}
