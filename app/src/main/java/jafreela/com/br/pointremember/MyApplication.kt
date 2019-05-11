package jafreela.com.br.pointremember

import android.app.Application
import io.realm.Realm

class MyApplication : Application() {

    override fun onCreate() {
        Realm.init(this)
        super.onCreate()
    }
}