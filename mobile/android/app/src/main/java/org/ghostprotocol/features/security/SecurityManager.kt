package org.ghostprotocol.features.security

import android.content.Context
import android.hardware.biometrics.BiometricPrompt
import android.os.CancellationSignal
import android.view.WindowManager

class SecurityManager private constructor(private val context: Context) {
    fun enableScreenshotPrevention(activity: android.app.Activity) {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
    }

    fun setupBiometricAuth(
        title: String,
        callback: (success: Boolean, error: String?) -> Unit
    ) {
        val cancellationSignal = CancellationSignal()
        val authCallback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                callback(true, null)
            }

            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                callback(false, errString.toString())
            }
        }

        val prompt = BiometricPrompt.Builder(context)
            .setTitle(title)
            .setNegativeButton("Cancel", context.mainExecutor) { _, _ ->
                callback(false, "Cancelled")
            }
            .build()

        prompt.authenticate(
            cancellationSignal,
            context.mainExecutor,
            authCallback
        )
    }

    fun createDecoyAccount(duressPassword: String, callback: (success: Boolean) -> Unit) {
        // Implementation for decoy account creation
    }

    companion object {
        @Volatile
        private var instance: SecurityManager? = null

        fun getInstance(context: Context): SecurityManager {
            return instance ?: synchronized(this) {
                instance ?: SecurityManager(context).also { instance = it }
            }
        }
    }
}
