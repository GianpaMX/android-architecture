package io.github.gianpamx.android.architecture.usecase

import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideSaveFormUseCase(): SaveFormUseCase = SaveFormUseCaseImpl()
}
