package id.buaja.post.di

import dagger.Binds
import dagger.Module
import id.buaja.domain.usecase.PostUseCase
import id.buaja.domain.usecase.PostUseCaseImpl

@Module
abstract class AppModule {
    @Binds
    abstract fun providePostUseCase(postUseCaseImpl: PostUseCaseImpl): PostUseCase
}
