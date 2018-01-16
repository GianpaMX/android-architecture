package io.github.gianpamx.android.architecture.app

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import io.github.gianpamx.android.architecture.form.FormViewModel
import io.github.gianpamx.android.architecture.gallery.GalleryViewModel
import io.github.gianpamx.android.architecture.providers.DateTimeProvider
import io.github.gianpamx.android.architecture.providers.VersionProvider
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.SaveFormUseCase


class ViewModelFactory(val dateTimeProvider: DateTimeProvider, val saveFormUseCase: SaveFormUseCase, val getFormUseCase: GetFormUseCase, val versionProvider: VersionProvider) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(FormViewModel::class.java) -> return FormViewModel(dateTimeProvider, saveFormUseCase, getFormUseCase, versionProvider) as T
            modelClass.isAssignableFrom(GalleryViewModel::class.java) -> return GalleryViewModel(getFormUseCase) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
