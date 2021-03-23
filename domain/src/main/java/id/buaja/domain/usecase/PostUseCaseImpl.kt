package id.buaja.domain.usecase

import id.buaja.domain.repository.PostRepository
import javax.inject.Inject

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

class PostUseCaseImpl @Inject constructor(private val postRepository: PostRepository) :
    PostUseCase {
    override fun getPost() = postRepository.getPost()
    override fun getPostByTitle(title: String) = postRepository.getPostByTitle(title)
}