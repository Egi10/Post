package id.buaja.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import id.buaja.data.source.local.room.PostDao
import id.buaja.data.source.local.room.PostDatabase
import javax.inject.Singleton

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): PostDatabase = Room.databaseBuilder(
        context,
        PostDatabase::class.java, "Post.db"
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideTourismDao(database: PostDatabase): PostDao = database.postDao()
}