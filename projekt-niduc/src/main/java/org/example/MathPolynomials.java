package org.example;
import java.util.Arrays;

import static java.lang.Math.pow;

public class MathPolynomials {
    //KOLEJNOŚĆ METOD:
    //1. comparePol(p1,p2) - porównuje dwa wielomiany; zwraca int {-1,0,1}
    //2. addition(s1,s2) - dodawanie sygnałów; zwraca Signal
    //3. multiplication(s1,s2) - mnożenie sygnałów; zwraca Signal
    //4. addPolynomials(p1,p2) - dodawanie wielomianów; zwraca Polynomial
    //5. mulPolynomials(p1,p2) - mnożenie wielomianów; zwraca Polynomial
    //6. moduloPol(p1,p2) - oblicza resztę z dzielenia dwóch wielpmianu p1 przez p2; zwraca Polynomial

    //-------------------------------------------------------------------------------------------------
    //1. PORÓWNANIE WIELOMIANÓW
    int comparePol(Polynomial p1, Polynomial p2){
        //gdy p1==p2 to zwraca wartość 0
        //gdy p1>p2 to zwraca wartość -1
        // gdy p1<p2 to zwraca wartość 1
        int l = p2.getPolynomial().length-1;

        if(p1.getPolynomial().length!=p2.getPolynomial().length){
            if(p1.getPolynomial().length>p2.getPolynomial().length){
                for(int i=p1.getPolynomial().length-1;i>=p2.getPolynomial().length;i--){
                    if(Integer.parseInt(p1.getPolynomialSignal(i).getValueD())>0)   return -1;
                }
            }
            else{
                for(int i=p2.getPolynomial().length-1;i>=p1.getPolynomial().length;i--){
                    if(Integer.parseInt(p2.getPolynomialSignal(i).getValueD())>0)   return 1;
                }
                l=p1.getPolynomial().length-1;
            }
        }

        //szukanie największego indeksu o różnej wartości
        while(Integer.parseInt(p1.getPolynomialSignal(l).getValueD())==Integer.parseInt(p2.getPolynomialSignal(l).getValueD())){
            if(l==0)    return 0; //są równe
            l--;
        }

        //porównując wartości w najwyższej potędze, która się różni, możemy określić, który wilomian jest większy
        if(Integer.parseInt(p1.getPolynomialSignal(l).getValueD())>Integer.parseInt(p2.getPolynomialSignal(l).getValueD()))
            return -1; //wartość znaczącego indeksu większa w p1
        else
            return 1;  //wartość znaczącego indeksu większa w p2
    }

    //-------------------------------------------------------------------------------------------------
    //2. DODAWANIE SYGNALÓW
    public Signal addition (Signal first, Signal second){
        //TypeChange typeChange=new TypeChange();

        String a, b, c, d, e;
        if(first.getValueV().charAt(0) == second.getValueV().charAt(0))
            e = "0";
        else
            e = "1";
        if(first.getValueV().charAt(1) == second.getValueV().charAt(1))
            d = "0";
        else
            d = "1";
        if(first.getValueV().charAt(2) == second.getValueV().charAt(2))
            c = "0";
        else
            c = "1";
        if(first.getValueV().charAt(3) == second.getValueV().charAt(3))
            b = "0";
        else
            b = "1";
        if(first.getValueV().charAt(4) == second.getValueV().charAt(4))
            a = "0";
        else
            a = "1";

        return new Signal(e+d+c+b+a, "vector");
    }

    //-------------------------------------------------------------------------------------------------
    //3. MNOŻENIE SYGNALÓW
    public Signal multiplication (Signal first, Signal second){

        int fir = Integer.parseInt(String.valueOf(first.getValueE().charAt(2))) + 10 *Integer.parseInt(String.valueOf(first.getValueE().charAt(1)));
        int sec = Integer.parseInt(String.valueOf(second.getValueE().charAt(2))) + 10 *Integer.parseInt(String.valueOf(second.getValueE().charAt(1)));
        if (fir == 32 || sec == 32) return new Signal("A32","element");
        String wynik;
        if((fir + sec)%31 < 10)
            wynik = "0" + String.valueOf((fir + sec)%31);
        else
            wynik = String.valueOf((fir + sec)%31);
        return new Signal("A" + wynik, "element");
    }

