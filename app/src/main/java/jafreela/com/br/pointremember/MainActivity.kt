package jafreela.com.br.pointremember

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
                .map { resolveInfo ->
                    App(
                            resolveInfo?.loadLabel(packageManager)?.toString(),
                            resolveInfo?.loadIcon(packageManager),
                            resolveInfo?.activityInfo?.packageName
                    )
                }
                .sortedBy { it.name }
        recyclerviewApps.layoutManager = LinearLayoutManager(this)
        recyclerviewApps.adapter = AppAdapter(appList, this)
    }

    override fun onClickAppItem(app: App) {
//        val intent = packageManager.getLaunchIntentForPackage(app.packageName)
        val intent = Intent(this, ScheduleNotificationActivity().javaClass)
        intent.putExtra("app", app)
        startActivity(intent)
    }
}
