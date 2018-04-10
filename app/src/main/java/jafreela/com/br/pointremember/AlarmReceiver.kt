package jafreela.com.br.pointremember

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
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

        if (intent.action != null && intent.action == "OPEN") {

            val intent3 = context.packageManager.getLaunchIntentForPackage(intent.getStringExtra("_packageName_"))
            context.startActivity(intent3)

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.cancel(intent.getIntExtra("id", 0))


        } else {

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

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

            val intent2 = Intent(context, AlarmReceiver::class.java)
            intent2.setAction("OPEN")
            intent2.putExtra("_packageName_", appAlarm.packageName)
            intent2.putExtra("id", appAlarm.id.toInt())

            val pendingIntent = PendingIntent.getBroadcast(context, appAlarm.id.toInt(), intent2, 0)

            val notification = NotificationCompat.Builder(context, CHANNEL_ID).apply {
                setContentTitle("Lembre-se")
                setContentText(appAlarm.descNotification)
                setPriority(NotificationCompat.PRIORITY_MAX)
                setSmallIcon(android.R.drawable.ic_dialog_info)
                setAutoCancel(true)
                setColorized(true)
                addAction(R.drawable.ic_launcher_background, "ABRIR", pendingIntent)
                setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                setVibrate(longArrayOf(300, 500, 300, 500))
            }

            notificationManager.notify(appAlarm.id.toInt(), notification.build())
        }
    }
}