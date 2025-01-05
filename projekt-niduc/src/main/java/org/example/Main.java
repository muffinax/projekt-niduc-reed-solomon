package org.example;


public class Main {
    public static void main(String[] args) {

        Coder coder = new Coder();
        Encoder encoder = new Encoder();
        Polynomial poly1 = new Polynomial(new String[] {"A12","A09","A00"}, "element");
        Polynomial poly2 = new Polynomial(new String[] {/*"A02",*/"A03"}, "element");
        Polynomial poly3 = new Polynomial(new String[] {"A22","A05","A00"}, "element");
        Polynomial polyt = new Polynomial(new String[] {"A32","A32","A32","A32","A32","A32","A20"}, "element");
        Generator generator= new Generator();
        Polynomial generator_poly = new Polynomial(generator.polynomial_generator());
        MathPolynomials mathPolynomials=new MathPolynomials();

        //Coder.coder();

        //poly1.show_polynomial();
        //System.out.println();
        //poly2.show_polynomial();
        //System.out.println();

        //PRZYKŁAD DLA NIKODEMA
        //p(x) = x^2 + 2x + 3
        Polynomial p = new Polynomial(new String[] {"3", "2", "1"}, "decimal");
        System.out.print("Kodowany wielomian: ");
        p.show_polynomial();
        System.out.println();
        //zakodowany wielomian p(x)
        Polynomial encP = encoder.encode(p);
        System.out.print("Zakodowany wielomian: ");
        encP.show_polynomial();
        System.out.println();
        //zakłamanie wielomianu - dodanie bita w częsci informacyjnej
        encP = mathPolynomials.addPolynomials(
                encP,
                new Polynomial(
                new String[]{"0","0","0","0","0","0","0","0","0","0","0","0","1"},"decimal"));
        System.out.print("Zakłamany wielomian: ");
        encP.show_polynomial();
        System.out.println();
        //dekodowanie wielomianu
        Polynomial decP = encoder.simpleDecoder(p);
        System.out.print("Odkodowany wielomian: ");
        decP.show_polynomial();
        System.out.println();


        //Polynomial poly4 = new Polynomial(new String[] {/*"A02",*/"30"}, "decimal");
        /*Polynomial polysum = mathPolynomials.add(poly2,poly4);
        poly2.show_polynomial();
        poly4.show_polynomial();
        polysum.show_polynomial();*/
        //TEST PROSTEGO DEKODERA
        /*System.out.println("Generator:");
        generator_poly.show_polynomial();
        System.out.println("Kodowany wielomian: generator + 1 na najmniejszeej pot");
        //poly2.show_polynomial();
        Polynomial enc2 = encoder.encode(mathPolynomials.addPolynomials(generator_poly, new Polynomial(new String[] {"A30"}, "element")));
        System.out.println("\nZakodowany wielomian:");
        enc2.show_polynomial();
        enc2 = mathPolynomials.addPolynomials(enc2, new Polynomial(
                new String[]{"A32","A32","A32","A32","A32","A32","A32","A32","A32","A32","A32","A32","A00"},"element"));
        System.out.println("\nPrzekłamany:");
        enc2.show_polynomial();
        Polynomial dec2 = encoder.simpleDecoder(enc2);
        System.out.println("\nOdkodowany wielomian:");
        dec2.show_polynomial();*/




        //mathPolynomials.moduloPol(generator_poly,poly2).show_polynomial();
        //System.out.println();

        //generator.polynomial_generator().show_polynomial();
        //System.out.println();

//        Signal s20 =new Signal("A20","element");
//        Signal s00 =new Signal("A32","element");
//        System.out.println(mathPolynomials.addition(s00,s20).getValueE());
//
//        mathPolynomials.mulPolynomials(poly2,polyt).show_polynomial();

//        String[] poly1coded = encoder.encode(poly1);
        //for(int i = 0; i < poly1coded.length; i++){
        //    System.out.println(poly1coded[i]);
        //}
    }
}