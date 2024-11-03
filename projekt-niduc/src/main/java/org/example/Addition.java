package org.example;

public class Addition {
    public Signal addition (Signal first, Signal second){
        TypeChange typeChange=new TypeChange();

        String a, b, c, d, e;
        if(first.getValueV().charAt(0) == second.getValueV().charAt(0))
            e = "0";
        else 
            e = "1";
        if(first.getValueV().charAt(1) == second.getValueV().charAt(1))
            d = "0";
        else 
            d = "1";
        if(first.getValueV().charAt(2) == second.getValueV().charAt(2))
            c = "0";
        else 
            c = "1";
        if(first.getValueV().charAt(3) == second.getValueV().charAt(3))
            b = "0";
        else 
            b = "1";
        if(first.getValueV().charAt(4) == second.getValueV().charAt(4))
            a = "0";
        else 
            a = "1";

        return new Signal(e+d+c+b+a, "vector");
    }

    Polynomial add_polynomials(Polynomial p1, Polynomial p2){
        String[] result={"0"};
        if(p1.getPolynomial().length>p2.getPolynomial().length) {
            result = new String[p1.getPolynomial().length];
            for(int i=0;i<p1.getPolynomial().length;i++){
                if(i<p2.getPolynomial().length)   result[i]=addition(p1.getPolynomialSignal(i),p2.getPolynomialSignal(i)).getValueV();
                else result[i]=p1.getPolynomialSignal(i).getValueV();
            }
        }
        else{
            result = new String[p2.getPolynomial().length];
            for(int i=0;i<p2.getPolynomial().length;i++){
                if(i<p1.getPolynomial().length)   result[i]=addition(p1.getPolynomialSignal(i),p2.getPolynomialSignal(i)).getValueV();
                else result[i]=p2.getPolynomialSignal(i).getValueV();
            }
        }
        return new Polynomial(result,"vector");
    }

}
