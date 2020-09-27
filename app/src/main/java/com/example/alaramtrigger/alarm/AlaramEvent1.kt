package com.example.alaramtrigger.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.alaramtrigger.R
import kotlinx.android.synthetic.main.activity_alaram_trigger.*
import java.util.*


class AlaramEvent1 : AppCompatActivity() {
    private var mhour: Int = 0
    private var mMinute: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alaram_trigger)
        pickertime.setOnTimeChangedListener { view, hourDay, minute ->

            mhour = hourDay
            mMinute = minute
            tv_text.text = ("Time Is: $mhour: $mMinute").trimIndent()
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun setTime(view: View) {

        val calendar = Calendar.getInstance()

            calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                pickertime.hour,
                pickertime.minute, 0
            )


        /*else {
            calendar.set(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                pickertime.currentHour,
                pickertime.currentMinute, 0
            )


        }*/
        setAlarm(calendar.timeInMillis)


    }

    private fun setAlarm(timeInMillis: Long) {
val alarmManager = getSystemService(Context.ALARM_SERVICE ) as AlarmManager
        val intent = Intent(this, AlarmReceiver1::class.java)

        val pendingIntent = PendingIntent.getBroadcast(this,0,intent,0)
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,timeInMillis,AlarmManager.INTERVAL_DAY,pendingIntent)
        Toast.makeText(this,"Alarm set",Toast.LENGTH_LONG).show()


    }

}
