package cz.uhk.util.impl;

import cz.uhk.util.Seznam;
import java.util.Iterator;

public class SpojovySeznam<E> implements Seznam<E> {


    private PrvekSeznamu<E> prvni;
    private PrvekSeznamu<E> posledni;
    private int velikost = 0; // Musíš mít tento atribut, aby image_8a51d2.png fungoval

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
    public void smaz(int pozice) {
        // Kontrola mezí z tvého screenshotu
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
            // Začneme od prvního prvku
            private PrvekSeznamu<E> aktualni = prvni;

            @Override
            public boolean hasNext() {
                // Máme další prvek, pokud aktuální není null
                return aktualni != null;
            }

            @Override
            public E next() {
                // Uložíme si hodnotu, kterou budeme vracet
                E hodnota = aktualni.hodnota;
                // Posuneme se na další prvek v řetězci
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
    E hodnota; // [cite: 27]
    PrvekSeznamu<E> dalsi; // [cite: 27]

    public PrvekSeznamu(E hodnota) {
        this.hodnota = hodnota;
    }
}
