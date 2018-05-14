package io.github.gianpamx.android.architecture.app

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


open class App : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    protected fun inject() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    override fun activityInjector() = dispatchingActivityInjector
}
