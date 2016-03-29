package com.naveen.netops;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    WifiManager wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        final ListView networkListView = (ListView)findViewById(R.id.listView_networks);
        final TextView chooseNetworkTextView = (TextView)findViewById(R.id.textView_choose_network);
        chooseNetworkTextView.setVisibility(View.INVISIBLE);


        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context c, Intent intent) {
                List<String> networkList = new ArrayList<String>();
                List<ScanResult> results = wifi.getScanResults();
                for (ScanResult result : results) {
                    networkList.add(result.SSID);
                }
                chooseNetworkTextView.setVisibility(View.VISIBLE);
                networkListView.setAdapter(new ArrayAdapter<String>(
                        getApplicationContext(), R.layout.row_network, networkList));
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    public void showNetworks(View view) {
        wifi.startScan();
    }
}
