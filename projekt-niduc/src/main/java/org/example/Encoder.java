package org.example;

public class Encoder {
    public Polynomial encode(Polynomial message){
        Generator generator = new Generator();
        Addition addition = new Addition();
        Multiplication multiplication = new Multiplication();
        String[] encodedMessage = new String[message.getPolynomial().length];
        //wielomian x^(n-k) * m(x)
        System.out.println("Wielomian m(x) na wielomian x^(n-k) * m(x):");
        Polynomial xnkmx = multiplication.mul_polynomials(new Polynomial(
                new String[]{"A00","A32","A32","A32","A32","A32","A32","A32","A32","A32","A32","A32","A32"},"element"), message);
        xnkmx.show_polynomial();

        //uzyskanie r(x)
        System.out.println("\nReszty r(x):");
        //Polynomial rx = new Polynomial();
        //Polynomial rx = podzielone xnkmx przez generujacy;

        //Otrzymanie ostatecznego wielomianu c(x):
        //Polynomial cx = addition.add_polynomials(xnkmx, rx);
        //zapisanie cx w tablicy string (nie wiem czy chcemy String[] czy zwracać polynomial?)
        System.out.println("Zakodowane c(x) w tablicy:");
        //for(int i = 0; i < cx.getPolynomial().length;i++){
            //encodedMessage[i] = cx.getPolynomialSignal(i).getValueD();
            //System.out.println(encodedMessage[i] + "\n");
        //}
        return xnkmx;
    }
}
