package io.github.gianpamx.android.architecture.providers

import java.util.*

interface DateTimeProvider {
    fun start(listener: Listener)

    fun stop()

    interface Listener {
        fun onTick(date: Date)
    }
}
