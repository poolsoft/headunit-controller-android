package com.freshollie.headunitcontroller.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.freshollie.headunitcontroller.activities.MainActivity;
import com.freshollie.headunitcontroller.R;

/**
 * Created by Freshollie on 14/12/2016.
 */

public class NotificationHandler {
    private Context context;
    public static int STATUS_NOTIFICATION_ID = 0;
    public static int SERVICE_NOTIFICATION_ID = 1;

    private NotificationCompat.Builder notificationBuilder;
    private NotificationManager notificationManager;

    private static Notification SERVICE_NOTIFICATION;

    public NotificationHandler(Context appContext) {
        context = appContext;
        buildNotification();
        notificationManager = (NotificationManager) context.getSystemService(Service.NOTIFICATION_SERVICE);
    }

    public void buildNotification() {
        notificationBuilder = new NotificationCompat.Builder(context.getApplicationContext())
                .setContentTitle(context.getString(R.string.app_name))
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_tablet_black_24dp)
                .setContentIntent(
                        PendingIntent.getActivity(
                                context,
                                0,
                                new Intent(context, MainActivity.class),
                                PendingIntent.FLAG_UPDATE_CURRENT
                        )
                );
    }

    public Notification getServiceNotification() {
        if (SERVICE_NOTIFICATION == null) {
            SERVICE_NOTIFICATION = notificationBuilder.build();
        }
        return SERVICE_NOTIFICATION;
    }

    public void notify(int notificiationId, Notification notification) {
        notificationManager.notify(notificiationId, notification);
    }

    public Notification notifyServiceStatus(String status) {
        SERVICE_NOTIFICATION = notificationBuilder.setContentText(status).build();
        notify(SERVICE_NOTIFICATION_ID, SERVICE_NOTIFICATION);
        return SERVICE_NOTIFICATION;
    }

    public Notification notifyServiceStatus(String status, PendingIntent intent) {
        SERVICE_NOTIFICATION = notificationBuilder
                        .setContentText(status)
                        .setContentIntent(intent)
                        .build();

        notify(SERVICE_NOTIFICATION_ID, SERVICE_NOTIFICATION);
        return SERVICE_NOTIFICATION;
    }

    public void notifyStatus(String status) {
        Notification notification = notificationBuilder
                .setContentText(status)
                .setOngoing(false)
                .setAutoCancel(true)
                .build();

        notify(STATUS_NOTIFICATION_ID, notification);
    }

    public void notifyStatus(String status, PendingIntent action) {
        Notification notification =
                notificationBuilder
                        .setContentText(status)
                        .setContentIntent(action)
                        .setOngoing(false)
                        .setAutoCancel(true)
                        .build();

        notify(STATUS_NOTIFICATION_ID, notification);
    }

    public void cancel(int id) {
        notificationManager.cancel(id);
    }
}