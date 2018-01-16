package io.github.gianpamx.android.architecture.providers

import android.content.Context

class AppVersionProvider(val context: Context) : VersionProvider {
    override fun getVersion(): String = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName
}
