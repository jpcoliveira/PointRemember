package jafreela.com.br.pointremember.interfaces

import jafreela.com.br.pointremember.model.AppAlarm

interface RealmListener {
    fun loadAlarms(alarms: List<AppAlarm>)
}