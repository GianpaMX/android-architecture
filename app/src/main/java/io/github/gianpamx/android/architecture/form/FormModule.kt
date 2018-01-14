package io.github.gianpamx.android.architecture.form

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import io.github.gianpamx.android.architecture.providers.DateTimeProvider

@Module
class FormModule {
    @Provides
    fun provideFormViewModel(activity: FormActivity, dateTimeProvider: DateTimeProvider, factory: ViewModelProvider.Factory): FormViewModel {
        return ViewModelProviders.of(activity, factory).get(FormViewModel::class.java)
    }
}
