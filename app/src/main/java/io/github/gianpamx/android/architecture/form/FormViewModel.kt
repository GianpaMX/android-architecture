package io.github.gianpamx.android.architecture.form

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.github.gianpamx.android.architecture.providers.DateTimeProvider
import io.github.gianpamx.android.architecture.providers.VersionProvider
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.SaveFormUseCase
import java.util.*

class FormViewModel(
        val dateTimeProvider: DateTimeProvider,
        val saveFormUseCase: SaveFormUseCase,
        val getFormUseCase: GetFormUseCase,
        versionProvider: VersionProvider
) : ViewModel(), DateTimeProvider.Listener {

    val dateTime = MutableLiveData<Date>()
    val isFormSaved = MutableLiveData<Boolean>()
    val appVersion = MutableLiveData<String>()
    val isEmpty = MutableLiveData<Boolean>()

    init {
        this.dateTimeProvider.start(this)

        appVersion.postValue(versionProvider.getVersion())

        isEmpty.postValue(false)

        isFormSaved.postValue(false)
        this.getFormUseCase.execute({ form ->
            isFormSaved.postValue(!isEmpty(form.name, form.phone))
        })
    }

    override fun onTick(date: Date) {
        dateTime.postValue(date)
    }

    fun send(name: String, phone: String) {
        isEmpty.postValue(false)
        if (isEmpty(name, phone)) {
            isEmpty.postValue(true)
            return
        }

        saveFormUseCase.execute(name, phone, {
            isFormSaved.postValue(true)
        })
    }

    private fun isEmpty(name: String?, phone: String?) =
            name?.isNullOrEmpty()!! || phone?.isNullOrEmpty()!!

    class Factory(
            private val dateTimeProvider: DateTimeProvider,
            private val saveFormUseCase: SaveFormUseCase,
            private val getFormUseCase: GetFormUseCase,
            private val versionProvider: VersionProvider) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                FormViewModel(dateTimeProvider, saveFormUseCase, getFormUseCase, versionProvider) as T
    }
}
