package org.example;

public class TypeChange {
    public String vectorToDecimal(String signal){
        signal = String.valueOf(Integer.parseInt(signal,2));
        return signal;
    }
    public String decimalToVector(String signal){
        signal = Integer.toBinaryString(Integer.parseInt(signal));
        if(signal.length() < 5){
            for(int i = signal.length(); i < 5; i++){
                signal = "0" + signal;
            }
        }
        return signal;
    }
    public String vectorToElement(String signal){
        switch(signal){
            case "00001":
                signal = "A00";break; //A31
            case "00010":
                signal = "A01";break;
            case "00100":
                signal = "A02";break;
            case "01000":
                signal = "A03";break;
            case "10000":
                signal = "A04";break;
            case "00011":
                signal = "A18";break;
            case "00101":
                signal = "A05";break;
            case "01001":
                signal = "A29";break;
            case "10001":
                signal = "A10";break;
            case "11000":
                signal = "A21";break;
            case "10100":
                signal = "A07";break;
            case "10010":
                signal = "A30";break;
            case "00110":
                signal = "A19";break;
            case "01100":
                signal = "A20";break;
            case "01010":
                signal = "A06";break;
            case "00111":
                signal = "A11";break;
            case "01011":
                signal = "A27";break;
            case "10011":
                signal = "A17";break;
            case "11100":
                signal = "A13";break;
            case "11010":
                signal = "A09";break;
            case "01110":
                signal = "A12";break;
            case "10110":
                signal = "A28";break;
            case "11001":
                signal = "A25";break;
            case "10101":
                signal = "A22";break;
            case "01101":
                signal = "A08";break;
            case "01111":
                signal = "A23";break;
            case "10111":
                signal = "A26";break;
            case "11011":
                signal = "A16";break;
            case "11101":
                signal = "A14";break;
            case "11110":
                signal = "A24";break;
            case "11111":
                signal = "A15";break;
            case "00000":
                signal = "A32";break; //wartsc 0
        }
        return signal;
    }
    public String elementToVector(String signal){
        switch(signal){
            case "A32": //0
                signal = "00000"; break;
            case "A00": //A31
                signal = "00001";break;
            case "A01":
                signal = "00010";break;
            case "A02":
                signal = "00100";break;
            case "A03":
                signal = "01000";break;
            case "A04":
                signal = "10000";break;
            case "A05":
                signal = "00101";break;
            case "A06":
                signal = "01010";break;
            case "A07":
                signal = "10100";break;
            case "A08":
                signal = "01101";break;
            case "A09":
                signal = "11010";break;
            case "A10":
                signal = "10001";break;
            case "A11":
                signal = "00111";break;
            case "A12":
                signal = "01110";break;
            case "A13":
                signal = "11100";break;
            case "A14":
                signal = "11101";break;
            case "A15":
                signal = "11111";break;
            case "A16":
                signal = "11011";break;
            case "A17":
                signal = "10011";break;
            case "A18":
                signal = "00011";break;
            case "A19":
                signal = "00110";break;
            case "A20":
                signal = "01100";break;
            case "A21":
                signal = "11000";break;
            case "A22":
                signal = "10101";break;
            case "A23":
                signal = "01111";break;
            case "A24":
                signal = "11110";break;
            case "A25":
                signal = "11001";break;
            case "A26":
                signal = "10111";break;
            case "A27":
                signal = "01011";break;
            case "A28":
                signal = "10110";break;
            case "A29":
                signal = "01001";break;
            case "A30":
                signal = "10010"; break;
        }
        return signal;
    }
}
