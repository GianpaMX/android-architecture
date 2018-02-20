package io.github.gianpamx.android.architecture.app

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.github.gianpamx.android.architecture.usecase.TestUseCaseModule
import javax.inject.Singleton


@Singleton
@Component(modules = [
AndroidInjectionModule::class,
AppModule::class,
ActivityBinder::class,
TestUseCaseModule::class])
interface TestAppComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent

    }

    fun inject(app: TestApp)
}
