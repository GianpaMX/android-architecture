package io.github.gianpamx.android.architecture.usecase

import dagger.Module
import dagger.Provides
import io.github.gianpamx.android.architecture.data.FormGateway

@Module
class UseCaseModule {
    @Provides
    fun provideSaveFormUseCase(formGateway: FormGateway): SaveFormUseCase = SaveFormUseCaseImpl(formGateway)

    @Provides
    fun provideGetFormUseCase(): GetFormUseCase = GetFormUseCaseImpl()
}
