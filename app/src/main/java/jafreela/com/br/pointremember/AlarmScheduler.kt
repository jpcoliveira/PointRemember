package jafreela.com.br.pointremember

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class AlarmScheduler(val context: Context) {

    fun createAlarm(date: Date, requestCode: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
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

    fun cancelAlarms(requestCode: Int) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        alarmManager.cancel(pendingIntent)
    }
}