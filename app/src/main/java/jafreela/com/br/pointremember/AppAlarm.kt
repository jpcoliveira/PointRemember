package jafreela.com.br.pointremember

import com.orm.SugarRecord
import java.util.*

class AppAlarm(
        packageNameNew: String = "",
        dateNew: Date = Date(),
        typeNotification: String = "",
        descNotification: String = "") : SugarRecord() {
    val packageName = packageNameNew
    val date = dateNew
    val typeNotification = typeNotification
    val descNotification = descNotification


    override fun toString(): String {
        return "AppAlarm(packageName='$packageName', date=$date, typeNotification='$typeNotification', descNotification='$descNotification')"
    }
}
