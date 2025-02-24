package org.ghostprotocol.features.payments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment

class CryptoPaymentFragment : Fragment() {
    private lateinit var currencySpinner: Spinner
    private lateinit var amountInput: EditText
    private lateinit var sendButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_crypto_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
    }

    private fun setupViews(view: View) {
        currencySpinner = view.findViewById(R.id.currency_spinner)
        amountInput = view.findViewById(R.id.amount_input)
        sendButton = view.findViewById(R.id.send_button)

        sendButton.setOnClickListener {
            val amount = amountInput.text.toString().toDoubleOrNull()
            val currency = currencySpinner.selectedItem.toString()
            if (amount != null) {
                sendPayment(amount, currency)
            }
        }
    }

    private fun sendPayment(amount: Double, currency: String) {
        // Implementation for payment sending
    }
}
