package org.example;

public class Signal {
    TypeChange typeChange=new TypeChange();
    private String valueV;  //vector
    private String valueE;  //element
    private String valueD;  //decimal

    Signal(){
        valueV="00000";
        valueE="A32";
        valueD="0";
    }
    public Signal(String value, String type){
        if(type.equals("decimal")){
            valueD=value;
            valueE=typeChange.vectorToElement(typeChange.decimalToVector(value));
            valueV=typeChange.decimalToVector(value);
        } else if (type.equals("vector")) {
            valueV=value;
            valueD=typeChange.vectorToDecimal(value);
            valueE=typeChange.vectorToElement(value);
        } else if (type.equals("element")) {
            valueE=value;
            valueV=typeChange.elementToVector(value);
            valueD=typeChange.vectorToDecimal(typeChange.elementToVector(value));
        }
        else{
            System.out.println("wrong type of signal");
            valueV="00000";
            valueE="A32";
            valueD="0";
        }
    }
    void setValue(String value, String type){
        if(type.equals("decimal")){
            valueD=value;
            valueE=typeChange.vectorToElement(typeChange.decimalToVector(value));
            valueV=typeChange.decimalToVector(value);
        } else if (type.equals("vector")) {
            valueV=value;
            valueD=typeChange.vectorToDecimal(value);
            valueE=typeChange.vectorToElement(value);
        } else if (type.equals("element")) {
            valueE=value;
            valueV=typeChange.elementToVector(value);
            valueD=typeChange.vectorToDecimal(typeChange.elementToVector(value));
        }
        else{
            System.out.println("wrong type of signal - can't change values");
        }
    }

    public String getValueV(){return valueV;}
    public String getValueD(){return valueD;}
    public String getValueE(){return valueE;}
}
