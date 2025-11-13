
package com.flam.rnd

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import android.graphics.SurfaceTexture
import android.os.Bundle
import android.util.Log
import android.view.TextureView
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : Activity() {
    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    private external fun processFrame(input: ByteArray, w: Int, h: Int): ByteArray

    private lateinit var textureView: TextureView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        textureView = TextureView(this)
        setContentView(textureView)

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1001)
        } else {
            startCameraPreview()
        }
    }

    @SuppressLint("MissingPermission")
    private fun startCameraPreview() {
        // Minimal placeholder: user must implement Camera2 capture and feed frames to native processFrame.
        // For scaffold, just log.
        Log.i("MainActivity", "startCameraPreview() - implement Camera2 to get frames and call processFrame()")
    }
}
