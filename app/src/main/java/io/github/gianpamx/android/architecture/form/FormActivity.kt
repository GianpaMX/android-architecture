package io.github.gianpamx.android.architecture.form

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.gianpamx.android.architecture.app.App
import io.github.gianpamx.androidarchitecure.R

class FormActivity : AppCompatActivity() {
    val component by lazy {
        (application as App).component.getFormComponent(FormModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.form_activity)
        component.inject(this)
    }
}
