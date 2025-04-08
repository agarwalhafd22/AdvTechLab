package com.example.bill;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity {
    Button btnSetReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        btnSetReminder = findViewById(R.id.btnSetReminder);

        btnSetReminder.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 20);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            Intent intent = new Intent(ReminderActivity.this, ReminderReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(ReminderActivity.this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            finish();
        });
    }
}