package io.github.gianpamx.android.architecture.form

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import dagger.android.AndroidInjection
import io.github.gianpamx.android.architecture.entity.EmptyNameException
import io.github.gianpamx.android.architecture.entity.EmptyPhoneException
import io.github.gianpamx.android.architecture.gallery.GalleryActivity
import io.github.gianpamx.androidarchitecture.R
import kotlinx.android.synthetic.main.form_activity.*
import java.util.*
import javax.inject.Inject

class FormActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    lateinit var viewModel: FormViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(FormViewModel::class.java)

        setContentView(R.layout.form_activity)

        sendButton.setOnClickListener {
            viewModel.send(nameEditText.text.toString(), phoneEditText.text.toString())
        }

        viewModel.isFormSaved.observe(this, formSavedObserver)
        viewModel.dateTime.observe(this, dateObserver)
        viewModel.appVersion.observe(this, appVersionObserver)
        viewModel.error.observe(this, errorObserver)
    }

    private val formSavedObserver = Observer<Boolean> { isFormSaved ->
        isFormSaved?.let {
            if (it) {
                finish()
                startActivity(Intent(this, GalleryActivity::class.java))
            }
        }
    }

    private val dateObserver = Observer<Date> { date ->
        dateTimeTextView.text = DateFormat.format(getString(R.string.form_date_format), date)
    }

    private val appVersionObserver = Observer<String> { appVersion ->
        versionTextView.text = getString(R.string.form_version, appVersion)
    }

    private val errorObserver = Observer<Throwable> {
        if (it is EmptyNameException) {
            nameEditText.error = getString(R.string.form_empty_error)
        }

        if (it is EmptyPhoneException) {
            phoneEditText.error = getString(R.string.form_empty_error)
        }
    }
}
