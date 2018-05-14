package io.github.gianpamx.android.architecture.app

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import javax.inject.Provider
import javax.inject.Singleton

@Module
class ViewModelFactoryModule {
    @Provides
    @Singleton
    fun provideViewModelFactory(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val creator = creators[modelClass] ?: creators.entries.firstOrNull {
                modelClass.isAssignableFrom(it.key)
            }?.value ?: throw IllegalArgumentException("unknown model class $modelClass")
            try {
                @Suppress("UNCHECKED_CAST")
                return creator.get() as T
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
    }
}
