package io.github.gianpamx.android.architecture.form

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.DateFormat
import dagger.android.AndroidInjection
import io.github.gianpamx.androidarchitecure.R
import kotlinx.android.synthetic.main.form_activity.*
import javax.inject.Inject

class FormActivity : AppCompatActivity() {

    @Inject
    lateinit var formViewModel: FormViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)

        setContentView(R.layout.form_activity)

        formViewModel.dateTime.observe(this, Observer { date ->
            dateTimeTextView.text = DateFormat.format(getString(R.string.form_date_format), date)
        })
    }
}
