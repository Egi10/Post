package id.buaja.data.source.local

import id.buaja.data.di.IoDispatcher
import id.buaja.data.source.local.entity.PostEntity
import id.buaja.data.source.local.room.PostDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

@Singleton
class LocalDataSource @Inject constructor(
    private val postDao: PostDao,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {
    suspend fun insertPost(listPost: List<PostEntity>) {
        CoroutineScope(dispatcher).launch {
            postDao.insertPost(listPost)
        }
    }

    fun getAllPost() = postDao.getAllPost()
    fun getPostByTitle(title: String) = postDao.getPostByTitle(title)
}