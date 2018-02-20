package io.github.gianpamx.android.architecture.app

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.gianpamx.android.architecture.providers.AndroidDateTimeProvider
import io.github.gianpamx.android.architecture.providers.AppVersionProvider
import io.github.gianpamx.android.architecture.providers.DateTimeProvider
import io.github.gianpamx.android.architecture.providers.VersionProvider
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.GetImagesUseCase
import io.github.gianpamx.android.architecture.usecase.SaveFormUseCase
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideDateTimeProvider(): DateTimeProvider = AndroidDateTimeProvider()

    @Provides
    @Singleton
    fun provideVersionProvider(context: Context): VersionProvider = AppVersionProvider(context)
}
