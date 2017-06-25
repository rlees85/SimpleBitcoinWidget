package com.brentpanther.ripplewidget;

import com.brentpanther.cryptowidget.Ids;
import com.brentpanther.cryptowidget.Prefs;
import com.brentpanther.cryptowidget.WidgetApplication;

/**
 * Created by brentpanther on 5/10/17.
 */

public class RippleApplication extends WidgetApplication {

    private RippleIds ids;

    @Override
    public void onCreate() {
        super.onCreate();
        ids = new RippleIds();
    }

    @Override
    public Ids getIds() {
        return ids;
    }

    @Override
    public Prefs getPrefs(int widgetId) {
        return new RipplePrefs(this, widgetId);
    }
}
