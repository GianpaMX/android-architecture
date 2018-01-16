package io.github.gianpamx.android.architecture.app

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.github.gianpamx.android.architecture.form.FormViewModel
import io.github.gianpamx.android.architecture.providers.DateTimeProvider
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.SaveFormUseCase


class ViewModelFactory(val dateTimeProvider: DateTimeProvider, val saveFormUseCase: SaveFormUseCase, val getFormUseCase: GetFormUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormViewModel::class.java)) {
            return FormViewModel(dateTimeProvider, saveFormUseCase, getFormUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
