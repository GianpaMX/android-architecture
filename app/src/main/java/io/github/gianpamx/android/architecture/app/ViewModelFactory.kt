package io.github.gianpamx.android.architecture.app

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.github.gianpamx.android.architecture.form.FormViewModel
import io.github.gianpamx.android.architecture.providers.DateTimeProvider


class ViewModelFactory(val dateTimeProvider: DateTimeProvider) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormViewModel::class.java)) {
            return FormViewModel(dateTimeProvider) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
