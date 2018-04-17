package io.github.gianpamx.android.architecture.data

import dagger.Module
import dagger.Provides
import io.github.gianpamx.android.architecture.data.room.FormDao
import io.github.gianpamx.android.architecture.data.room.FormRepository
import org.mockito.Mockito.mock
import javax.inject.Singleton

@Module
class MockModule {
    @Provides
    @Singleton
    fun providesFormDao(): FormDao {
        return mock(FormDao::class.java)
    }

    @Provides
    @Singleton
    fun provideFormGateway(formDao: FormDao): FormGateway = FormRepository(formDao)

    @Provides
    @Singleton
    fun provideImagesGateway(): ImagesGateway {
        return mock(ImagesGateway::class.java)
    }
}
