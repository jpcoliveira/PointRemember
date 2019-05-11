package jafreela.com.br.pointremember.interfaces

import jafreela.com.br.pointremember.model.App

interface RealmListener {
    fun loadAppAlarms(apps: List<App>)
}