package cz.uhk.merapp;

import cz.uhk.merapp.data.Mereni;

public class Main {
    public static void main(String[] args) {
        Mereni m = new Mereni();

        m.pridejMereni(10.5);
        m.pridejMereni(20);
        m.pridejMereni(15.2);
        m.pridejMereni(30.8);

        System.out.println("Pocet mereni: " + m.pocet());
        System.out.println("Soucet: " + m.soucet());
        System.out.println("Prumer: " + m.prumer());
        System.out.println("Maximum: " + m.max());
        System.out.println("Minimum: " + m.min());

        System.out.println("Druhy prvek (index 1): " + m.get(1));
    }
}