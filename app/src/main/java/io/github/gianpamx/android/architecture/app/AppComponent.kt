package io.github.gianpamx.android.architecture.app

import dagger.Component
import io.github.gianpamx.android.architecture.form.FormComponent
import io.github.gianpamx.android.architecture.form.FormModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(app: App)
    fun getFormComponent(module: FormModule): FormComponent
}
