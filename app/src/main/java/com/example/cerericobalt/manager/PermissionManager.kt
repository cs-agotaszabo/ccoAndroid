package com.example.cerericobalt.manager

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener

class PermissionManager(private var context: Context) {

    fun askForPermissions(
        permissionsList: Collection<String>,
        onSuccess: (() -> Unit),
        onFail: (() -> Unit)
    ) {
        Dexter.withActivity(context as? Activity)
            .withPermissions(permissionsList)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionRationaleShouldBeShown(
                    permissions: MutableList<PermissionRequest>,
                    token: PermissionToken
                ) {
                    //continue until user denies it forever
                    token.continuePermissionRequest()
                }

                override fun onPermissionsChecked(report: MultiplePermissionsReport?) {
                    if (report != null) {
                        if (report.areAllPermissionsGranted()) {
                            //all asked permissions are granted => continue
                            onSuccess.invoke()
                        } else {
                            var denied4Ever = 0
                            //need to verify which is denied and which is denied forever
                            report.deniedPermissionResponses.map {
                                if (it.isPermanentlyDenied) {
                                    //need to check how many of them are permanently denied
                                    denied4Ever++
                                }
                            }
                            if (report.deniedPermissionResponses.size == denied4Ever) {
                                //all are denied forever => show snackbar
                                showPermissionDeniedSnackbar(context)
                            }
                            onFail()
                        }
                    }
                }
            }).check()
    }

    private fun showPermissionDeniedSnackbar(context: Context) {
        Toast.makeText(context, "Permission denied", Toast.LENGTH_LONG).show()
//        InAppMessageManager.createSnackBar(
//            view,
//            context.getString(R.string.permission_denied),
//            Snackbar.LENGTH_LONG,
//            context
//        )
//            .setAction(R.string.settings) {
//                val intent = Intent()
//                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
//                intent.data = Uri.fromParts("package", context.packageName, null)
//                context.startActivity(intent)
//            }
//            .show()
    }
}