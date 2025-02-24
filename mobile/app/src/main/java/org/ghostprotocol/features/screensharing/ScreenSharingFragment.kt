package org.ghostprotocol.features.screensharing

import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import androidx.fragment.app.Fragment

class ScreenSharingFragment : Fragment() {
    private lateinit var qualitySelector: RadioGroup
    private lateinit var startButton: Button
    private var isSharing = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_screen_sharing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
    }

    private fun setupViews(view: View) {
        qualitySelector = view.findViewById(R.id.quality_selector)
        startButton = view.findViewById(R.id.start_button)

        startButton.setOnClickListener {
            if (!isSharing) {
                startScreenSharing()
            } else {
                stopScreenSharing()
            }
        }

        qualitySelector.setOnCheckedChangeListener { _, checkedId ->
            val resolution = when (checkedId) {
                R.id.quality_high -> Resolution(1280, 720)
                else -> Resolution(854, 480)
            }
            updateScreenShareResolution(resolution)
        }
    }

    private fun startScreenSharing() {
        val mediaProjectionManager = requireContext().getSystemService(
            MediaProjectionManager::class.java
        )
        startActivityForResult(
            mediaProjectionManager.createScreenCaptureIntent(),
            REQUEST_MEDIA_PROJECTION
        )
    }

    private fun stopScreenSharing() {
        // Implementation for stopping screen share
        isSharing = false
        startButton.text = "Start Sharing"
    }

    private fun updateScreenShareResolution(resolution: Resolution) {
        // Implementation for resolution update
    }

    data class Resolution(val width: Int, val height: Int)

    companion object {
        private const val REQUEST_MEDIA_PROJECTION = 1
    }
}
