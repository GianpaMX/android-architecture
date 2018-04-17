package io.github.gianpamx.android.architecture.gallery

import android.arch.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import io.github.gianpamx.android.architecture.usecase.GetFormUseCase
import io.github.gianpamx.android.architecture.usecase.GetImagesUseCase

@Module
class GalleryModule {

    @Provides
    fun provideGalleryViewModelFactory(getFormUseCase: GetFormUseCase, getImagesUseCase: GetImagesUseCase) =
            GalleryViewModel.Factory(getFormUseCase, getImagesUseCase)

    @Provides
    fun provideGalleryViewModel(activity: GalleryActivity, factory: GalleryViewModel.Factory) =
            ViewModelProviders.of(activity, factory).get(GalleryViewModel::class.java)
}
