package org.example;

public class Comparator {

    int compare_pol(Polynomial p1, Polynomial p2){
    //porównuje dwa wielomiany:
    //gdy p1==p2 to zwraca wartość 1
    //gdy p1>p2 to zwraca wartość -1
    // gdy p1<p2 to zwraca wartość 1

        //oba wielomiany mają tą samą długość
        if(p1.getPolynomial().length==p2.getPolynomial().length){
            //l - przechowuje wartość pierwszego indeksu "znaczącego" (najwyższa potęga , która różni się w obu wielomianach - lub 0 gdy są równe)
            int l=p1.getPolynomial().length-1;
            //szukanie "znaczącego" indeksu
            while(Integer.parseInt(p1.getPolynomialSignal(l).getValueD())==Integer.parseInt(p2.getPolynomialSignal(l).getValueD())){
                if(l==0)
                    return 0; //są równe
                l--;
            }
            if(Integer.parseInt(p1.getPolynomialSignal(l).getValueD())>Integer.parseInt(p2.getPolynomialSignal(l).getValueD()))
                return -1; //wartość znaczącego indeksu większa w p1
            else
                return 1;  //wartość znaczącego indeksu większa w p2
        } else if (p1.getPolynomial().length>p2.getPolynomial().length)
            return -1;  //p1 większy
        else
            return 1;   //p2 większy
    }
}
