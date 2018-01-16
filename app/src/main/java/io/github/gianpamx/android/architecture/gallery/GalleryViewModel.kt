package io.github.gianpamx.android.architecture.gallery

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.GetImagesUseCase

class GalleryViewModel : ViewModel {
    val getFormUseCase: GetFormUseCase
    val getImagesUseCase: GetImagesUseCase

    val name = MutableLiveData<String>()
    val images = MutableLiveData<List<String>>()

    constructor(getFormUseCase: GetFormUseCase, getImagesUseCase: GetImagesUseCase) {
        this.getFormUseCase = getFormUseCase
        this.getImagesUseCase = getImagesUseCase

        this.images.value = emptyList()

        this.getFormUseCase.execute({ form ->
            name.postValue(form.name)
        })

        this.getImagesUseCase.execute({ images ->
            this.images.postValue(images)
        })
    }
}
