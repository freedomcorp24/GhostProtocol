package org.ghostprotocol.service.security

import android.content.Context
import android.os.CancellationSignal
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

class BiometricAuthManager(private val context: Context) {

    private val biometricManager = BiometricManager.from(context)
    private val executor = ContextCompat.getMainExecutor(context)

    fun canAuthenticate(): Boolean {
        return when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> true
            else -> false
        }
    }

    fun authenticate(
        title: String = "Verify Identity",
        subtitle: String? = null,
        description: String? = null,
        negativeButtonText: String = "Cancel",
        callback: (success: Boolean, error: String?) -> Unit
    ) {
        if (!canAuthenticate()) {
            callback(false, "Biometric authentication not available")
            return
        }

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .apply { subtitle?.let { setSubtitle(it) } }
            .apply { description?.let { setDescription(it) } }
            .setNegativeButtonText(negativeButtonText)
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG)
            .build()

        val biometricPrompt = BiometricPrompt(
            context as FragmentActivity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    callback(true, null)
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    when (errorCode) {
                        BiometricPrompt.ERROR_NEGATIVE_BUTTON -> callback(false, "Cancelled")
                        BiometricPrompt.ERROR_LOCKOUT -> callback(false, "Too many attempts. Try again later")
                        BiometricPrompt.ERROR_LOCKOUT_PERMANENT -> callback(false, "Biometric authentication disabled")
                        else -> callback(false, errString.toString())
                    }
                }

                override fun onAuthenticationFailed() {
                    callback(false, "Authentication failed")
                }
            }
        )

        val cancellationSignal = CancellationSignal()
        cancellationSignal.setOnCancelListener {
            callback(false, "Authentication cancelled")
        }

        biometricPrompt.authenticate(promptInfo)
    }
}
