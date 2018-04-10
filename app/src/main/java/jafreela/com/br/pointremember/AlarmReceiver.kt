package jafreela.com.br.pointremember

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.orm.SugarRecord


class AlarmReceiver : BroadcastReceiver() {

    private val CHANNEL_ID = "CHANNEL"
    private lateinit var mContext: Context

    override fun onReceive(context: Context, intent: Intent) {

        mContext = context

        val appNotification = AppNotification(context, CHANNEL_ID)

        when (intent.action) {
            Constants.Action.OPEN -> {

                val intentApp =
                        mContext
                                .packageManager
                                .getLaunchIntentForPackage(intent.getStringExtra(Constants.PACKAGE_NAME))
                mContext.startActivity(intentApp)

                appNotification.cancel(intent.getIntExtra(Constants.ID, 0))
            }

            Constants.Action.NOTIFICATION -> {
                val appAlarm = SugarRecord
                        .findById(AppAlarm::class.java,
                                intent.getIntExtra(Constants.ID, 0))

                appNotification.create(buildPendingIntent(appAlarm), appAlarm.id.toInt())
            }

            else -> throw IllegalStateException("action error")
        }
    }

    fun buildPendingIntent(appAlarm: AppAlarm): PendingIntent {
        val intentOpen = Intent(mContext, AlarmReceiver::class.java)
        intentOpen.setAction(Constants.Action.OPEN)
        intentOpen.putExtra(Constants.PACKAGE_NAME, appAlarm.packageName)
        intentOpen.putExtra(Constants.ID, appAlarm.id.toInt())
        return PendingIntent.getBroadcast(mContext, appAlarm.id.toInt(), intentOpen, 0)
    }

}