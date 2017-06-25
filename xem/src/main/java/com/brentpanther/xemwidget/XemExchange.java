package com.brentpanther.xemwidget;

import com.brentpanther.cryptowidget.Exchange;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.brentpanther.cryptowidget.ExchangeHelper.getJSONObject;

/**
 * Created by brentpanther on 5/10/17.
 */

enum XemExchange implements Exchange {

    BTER(R.array.currencies_bter, "bter") {
        @Override
        public String getValue(String currencyCode) throws Exception {
            String url = "http://data.bter.com/api2/1/ticker/xem_cny";
            return getJSONObject(url).getString("last");
        }
    },
    HITBTC(R.array.currencies_hitbtc, "hitbtc") {
        @Override
        public String getValue(String currencyCode) throws Exception {
            JSONObject obj = getJSONObject("https://api.hitbtc.com/api/1/public/XEMBTC/ticker");
            double dXEMVAL = Double.parseDouble(obj.getString("last"));
            double dBTCVAL = BTC_to_FIAT(currencyCode);

            double dXEMFIAT = dXEMVAL * dBTCVAL;
            return String.valueOf(dXEMFIAT);
        }
    },
    POLONIEX(R.array.currencies_poloniex, "poloniex") {
        @Override
        public String getValue(String currencyCode) throws Exception {
            JSONObject obj = getJSONObject("https://poloniex.com/public?command=returnTicker");
            double dXEMVAL = Double.parseDouble(obj.getJSONObject("BTC_XEM").getString("last"));
            double dBTCVAL = BTC_to_FIAT(currencyCode);

            double dXEMFIAT = dXEMVAL * dBTCVAL;
            return String.valueOf(dXEMFIAT);
        }
    };

    private final int currencyArrayID;
    private String label;

    XemExchange(int currencyArrayID, String label) {
        this.currencyArrayID = currencyArrayID;
        this.label = label;
    }

    public double BTC_to_FIAT(String currencyCode) throws Exception {
        JSONObject obj = getJSONObject(String.format("https://api.coinbase.com/v2/prices/BTC-%s/spot", currencyCode));
        return Double.parseDouble(obj.getJSONObject("data").getString("amount"));
    }

    @Override
    public String getValue(String currencyCode) throws Exception {
        return null;
    }

    @Override
    public int getCurrencies() {
        return currencyArrayID;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
