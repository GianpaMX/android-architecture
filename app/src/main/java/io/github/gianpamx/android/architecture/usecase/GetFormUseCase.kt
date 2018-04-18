package io.github.gianpamx.android.architecture.usecase

import io.github.gianpamx.android.architecture.data.FormGateway
import io.github.gianpamx.android.architecture.entity.Form
import kotlin.concurrent.thread

interface GetFormUseCase {
    fun execute(success: ((Form) -> Unit)? = null, failure: ((Throwable) -> Unit)? = null);
}

class GetFormUseCaseImpl(private val formGateway: FormGateway) : GetFormUseCase {
    override fun execute(success: ((Form) -> Unit)?, failure: ((Throwable) -> Unit)?) {
        thread {
            try {
                executeSync()?.let { success?.invoke(it) }
            } catch (t: Throwable) {
                failure?.invoke(t)
            }
        }
    }

    internal fun executeSync() = formGateway.findForm()
}
