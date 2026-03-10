package cz.uhk.util.impl;

import cz.uhk.util.Seznam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpojovySeznamTest {
    Seznam<Integer> seznam;

    @BeforeEach
    void setupData() {
        seznam = new SpojovySeznam<>();
    }

    @Test
    void pridej() {
        initData();
        assertEquals(4, seznam.pocet());
        seznam.pridej(25,3);
        assertEquals(5, seznam.pocet());
        assertEquals(25, seznam.vrat(3));
        seznam.pridej(55,5);
        assertEquals(6, seznam.pocet());
        assertEquals(55, seznam.vrat(5));
        seznam.pridej(55,-1);
        assertEquals(7, seznam.pocet());
        assertEquals(55, seznam.vrat(0));
        seznam.pridej(100,12);
        assertEquals(8, seznam.pocet());
        assertEquals(100, seznam.vrat(7));
    }

    @Test
    void smaz() {
        initData();
        seznam.smaz(1);
        assertEquals(3, seznam.pocet());
        assertEquals(30, seznam.vrat(1));
    }

    private void initData() {
        seznam.pridej(10);
        seznam.pridej(20);
        seznam.pridej(30);
        seznam.pridej(40);
    }

    @Test
    void vrat() {
        initData();

        assertEquals(10, seznam.vrat(0));
        assertEquals(20, seznam.vrat(1));
        assertEquals(40, seznam.vrat(seznam.pocet()-1));
        assertNull(seznam.vrat(100), "Pozice mimo rozsah");
        assertNull(seznam.vrat(-100), "Pozice mimo rozsah");


    }

    @Test
    @DisplayName("Pocet prvku")
    void pocet() {
        assertEquals(0,seznam.pocet(), "pocet - prazdny seznam");
        initData();
        assertEquals(4,seznam.pocet(), "pocet - 2 prvky");
    }
}
