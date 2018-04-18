package jafreela.com.br.pointremember.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatHourMinute() = SimpleDateFormat("HH:mm").format(time)