package com.example.cerericobalt

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.cerericobalt.manager.PermissionManager
import com.example.cerericobalt.utils.AppConstants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val isReadExternalGranted: Boolean by lazy {
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private val isWriteExternalGranted: Boolean by lazy {
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askForPermissions()

        paidLeave.setOnClickListener {
            this.goToFillRequestActivity(AppConstants.PAID_LEAVE)
        }
        leaveRequest.setOnClickListener {
            this.goToFillRequestActivity(AppConstants.LEAVE_REQUEST)
        }
    }

    private fun goToFillRequestActivity(requestType: String) {
        if (isReadExternalGranted && isWriteExternalGranted) {
            startActivity(
                Intent(this, FillRequestActivity::class.java)
                    .putExtra(AppConstants.REQUEST_TYPE, requestType)
            )
        } else {
            askForPermissions()
        }
    }

    private fun askForPermissions() {
        PermissionManager(this).askForPermissions(
            listOf(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ),
            onSuccess = {
            },
            onFail = {

            }
        )
    }

}