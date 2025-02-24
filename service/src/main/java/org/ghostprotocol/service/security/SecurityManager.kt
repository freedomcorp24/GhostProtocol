package org.ghostprotocol.service.security

import android.content.Context
import android.content.SharedPreferences
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

class SecurityManager(context: Context) {
    private val biometricAuthManager = BiometricAuthManager(context)
    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("ghost_protocol_security", Context.MODE_PRIVATE)
    
    companion object {
        private const val BIOMETRIC_KEY_NAME = "ghost_protocol_biometric_key"
        private const val PATTERN_KEY = "pattern_hash"
        private const val DURESS_PATTERN_KEY = "duress_pattern_hash"
    }

    fun setupBiometricAuth(
        title: String = "Verify Identity",
        subtitle: String? = null,
        description: String? = null,
        callback: (success: Boolean, error: String?) -> Unit
    ) {
        if (!biometricAuthManager.canAuthenticate()) {
            callback(false, "Biometric authentication not available")
            return
        }

        // Generate encryption key that requires biometric auth
        generateBiometricKey()

        biometricAuthManager.authenticate(
            title = title,
            subtitle = subtitle,
            description = description
        ) { success, error ->
            if (success) {
                // Store successful biometric setup
                sharedPreferences.edit()
                    .putBoolean("biometric_enabled", true)
                    .apply()
            }
            callback(success, error)
        }
    }

    fun authenticateWithBiometrics(callback: (success: Boolean, error: String?) -> Unit) {
        biometricAuthManager.authenticate { success, error ->
            if (success) {
                try {
                    // Verify biometric key is still valid
                    val cipher = getCipher()
                    cipher.init(Cipher.ENCRYPT_MODE, getBiometricKey())
                    callback(true, null)
                } catch (e: Exception) {
                    callback(false, "Biometric key validation failed")
                }
            } else {
                callback(false, error)
            }
        }
    }

    fun storePattern(pattern: String, isDuress: Boolean = false) {
        val key = if (isDuress) DURESS_PATTERN_KEY else PATTERN_KEY
        sharedPreferences.edit()
            .putString(key, hashPattern(pattern))
            .apply()
    }

    fun verifyPattern(pattern: String, isDuress: Boolean = false): Boolean {
        val key = if (isDuress) DURESS_PATTERN_KEY else PATTERN_KEY
        val storedHash = sharedPreferences.getString(key, null)
        return storedHash != null && storedHash == hashPattern(pattern)
    }

    private fun generateBiometricKey() {
        val keyGenerator = KeyGenerator.getInstance(
            KeyProperties.KEY_ALGORITHM_AES,
            "AndroidKeyStore"
        )

        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
            BIOMETRIC_KEY_NAME,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_CBC)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
            .setUserAuthenticationRequired(true)
            .setInvalidatedByBiometricEnrollment(true)
            .build()

        keyGenerator.init(keyGenParameterSpec)
        keyGenerator.generateKey()
    }

    private fun getBiometricKey(): SecretKey {
        return (keyStore.getEntry(BIOMETRIC_KEY_NAME, null) as KeyStore.SecretKeyEntry).secretKey
    }

    private fun getCipher(): Cipher {
        return Cipher.getInstance(
            KeyProperties.KEY_ALGORITHM_AES + "/"
                    + KeyProperties.BLOCK_MODE_CBC + "/"
                    + KeyProperties.ENCRYPTION_PADDING_PKCS7
        )
    }

    private fun hashPattern(pattern: String): String {
        return java.security.MessageDigest
            .getInstance("SHA-256")
            .digest(pattern.toByteArray())
            .fold("") { str, byte -> str + "%02x".format(byte) }
    }
}
