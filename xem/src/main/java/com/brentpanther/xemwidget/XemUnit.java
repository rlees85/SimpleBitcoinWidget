package com.brentpanther.xemwidget;

import com.brentpanther.cryptowidget.Unit;

/**
 * Created by brentpanther on 5/10/17.
 */

public enum XemUnit implements Unit {

    XEM;

    @Override
    public double adjust(String amount) {
        return Double.valueOf(amount);
    }
}
