package io.github.gianpamx.android.architecture.form

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.github.gianpamx.android.architecture.providers.DateTimeProvider
import io.github.gianpamx.android.architecture.providers.VersionProvider
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.SaveFormUseCase
import java.util.*

class FormViewModel : ViewModel, DateTimeProvider.Listener {
    val dateTimeProvider: DateTimeProvider
    val saveFormUseCase: SaveFormUseCase
    val getFormUseCase: GetFormUseCase

    val dateTime = MutableLiveData<Date>()
    val isFormSaved = MutableLiveData<Boolean>()
    val appVersion = MutableLiveData<String>()

    constructor(dateTimeProvider: DateTimeProvider,
                saveFormUseCase: SaveFormUseCase,
                getFormUseCase: GetFormUseCase,
                versionProvider: VersionProvider) {
        this.dateTimeProvider = dateTimeProvider
        this.dateTimeProvider.start(this)

        this.saveFormUseCase = saveFormUseCase
        this.getFormUseCase = getFormUseCase

        appVersion.postValue(versionProvider.getVersion())

        isFormSaved.postValue(false)
        this.getFormUseCase.execute({ form ->
            isFormSaved.postValue(!form.name.isNullOrEmpty() && !form.phone.isNullOrEmpty())
        })
    }

    override fun onTick(date: Date) {
        dateTime.postValue(date)
    }

    fun send(name: String, phone: String) {
        saveFormUseCase.execute(name, phone, {
            isFormSaved.postValue(true)
        })
    }
}
