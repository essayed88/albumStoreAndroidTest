package com.leconcoin.test.albumstore

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber

class App : Application() {

    companion object {
        var REALM_SCHEMA_VERSION = 1
    }

    override fun onCreate() {
        super.onCreate()
        setUpRealm()
        //setUpToothpick()
        Timber.plant(Timber.DebugTree())
     //   Stetho.initializeWithDefaults(this)
    }

    private fun setUpRealm() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(REALM_SCHEMA_VERSION.toLong())
            .build()
        Realm.setDefaultConfiguration(config)
    }
/*
    private fun setUpToothpick() {
        // Toothpick
        val appScope = Toothpick.openScope(this)
        appScope.installModules(SmoothieApplicationModule(this), ApplicationModule(this))
    }

 */
}