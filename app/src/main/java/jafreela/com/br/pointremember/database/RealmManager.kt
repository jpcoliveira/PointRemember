package jafreela.com.br.pointremember.database

import io.realm.Realm
import io.realm.RealmConfiguration


class RealmManager {

    fun getInstance(): Realm {
        val config = RealmConfiguration.Builder()
                .name("openappremember.realm")
                .schemaVersion(2)
                .build()
        return Realm.getInstance(config)
    }
}