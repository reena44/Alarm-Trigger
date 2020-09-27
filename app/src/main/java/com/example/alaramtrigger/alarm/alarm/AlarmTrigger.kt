package com.example.todo.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.alaramtrigger.R
import com.example.alaramtrigger.alarm.AlarmReceiver2
import com.example.alaramtrigger.alarm.AlarmTrigger
import com.example.todo.R
import com.example.todo.alarmUtils.TriggerAlarm
import kotlinx.android.synthetic.main.activity_alaram_trigger.*
import java.util.*

class AlarmTrigger:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_alaram_trigger)
        val now = Calendar.getInstance()
        pickerdate!!.init(
            now[Calendar.YEAR],
            now[Calendar.MONTH],
            now[Calendar.DAY_OF_MONTH],
            null
        )
        pickertime!!.currentHour = now[Calendar.HOUR_OF_DAY]
        pickertime!!.currentMinute = now[Calendar.MINUTE]
        setalarm!!.setOnClickListener {
            val current = Calendar.getInstance()
            val cal = Calendar.getInstance()
            cal[pickerdate!!.year, pickerdate!!.month, pickerdate!!.dayOfMonth, pickertime!!.currentHour, pickertime!!.currentMinute] = 0
            if (cal <= current) {
                //The set Date/Time already passed
                Toast.makeText(
                    applicationContext,
                    "Invalid Date/Time",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                setAlarm(cal)
            }
        }
    }

    private fun setAlarm(targetCal: Calendar) {
        info!!.text = ("""
                ***
                Alarm is set@ """ + targetCal.time + """
                ***
                
                """).trimIndent()
        val intent = Intent(baseContext, AlarmReceiver2::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(baseContext, AlarmTrigger.RQS_1, intent, 0)
        val alarmManager =
            getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager[AlarmManager.RTC_WAKEUP, targetCal.timeInMillis] = pendingIntent
    }

}