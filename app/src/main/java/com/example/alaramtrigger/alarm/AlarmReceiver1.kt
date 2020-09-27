package com.example.alaramtrigger.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.provider.Settings


class AlarmReceiver1 : BroadcastReceiver() {
override fun onReceive(context: Context, intent: Intent?) {

 val mediaPlayer= MediaPlayer.create(context,Settings.System.DEFAULT_RINGTONE_URI)
 mediaPlayer.start()
}}
