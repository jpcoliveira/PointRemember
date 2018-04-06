package jafreela.com.br.pointremember

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_schedule_notification.*
import java.text.SimpleDateFormat
import java.util.*


class ScheduleNotificationActivity : AppCompatActivity() {

    lateinit var alarmMgr: AlarmManager
    lateinit var alarmIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_notification)
        timeEntry.setOnClickListener { showTimePickerDialog { timeEntry } }
        timeLunch.setOnClickListener { showTimePickerDialog { timeLunch } }

        switch1.setOnCheckedChangeListener { compoundButton, isChecked ->

            if (isChecked) {

            } else {
                alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val intent = Intent("ALARME")
                val alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
                alarmMgr.cancel(alarmIntent)
            }

        }
    }

    private fun showTimePickerDialog(textView: () -> TextView) {
        val builder = TimePickerDialog(this,
                TimePickerDialog.OnTimeSetListener { timePicker, hour, min ->

                    val calendar = Calendar.getInstance()
                    calendar.time = Date(
                            calendar.time.year,
                            calendar.time.month,
                            calendar.time.date,
                            hour,
                            min
                    )
                    val format = SimpleDateFormat("HH:mm")
                    textView().text = format.format(calendar.time)
                    Log.i("ALARME", calendar.time.toString())

                    alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    val intent = Intent(this, AlarmReceiver::class.java)
                    alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

                    alarmMgr.setRepeating(AlarmManager.RTC, calendar.timeInMillis, AlarmManager.INTERVAL_DAY, alarmIntent);


                }, 12, 0, true)
        builder.show()
    }
}