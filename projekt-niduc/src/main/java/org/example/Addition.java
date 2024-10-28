package org.example;

public class Addition {
    public Signal addition (Signal first, Signal second){
        if(!first.type.equals("vector")){
            if(first.type.equals("decimal")){
                //zmien decimal->vector
                decimalToVector(first);
            }else /*zmien element->vector*/elementToVector(first);
        }
        if(!second.type.equals("vector")){
            if(second.type.equals("decimal")){
                //zmien decimal->vector
                decimalToVector(second);
            }else /*zmien element->vector*/elementToVector(second);
        }
        String a, b, c, d, e;
        if(first.value.charAt(0) == second.value.charAt(0))
            e = "0";
        else 
            e = "1";
        if(first.value.charAt(1) == second.value.charAt(1))
            d = "0";
        else 
            d = "1";
        if(first.value.charAt(2) == second.value.charAt(2))
            c = "0";
        else 
            c = "1";
        if(first.value.charAt(3) == second.value.charAt(3))
            b = "0";
        else 
            b = "1";
        if(first.value.charAt(4) == second.value.charAt(4))
            a = "0";
        else 
            a = "1";

        return new Signal(a+b+c+d+e, "vector");
    }
}
