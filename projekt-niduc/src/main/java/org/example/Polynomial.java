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
        /*for(int i=polynomial.length-1;i>=0;i--){
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
        System.out.println();*/
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
        int len = polynomial.length;
        if (direction.equals("L")) {
            Signal first = polynomial[0];
            System.arraycopy(polynomial, 1, polynomial, 0, len - 1);
            polynomial[len - 1] = first;
        } else if (direction.equals("R")) {
            Signal last = polynomial[len - 1];
            System.arraycopy(polynomial, 0, polynomial, 1, len - 1);
            polynomial[0] = last;
        }
    }
    Signal[] getPolynomial(){return polynomial;}
    Signal getPolynomialSignal(int i){return polynomial[i];}    //i to wartość potęgi x
}
