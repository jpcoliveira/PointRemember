package jafreela.com.br.pointremember

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class AppAdapter(apps: List<App>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val appList = apps

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AppViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false))
    }

    override fun getItemCount() = appList?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AppViewHolder)
            appList?.let { holder.bindData(appList.get(position)) }
    }

    class AppViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val nameApp = itemView?.findViewById<TextView>(R.id.textNameApp)
        val imageApp = itemView?.findViewById<ImageView>(R.id.imageViewApp)

        fun bindData(app: App) {
            nameApp?.text = app?.name
            app?.icon.let { imageApp?.setImageDrawable(app.icon) }
        }

        override fun onClick(view: View?) {

        }
    }

    open interface Callback {
        fun onClickAppItem(app: App)
    }
}