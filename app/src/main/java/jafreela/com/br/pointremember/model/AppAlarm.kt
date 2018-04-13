package jafreela.com.br.pointremember.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

class AppAlarm(
        @PrimaryKey
        val id: String = UUID.randomUUID().toString(),
        val packageName: String = "",
        val date: Date = Date(),
        val typeNotification: String = "",
        val descNotification: String = "") : RealmObject() {

    override fun toString(): String {
        return "AppAlarm(packageName='$packageName', date=$date, typeNotification='$typeNotification', descNotification='$descNotification')"
    }
}
