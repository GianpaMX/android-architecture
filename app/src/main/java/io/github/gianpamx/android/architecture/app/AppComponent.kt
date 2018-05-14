package io.github.gianpamx.android.architecture.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.github.gianpamx.android.architecture.data.retrofit.RetrofitModule
import io.github.gianpamx.android.architecture.data.room.RoomModule
import io.github.gianpamx.android.architecture.usecase.UseCaseModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    Binder::class,
    UseCaseModule::class,
    RoomModule::class,
    RetrofitModule::class
])
interface AppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(app: App)
}
