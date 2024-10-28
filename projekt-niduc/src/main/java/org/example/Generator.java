package org.example;

import java.io.IOError;

public class Generator{
    //marta - obliczanie wielomianu generującgo - zrobię z tego osobną klasę
    Multiplication multiplication;
    String generating_polynomial(Signal signal, int t){
        String generator="00001";
        if(t<1)
            throw new IllegalArgumentException("zdolność korekcyjna musi być większa niż 1");

        for(int i=2;i<2*t+1;i++){
            String podstawienie = "0000"+i;
            //generator = multiplication(generator,podstawienie);
        }
        return generator;
    }
}
