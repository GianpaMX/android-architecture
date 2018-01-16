package io.github.gianpamx.android.architecture.usecase

import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock

@Module
class TestUseCaseModule {
    @Provides
    fun provideSaveFormUseCase(): SaveFormUseCase {
        return mock(SaveFormUseCase::class.java)
    }

    @Provides
    fun provideGetFormUseCase(): GetFormUseCase {
        return mock(GetFormUseCase::class.java)
    }

    @Provides
    fun provideGetImagesUseCase(): GetImagesUseCase {
        return mock(GetImagesUseCase::class.java)
    }
}
