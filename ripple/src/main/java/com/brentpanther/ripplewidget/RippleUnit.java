package com.brentpanther.ripplewidget;

import com.brentpanther.cryptowidget.Unit;

/**
 * Created by brentpanther on 5/10/17.
 */

public enum RippleUnit implements Unit {

    XRP;

    @Override
    public double adjust(String amount) {
        return Double.valueOf(amount);
    }
}
