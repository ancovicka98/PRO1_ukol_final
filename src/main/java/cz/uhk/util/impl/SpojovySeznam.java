package cz.uhk.util.impl;

import cz.uhk.util.Seznam;

import java.util.Iterator;

//genericka trida spojoveho linearniho seznamu
public class SpojovySeznam<E> implements Seznam<E> {
    private PrvekSeznamu<E> prvni, posledni;

    @Override
    public void pridej(E hodnota) {
        var novy = new PrvekSeznamu<E>(hodnota);
        if (prvni == null){
            prvni = posledni = novy;
        }else{
            posledni.dalsi = novy;
            posledni = posledni.dalsi;
        }
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

    @Override
    public void smaz(int pozice) {
        if (pozice == 0){
            prvni = prvni.dalsi;
        }else {
            var predchozi = vratPrvek(pozice - 1);
            if (predchozi != null){
                predchozi.dalsi = predchozi.dalsi.dalsi;
            }
        }

    }

    @Override
    public E vrat(int pozice) {
        PrvekSeznamu<E> prvek = vratPrvek(pozice);
        return (prvek != null) ? prvek.hodnota : null;
    }

    private PrvekSeznamu<E> vratPrvek(int pozice) {
        if (pozice <0){
            return null;
        }
        var pom = prvni;
        for(int i = 0; i < pozice && pom != null; i++){
            pom = pom.dalsi;
        }
        return pom;
    }

    @Override
    public int pocet() {
        var pocet = 0;
        for (var pom = prvni;pom != null; pocet ++){
            pom = pom.dalsi;
        }
        return pocet;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            PrvekSeznamu<E> aktualni = prvni;
            @Override
            public boolean hasNext() {
                return aktualni != null && aktualni.dalsi != null;
            }

            @Override
            public E next() {
                if (aktualni == null)return null;
                aktualni = aktualni.dalsi;
                return (aktualni != null) ? aktualni.hodnota : null;
            }
        };
    }
}

class PrvekSeznamu<T> {
    T hodnota;
    PrvekSeznamu<T> dalsi;

    public PrvekSeznamu(T hodnota) {
        this.hodnota = hodnota;
    }
}

