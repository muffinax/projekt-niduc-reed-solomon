package org.example;

public class Polynomial {
    //wielomian składający się z tablicy sygnałów; indeks tablicy odpowiada potędze x
    private Signal[] polynomial;
    final int m=5;
    final int t=6; //zdolność korekcyjna
    Polynomial(String[] polynomial, String type){
        this.polynomial=new Signal[polynomial.length];
        for(int i=0;i<polynomial.length;i++){
            this.polynomial[i]=new Signal(polynomial[i], type);
        }
    }
    Polynomial(Polynomial polynomial){
        this.polynomial=polynomial.getPolynomial();
    }
    Polynomial(){
        this.polynomial=new Signal[1];
        this.polynomial[0]=new Signal("0", "decimal");
    }

    //wypisuje wielomian w każdym możliwym zapisie
    void show_polynomial(){
        for(int i=polynomial.length-1;i>=0;i--){
//            if(!polynomial[i].getValueD().equals("0")) {         //pomija x oo wartości 0
                if(i<polynomial.length-1) System.out.print(" + ");
                System.out.print("x^" + i + "(" + polynomial[i].getValueD() + ")");
//            }
        }
        System.out.println();
        for(int i=polynomial.length-1;i>=0;i--){
//            if(!polynomial[i].getValueD().equals("0")) {         //pomija x oo wartości 0
            if(i<polynomial.length-1) System.out.print(" + ");
            System.out.print("x^" + i + "(" + polynomial[i].getValueV() + ")");
//            }
        }
        System.out.println();
        for(int i=polynomial.length-1;i>=0;i--){
//            if(!polynomial[i].getValueD().equals("0")) {         //pomija x oo wartości 0
            if(i<polynomial.length-1) System.out.print(" + ");
            System.out.print("x^" + i + "(" + polynomial[i].getValueE() + ")");
//            }
        }
        System.out.println();
    }
    //oblicza wage Hamminga wielomianu
    public int hammingWeight(){
        int result = 0;
        for(int i = 0; i < polynomial.length; i++){
            if(!polynomial[i].getValueE().equals("A32")){
                result++;
            }
        }
        return result;
    }

    //przesuwa wspolczynniki wielomianu w lewo (L) lub prawo (R)
    public void shiftPolynomial(String direction){
        if(direction.equals("L")){
            int len = polynomial.length;
            Signal help = polynomial[0];
            for(int i = 1; i < polynomial.length; i++){
                polynomial[i-1] = polynomial[i];
            }
            polynomial[len] = help;
        }
        else if(direction.equals("R")){
            int len = polynomial.length;
            Signal help = polynomial[len];
            for(int i = polynomial.length; i > 0; i--){
                polynomial[i] = polynomial[i-1];
            }
            polynomial[0] = help;
        }
    }
    Signal[] getPolynomial(){return polynomial;}
    Signal getPolynomialSignal(int i){return polynomial[i];}    //i to wartość potęgi x
}
