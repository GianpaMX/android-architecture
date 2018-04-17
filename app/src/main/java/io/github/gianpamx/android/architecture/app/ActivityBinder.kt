package io.github.gianpamx.android.architecture.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.github.gianpamx.android.architecture.form.FormActivity
import io.github.gianpamx.android.architecture.form.FormModule
import io.github.gianpamx.android.architecture.gallery.GalleryActivity
import io.github.gianpamx.android.architecture.gallery.GalleryModule


@Module
abstract class ActivityBinder {
    @ContributesAndroidInjector(modules = [FormModule::class])
    abstract fun bindFormActivity(): FormActivity

    @ContributesAndroidInjector(modules = [GalleryModule::class])
    abstract fun bindGalleryActivity(): GalleryActivity
}
