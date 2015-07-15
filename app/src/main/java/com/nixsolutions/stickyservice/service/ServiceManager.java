package com.nixsolutions.stickyservice.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.nixsolutions.stickyservice.log.Logg;

import java.lang.reflect.ParameterizedType;

public class ServiceManager<T extends StickyService> implements ServiceConnection {

    private static final String TAG = ServiceManager.class.getName();

    private boolean connected;

    private BindActivity mActivity;
    private T service;
    private Class<T> serviceClass;

    public ServiceManager(BindActivity activity) {
        mActivity = activity;

        try {
            serviceClass = getGenericClass(mActivity);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void bindService() {
        Intent intent = new Intent(mActivity.getApplicationContext(), serviceClass);

        mActivity.bindService(intent, this, Context.BIND_AUTO_CREATE
                | Context.BIND_ABOVE_CLIENT
                | Context.BIND_IMPORTANT);
    }

    @SuppressWarnings("unchecked")
    public Class<T> getGenericClass(BindActivity mActivity) throws ClassNotFoundException {

        if (serviceClass == null) {
            serviceClass = (Class<T>) ((ParameterizedType) mActivity.getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
        }

        return serviceClass;
    }

    public void unbindService() {
        mActivity.unbindService(this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void onServiceConnected(ComponentName name, IBinder binder) {
        service = (T) ((StickyService.Binder) binder).getService();
        connected = true;

        Logg.d(TAG, "Service connected");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        connected = false;

        Logg.d(TAG, "Service disconnected");
    }

    public boolean isConnected() {
        return connected;
    }

    public T getService() {
        return service;
    }

    public boolean isForeground() {
        return isConnected() && service.isForeground();
    }

    public void startService() {
        mActivity.startService(new Intent(mActivity.getApplicationContext(), serviceClass));
    }

    public void stopService() {
        mActivity.stopService(new Intent(mActivity.getApplicationContext(), serviceClass));
    }
}
