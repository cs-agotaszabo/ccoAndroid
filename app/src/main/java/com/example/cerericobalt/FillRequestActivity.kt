package com.example.cerericobalt

import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cerericobalt.utils.AppConstants
import io.github.lucasfsc.html2pdf.Html2Pdf
import kotlinx.android.synthetic.main.activity_fill_request.*
import java.io.File

class FillRequestActivity : AppCompatActivity(), Html2Pdf.OnCompleteConversion {
    private var requestType: String = AppConstants.PAID_LEAVE
    private var html: String = AppConstants.PAID_LEAVE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_request)

        requestType = intent?.extras?.getString(AppConstants.REQUEST_TYPE) ?: AppConstants.PAID_LEAVE

        setExtraData()
        // Enables Always-on
        convertButton.setOnClickListener {
            convertToPDF()
        }
    }

    private fun setExtraData() {
        if(requestType == AppConstants.PAID_LEAVE) {
            paidLeave.visibility = View.VISIBLE
            html = AppConstants.CONCEDIU_HTML
        } else {
            leaveRequest.visibility = View.VISIBLE
            html = AppConstants.INVOIRE_HTML
        }
    }

    private fun convertToPDF() {
        val file = getFile()
        val htmlString = extractAndSetData()
        val converter = Html2Pdf.Companion.Builder()
            .context(this)
            .file(file)
            .html(htmlString)
            .build()
        converter.convertToPdf(this)
    }

    private fun getFile(): File {
        val path = Environment.getExternalStorageDirectory().absolutePath + "/" + "CereriCobalt"
        val fileName = "Test.pdf"
        val dir = File(path);
        if (!dir.exists())
            dir.mkdirs()
        return File(dir, fileName)
    }

    private fun extractAndSetData(): String {
        val employeeName = "${employeeFirstName.text.toString()} ${employeeLastName.text.toString()}".capitalize()
        html = html.replace(AppConstants.EMPLOYEE_NAME_KEY, employeeName, true)

        val employeeOccupiedFunction = employeeFunction.text.toString().capitalize()
        html = html.replace(
            AppConstants.EMPLOYEE_OCCUPIED_POSITION,
            employeeOccupiedFunction,
            true
        )

        return html
    }

    override fun onFailed() {
        Toast.makeText(this, "failed", Toast.LENGTH_LONG).show()
    }

    override fun onSuccess() {
        Toast.makeText(this, "success", Toast.LENGTH_LONG).show()
    }
}