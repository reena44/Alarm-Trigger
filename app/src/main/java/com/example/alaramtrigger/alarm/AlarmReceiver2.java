package com.example.alaramtrigger.alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Vibrator;
import android.provider.Settings;

import androidx.core.app.NotificationCompat;

import com.example.alaramtrigger.R;


public class AlarmReceiver2 extends BroadcastReceiver {
 
 private static final int MY_NOTIFICATION_ID=1;
 NotificationManager notificationManager;
 Notification myNotification;
 private final String myBlog = "http://android-er.blogspot.com/";



    @Override
    public void onReceive(Context context, Intent intent) {
        Vibrator vibrator = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE) ;
  vibrator.vibrate(8000);

        MediaPlayer mediaPlayer= MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.start();

        Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myBlog));
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                myIntent,
                0);

        myNotification = new NotificationCompat.Builder(context)
                .setContentTitle("Wake UP!!!")
                .setContentText("Alarm is belling")
                .setTicker("Notification!")
                .setWhen(System.currentTimeMillis())
                .setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        notificationManager =
                (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(MY_NOTIFICATION_ID, myNotification);


    }
}
