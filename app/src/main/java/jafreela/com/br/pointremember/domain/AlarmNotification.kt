package jafreela.com.br.pointremember.domain

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import jafreela.com.br.pointremember.R

class AlarmNotification(private val context: Context, private val channelId: String) {

    private val notificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    fun create(actionList: List<NotificationCompat.Action>, id: Int) {
        createNotification(id, builNotification(actionList))
    }

    fun cancel(id: Int) {
        notificationManager.cancel(id)
    }

    private fun builNotification(actionList: List<NotificationCompat.Action>): Notification {
        return NotificationCompat.Builder(context, channelId).apply {
            setContentTitle(context.getString(R.string.remember))
            setContentText("")
            setPriority(NotificationCompat.PRIORITY_MAX)
            setSmallIcon(android.R.drawable.ic_dialog_info)
            setAutoCancel(true)
            setColorized(true)
            setColor(context.resources.getColor(R.color.accent_material_light))

            actionList.forEach { action: NotificationCompat.Action ->
                addAction(action)
            }

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