package org.example;

public class TypeChange {
    public void vectorToDecimal(Signal signal){
        signal.value = String.valueOf(Integer.parseInt(signal.value,2));
        signal.type = "decimal";
    }
    public void decimalToVector(Signal signal){
        signal.value = Integer.toBinaryString(Integer.parseInt(signal.value));
        if(signal.value.length() < 5){
            for(int i = signal.value.length(); i < 5; i++){
                signal.value = "0" + signal.value;
            }
        }
        signal.type = "vector";
    }
    public void vectorToElement(Signal signal){
        switch(signal.value){
            case "00001":
                signal.value = "A00";break; //A31
            case "00010":
                signal.value = "A01";break;
            case "00100":
                signal.value = "A02";break;
            case "01000":
                signal.value = "A03";break;
            case "10000":
                signal.value = "A04";break;
            case "00011":
                signal.value = "A18";break;
            case "00101":
                signal.value = "A05";break;
            case "01001":
                signal.value = "A29";break;
            case "10001":
                signal.value = "A10";break;
            case "11000":
                signal.value = "A21";break;
            case "10100":
                signal.value = "A07";break;
            case "10010":
                signal.value = "A30";break;
            case "00110":
                signal.value = "A19";break;
            case "01100":
                signal.value = "A20";break;
            case "01010":
                signal.value = "A06";break;
            case "00111":
                signal.value = "A11";break;
            case "01011":
                signal.value = "A27";break;
            case "10011":
                signal.value = "A17";break;
            case "11100":
                signal.value = "A13";break;
            case "11010":
                signal.value = "A09";break;
            case "01110":
                signal.value = "A12";break;
            case "10110":
                signal.value = "A28";break;
            case "11001":
                signal.value = "A25";break;
            case "10101":
                signal.value = "A22";break;
            case "01101":
                signal.value = "A08";break;
            case "01111":
                signal.value = "A23";break;
            case "10111":
                signal.value = "A26";break;
            case "11011":
                signal.value = "A16";break;
            case "11101":
                signal.value = "A14";break;
            case "11110":
                signal.value = "A24";break;
            case "11111":
                signal.value = "A15";break;
            case "00000":
                signal.value = "A32";break; //wartsc 0
        }
        signal.type = "element";
    }
    public void elementToVector(Signal signal){
        switch(signal.value){
            case "0": //A32
                signal.value = "00000"; break;
            case "A00": //A31
                signal.value = "00001";break;
            case "A01":
                signal.value = "00010";break;
            case "A02":
                signal.value = "00100";break;
            case "A03":
                signal.value = "01000";break;
            case "A04":
                signal.value = "10000";break;
            case "A05":
                signal.value = "00101";break;
            case "A06":
                signal.value = "01010";break;
            case "A07":
                signal.value = "10100";break;
            case "A08":
                signal.value = "01101";break;
            case "A09":
                signal.value = "11010";break;
            case "A10":
                signal.value = "10001";break;
            case "A11":
                signal.value = "00111";break;
            case "A12":
                signal.value = "01110";break;
            case "A13":
                signal.value = "11100";break;
            case "A14":
                signal.value = "11101";break;
            case "A15":
                signal.value = "11111";break;
            case "A16":
                signal.value = "11011";break;
            case "A17":
                signal.value = "10011";break;
            case "A18":
                signal.value = "00011";break;
            case "A19":
                signal.value = "00110";break;
            case "A20":
                signal.value = "11000";break;
            case "A21":
                signal.value = "11000";break;
            case "A22":
                signal.value = "10101";break;
            case "A23":
                signal.value = "01111";break;
            case "A24":
                signal.value = "11110";break;
            case "A25":
                signal.value = "11001";break;
            case "A26":
                signal.value = "10111";break;
            case "A27":
                signal.value = "01011";break;
            case "A28":
                signal.value = "10110";break;
            case "A29":
                signal.value = "01001";break;
            case "A30":
                signal.value = "10010"; break;
        }
        signal.type = "vector";    
    }
}
