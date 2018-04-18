package jafreela.com.br.pointremember.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import io.realm.RealmList
import jafreela.com.br.pointremember.R
import jafreela.com.br.pointremember.adapter.AppAdapter
import jafreela.com.br.pointremember.database.AppAlarmDao
import jafreela.com.br.pointremember.database.RealmManager
import jafreela.com.br.pointremember.extensions.startActivityNew
import jafreela.com.br.pointremember.interfaces.RealmListener
import jafreela.com.br.pointremember.model.App
import jafreela.com.br.pointremember.model.AppAlarm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AppAdapter.Callback, RealmListener {

    val realmDao by lazy {
        AppAlarmDao(RealmManager(), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val alarmList = RealmList<AppAlarm>()

        alarmList.add(AppAlarm(descNotification = "ola1"))
        alarmList.add(AppAlarm(descNotification = "ola2"))
        alarmList.add(AppAlarm(descNotification = "ola3"))
        alarmList.add(AppAlarm(descNotification = "ola4"))

        val appList = listOf(
                App(name = "teste1", packageName = "com.br12", alarmList = alarmList),
                App(name = "teste2", packageName = "com.br22", alarmList = alarmList),
                App(name = "teste3", packageName = "com.br32", alarmList = alarmList))


        appList.forEach { app: App -> realmDao.save(app) }


        realmDao.getAlarms()

        floatingActionButton.setOnClickListener { view ->
            startActivityNew<ScheduleActivity>(null)
        }
        recyclerviewApps.layoutManager = LinearLayoutManager(this)

        Log.i("MainActivity", "create")
    }

    override fun onClickAppItem(app: App) {
        val bundle = Bundle()
        bundle.putString("_packageName_", app.packageName)
        startActivityNew<ScheduleActivity>(bundle)
    }

    override fun loadAppAlarms(apps: List<App>) {
        Log.i("MainActivity", "loadAppAlarms")
        recyclerviewApps.adapter = AppAdapter(apps, this)
    }
}
