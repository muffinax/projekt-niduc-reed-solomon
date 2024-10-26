package org.example;

public class Signal {
    String value; //wartosc sygnału
    String type; // w jakiej formie zapisany: decimal/vector/element
    public Signal(String value, String type){
        this.type = type;
        this.value = value;
    }
}
