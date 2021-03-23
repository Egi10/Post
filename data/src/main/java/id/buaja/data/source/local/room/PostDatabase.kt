package id.buaja.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import id.buaja.data.source.local.entity.PostEntity

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

@Database(entities = [PostEntity::class], version = 1, exportSchema = false)
abstract class PostDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
}