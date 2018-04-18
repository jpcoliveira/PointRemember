package jafreela.com.br.pointremember.model

import android.graphics.drawable.Drawable
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class App(
        name: String? = "",
        iconResource: Int? = null,
        packageName: String = "",
        alarmList: RealmList<AppAlarm>? = null) : RealmObject() {
    @PrimaryKey
    var packageName = packageName
    var name = name
    var iconResource = iconResource
    var alarmList = alarmList
}