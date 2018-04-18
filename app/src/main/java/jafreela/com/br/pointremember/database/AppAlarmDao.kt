package jafreela.com.br.pointremember.database

import io.realm.Realm
import jafreela.com.br.pointremember.interfaces.RealmListener
import jafreela.com.br.pointremember.model.AppAlarm


class AppAlarmDao(val realmManager: RealmManager, val listener: RealmListener) {

    fun save(appAlarm: AppAlarm) {
        val realmInstance = realmManager.getInstance()

        realmInstance.executeTransaction(Realm.Transaction { realm ->
            realm.insertOrUpdate(appAlarm)
        })

        realmInstance.close()
    }

    fun getAlarms() {
        val realmInstance = realmManager.getInstance()

        realmInstance.executeTransactionAsync(
                { realm ->
                    val results = realm.where(AppAlarm::class.java).findAll()
                    listener.loadAlarms(results)
                }
        )
        realmInstance.close()
    }
}