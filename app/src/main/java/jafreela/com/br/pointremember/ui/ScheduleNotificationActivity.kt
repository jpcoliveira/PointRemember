package jafreela.com.br.pointremember.ui

import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import jafreela.com.br.pointremember.domain.AlarmScheduler
import jafreela.com.br.pointremember.util.Constants
import jafreela.com.br.pointremember.R
import jafreela.com.br.pointremember.model.AppAlarm
import kotlinx.android.synthetic.main.activity_schedule_notification.*
import java.text.SimpleDateFormat
import java.util.*

class ScheduleNotificationActivity : AppCompatActivity() {

    lateinit var alarmScheduler: AlarmScheduler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule_notification)

        alarmScheduler = AlarmScheduler(this)

        val namePackage = intent.getStringExtra(Constants.PACKAGE_NAME)

        initClocks()

        switch1.setOnCheckedChangeListener { compoundButton, isChecked ->
            if (isChecked) {
                val entryDate = timeEntry.text.toString().getDateByFormatedString()
                val lunchDate = timeLunch.text.toString().getDateByFormatedString()
                createAlarms(
                        listOf(
                                getEntryAlarmByDate(entryDate, namePackage),
                                getLunchAlarmByDate(lunchDate, namePackage)
                        )
                )
            } else {
                cancelAlarms()
            }
        }
    }

    fun initClocks() {
//        val alarms = SugarRecord.findAll(AppAlarm::class.java)
//
//        alarms.forEach { appAlarm ->
//            if (appAlarm.typeNotification == "entry")
//                timeEntry.text = appAlarm.date.getFormatDate()
//            else
//                timeLunch.text = appAlarm.date.getFormatDate()
//
//            switch1.isChecked = true
//        }

        timeEntry.setOnClickListener {
            showTimePickerDialog({ date -> timeEntry.text = date.getFormatDate() }).show()
        }

        timeLunch.setOnClickListener {
            showTimePickerDialog({ date -> timeLunch.text = date.getFormatDate() }).show()
        }
    }


    fun showTimePickerDialog(callback: (Date) -> Unit) =
            TimePickerDialog(this,
                    TimePickerDialog.OnTimeSetListener { timePicker, hour, min ->
                        callback(createCurrentDateWith(hour, min))
                    }, 12, 0, true)

    fun createAlarms(alarms: List<AppAlarm>) {
        alarms.forEach { appAlarm: AppAlarm ->
//            appAlarm.save()
            alarmScheduler.createRepeatingAlarm(appAlarm.date, appAlarm.id.toInt())
        }
    }

    fun cancelAlarms() {

//        val alarms = SugarRecord.findAll(AppAlarm::class.java)

//        alarms.forEach { appAlarm: AppAlarm ->
//            appAlarm.delete()
//            alarmScheduler.cancelAlarms(appAlarm.id.toInt())
//        }
    }

    fun getEntryAlarmByDate(date: Date, namePackage: String) =
            AppAlarm(
                    namePackage,
                    date =   date,
                    typeNotification = "entry",
                    descNotification = getString(R.string.desc_entry_notification)
            )

    fun getLunchAlarmByDate(date: Date, namePackage: String) =
            AppAlarm(
                    namePackage,
                    date =  date,
                    typeNotification = "lunch",
                    descNotification = getString(R.string.desc_lunch_notification)
            )

    fun createCurrentDateWith(hour: Int, minute: Int) = Date(Date().year, Date().month, Date().date, hour, minute)
    fun Date.getFormatDate() = SimpleDateFormat("HH:mm").format(time)
    fun String.getDateByFormatedString(): Date {
        val date = SimpleDateFormat("HH:mm").parse(this)
        return createCurrentDateWith(date.hours, date.minutes)
    }
}