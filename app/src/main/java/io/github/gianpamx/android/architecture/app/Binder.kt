package io.github.gianpamx.android.architecture.app

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import io.github.gianpamx.android.architecture.form.FormActivity
import io.github.gianpamx.android.architecture.form.FormViewModel
import io.github.gianpamx.android.architecture.gallery.GalleryActivity
import io.github.gianpamx.android.architecture.gallery.GalleryViewModel


@Module(includes = [ViewModelFactoryModule::class])
abstract class Binder {
    @ContributesAndroidInjector
    abstract fun bindFormActivity(): FormActivity

    @Binds
    @IntoMap
    @ViewModelKey(FormViewModel::class)
    abstract fun bindMainViewModel(viewModel: FormViewModel): ViewModel


    @ContributesAndroidInjector
    abstract fun bindGalleryActivity(): GalleryActivity

    @Binds
    @IntoMap
    @ViewModelKey(GalleryViewModel::class)
    abstract fun bindGalleryViewModel(viewModel: GalleryViewModel): ViewModel
}
