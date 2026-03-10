package cz.uhk.util;

public interface Seznam<E> extends Iterable<E> {
    void pridej(E prvek);
    void smaz(int pozice);
    E vrat(int pozice);
    int pocet();
}
