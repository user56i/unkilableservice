package com.nixsolutions.stickyservice;

import android.content.Intent;
import android.os.Bundle;

import com.nixsolutions.stickyservice.service.BindActivity;
import com.nixsolutions.stickyservice.service.ServiceManager;
import com.nixsolutions.stickyservice.service.WorkService;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BindActivity<WorkService> {

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
    @OnClick(R.id.next)
    public void onNextClick() {
        startActivity(new Intent(this, Main2Activity.class));
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
