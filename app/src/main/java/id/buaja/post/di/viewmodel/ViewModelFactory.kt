package id.buaja.post.di.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id.buaja.data.di.MainDispatcher
import id.buaja.domain.usecase.PostUseCase
import id.buaja.post.di.AppScope
import id.buaja.post.ui.home.HomeViewModel
import id.buaja.post.ui.search.SearchPostViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

@AppScope
class ViewModelFactory @Inject constructor(
    private val postUseCase: PostUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(postUseCase, dispatcher) as T
            }

            modelClass.isAssignableFrom(SearchPostViewModel::class.java) -> {
                SearchPostViewModel(postUseCase, dispatcher) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}