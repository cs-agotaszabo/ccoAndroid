package com.example.cerericobalt

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cerericobalt.utils.AppConstants
import io.github.lucasfsc.html2pdf.Html2Pdf
import kotlinx.android.synthetic.main.activity_fill_request.*
import java.io.File
import java.util.*

class FillRequestActivity : AppCompatActivity(), Html2Pdf.OnCompleteConversion {
    private var requestType: String = AppConstants.PAID_LEAVE
    private var html: String = AppConstants.PAID_LEAVE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_request)
        requestType =
            intent?.extras?.getString(AppConstants.REQUEST_TYPE) ?: AppConstants.PAID_LEAVE
        setExtraData()
        setClickListeners()
    }

    private fun setClickListeners() {
        convertButton.setOnClickListener {
            convertToPDF()
        }

        startDate.setOnClickListener {
            openDaTePickerDialog(startDate)
        }
        endDate.setOnClickListener {
            openDaTePickerDialog(endDate)
        }
        leaveDate.setOnClickListener {
            openDaTePickerDialog(leaveDate)
        }
        fillDateField.setOnClickListener {
            openDaTePickerDialog(fillDate)
        }
        startTime.setOnClickListener {
            openTimePickerDialog(startTime)
        }
        endTime.setOnClickListener {
            openTimePickerDialog(endTime)
        }
        setFillDate()
    }

    private fun openDaTePickerDialog(textView: TextView) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                // Display Selected date in textbox
                val monthV = if (monthOfYear+1 < 10) "0${monthOfYear+1}" else (monthOfYear+1).toString()
                textView.text = ("$dayOfMonth. $monthV. $year.")
            }, year, month, day
        )
        dpd.show()
    }

    private fun openTimePickerDialog(textView: TextView) {
        val currentTime = Calendar.getInstance()
        val hour = currentTime.get(Calendar.HOUR_OF_DAY)
        val minute = currentTime.get(Calendar.MINUTE)
        val tpd = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                textView.text = String.format("$hourOfDay: $minute")
            }, hour, minute, false
        )
        tpd.show()
    }

    private fun setExtraData() {
        if (requestType == AppConstants.PAID_LEAVE) {
            paidLeave.visibility = View.VISIBLE
            html = AppConstants.CONCEDIU_HTML
        } else {
            leaveRequest.visibility = View.VISIBLE
            html = AppConstants.INVOIRE_HTML
        }
        setFillDate()
    }

    private fun setFillDate() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)
        val monthV = if (month < 10) "0${month}" else month.toString()
        fillDate.text = "$day. $monthV. $year."
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
        val employeeName =
            "${employeeFirstName.text.toString()} ${employeeLastName.text.toString()}".capitalize()
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