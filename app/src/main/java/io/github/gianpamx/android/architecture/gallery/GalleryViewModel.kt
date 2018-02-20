package io.github.gianpamx.android.architecture.gallery

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.view.View
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.GetImagesUseCase

class GalleryViewModel(
        private val getFormUseCase: GetFormUseCase,
        private val getImagesUseCase: GetImagesUseCase) : ViewModel() {

    val name = MutableLiveData<String>()
    val images = MutableLiveData<List<String>>()

    init {
        this.images.value = emptyList()

        this.getFormUseCase.execute({ form ->
            name.postValue(form.name)
        })

        this.getImagesUseCase.execute({ images ->
            this.images.postValue(images)
        })
    }

    class Factory(private val getFormUseCase: GetFormUseCase,
                  private val getImagesUseCase: GetImagesUseCase) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                GalleryViewModel(getFormUseCase, getImagesUseCase) as T
    }
}
