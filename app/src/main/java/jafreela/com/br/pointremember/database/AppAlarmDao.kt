package jafreela.com.br.pointremember.database

import android.util.Log
import jafreela.com.br.pointremember.interfaces.RealmListener
import jafreela.com.br.pointremember.model.AppAlarm


class AppAlarmDao(val realmManager: RealmManager, val listener: RealmListener) {

    fun save(appAlarm: AppAlarm) {
        val realm = realmManager.getInstance()


        realm.close()
    }

    fun getAll() {
        val realmInstance = realmManager.getInstance()

        realmInstance.executeTransactionAsync(
                {
                    realm ->
                    val results = realm.where(AppAlarm::class.java).findAll()
                    listener.fetchAll(results)
                },
                { Log.i("AppAlarmDao", "getAll onSuccess") },
                { error -> Log.e("AppAlarmDao", "getAll error") }
        )

        realmInstance.close()
    }

}