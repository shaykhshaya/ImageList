package com.shaya.fliptree.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shaya.fliptree.network.ImageApi
import com.shaya.fliptree.response.ImageItem
import kotlinx.coroutines.launch


enum class ImageApiStatus { LOADING, ERROR, DONE }

class ImageListViewModel : ViewModel() {


    private val _status = MutableLiveData<ImageApiStatus>()
    val status: LiveData<ImageApiStatus> = _status

    private val _images = MutableLiveData<List<ImageItem>>()
    val images: LiveData<List<ImageItem>> = _images

    var itemList: List<ImageItem>? = arrayListOf()

    init {
        getImagesList()
    }


    fun getImagesList() {
        viewModelScope.launch {
            _status.value = ImageApiStatus.LOADING
            try {
                _images.value = ImageApi.retrofitService.getImages()
                itemList = _images.value
                _status.value = ImageApiStatus.DONE

            } catch (e: Exception) {
                _status.value = ImageApiStatus.ERROR
                itemList = null
                _images.value = listOf()
            }
        }
    }


    fun onFilter(title: String) {
        if (title.isEmpty()) {
            _images.value = itemList
        }

        val filteredList = itemList?.filter {
            it.title?.lowercase()?.contains(title.lowercase()) == true
        }

        filteredList?.let {
            _images.value = it
        }
    }


}