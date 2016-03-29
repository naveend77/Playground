package com.naveen.netops;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    WifiManager wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
    }

    public void chooseNetwork(View view) {
        wifi.startScan();
        List<ScanResult> results = wifi.getScanResults();
        for (ScanResult result : results) {
            Toast.makeText(this, result.SSID,  Toast.LENGTH_SHORT).show();
        }
    }
}
