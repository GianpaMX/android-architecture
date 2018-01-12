package io.github.gianpamx.android.architecture.form

import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(modules = arrayOf(FormModule::class))
interface FormComponent {
    fun inject(activity: FormActivity)
}
