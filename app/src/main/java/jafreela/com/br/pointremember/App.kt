package jafreela.com.br.pointremember

import android.graphics.drawable.Drawable
import java.io.Serializable

data class App(val name: String?, val icon: Drawable?, val packageName: String?) : Serializable