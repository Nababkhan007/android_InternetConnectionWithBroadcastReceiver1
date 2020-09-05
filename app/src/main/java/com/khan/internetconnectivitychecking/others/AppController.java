package com.khan.internetconnectivitychecking.others;

import android.app.Application;

public class AppController extends Application {
    private static AppController appController;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
    }

    public static synchronized AppController getInstance() {
        return appController;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener connectivityListener) {
        ConnectivityReceiver.connectivityReceiverListener = connectivityListener;
    }
}
