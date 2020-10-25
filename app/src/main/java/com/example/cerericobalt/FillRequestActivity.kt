package com.example.cerericobalt

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cerericobalt.utils.AppConstants
import com.example.cerericobalt.utils.DateHelper
import io.github.lucasfsc.html2pdf.Html2Pdf
import kotlinx.android.synthetic.main.activity_fill_request.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*


class FillRequestActivity : AppCompatActivity(), Html2Pdf.OnCompleteConversion {
    private var requestType: String = AppConstants.PAID_LEAVE
    private var html: String = AppConstants.PAID_LEAVE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_request)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        requestType =
            intent?.extras?.getString(AppConstants.REQUEST_TYPE) ?: AppConstants.PAID_LEAVE
        setExtraData()
        setClickListeners()
    }

    private fun setClickListeners() {
        convertButton.setOnClickListener {
            convertToPDF()
        }
        signatureButton.setOnClickListener {
            startActivity(
                Intent(this, SignatureActivity::class.java)
            )
        }
        startDate.setOnClickListener {
            openDatePickerDialog(startDate)
        }
        endDate.setOnClickListener {
            openDatePickerDialog(endDate)
        }
        leaveDate.setOnClickListener {
            openDatePickerDialog(leaveDate)
        }
        fillDateField.setOnClickListener {
            openDatePickerDialog(fillDate)
        }
        startTime.setOnClickListener {
            openTimePickerDialog(startTime)
        }
        endTime.setOnClickListener {
            openTimePickerDialog(endTime)
        }
        setFillDate()
    }

    private fun openDatePickerDialog(textView: TextView) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                val monthV =
                    if (monthOfYear + 1 < 10) "0${monthOfYear + 1}" else (monthOfYear + 1).toString()
                val dayV = if (dayOfMonth < 10) "0$dayOfMonth" else dayOfMonth.toString()
                textView.text = ("$dayV. $monthV. $year")
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
            pageTitle.text = getString(R.string.paid_leave)
            html = AppConstants.CONCEDIU_HTML
        } else {
            leaveRequest.visibility = View.VISIBLE
            pageTitle.text = getString(R.string.leave_request)
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
        fillDate.text = String.format("$day. $monthV. $year")
    }

    private fun convertToPDF() {
        if (!isFormIncomplete()) {
            if (isSignatureSaved()) {
                val converter = Html2Pdf.Companion.Builder()
                    .context(this)
                    .file(getFile())
                    .html(extractAndSetData())
                    .build()
                converter.convertToPdf(this)
            }
        } else {
            AlertDialog.Builder(this)
                .setMessage("Toate câmpurile sunt obligatorii!")
                .show()

        }
    }

    private fun getFile(): File {
        val path = Environment.getExternalStorageDirectory().absolutePath + "/" + "CereriCobalt"
        val fileName = getFileName()
        val dir = File(path);
        if (!dir.exists())
            dir.mkdirs()
        return File(dir, fileName)
    }

    private fun getFileName(): String {
        val employeeName =
            "${employeeFirstName.text.toString()}_${employeeLastName.text.toString()}".capitalize()

        return if (requestType == AppConstants.PAID_LEAVE) {
            "${AppConstants.CERERE_CONCEDIU}_$employeeName.pdf"
        } else {
            "${AppConstants.CERERE_INVOIRE}_$employeeName.pdf"
        }
    }

    private fun getImage() {
        val bm =
            BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().absolutePath + "/" + "CereriCobalt/signature.jpeg")
        val baos = ByteArrayOutputStream()
        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos) // bm is the bitmap object
        val b: ByteArray = baos.toByteArray()
        val encodedImage: String = Base64.getEncoder().encodeToString(b)
        html = html.replaceFirst(AppConstants.SIGNATURE_KEY, "data:image/jpg;base64,$encodedImage")

    }

    private fun extractAndSetData(): String {
        getImage()
        val employeeName =
            "${employeeFirstName.text.toString()} ${employeeLastName.text.toString()}"
                .toUpperCase(Locale.getDefault())
        html = html.replaceFirst(AppConstants.EMPLOYEE_NAME_KEY, employeeName, true)

        val employeeOccupiedFunction =
            employeeFunction.text.toString().toUpperCase(Locale.getDefault())
        html = html.replaceFirst(
            AppConstants.EMPLOYEE_OCCUPIED_POSITION,
            employeeOccupiedFunction,
            true
        )

        val fillDate = fillDate.text.toString().replace(". ", "/")
        html = html.replaceFirst(AppConstants.FILL_DATE_KEY, fillDate, true)

        if (requestType == AppConstants.PAID_LEAVE) {
            val startDate = startDate.text.toString()
            html = html.replaceFirst(AppConstants.START_DATE_KEY, startDate, true)
            val endDate = endDate.text.toString()
            html = html.replaceFirst(AppConstants.END_DATE_KEY, endDate, true)
            val nrOfDays = DateHelper.getBusinessDays(startDate, endDate)
            html = html.replaceFirst(AppConstants.NR_OF_DAYS_KEY, nrOfDays)
        } else {
            val date = leaveDate.text.toString().replace(". ", "/")
            html = html.replaceFirst(AppConstants.LEAVE_DATE_KEY, date)
            html = html.replaceFirst(
                AppConstants.START_TIME_KEY,
                startTime.text.toString().replace(" ", "")
            )
            html = html.replaceFirst(
                AppConstants.END_TIME_KEY,
                endTime.text.toString().replace(" ", "")
            )
            html = html.replaceFirst(
                AppConstants.REQUEST_REASON_KEY,
                requestReason.text.toString().toUpperCase(Locale.getDefault())
            )
            html = html.replaceFirst(
                AppConstants.RECUPERATION_PERIOD_KEY,
                recuperationPeriod.text.toString().toUpperCase(Locale.getDefault())
            )
        }
        return html
    }

    private fun isFormIncomplete(): Boolean {
        val requiredCommonFields =
            employeeFirstName.text.isNullOrBlank() || employeeLastName.text.isNullOrBlank() || employeeFunction.text.isNullOrBlank() ?: false
        val requiredFields = if (requestType == AppConstants.PAID_LEAVE) {
            startDate.text.isNullOrBlank() || endDate.text.isNullOrBlank()
        } else {
            leaveDate.text.isNullOrBlank() || startTime.text.isNullOrBlank() || endTime.text.isNullOrBlank() || requestReason.text.isNullOrBlank() || recuperationPeriod.text.isNullOrBlank()
        }
        return requiredCommonFields || requiredFields
    }

    private fun isSignatureSaved(): Boolean {
        val bm =
            BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().absolutePath + "/" + "CereriCobalt/signature.jpeg")
        return if (bm !== null) {
            true
        } else {
            AlertDialog.Builder(this)
                .setMessage("Te rog adaugă o semnătură")
                .show()
            false
        }
    }

    private fun sendMail() {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, pageTitle.text.toString())
        val root = Environment.getExternalStorageDirectory()
        val pathToMyAttachedFile = "/" + "CereriCobalt/" + getFileName()
        val file = File(root, pathToMyAttachedFile)
        if (!file.exists() || !file.canRead()) {
            return
        }
        val uri: Uri = Uri.fromFile(file)
        emailIntent.putExtra(Intent.EXTRA_STREAM, uri)
        startActivity(Intent.createChooser(emailIntent, "Send Email..."))
    }

    override fun onFailed() {
        Toast.makeText(this, "Eroare", Toast.LENGTH_LONG).show()

    }

    override fun onSuccess() {
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
        sendMail()
    }
}