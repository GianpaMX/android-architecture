package io.github.gianpamx.android.architecture.gallery

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.GetImagesUseCase
import javax.inject.Inject

class GalleryViewModel @Inject constructor(
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
}
