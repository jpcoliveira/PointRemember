package jafreela.com.br.pointremember

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AppAdapter.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, 0)
        val appList = pkgAppsList
                .asSequence()
                .map { resolveInfo ->
                    App(
                            resolveInfo?.loadLabel(packageManager)?.toString(),
                            resolveInfo?.loadIcon(packageManager),
                            resolveInfo?.activityInfo?.packageName
                    )
                }
                .sortedBy { it.name }
                .toList()
        recyclerviewApps.layoutManager = LinearLayoutManager(this)
        recyclerviewApps.adapter = AppAdapter(appList, this)
    }

    override fun onClickAppItem(app: App) {
        val bundle = Bundle()
        bundle.putString("_packageName_", app.packageName)
        startActivityNew<ScheduleNotificationActivity>(bundle)
    }

    inline fun <reified T : Any> Context.startActivityNew(bundle: Bundle?) {
        val intent = Intent(this, T::class.java)
        bundle?.keySet()?.map { key -> intent.putExtra(key, bundle.get(key) as String) }
        startActivity(intent)
    }
}
