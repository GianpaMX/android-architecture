package io.github.gianpamx.android.architecture.data

import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import javax.inject.Singleton

@Module
class MockModule {
    @Provides
    @Singleton
    fun provideFormGateway(): FormGateway {
        return mock(FormGateway::class.java)
    }

    @Provides
    @Singleton
    fun provideImagesGateway(): ImagesGateway {
        return mock(ImagesGateway::class.java)
    }
}
