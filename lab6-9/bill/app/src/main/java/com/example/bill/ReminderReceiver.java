package com.example.bill;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class ReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "budgetNotify")
               // Make sure this icon exists in drawable
                .setContentTitle("Bill Payment Reminder")
                .setContentText("Don't forget to pay your bills today!")
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);

        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // Permission not granted, do not show the notification
            return;
        }

        manager.notify(1, builder.build());
    }
}
