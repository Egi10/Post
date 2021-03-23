package id.buaja.data.di

import dagger.Binds
import dagger.Module
import id.buaja.data.repository.PostRepositoryImpl
import id.buaja.domain.repository.PostRepository

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository
}