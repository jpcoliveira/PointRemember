package jafreela.com.br.pointremember.domain

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import jafreela.com.br.pointremember.util.Constants
import java.util.*

class AlarmScheduler(val context: Context) {

    private val alarmManager by lazy {
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    fun createRepeatingAlarm(date: Date, requestCode: Int) {
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.setAction(Constants.Action.NOTIFICATION)
        intent.putExtra(Constants.ID, requestCode)

        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        alarmManager.setInexactRepeating(
                AlarmManager.RTC,
                date.time,
                AlarmManager.INTERVAL_DAY,
                pendingIntent)
    }

    fun createUniqueAlarm(date: Date, requestCode: Int) {
        val intent = Intent(context, AlarmReceiver::class.java)
        intent.setAction(Constants.Action.NOTIFICATION)
        intent.putExtra(Constants.ID, requestCode)

        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        alarmManager.set(
                AlarmManager.RTC,
                date.time,
                pendingIntent
        )
    }

    fun cancelAlarms(requestCode: Int) {
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.cancel(pendingIntent)
    }
}