package com.khan.internetconnectivitychecking.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.khan.internetconnectivitychecking.R;
import com.khan.internetconnectivitychecking.others.AppController;
import com.khan.internetconnectivitychecking.others.ConnectivityReceiver;
import com.khan.internetconnectivitychecking.others.Utility;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener {
    private RelativeLayout relativeLayout;
    private WebView webView;
    private ImageView disconnectCableIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialization();

        setConnectivityReceiver();
    }

    private void setConnectivityReceiver() {
        registerReceiver(
                new ConnectivityReceiver(), new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        AppController.getInstance().setConnectivityListener(this);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void webView(String url) {
        webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDisplayZoomControls(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.setWebViewClient(new MainActivity.MyWebViewClient());
    }

    private static class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    private void initialization() {
        relativeLayout = findViewById(R.id.relativeLayoutId);
        webView = findViewById(R.id.webViewId);
        disconnectCableIv = findViewById(R.id.disconnectCableIvId);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        Utility.showSnackBar(relativeLayout, this, isConnected);

        if (isConnected) {
            disconnectCableIv.setVisibility(View.GONE);
            webView.setVisibility(View.VISIBLE);
            webView("https://www.youtube.com/watch?v=-60HcvTQU1o");

        } else {
            webView.setVisibility(View.GONE);
            webView("");
            disconnectCableIv.setVisibility(View.VISIBLE);
        }
    }
}