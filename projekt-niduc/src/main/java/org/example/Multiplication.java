package org.example;

public class Multiplication {
    public Signal multiplication (Signal first, Signal second){
        if(!first.type.equals("element")){
            if(first.type.equals("decimal")){
                //zmien decimal->element
                decimalToVector(first);
                vectorToElement(first);
            }else /*zmien vector->element*/vectorToElement(first);
        }
        if(!second.type.equals("element")){
            if(second.type.equals("decimal")){
                //zmien decimal->element
                decimalToVector(second);
                vectorToElement(second);
            }else /*zmien vector->element*/vectorToElement(second);
        }
        return new Signal(
                String.valueOf((Integer.parseInt(first.value) + Integer.parseInt(second.value)) % 31),
                "element");
    }
}
