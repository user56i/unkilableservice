package com.nixsolutions.stickyservice.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.nixsolutions.stickyservice.NotificationUtils;
import com.nixsolutions.stickyservice.log.Logg;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;

public class StickyService extends Service {

    private static final String TAG = StickyService.class.getName();
    private static final int FOREGROUND_ID = 10;

    private boolean foreground;
    private boolean intentStarted;

    public StickyService() {
        intentStarted = false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    public class Binder extends android.os.Binder {
        public StickyService getService() {
            return StickyService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logg.d(TAG, "onStartCommand " + startId + " | flags " + flags);
        intentStarted = true;
        return START_STICKY;
    }

    protected void startForeground() {
        startForeground(FOREGROUND_ID, NotificationUtils.getForeground(getApplicationContext()));
        foreground = true;
        Logg.d(TAG, "startForeground");
    }

    protected void stopForeground() {
        Logg.d(TAG, "stopForeground");
        stopForeground(true);
        foreground = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logg.d(TAG, "onDestroy");
    }

    public boolean isForeground() {
        return foreground;
    }

    public boolean isIntentStarted() {
        return intentStarted;
    }
}
