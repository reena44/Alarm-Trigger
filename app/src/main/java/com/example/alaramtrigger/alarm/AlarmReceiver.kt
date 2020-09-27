package com.example.alaramtrigger.alarm

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Vibrator
import android.provider.Settings
import androidx.core.app.NotificationCompat
import com.example.alaramtrigger.R


class AlarmReceiver : BroadcastReceiver() {
    var notificationManager: NotificationManager? = null
    var myNotification: Notification? = null
    private val myBlog = "http://android-er.blogspot.com/"
    override fun onReceive(context: Context, intent: Intent) {
        val vibrator = context
            .getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(8000)
        val mediaPlayer =
            MediaPlayer.create(context, Settings.System.DEFAULT_RINGTONE_URI)
        mediaPlayer.start()
        val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(myBlog))
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            myIntent,
            0
        )
        myNotification = NotificationCompat.Builder(context)
            .setContentTitle("Wake UP!!!")
            .setContentText("Alarm is belling")
            .setTicker("Notification!")
            .setWhen(System.currentTimeMillis())
            .setContentIntent(pendingIntent)
            .setDefaults(Notification.DEFAULT_SOUND)
            .setAutoCancel(true)
            .setSmallIcon(R.mipmap.ic_launcher)
            .build()
        notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager!!.notify(MY_NOTIFICATION_ID, myNotification)
    }

    companion object {
        private const val MY_NOTIFICATION_ID = 1
    }
}
