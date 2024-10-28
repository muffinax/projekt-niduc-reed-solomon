package org.example;

public class Multiplication {
    public Signal multiplication (Signal first, Signal second){
        if(!first.type.equals("element")){
            if(first.type.equals("decimal")){
                //zmien decimal->vector
                decimalToVector(first);
            }/*zmien vector->element*/vectorToElement(first);
        }
        if(!second.type.equals("element")){
            if(second.type.equals("decimal")){
                //zmien decimal->vector
                decimalToVector(second);
            }/*zmien vector->element*/vectorToElement(second);
        }
        return new Signal(
                String.valueOf((Integer.parseInt(first.value) + Integer.parseInt(second.value)) % 31),
                "element");
    }
}
