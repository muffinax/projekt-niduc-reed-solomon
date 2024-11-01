package org.example;

public class Polynomial {       //Działa trochę jak BigInteger
    private int[] polynomial;
    final int m=5;
    final int t=6; //zdolność korekcyjna
    String type;
    Polynomial(int[] polynomial){           //String[] polynomial
        this.polynomial=polynomial;         //this.polynomial=new Signal[polynomial.length]()
                                            //for() this.polynomial[i]=new Signal(polynomial[i])
    }

    //for decimals
    void add_polynomials(int[] polynomial_2){       //pol1.add_polynomials(pol2)
        int[] result;
        if(polynomial.length>polynomial_2.length) {
            result = new int[polynomial.length];
            for(int i=0;i<polynomial.length;i++){
                if(i<polynomial_2.length)   result[i]=polynomial[i]+polynomial_2[i];
                else result[i]=polynomial[i];
            }
        }
        else{
            result = new int[polynomial_2.length];
            for(int i=0;i<polynomial_2.length;i++){
                if(i<polynomial.length)   result[i]=polynomial[i]+polynomial_2[i];
                else result[i]=polynomial_2[i];
            }
        }
        polynomial=result;
    }
    void mul_polynomials(int[] polynomial_2){
        int[] result = new int[polynomial.length+polynomial_2.length-1];
        for(int i=0;i<result.length;i++) result[i] = 0;
        for(int i=0;i<polynomial.length;i++){
            for(int j=0;j<polynomial_2.length;j++){
                result[i+j]+=polynomial[i]*polynomial_2[j];
            }
        }
        polynomial=result;
    }
    int[] generator(){
        int[] result = new int[10];
        return result;
    }
    void show_polynomial(){
        for(int i=polynomial.length-1;i>=0;i--){
            if(polynomial[i]!=0) {
                if(i<polynomial.length-1) System.out.print(" + ");
                System.out.print("x^" + i + "(" + polynomial[i] + ")");
            }
        }
        System.out.println();
    }
    int[] getPolynomial(){return polynomial;}
}
