package jafreela.com.br.pointremember.database

import io.realm.Realm
import jafreela.com.br.pointremember.interfaces.RealmListener
import jafreela.com.br.pointremember.model.App


class AppAlarmDao(val realmManager: RealmManager, val listener: RealmListener) {

    fun save(appAlarm: App) {
        val realmInstance = realmManager.getInstance()

        realmInstance.executeTransaction(Realm.Transaction { realm ->
            realm.insertOrUpdate(appAlarm)
        })
    }

    fun getAlarms() {
        val realmInstance = realmManager.getInstance()

        realmInstance.executeTransaction(
                { realm ->
                    val results = realm.where(App::class.java).findAll()
                    listener.loadAppAlarms(results)
                }
        )
    }
}