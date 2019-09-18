package com.hxh.dn_netframework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hxh.dn_netframework.http.DNNetFramework;
import com.hxh.dn_netframework.http.IDataListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        String url = "http://59.110.162.30/app_updater_version.json";
        DNNetFramework.sendJsonRequest(null, url, AppVersion.class, new IDataListener<AppVersion>() {
            @Override
            public void onSuccess(AppVersion appVersion) {
                Toast.makeText(MainActivity.this, "onSuccess:" + appVersion.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure() {
                Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
