package com.example.myproject;

import static android.content.Context.NOTIFICATION_SERVICE;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

public class Notification extends BroadcastReceiver {
    public static final String titleExtra="Ranganath";
    public static final String desExtra="This is a notification";
    @Override
    public void onReceive(Context context, Intent intent) {
        android.app.Notification mbuilder=new NotificationCompat.Builder(context, String.valueOf(1))
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Ranganath")
                .setContentText("Raghu").build();
        NotificationManager notificationManager= (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1,mbuilder);
    }
}
