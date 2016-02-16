package com.nsd1.foxconn.galaxy7_provision;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by nsd1 on 2016/2/15.
 */
public class ProvisionService extends Service{

    private ProvisionBinder mBinder = new ProvisionBinder();
    class ProvisionBinder extends Binder {
        public void startDownload() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("MyService", "startDownload executed");
                }
            }).start();
        }
        public void getProgress() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.d("MyService", "getProgress executed");
                }
            }).start();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
        // return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service Created...", Toast.LENGTH_LONG).show();
        super.onCreate();
        Log.d("MyService", "onCreate executed");
    }
    @Override
    public void onDestroy() {
        Toast.makeText(this,"Service Destroyed...", Toast.LENGTH_LONG).show();
        super.onDestroy();
        Log.d("MyService", "onDestroy executed");
    }
}
