package jafreela.com.br.pointremember

import com.orm.SugarRecord
import java.util.*

class AppAlarm(
        val packageName: String = "",
        val date: Date = Date(),
        val typeNotification: String = "",
        val descNotification: String = "") : SugarRecord() {

    override fun toString(): String {
        return "AppAlarm(packageName='$packageName', date=$date, typeNotification='$typeNotification', descNotification='$descNotification')"
    }
}
