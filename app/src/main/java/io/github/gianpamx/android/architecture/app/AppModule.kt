package io.github.gianpamx.android.architecture.app

import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import io.github.gianpamx.android.architecture.form.FormComponent
import io.github.gianpamx.android.architecture.providers.AndroidDateTimeProvider
import io.github.gianpamx.android.architecture.providers.DateTimeProvider
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.SaveFormUseCase
import javax.inject.Singleton

@Module(subcomponents = arrayOf(FormComponent::class))
class AppModule {
    @Provides
    @Singleton
    fun provideDateTimeProvider(): DateTimeProvider = AndroidDateTimeProvider()

    @Provides
    @Singleton
    fun provideViewModelFactory(dateTimeProvider: DateTimeProvider, saveFormUseCase: SaveFormUseCase, getFormUseCase: GetFormUseCase): ViewModelProvider.Factory {
        return ViewModelFactory(dateTimeProvider, saveFormUseCase, getFormUseCase)
    }
}
