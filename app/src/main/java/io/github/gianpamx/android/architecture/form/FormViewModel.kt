package io.github.gianpamx.android.architecture.form

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.github.gianpamx.android.architecture.providers.DateTimeProvider
import io.github.gianpamx.android.architecture.usecase.SaveFormUseCase
import java.util.*

class FormViewModel : ViewModel, DateTimeProvider.Listener {
    val dateTimeProvider: DateTimeProvider
    val saveFormUseCase: SaveFormUseCase

    var dateTime: MutableLiveData<Date> = MutableLiveData()
    var isFormSaved: MutableLiveData<Boolean> = MutableLiveData()

    constructor(dateTimeProvider: DateTimeProvider, saveFormUseCase: SaveFormUseCase) {
        this.dateTimeProvider = dateTimeProvider
        this.dateTimeProvider.start(this)

        this.saveFormUseCase = saveFormUseCase
    }

    override fun onTick(date: Date) {
        dateTime.value = date
    }

    fun send(name: String, phone: String) {
        saveFormUseCase.execute(name, phone, {
            isFormSaved.value = true
        })
    }
}
