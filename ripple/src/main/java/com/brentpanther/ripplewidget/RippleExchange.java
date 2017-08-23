package com.brentpanther.ripplewidget;

import com.brentpanther.cryptowidget.Exchange;

import org.json.JSONObject;

import static com.brentpanther.cryptowidget.ExchangeHelper.getJSONObject;

/**
 * Created by brentpanther on 5/10/17.
 */

enum RippleExchange implements Exchange {

    BITFINEX(R.array.currencies_bitfinex, "bitfinex") {
        @Override
        public String getValue(String currencyCode) throws Exception {
            JSONObject obj = getJSONObject("https://api.bitfinex.com/v1/ticker/xrpbtc");
            double dXRPVAL = Double.parseDouble(obj.getString("last_price"));
            double dBTCVAL = BTC_to_FIAT(currencyCode);
            double dXRPFIAT = dXRPVAL * dBTCVAL;
            return String.valueOf(dXRPFIAT);
        }
    },
    BTER(R.array.currencies_bter, "bter") {
        @Override
        public String getValue(String currencyCode) throws Exception {
            String url = "https://data.bter.com/api2/1/ticker/xrp_cny";
            return getJSONObject(url).getString("last");
        }
    },
    KRAKEN(R.array.currencies_kraken, "kraken") {
        @Override
        public String getValue(String currencyCode) throws Exception {
            JSONObject obj = getJSONObject(String.format("https://api.kraken.com/0/public/Ticker?pair=XRP%s", currencyCode));
            JSONObject obj2 = obj.getJSONObject("result").getJSONObject("XXRPZ" + currencyCode);
            return (String)obj2.getJSONArray("c").get(0);
        }
    },
    POLONIEX(R.array.currencies_poloniex, "poloniex") {
        @Override
        public String getValue(String currencyCode) throws Exception {
            JSONObject obj = getJSONObject("https://poloniex.com/public?command=returnTicker");
            double dXRPVAL = Double.parseDouble(obj.getJSONObject("BTC_XRP").getString("last"));
            double dBTCVAL = BTC_to_FIAT(currencyCode);

            double dXRPFIAT = dXRPVAL * dBTCVAL;
            return String.valueOf(dXRPFIAT);
        }
    };

    private final int currencyArrayID;
    private String label;

    RippleExchange(int currencyArrayID, String label) {
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
