package id.buaja.post.di

import dagger.Component
import id.buaja.data.di.CoreComponent
import id.buaja.post.ui.HomeFragment

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(fragment: HomeFragment)
}