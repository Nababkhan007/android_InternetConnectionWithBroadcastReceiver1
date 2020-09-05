package com.khan.internetconnectivitychecking.others;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.khan.internetconnectivitychecking.R;

public class Utility {
    @SuppressLint("StaticFieldLeak")
    public static void showSnackBar(View view, Context context,  boolean isConnected) {
        CharSequence message;

        if (isConnected) {
            message = context.getText(R.string.online_text);

        } else {
            message = context.getText(R.string.offline_text);
        }

        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
