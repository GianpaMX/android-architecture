package io.github.gianpamx.android.architecture.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.github.gianpamx.android.architecture.data.room.RoomModule
import io.github.gianpamx.android.architecture.usecase.UseCaseModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
        AndroidInjectionModule::class,
        AppModule::class,
        ActivitiesModule::class,
        UseCaseModule::class,
        RoomModule::class
))
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(app: App)
}
