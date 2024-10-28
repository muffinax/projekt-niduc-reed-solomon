package org.example;

public class TypeChange {
    public void vectorToDecimal(Signal signal){
        signal.value = String.valueOf(Integer.parseInt(signal.value,2));
        signal.type = "decimal";
    }
    public void decimalToVector(Signal signal){
        signal.value = Integer.toBinaryString(Integer.parseInt(signal.value));
        signal.type = "vector";
    }
    public void vectorToElement(Signal signal){
        switch(signal.value){
            case "00001":
                signal.value = "A00"; //A31
            case "00010":
                signal.value = "A01";
            case "00100":
                signal.value = "A02";
            case "01000":
                signal.value = "A03";
            case "10000":
                signal.value = "A04";
            case "00011":
                signal.value = "A18";
            case "00101":
                signal.value = "A05";
            case "01001":
                signal.value = "A29";
            case "10001":
                signal.value = "A10";
            case "11000":
                signal.value = "A21";
            case "10100":
                signal.value = "A07";
            case "10010":
                signal.value = "A30";
            case "00110":
                signal.value = "A19";
            case "01100":
                signal.value = "A20";
            case "01010":
                signal.value = "A06";
            case "00111":
                signal.value = "A11";
            case "01011":
                signal.value = "A27";
            case "10011":
                signal.value = "A17";
            case "11100":
                signal.value = "A13";
            case "11010":
                signal.value = "A09";
            case "01110":
                signal.value = "A12";
            case "10110":
                signal.value = "A28";
            case "11001":
                signal.value = "A25";
            case "10101":
                signal.value = "A22";
            case "01101":
                signal.value = "A08";
            case "01111":
                signal.value = "A23";
            case "10111":
                signal.value = "A26";
            case "11011":
                signal.value = "A16";
            case "11101":
                signal.value = "A14";
            case "11110":
                signal.value = "A24";
            case "11111":
                signal.value = "A15";
            case "00000":
                signal.value = "0"; //A32
        }
        signal.type = "element";
    }
    public void elementToVector(Signal signal){
        switch(signal.value){
            case "0": //A32
                signal.value = "00000"; 
            case "A00": //A31
                signal.value = "00001";
            case "A01":
                signal.value = "00010";
            case "A02":
                signal.value = "00100";
            case "A03":
                signal.value = "01000";
            case "A04":
                signal.value = "10000";
            case "A05":
                signal.value = "00101";
            case "A06":
                signal.value = "01010";
            case "A07":
                signal.value = "10100";
            case "A08":
                signal.value = "01101";
            case "A09":
                signal.value = "11010";
            case "A10":
                signal.value = "10001";
            case "A11":
                signal.value = "00111";
            case "A12":
                signal.value = "01110";
            case "A13":
                signal.value = "11100";
            case "A14":
                signal.value = "11101";
            case "A15":
                signal.value = "11111";
            case "A16":
                signal.value = "11011";
            case "A17":
                signal.value = "10011";
            case "A18":
                signal.value = "00011";
            case "A19":
                signal.value = "00110";
            case "A20":
                signal.value = "11000";
            case "A21":
                signal.value = "11000";
            case "A22":
                signal.value = "10101";
            case "A23":
                signal.value = "01111";
            case "A24":
                signal.value = "11110";
            case "A25":
                signal.value = "11001";
            case "A26":
                signal.value = "10111";
            case "A27":
                signal.value = "01011";
            case "A28":
                signal.value = "10110";
            case "A29":
                signal.value = "01001";
            case "A30":
                signal.value = "10010"; 
        }
        signal.type = "vector";    
    }
}
