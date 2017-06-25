package com.brentpanther.xemwidget;

import com.brentpanther.cryptowidget.Ids;
import com.brentpanther.cryptowidget.Prefs;
import com.brentpanther.cryptowidget.WidgetApplication;

/**
 * Created by brentpanther on 5/10/17.
 */

public class XemApplication extends WidgetApplication {

    private XemIds ids;

    @Override
    public void onCreate() {
        super.onCreate();
        ids = new XemIds();
    }

    @Override
    public Ids getIds() {
        return ids;
    }

    @Override
    public Prefs getPrefs(int widgetId) {
        return new XemPrefs(this, widgetId);
    }
}
