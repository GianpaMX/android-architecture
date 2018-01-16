package io.github.gianpamx.android.architecture.usecase

import dagger.Module
import dagger.Provides
import io.github.gianpamx.android.architecture.data.FormGateway
import io.github.gianpamx.android.architecture.data.ImagesGateway

@Module
class UseCaseModule {
    @Provides
    fun provideSaveFormUseCase(formGateway: FormGateway): SaveFormUseCase = SaveFormUseCaseImpl(formGateway)

    @Provides
    fun provideGetFormUseCase(formGateway: FormGateway): GetFormUseCase = GetFormUseCaseImpl(formGateway)

    @Provides
    fun provideGetImagesUseCase(imagesGateway: ImagesGateway): GetImagesUseCase = GetImagesUseCaseImpl(imagesGateway)
}
