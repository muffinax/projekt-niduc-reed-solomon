package org.example;

public class Signal {
    String value; //wartosc sygnału
    String type; // w jakiej formie zapisany: decimal/vector/element

    //marta
    final int m=5;
    final int t=6; //zdolność korekcyjna
    int p=2; // liczba pierwsza(czyli chyba system)??? wartość przykładowa
    int[] tab_value=new int[m];   //wartość w wektorze czy coś takiego
    int[] generator={0,1};   // generator - wielomian generujący
    //marta

    public Signal(String value, String type){
        this.type = type;
        this.value = value;
    }

    //marta - obliczanie wielomianu generującgo - zrobię z tego osobną klasę
    void generating_polynomial(){
        if(t<1) System.out.println("zdolność korekcyjna musi być większa niż 1");
        else {
            for(int i=2;i<2*t+1;i++){
                int[] podstawienie = {0,i};
                //generator = //multyplikacja(generator,podstawienie)
            }
        }
    }
}
