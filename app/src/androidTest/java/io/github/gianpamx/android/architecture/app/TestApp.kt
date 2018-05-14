package io.github.gianpamx.android.architecture.app

class TestApp : App() {
    lateinit var testAppComponent: TestAppComponent

    override fun onCreate() {
        super.onCreate()

        testAppComponent = DaggerTestAppComponent.builder()
                .application(this)
                .build()

        testAppComponent.inject(this)
    }
}
