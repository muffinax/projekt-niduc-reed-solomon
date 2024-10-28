package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String value;
        System.out.println("Podaj wartość pierwszego wielomianu(dziesiętnie)");
        value= in.nextLine();
        Signal first = new Signal(value, "decimal");
        System.out.println("Podaj wartość drugiego wielomianu(dziesiętnie)");
        value= in.nextLine();
        Signal second = new Signal(value, "decimal");

        //mnożenie

    }
}