package jafreela.com.br.pointremember.model

import android.graphics.drawable.Drawable
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Ignore
import io.realm.annotations.PrimaryKey

open class App(
        name: String? = "",
        icon: Drawable? = null,
        packageName: String? = "",
        alarmList: RealmList<AppAlarm>? = null) : RealmObject() {
    @PrimaryKey
    var packageName = packageName
    var name = name
    @Ignore
    var icon = icon
    var alarmList = alarmList
}