package jafreela.com.br.pointremember.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle


inline fun <reified T : Any> Context.startActivityNew(bundle: Bundle?) {
    val intent = Intent(this, T::class.java)
    bundle?.keySet()?.map { key -> intent.putExtra(key, bundle.get(key) as String) }
    startActivity(intent)
}