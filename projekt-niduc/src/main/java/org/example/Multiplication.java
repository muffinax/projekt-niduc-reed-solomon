package org.example;

public class Multiplication {
    public Signal multiplication (Signal first, Signal second){
        TypeChange typeChange=new TypeChange();
        if(!first.type.equals("element")){
            if(first.type.equals("decimal")){
                //zmien decimal->vector
                typeChange.decimalToVector(first);
            }/*zmien vector->element*/typeChange.vectorToElement(first);
        }
        if(!second.type.equals("element")){
            if(second.type.equals("decimal")){
                //zmien decimal->vector
                typeChange.decimalToVector(second);
            }/*zmien vector->element*/typeChange.vectorToElement(second);
        }
        return new Signal(
                String.valueOf((Integer.parseInt(first.value.charAt(0)) + Integer.parseInt(second.value.charAt(0))) % 31), "element");
    }
}
