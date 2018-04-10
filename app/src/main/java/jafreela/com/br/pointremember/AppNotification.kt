package jafreela.com.br.pointremember

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat

class AppNotification(private val context: Context, private val channelId: String) {

    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun create(pendingIntent: PendingIntent, id: Int) {
        createNotification(id, builNotification(pendingIntent))
    }

    fun cancel(id: Int) {
        notificationManager.cancel(id)
    }

    private fun builNotification(pendingIntent: PendingIntent): Notification {
        return NotificationCompat.Builder(context, channelId).apply {
            setContentTitle(context.getString(R.string.remember))
            setContentText("")
            setPriority(NotificationCompat.PRIORITY_MAX)
            setSmallIcon(android.R.drawable.ic_dialog_info)
            setAutoCancel(true)
            setColorized(true)
            setColor(context.resources.getColor(R.color.accent_material_light))
            addAction(R.drawable.ic_launcher_background, context.getString(R.string.open), pendingIntent)
            setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            setVibrate(longArrayOf(300, 500, 300, 500))
        }.build()
    }

    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun buildNotificationChannel(): NotificationChannel {
        val notificationChannel = NotificationChannel(channelId, "notificacao", NotificationManager.IMPORTANCE_MAX)
        notificationChannel.setBypassDnd(true)
        notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        notificationChannel.enableLights(true)
        return notificationChannel
    }

    private fun createNotification(id: Int, notification: Notification) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(buildNotificationChannel())
        }
        notificationManager.notify(id, notification)
    }
}