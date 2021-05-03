package com.cnd.cameratestmlkit.helper

import android.util.Log
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage

/**
 ** Written by CNStudio 03/05/2021 23.28
 ** Author @JoeFachrizal
 ** Happy Code...
 **/

class MyImageAnalyzer (private val listener: ScanningResultListener
) : ImageAnalysis.Analyzer {

    private var isScanning: Boolean = false

    @ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {
        scanBarcode(imageProxy)
    }

    @ExperimentalGetImage
    private fun scanBarcode(imageProxy: ImageProxy) {
        imageProxy.image?.let { image ->
            val inputImage = InputImage.fromMediaImage(image, imageProxy.imageInfo.rotationDegrees)
            val scanner = BarcodeScanning.getClient()

            isScanning = true
            scanner.process(inputImage)
                .addOnSuccessListener { barcodes ->
                    barcodes?.firstOrNull().let { barcode ->
                        val rawValue = barcode?.rawValue
                        rawValue?.let {
                            Log.d("Barcode", it)
                            listener.onScanned(it)
                        }
                    }

                    isScanning = false
                    imageProxy.close()
                }.addOnFailureListener {
                    isScanning = false
                    imageProxy.close()
                }
        }
    }
}