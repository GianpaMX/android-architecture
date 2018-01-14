package io.github.gianpamx.android.architecture.form

import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(FormModule::class))
interface FormComponent {
    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun activity(activity: FormActivity): Builder

        fun build(): FormComponent

    }

    fun inject(activity: FormActivity)
}
