package jafreela.com.br.pointremember.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class AppAlarm(
        date: Date = Date(),
        descNotification: String = "") : RealmObject() {
    @PrimaryKey
    var id = UUID.randomUUID().toString()
    var date = date
    var descNotification = descNotification
}
