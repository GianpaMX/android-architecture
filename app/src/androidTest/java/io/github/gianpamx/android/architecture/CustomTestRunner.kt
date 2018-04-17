package io.github.gianpamx.android.architecture

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import io.github.gianpamx.android.architecture.app.TestApp


class CustomTestRunner : AndroidJUnitRunner() {
    @Throws(InstantiationException::class, IllegalAccessException::class, ClassNotFoundException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, TestApp::class.java.getName(), context)
    }
}
