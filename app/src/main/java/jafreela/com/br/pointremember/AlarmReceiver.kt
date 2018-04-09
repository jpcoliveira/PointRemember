package jafreela.com.br.pointremember

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.orm.SugarRecord


class AlarmReceiver : BroadcastReceiver() {

    private val CHANNEL_ID = "CHANNEL"

    @SuppressLint("WrongConstant")
    override fun onReceive(context: Context, intent: Intent) {
        Log.i("ALARME", "O alarme executou as: ")

        val notificationManager by lazy {
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.i("ALARME", "...")
            val notificationChannel = NotificationChannel(CHANNEL_ID, "notificacao", NotificationManager.IMPORTANCE_MAX)
            notificationChannel.setBypassDnd(true)
            notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            notificationChannel.enableLights(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val appAlarm = SugarRecord.findById(AppAlarm::class.java, intent.getIntExtra("id", 1))

        Log.i("ALARME", appAlarm.toString())

        val intent2 = context.packageManager.getLaunchIntentForPackage(appAlarm.packageName)
        intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        val pendingIntent = PendingIntent.getBroadcast(context, appAlarm.id.toInt(), intent2, 0)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID).apply {
            setContentTitle("Lembre-se")
            setContentText(appAlarm.descNotification)
            setPriority(NotificationCompat.PRIORITY_MAX)
            setSmallIcon(android.R.drawable.ic_dialog_info)
            setAutoCancel(true)
            setColorized(true)
            addAction(android.R.drawable.ic_dialog_info, "teste", pendingIntent)
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            setVibrate(longArrayOf(300, 500, 300, 500))
        }

        notificationManager.notify(appAlarm.id.toInt(), notification.build())
    }


}