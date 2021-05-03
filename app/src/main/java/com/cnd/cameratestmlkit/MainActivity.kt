package com.cnd.cameratestmlkit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cnd.cameratestmlkit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val cameraPermissionRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnScan.setOnClickListener {
//            if (ContextCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.CAMERA
//                ) == PackageManager.PERMISSION_GRANTED
//            ) {
                val i = Intent(this, ScanActivity::class.java)
                this.startActivity(i)
//            } else {
//                ActivityCompat.requestPermissions(
//                    this, arrayOf(Manifest.permission.CAMERA),
//                    cameraPermissionRequestCode
//                )
//            }
        }
    }
}