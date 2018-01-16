package io.github.gianpamx.android.architecture.gallery

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides

@Module
class GalleryModule {
    @Provides
    fun provideFormViewModel(activity: GalleryActivity, factory: ViewModelProvider.Factory): GalleryViewModel {
        return ViewModelProviders.of(activity, factory).get(GalleryViewModel::class.java)
    }
}
