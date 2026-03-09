package cz.uhk.util.impl;

import cz.uhk.util.Seznam;

public class SpojovySeznam<E> implements Seznam<E> {
    // Reference na začátek a konec řetězce
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
    public void smaz(int pozice) {
        if (pozice < 0 || pozice >= velikost) throw new IndexOutOfBoundsException();

        if (pozice == 0) {
            // Mažeme první prvek
            prvni = prvni.dalsi;
            if (prvni == null) posledni = null;
        } else {
            // Najdeme prvek těsně před tím, který chceme smazat
            PrvekSeznamu<E> predchozi = najdiPrvek(pozice - 1);
            predchozi.dalsi = predchozi.dalsi.dalsi;

            // Pokud jsme smazali poslední, musíme aktualizovat referenci 'posledni'
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

    // Pomocná metoda pro nalezení uzlu na daném indexu
    private PrvekSeznamu<E> najdiPrvek(int pozice) {
        if (pozice < 0 || pozice >= velikost) throw new IndexOutOfBoundsException();
        PrvekSeznamu<E> aktualni = prvni;
        for (int i = 0; i < pozice; i++) {
            aktualni = aktualni.dalsi;
        }
        return aktualni;
    }
}

/**
 * Pomocná třída reprezentující jeden článek řetězce
 */
class PrvekSeznamu<E> {
    E hodnota;
    PrvekSeznamu<E> dalsi;

    public PrvekSeznamu(E hodnota) {
        this.hodnota = hodnota;
    }
}