package com.nsd1.foxconn.galaxy7_provision;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button bindService;
    private Button unbindService;

    private ProvisionService.ProvisionBinder provisionBinder;

    private ServiceConnection connection = new ServiceConnection(){
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            provisionBinder = (ProvisionService.ProvisionBinder) service;
            provisionBinder.startDownload();
            Toast toast1 = Toast.makeText(MainActivity.this,"Start Download...", Toast.LENGTH_LONG);
            toast1.show();
            provisionBinder.getProgress();
            Toast toast2 = Toast.makeText(MainActivity.this,"Get Progress...", Toast.LENGTH_LONG);
            toast2.show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindService = (Button) findViewById(R.id.bind_service);
        unbindService = (Button) findViewById(R.id.unbind_service);

        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bind_service:
                Intent bindIntent = new Intent(this, ProvisionService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE); // 绑定服務
                break;
            case R.id.unbind_service:
                unbindService(connection); // 解绑服務
                break;
            default:
                break;
        }
    }

}
