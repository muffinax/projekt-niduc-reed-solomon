package org.example;

public class Signal {
    String value; //wartosc sygnału
    String type; // w jakiej formie zapisany: decimal/vector/element

    //marta
    final int m=5;
    final int t=6; //zdolność korekcyjna
    //marta

    public Signal(String value, String type){
        this.type = type;
        this.value = value;
    }
}
