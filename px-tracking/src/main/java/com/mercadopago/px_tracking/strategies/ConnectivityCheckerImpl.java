package com.mercadopago.px_tracking.strategies;

import android.content.Context;

public class ConnectivityCheckerImpl implements ConnectivityChecker {
    public ConnectivityCheckerImpl(Context context) {

    }

    @Override
    public boolean hasWifiConnection() {
        return true;
    }
}
