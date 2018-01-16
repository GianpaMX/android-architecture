package io.github.gianpamx.android.architecture.data.room

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.gianpamx.android.architecture.data.FormGateway

@Module
class RoomModule {
    @Provides
    fun providesAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "database").build()

    @Provides
    fun providesFormDao(database: AppDatabase) = database.formDao()

    @Provides
    fun provideFormGateway(formDao: FormDao): FormGateway = FormRepository(formDao)
}
