package org.example;

import java.lang.IllegalArgumentException;

public class Generator{
    //marta - obliczanie wielomianu generującgo - zrobię z tego osobną klasę
    Multiplication multi;
    Signal generating_polynomial(Signal signal, int t){
        Signal generator= new Signal("00001", "vector");
        if(t<1)
            throw new IllegalArgumentException("zdolność korekcyjna musi być większa niż 1");

        Signal podstawienie= new Signal("00000", "vector");
        for(int i=2;i<2*t+1;i++){
            podstawienie.value = "0000"+i;
            generator = multi.multiplication(generator,podstawienie);
        }
        return generator;
    }
}
