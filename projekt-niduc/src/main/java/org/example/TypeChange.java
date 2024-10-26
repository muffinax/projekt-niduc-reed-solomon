package org.example;

public class TypeChange {
    public static void binaryToDecimal(Signal signal){
        signal.value = String.valueOf(Integer.parseInt(signal.value,2));
        signal.type = "decimal";
    }
    public static void decimalToBinary(Signal signal){
        signal.value = Integer.toBinaryString(Integer.parseInt(signal.value));
        signal.type = "vector";
    }
}
