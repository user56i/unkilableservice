package com.nixsolutions.stickyservice.service;

import com.nixsolutions.stickyservice.log.Logg;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class WorkService extends StickyService implements ISomeWorker {

    private static final String TAG = WorkService.class.getName();

    private Subscription subscription;

    @Override
    public void startSomeWork() {
        startForeground();

        subscription = Observable.interval(500, TimeUnit.MILLISECONDS)
                .limit(10)
                .map(new Func1<Long, Long>() {
                    @Override
                    public Long call(Long aLong) {
                        Logg.e(TAG, "working " + aLong);

                        return aLong;
                    }
                })
                .toList()
                .subscribe(new Action1<List<Long>>() {
                    @Override
                    public void call(List<Long> longs) {
                        Logg.e(TAG, "worked on " + longs.size());
                        stopForeground();
                    }
                });
    }

    @Override
    public void stopSomeWork() {
        stopForeground();
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}
