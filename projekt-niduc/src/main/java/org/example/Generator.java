package org.example;

import java.io.IOError;

public class Generator{
    //marta - obliczanie wielomianu generującgo - zrobię z tego osobną klasę
    int[] generating_polynomial(int[] signal, int t){
        int[] generator={0,0,0,0,1};
        if(t<1)
            throw new IllegalArgumentException("zdolność korekcyjna musi być większa niż 1");

        for(int i=2;i<2*t+1;i++){
            int[] podstawienie = {0,0,0,0,i};
            //generator = //multyplikacja(generator,podstawienie)
        }
        return generator;
    }
}
