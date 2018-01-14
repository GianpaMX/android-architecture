package io.github.gianpamx.android.architecture.providers

import android.os.Handler
import java.util.*

class AndroidDateTimeProvider : DateTimeProvider {
    private val ONE_SECOND: Long = 1000

    private var handler: Handler? = null

    private var isTickerActive: Boolean = false

    private lateinit var listener: DateTimeProvider.Listener

    private val ticker = object : Runnable {
        override fun run() {
            if (!isTickerActive) {
                handler = null;
                return
            }

            handler?.postDelayed(this, ONE_SECOND)
            listener.onTick(Date())
        }
    }

    override fun start(listener: DateTimeProvider.Listener) {
        this.listener = listener

        isTickerActive = true
        handler = Handler()
        handler?.postDelayed(ticker, 0)
    }

    override fun stop() {
        isTickerActive = false
    }
}
