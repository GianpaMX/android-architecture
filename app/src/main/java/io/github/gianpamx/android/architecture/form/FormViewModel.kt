package io.github.gianpamx.android.architecture.form

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.github.gianpamx.android.architecture.providers.DateTimeProvider
import java.util.*

class FormViewModel : ViewModel, DateTimeProvider.Listener {
    var dateTimeProvider: DateTimeProvider

    var dateTime: MutableLiveData<Date> = MutableLiveData()

    constructor(dateTimeProvider: DateTimeProvider) {
        this.dateTimeProvider = dateTimeProvider
        this.dateTimeProvider.start(this)
    }

    override fun onTick(date: Date) {
        dateTime.value = date
    }
}
