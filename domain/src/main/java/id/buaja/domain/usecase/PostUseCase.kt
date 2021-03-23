package id.buaja.domain.usecase

import id.buaja.domain.Resource
import id.buaja.domain.model.Post
import kotlinx.coroutines.flow.Flow

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

interface PostUseCase {
    fun getPost(): Flow<Resource<List<Post>>>
}