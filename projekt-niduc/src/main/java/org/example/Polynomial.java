package org.example;

public class Polynomial {       //Działa trochę jak BigInteger
    private Signal[] polynomial;
    final int m=5;
    final int t=6; //zdolność korekcyjna
    Polynomial(String[] polynomial, String type){
        this.polynomial=new Signal[polynomial.length];
        for(int i=0;i<polynomial.length;i++){
            this.polynomial[i]=new Signal(polynomial[i], type);
        }
    }
    Polynomial(){
        this.polynomial=new Signal[1];
        this.polynomial[0]=new Signal("0", "decimal");
    }
    void show_polynomial(){
        for(int i=polynomial.length-1;i>=0;i--){
//            if(!polynomial[i].getValueD().equals("0")) {         //pomija 0
                if(i<polynomial.length-1) System.out.print(" + ");
                System.out.print("x^" + i + "(" + polynomial[i].getValueD() + ")");
//            }
        }
        System.out.println();
        for(int i=polynomial.length-1;i>=0;i--){
//            if(!polynomial[i].getValueD().equals("0")) {         //pomija 0
            if(i<polynomial.length-1) System.out.print(" + ");
            System.out.print("x^" + i + "(" + polynomial[i].getValueV() + ")");
//            }
        }
        System.out.println();
        for(int i=polynomial.length-1;i>=0;i--){
//            if(!polynomial[i].getValueD().equals("0")) {         //pomija 0
            if(i<polynomial.length-1) System.out.print(" + ");
            System.out.print("x^" + i + "(" + polynomial[i].getValueE() + ")");
//            }
        }
        System.out.println();
    }
    Signal[] getPolynomial(){return polynomial;}
    Signal getPolynomialSignal(int i){return polynomial[i];}
}
