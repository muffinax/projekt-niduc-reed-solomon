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
        int fir = Integer.parseInt(String.valueOf(first.value.charAt(2))) + 10 *Integer.parseInt(String.valueOf(first.value.charAt(1)));
        int sec = Integer.parseInt(String.valueOf(second.value.charAt(2))) + 10 *Integer.parseInt(String.valueOf(second.value.charAt(1)));
        if (fir == 32 || sec == 32) return new Signal("A32","element");
        String wynik;
        if((fir + sec)%31 < 10)
            wynik = "0" + String.valueOf((fir + sec)%31);
        else
            wynik = String.valueOf((fir + sec)%31);
        return new Signal("A" + wynik, "element");
    }
}
