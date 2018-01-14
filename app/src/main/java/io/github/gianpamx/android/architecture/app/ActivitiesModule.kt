package io.github.gianpamx.android.architecture.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.gianpamx.android.architecture.form.FormActivity
import io.github.gianpamx.android.architecture.form.FormModule


@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector(modules = arrayOf(FormModule::class))
    abstract fun bindFormActivity(): FormActivity
}
