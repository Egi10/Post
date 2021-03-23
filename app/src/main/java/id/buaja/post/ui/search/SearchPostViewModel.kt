package id.buaja.post.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.data.di.MainDispatcher
import id.buaja.domain.model.Post
import id.buaja.domain.usecase.PostUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Julsapargi Nursam on 3/24/21.
 */

class SearchPostViewModel(
    private val postUseCase: PostUseCase,
    @MainDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _success = MutableLiveData<List<Post>>()
    val success: LiveData<List<Post>> get() = _success

    private val _empty = MutableLiveData<String>()
    val empty: LiveData<String> get() = _empty

    fun getPostByTitle(title: String) {
        viewModelScope.launch(dispatcher) {
            postUseCase.getPostByTitle(title)
                .collect {
                    if (it.isNullOrEmpty()) {
                        _empty.postValue("Belum Ada Data Saat Ini")
                    } else {
                        _success.postValue(it)
                    }
                }
        }
    }
}