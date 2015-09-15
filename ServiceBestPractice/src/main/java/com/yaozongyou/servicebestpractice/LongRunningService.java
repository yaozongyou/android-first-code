package com.yaozongyou.servicebestpractice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.Date;
import android.util.Log;
import android.app.AlarmManager;
import android.os.SystemClock;
import android.app.PendingIntent;

public class LongRunningService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d("LongRunningService", "executed at " + new Date().toString());
            }
        }).start();

        AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
        int anMinute = 60 * 1000;
        long triggerAtTime = SystemClock.elapsedRealtime() + anMinute;
        Intent i = new Intent(this, AlarmReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);
        return super.onStartCommand(intent, flags, startId);
    }
}
