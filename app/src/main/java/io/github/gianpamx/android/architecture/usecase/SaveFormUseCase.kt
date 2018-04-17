package io.github.gianpamx.android.architecture.usecase

import io.github.gianpamx.android.architecture.data.FormGateway
import io.github.gianpamx.android.architecture.entity.EmptyNameException
import io.github.gianpamx.android.architecture.entity.EmptyPhoneException
import io.github.gianpamx.android.architecture.entity.Form
import kotlin.concurrent.thread

interface SaveFormUseCase {
    fun execute(name: String?, phone: String?, success: (() -> Unit)? = null, failure: ((throwable: Throwable) -> Unit)? = null)
}

class SaveFormUseCaseImpl(private val formGateway: FormGateway) : SaveFormUseCase {
    override fun execute(name: String?, phone: String?, success: (() -> Unit)?, failure: ((throwable: Throwable) -> Unit)?) {
        thread {
            try {
                executeSync(name, phone)
                success?.invoke()
            } catch (t: Throwable) {
                failure?.invoke(t)
            }
        }
    }

    internal fun executeSync(name: String?, phone: String?) {
        if (name.isNullOrEmpty()) throw EmptyNameException()
        if (phone.isNullOrEmpty()) throw EmptyPhoneException()
        formGateway.persist(Form(name!!, phone!!)) // Safe due to previous check
    }
}
