package jafreela.com.br.pointremember.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.RealmList
import jafreela.com.br.pointremember.R
import jafreela.com.br.pointremember.extensions.formatHourMinute
import jafreela.com.br.pointremember.model.AppAlarm

class AppAlarmAdapter(val alarmList: RealmList<AppAlarm>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AppAlarmViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_app_alarm, parent, false))
    }

    override fun getItemCount() = alarmList?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AppAlarmViewHolder)
            alarmList?.let { holder.bindData(alarmList.get(position)) }
    }

    inner class AppAlarmViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val hourAlarm = itemView?.findViewById<TextView>(R.id.hourAlarm)

        fun bindData(alarm: AppAlarm?) {
            val description = "alarme às ${alarm?.date?.formatHourMinute()}"
            hourAlarm?.text = description
        }
    }
}