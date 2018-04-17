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
        dateTimeProvider: DateTimeProvider,
        private val saveFormUseCase: SaveFormUseCase,
        private val getFormUseCase: GetFormUseCase,
        versionProvider: VersionProvider
) : ViewModel(), DateTimeProvider.Listener {

    val dateTime = MutableLiveData<Date>()
    val isFormSaved = MutableLiveData<Boolean>()
    val appVersion = MutableLiveData<String>()
    val error = MutableLiveData<Throwable>()

    init {
        dateTimeProvider.start(this)

        appVersion.postValue(versionProvider.getVersion())

        isFormSaved.postValue(false)
        this.getFormUseCase.execute({
            isFormSaved.postValue(true)
        })
    }

    override fun onTick(date: Date) {
        dateTime.postValue(date)
    }

    fun send(name: String?, phone: String?) {
        saveFormUseCase.execute(name, phone,
                success = { isFormSaved.postValue(true) },
                failure = { error.postValue(it) }
        )
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(
            private val dateTimeProvider: DateTimeProvider,
            private val saveFormUseCase: SaveFormUseCase,
            private val getFormUseCase: GetFormUseCase,
            private val versionProvider: VersionProvider) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                FormViewModel(dateTimeProvider, saveFormUseCase, getFormUseCase, versionProvider) as T
    }
}
