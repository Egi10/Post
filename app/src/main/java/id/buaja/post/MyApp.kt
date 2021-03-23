package id.buaja.post

import android.app.Application
import id.buaja.data.di.CoreComponent
import id.buaja.data.di.DaggerCoreComponent
import id.buaja.post.di.AppComponent
import id.buaja.post.di.DaggerAppComponent

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

open class MyApp : Application() {
    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.factory().create(applicationContext)
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(coreComponent)
    }
}