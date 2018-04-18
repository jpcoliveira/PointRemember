package jafreela.com.br.pointremember.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import jafreela.com.br.pointremember.R
import jafreela.com.br.pointremember.adapter.AppSelectAdapter
import jafreela.com.br.pointremember.model.App
import kotlinx.android.synthetic.main.activity_main.*

class SelectAppActivity : AppCompatActivity(), AppSelectAdapter.Callback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_app_activity)

        val mainIntent = Intent(Intent.ACTION_MAIN, null)
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER)
        val pkgAppsList = getPackageManager().queryIntentActivities(mainIntent, 0)
        val appList = pkgAppsList
                .asSequence()
                .map { resolveInfo ->
                    App(
                            name = resolveInfo?.loadLabel(packageManager)?.toString(),
                            icon = resolveInfo?.loadIcon(packageManager),
                            packageName = resolveInfo?.activityInfo?.packageName
                    )
                }
                .sortedBy { it.name }
                .toList()

        recyclerviewApps.layoutManager = LinearLayoutManager(this)
        recyclerviewApps.adapter = AppSelectAdapter(appList, this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onClickAppItem(app: App) {
        Log.i("SelectAppActivity", "onClickAppItem")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
