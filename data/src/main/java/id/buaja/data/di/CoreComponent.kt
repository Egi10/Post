package id.buaja.data.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import id.buaja.domain.repository.PostRepository
import javax.inject.Singleton

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

@Singleton
@Component(
    modules = [RepositoryModule::class, DispatcherModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository() : PostRepository
}