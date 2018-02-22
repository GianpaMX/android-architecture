package io.github.gianpamx.android.architecture.form

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import io.github.gianpamx.android.architecture.providers.DateTimeProvider
import io.github.gianpamx.android.architecture.providers.VersionProvider
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.SaveFormUseCase

@Module
class FormModule {
    @Provides
    fun provideGalleryViewModelFactory(dateTimeProvider: DateTimeProvider,
                                       saveFormUseCase: SaveFormUseCase,
                                       getFormUseCase: GetFormUseCase,
                                       versionProvider: VersionProvider) =
            FormViewModel.Factory(dateTimeProvider, saveFormUseCase, getFormUseCase, versionProvider)


    @Provides
    fun provideFormViewModel(activity: FormActivity,
                             dateTimeProvider: DateTimeProvider,
                             factory: FormViewModel.Factory) =
            ViewModelProviders.of(activity, factory).get(FormViewModel::class.java)
}
