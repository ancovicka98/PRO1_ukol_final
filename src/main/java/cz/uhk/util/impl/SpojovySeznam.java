package cz.uhk.util.impl;

import cz.uhk.util.Seznam;
import java.util.Iterator;

public class SpojovySeznam<E> implements Seznam<E> {


    private PrvekSeznamu<E> prvni;
    private PrvekSeznamu<E> posledni;
    private int velikost = 0;

    @Override
    public void pridej(E prvek) {
        PrvekSeznamu<E> novy = new PrvekSeznamu<>(prvek);
        if (prvni == null) {
            prvni = novy;
            posledni = novy;
        } else {
            posledni.dalsi = novy;
            posledni = novy;
        }
        velikost++;
    }

    @Override
    public void pridej(E hodnota, int pozice) {
        var novy = new PrvekSeznamu<E>(hodnota);
        if (prvni == null){ // pokud nemame prvni prvek, nas novy prvek je zacatek i konec - novy seznam
            prvni = posledni = novy;
        }else if(pozice <= 0){  // pokud je pozice mensi rovna nule - dame prvek na zacatek
            novy.dalsi = prvni;
            prvni = novy;
        }else if (pozice >= pocet()){       // pokud je pozice vetsi rovna poctu - dame prvek na konec
            posledni.dalsi = novy;
            posledni = posledni.dalsi;
        }else{      // jinak vlozime prvek do seznamu
            var predchozi = vratPrvek(pozice - 1);
            if (predchozi != null) {
                novy.dalsi = predchozi.dalsi;
                predchozi.dalsi = novy;
            }
        }
    }


    }
    @Override
    public void smaz(int pozice) {
        if (pozice < 0 || pozice >= velikost) throw new IndexOutOfBoundsException();

        if (pozice == 0) {
            prvni = prvni.dalsi;
            if (prvni == null) posledni = null;
        } else {
            PrvekSeznamu<E> predchozi = najdiPrvek(pozice - 1);
            predchozi.dalsi = predchozi.dalsi.dalsi;
            if (predchozi.dalsi == null) {
                posledni = predchozi;
            }
        }
        velikost--;
    }

    @Override
    public E vrat(int pozice) {
        return najdiPrvek(pozice).hodnota;
    }

    @Override
    public int pocet() {
        return velikost;
    }
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private PrvekSeznamu<E> aktualni = prvni;

            @Override
            public boolean hasNext() {
                return aktualni != null;
            }

            @Override
            public E next() {
                E hodnota = aktualni.hodnota;
                aktualni = aktualni.dalsi;
                return hodnota;
            }
        };
    }


    private PrvekSeznamu<E> najdiPrvek(int pozice) {
        PrvekSeznamu<E> aktualni = prvni;
        for (int i = 0; i < pozice; i++) {
            aktualni = aktualni.dalsi;
        }
        return aktualni;
    }
}


class PrvekSeznamu<E> {
    E hodnota;
    PrvekSeznamu<E> dalsi;

    public PrvekSeznamu(E hodnota) {
        this.hodnota = hodnota;
    }
}
