package com.nixsolutions.stickyservice;

import android.os.Bundle;

import com.nixsolutions.stickyservice.service.BindActivity;
import com.nixsolutions.stickyservice.service.WorkService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends BindActivity<WorkService> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonStart)
    public void onStartClick() {
        getServiceManager().getService().startSomeWork();
    }

    @OnClick(R.id.buttonStop)
    public void onStopClick() {
        getServiceManager().getService().stopSomeWork();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getServiceManager().startService();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (!getServiceManager().isForeground()) {
            getServiceManager().stopService();
        }
    }
}
