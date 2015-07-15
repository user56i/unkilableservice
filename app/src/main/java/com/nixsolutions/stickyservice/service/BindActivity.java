package com.nixsolutions.stickyservice.service;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nixsolutions.stickyservice.log.Logg;

public class BindActivity<T extends StickyService> extends AppCompatActivity {

    private static final String TAG = BindActivity.class.getName();
    private ServiceManager<T> serviceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        serviceManager = new ServiceManager<T>(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Logg.d(TAG, "Establishing connection");
        serviceManager.bindService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        serviceManager.unbindService();
    }

    public ServiceManager<T> getServiceManager() {
        return serviceManager;
    }
}
