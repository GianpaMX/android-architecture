package io.github.gianpamx.android.architecture.form

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import dagger.android.AndroidInjection
import io.github.gianpamx.android.architecture.gallery.GalleryActivity
import io.github.gianpamx.androidarchitecture.R
import kotlinx.android.synthetic.main.form_activity.*
import javax.inject.Inject

class FormActivity : AppCompatActivity() {

    @Inject
    lateinit var formViewModel: FormViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)

        setContentView(R.layout.form_activity)

        sendButton.setOnClickListener {
            formViewModel.send(nameEditText.text.toString(), phoneEditText.text.toString())
        }

        formViewModel.isFormSaved.observe(this, Observer { isFormSaved ->
            if (isFormSaved!!) {
                finish()
                startActivity(Intent(this, GalleryActivity::class.java))
            }
        })

        formViewModel.dateTime.observe(this, Observer { date ->
            dateTimeTextView.text = DateFormat.format(getString(R.string.form_date_format), date)
        })
    }
}
