package io.github.gianpamx.android.architecture.gallery

import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(GalleryModule::class))
interface GalleryComponent {
    @Subcomponent.Builder
    interface Builder {

        @BindsInstance
        fun activity(activity: GalleryActivity): Builder

        fun build(): GalleryComponent

    }

    fun inject(activity: GalleryActivity)
}
