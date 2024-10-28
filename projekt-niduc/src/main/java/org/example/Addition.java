package org.example;

public class Addition {
    public static Signal multiplication (Signal first, Signal second){
        if(!first.type.equals("vector")){
            if(first.type.equals("decimal")){
                //zmien decimal->vector
            }else /*zmien element->vector*/;
        }
        if(!second.type.equals("vector")){
            if(second.type.equals("decimal")){
                //zmien decimal->vector
            }else /*zmien element->vector*/;
        }
        String a, b, c, d, e;
        if(first.value[0] == second.value[0])
            e = "0";
        else 
            e = "1";
        if(first.value[1] == second.value[1])
            d = "0";
        else 
            d = "1";
        if(first.value[2] == second.value[2])
            c = "0";
        else 
            c = "1";
        if(first.value[3] == second.value[3])
            b = "0";
        else 
            b = "1";
        if(first.value[4] == second.value[4])
            a = "0";
        else 
            a = "1";

        return new Signal(a+b+c+d+e, "vector");
    }
}
