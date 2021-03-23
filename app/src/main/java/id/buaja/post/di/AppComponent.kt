package id.buaja.post.di

import dagger.Component
import id.buaja.data.di.CoreComponent
import id.buaja.data.di.DispatcherModule
import id.buaja.post.ui.home.HomeFragment
import id.buaja.post.ui.search.SearchPostFragment

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, DispatcherModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)
    fun inject(fragment: SearchPostFragment)
}