package org.example;

public class Signal {
    String value; //wartosc sygnału
    String type; // w jakiej formie zapisany: decimal/vector/element

    //marta
    final int m=5;
    final int t=6; //zdolność korekcyjna
    int p=2; // liczba pierwsza(czyli chyba system)??? wartość przykładowa
    int[] tab_value=new int[m];   //wartość w wektorze czy coś takiego
    //marta

    public Signal(String value, String type){
        this.type = type;
        this.value = value;
    }
}
