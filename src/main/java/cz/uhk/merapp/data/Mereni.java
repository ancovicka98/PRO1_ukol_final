package cz.uhk.merapp.data;

import cz.uhk.util.Seznam;
import cz.uhk.util.impl.SpojovySeznam;

public class Mereni {
    private Seznam<Number> data = new SpojovySeznam<>();

    public void pridejMereni(Number m) {
        data.pridej(m);
    }

    public Number get(int index) {
        return data.vrat(index);
    }

    public int pocet() {
        return data.pocet();
    }

    public Number soucet() {
        double suma = 0;
        for (Number n : data) {
            if (n != null) {
                suma += n.doubleValue();
            }
        }
        return suma;
    }

    public Number prumer() {
        if (data.pocet() == 0) return 0;
        return soucet().doubleValue() / data.pocet();
    }

    public Number max() {
        if (data.pocet() == 0) return null;
        Number max = data.vrat(0);
        for (Number n : data) {
            if (n.doubleValue() > max.doubleValue()) {
                max = n;
            }
        }
        return max;
    }

    public Number min() {
        if (data.pocet() == 0) return null;
        Number min = data.vrat(0);
        for (Number n : data) {
            if (n.doubleValue() < min.doubleValue()) {
                min = n;
            }
        }
        return min;
    }
}