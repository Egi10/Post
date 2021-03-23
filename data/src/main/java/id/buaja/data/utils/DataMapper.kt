package id.buaja.data.utils

import id.buaja.data.source.local.entity.PostEntity
import id.buaja.data.source.remote.response.PostResponseItem
import id.buaja.domain.model.Post

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

object DataMapper {
    fun mapEntitiesToDomain(input: List<PostEntity>): List<Post> {
        val postEntity = ArrayList<Post>()
        input.map {
            val post = Post(
                id = it.id,
                title = it.title,
                body = it.body
            )
            postEntity.add(post)
        }
        return postEntity
    }

    fun mapResponseToEntities(input: List<PostResponseItem>): List<PostEntity> {
        val postEntity = ArrayList<PostEntity>()
        input.map {
            val post = PostEntity(
                id = it.id ?: 0,
                title = it.title ?: "",
                body = it.body ?: ""
            )
            postEntity.add(post)
        }
        return postEntity
    }
}