package cz.uhk.util;
/**

 Rozhrani seznamu prvku
 @param <E> typ prvku*/
public interface Seznam<E> extends Iterable<E>{
    void pridej(E prvek);
    void pridej(E prvek,int pozice);
    void smaz(int pozice);
    E vrat(int pozice);
    int pocet();
}