    //-------------------------------------------------------------------------------------------------
    //4. DODAWANIE WIELOMIANÓW
    Polynomial addPolynomials(Polynomial p1, Polynomial p2){
        int l;  //l1 - dłuższy wielomian
        String[] result;

        if(p1.getPolynomial().length>p2.getPolynomial().length){
            result = new String[p1.getPolynomial().length];
            for(int i=0;i<p1.getPolynomial().length;i++){
                if(i<p2.getPolynomial().length)   result[i]=addition(p1.getPolynomialSignal(i),p2.getPolynomialSignal(i)).getValueV();
                else result[i]=p1.getPolynomialSignal(i).getValueV();
            }
        }
        else {
            result = new String[p2.getPolynomial().length];
            for(int i=0;i<p2.getPolynomial().length;i++){
                if(i<p1.getPolynomial().length)   result[i]=addition(p1.getPolynomialSignal(i),p2.getPolynomialSignal(i)).getValueV();
                else result[i]=p2.getPolynomialSignal(i).getValueV();
            }
        }

        return new Polynomial(result,"vector");
    }

    //-------------------------------------------------------------------------------------------------
    //5. MNOŻENIE WIELOMIANÓW
    Polynomial mulPolynomials(Polynomial p1, Polynomial p2){
        //długość tablicy to suma długości mnożonych wielomianów
        String[] value = new String[p1.getPolynomial().length+p2.getPolynomial().length-1];

        //zerowanie początkowej tablicy wyniku
        Arrays.fill(value, "A32");

        Polynomial result = new Polynomial(value,"element");

        for(int i=0;i<p1.getPolynomial().length;i++){
            for(int j=0;j<p2.getPolynomial().length;j++) {

//                  testowanie
//                System.out.println("p1["+i+"]   *   p2["+j+"]");
//                System.out.println(result.getPolynomialSignal(i+j).getValueE()+" + "+p1.getPolynomialSignal(i).getValueE()+ " * "+ p2.getPolynomialSignal(j).getValueE());

                result.getPolynomialSignal(i+j).setValue(addition(result.getPolynomialSignal(i+j), multiplication(p1.getPolynomialSignal(i), p2.getPolynomialSignal(j))).getValueE(),"element");
//                  testowanie
//                System.out.println(result.getPolynomialSignal(i+j).getValueE());
//                System.out.println();
            }
        }
        return result;
    }

    //-------------------------------------------------------------------------------------------------
    //6. OBLICZANIE RESZTY Z DZIELENIA p1 PRZEZ p2 (p1%p2)
    //!!! p2 MUSI MIEC WARTOSC SYGNALU 1 DLA x O NAJWIĘKSZEJ POTĘDZE (p2[length-1]==1) !!!

    Polynomial moduloPol(Polynomial p1,Polynomial p2){
        //sprawdza, czy dzielnik jest poprawny
        if(p2.getPolynomialSignal((p2.getPolynomial().length)-1).getValueD().equals("1")){
            //gdy są takie same reszta = 0
            if(comparePol(p1,p2)==0){
                return new Polynomial(new String[]{"0"},"decimal");
            }
            //gdy p1<p2 reszta=p1
            else if (comparePol(p1,p2)==1) {
                return p1;
            }
            //gdy p1>p2 -> należy obliczyć resztę
            else{
                Polynomial result;      //wynik częściowego dzielenia
                String[] sresult;       //wynik częściowego dzielenia w typie String

                //warunek oblicza maksymalną potęgę x potrzebną do dzielenia
                for(int i=p1.getPolynomial().length-p2.getPolynomial().length;i>=0;i--){
                    sresult=new String[i+1];    //tablica ma długość znaczącego x

                    Polynomial test=new Polynomial();

                    //nadajemy wartość sresult i result wartosc x do potegi dzielnika
                    for (int j=0;j<i;j++)   sresult[j]="0";
                    sresult[i]=p1.getPolynomialSignal(p1.getPolynomial().length-1).getValueD();
                    result = new Polynomial(sresult,"decimal");

                    //sumuje największy możliwy element - w ten sposób wielomian się zmniejsza
                    if(sresult.length+p2.getPolynomial().length-1==p1.getPolynomial().length)
                        p1=addPolynomials(p1,mulPolynomials(result,p2));

                }
                return p1;
            }
        }
        else{
            System.out.println("Dzielnik musi miec wartosc sygnalu 1, przy najwyzszej potedze - zwracana jest wartosc dzielnika");
            return p2;
        }
    }
}
