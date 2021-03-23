package id.buaja.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.buaja.data.source.local.entity.PostEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(post: List<PostEntity>)

    @Query("SELECT * FROM post")
    fun getAllPost(): Flow<List<PostEntity>>

    @Query("SELECT * FROM post WHERE title = :title")
    fun getPostByTitle(title: String): Flow<List<PostEntity>>
}