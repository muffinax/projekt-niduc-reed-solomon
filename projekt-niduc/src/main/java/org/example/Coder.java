package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Coder {
    private static final Map<String, Integer> stringToCode = new HashMap<>();
    private static final Map<Integer, String> codeToString = new HashMap<>();
    
    static {
        // Mapowanie stringów
        stringToCode.put("A", 0b00000);//0
        stringToCode.put("B", 0b00001);//1
        stringToCode.put("C", 0b00010);//2
        stringToCode.put("D", 0b00011);//3
        stringToCode.put("E", 0b00100);//4
        stringToCode.put("F", 0b00101);//5
        stringToCode.put("G", 0b00110);//6
        stringToCode.put("H", 0b00111);//7
        stringToCode.put("I", 0b01000);//8
        stringToCode.put("J", 0b01001);//9
        stringToCode.put("K", 0b01010);//10
        stringToCode.put("L", 0b01011);//11
        stringToCode.put("M", 0b01100);//12
        stringToCode.put("N", 0b01101);//13
        stringToCode.put("O", 0b01110);//14
        stringToCode.put("P", 0b01111);//15
        stringToCode.put("Q", 0b10000);//16
        stringToCode.put("R", 0b10001);//17
        stringToCode.put("S", 0b10010);//18
        stringToCode.put("T", 0b10011);//19
        stringToCode.put("U", 0b10100);//20
        stringToCode.put("V", 0b10101);//21
        stringToCode.put("W", 0b10110);//22
        stringToCode.put("X", 0b10111);//23
        stringToCode.put("Y", 0b11000);//24
        stringToCode.put("Z", 0b11001);//25

        stringToCode.put(" ", 0b11010);//26
        stringToCode.put(",", 0b11011);//27
        stringToCode.put(".", 0b11100);//28
        stringToCode.put("!", 0b11101);//29
        stringToCode.put("?", 0b11110);//30
        stringToCode.put(" ", 0b11111);//31

        // Tworzenie odwrotnego mapowania
        for(Map.Entry<String, Integer> entry : stringToCode.entrySet()) {
            codeToString.put(entry.getValue(), entry.getKey());
        }
    }
     // Funkcja enkodująca ciąg stringów na ciąg bitów
     public static String encode(String input) {
        StringBuilder bitStream = new StringBuilder();

        // Iterujemy po kluczach w mapie stringToCode i zamieniamy je na kody
        for(String key : stringToCode.keySet()) {
            if(input.contains(key)) {
                int code = stringToCode.get(key);
                bitStream.append(String.format("%05d", Integer.parseInt(Integer.toBinaryString(code))));
                input = input.replace(key, ""); // Usuwamy zakodowaną część
            }
        }

        return bitStream.toString();
    }
    // Funkcja dekodująca ciąg bitów na ciąg stringów
    public static String decode(String bitStream) {
        StringBuilder output = new StringBuilder();

        for(int i = 0; i < bitStream.length(); i += 5) {
            String bitSegment = bitStream.substring(i, i + 5);
            int code = Integer.parseInt(bitSegment, 2);

            if(codeToString.containsKey(code)) {
                output.append(codeToString.get(code));
            }
            else
                throw new IllegalArgumentException("Niepoprawny kod: " + bitSegment);
        }

        return output.toString();
    }
    public static void coder() {
        Scanner in = new Scanner(System.in);
        System.out.println("Wpisz zdanie do zakodowania: ");
        String input = in.nextLine();
        String encoded = encode(input);
        System.out.println("Zakodowane: " + encoded);

        String decoded = decode(encoded);
        System.out.println("Odkodowane: " + decoded);
    }
}
