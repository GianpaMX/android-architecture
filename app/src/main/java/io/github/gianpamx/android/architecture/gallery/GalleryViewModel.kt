package io.github.gianpamx.android.architecture.gallery

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase

class GalleryViewModel : ViewModel {
    val getFormUseCase: GetFormUseCase

    val name: MutableLiveData<String> = MutableLiveData()

    constructor(getFormUseCase: GetFormUseCase) {
        this.getFormUseCase = getFormUseCase

        this.getFormUseCase.execute({ form ->
            name.postValue(form.name)
        })
    }
}
