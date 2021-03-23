package id.buaja.post.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.buaja.domain.Resource
import id.buaja.domain.model.Post
import id.buaja.domain.usecase.PostUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Julsapargi Nursam on 3/23/21.
 */

class HomeViewModel(
    private val postUseCase: PostUseCase,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _success = MutableLiveData<List<Post>>()
    val success: LiveData<List<Post>> get() = _success

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    init {
        getPost()
    }

    fun getPost() {
        viewModelScope.launch(dispatcher) {
            postUseCase.getPost().collect {
                when (it) {
                    is Resource.Loading -> {
                        _loading.value = true
                    }

                    is Resource.Success -> {
                        _loading.value = false
                        _success.postValue(it.data)
                    }

                    is Resource.Error -> {
                        _loading.value = false
                        _errorMessage.postValue(it.message)
                    }
                }
            }
        }
    }
}