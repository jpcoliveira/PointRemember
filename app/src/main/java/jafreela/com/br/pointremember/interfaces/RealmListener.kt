package jafreela.com.br.pointremember.interfaces

import jafreela.com.br.pointremember.model.AppAlarm

interface RealmListener {
    fun fetchAll(list: List<AppAlarm>)
}