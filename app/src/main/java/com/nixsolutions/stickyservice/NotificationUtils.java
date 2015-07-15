package com.nixsolutions.stickyservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;

public class NotificationUtils {
    public static Notification getForeground(Context context) {
        return new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setWhen(0)
                .setOngoing(false)
                .setContentIntent(getOpenPendingIntent(context))
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setContentTitle(context.getString(R.string.app_name)).build();
    }


    private static PendingIntent getOpenPendingIntent(Context context) {
        Intent intentBackToActivity = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context, 0,
                intentBackToActivity, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
