package org.example;

public class Multiplication {
    public static Signal multiplication (Signal first, Signal second){
        if(!first.type.equals("element")){
            if(first.type.equals("decimal")){
                //zmien decimal->element
            }else /*zmien vector->element*/;
        }
        if(!second.type.equals("element")){
            if(second.type.equals("decimal")){
                //zmien decimal->element
            }else /*zmien vector->element*/;
        }
        return new Signal(
                String.valueOf((Integer.parseInt(first.value) + Integer.parseInt(second.value)) % 31),
                "element");
    }
}
