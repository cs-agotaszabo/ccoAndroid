package com.example.cerericobalt

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.github.lucasfsc.html2pdf.Html2Pdf
import kotlinx.android.synthetic.main.activity_fill_request.*

class FillRequestActivity : AppCompatActivity(), Html2Pdf.OnCompleteConversion {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fill_request)

        // Enables Always-on
        convertButton.setOnClickListener {  }
    }


    private fun convertToHTML() {
        Toast.makeText(this, "perm granted, convert", Toast.LENGTH_SHORT).show()
//            val htmlString =
//                "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Example</title> </head><body><h1>Hello</h1></body></html>"
//            val file =
//                File(Environment.getExternalStorageDirectory().toString(), "file.pdf")
//            val s = Environment.DIRECTORY_DOCUMENTS
//        val converter = Html2Pdf.Companion.Builder()
//            .context(this)
//            .file(file)
//            .html(htmlString)
//            .build()
//        converter.convertToPdf(this)
    }

    override fun onFailed() {
        Toast.makeText(this, "failed", Toast.LENGTH_LONG).show()
    }

    override fun onSuccess() {
        Toast.makeText(this, "failed", Toast.LENGTH_LONG).show()
    }
}