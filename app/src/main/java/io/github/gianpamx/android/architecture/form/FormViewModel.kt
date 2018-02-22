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
    val error = MutableLiveData<Throwable>()

    init {
        this.dateTimeProvider.start(this)

        appVersion.postValue(versionProvider.getVersion())

        isFormSaved.postValue(false)
        this.getFormUseCase.execute({ form ->
            isFormSaved.postValue(true)
        })
    }

    override fun onTick(date: Date) {
        dateTime.postValue(date)
    }

    fun send(name: String?, phone: String?) {
        saveFormUseCase.execute(name, phone, {
            isFormSaved.postValue(true)
        }, {
            error.postValue(it)
        })
    }

    class Factory(
            private val dateTimeProvider: DateTimeProvider,
            private val saveFormUseCase: SaveFormUseCase,
            private val getFormUseCase: GetFormUseCase,
            private val versionProvider: VersionProvider) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                FormViewModel(dateTimeProvider, saveFormUseCase, getFormUseCase, versionProvider) as T
    }
}
