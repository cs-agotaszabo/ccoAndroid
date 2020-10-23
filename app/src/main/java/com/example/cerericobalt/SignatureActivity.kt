package com.example.cerericobalt

import android.R.attr.path
import android.graphics.Bitmap.CompressFormat
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_signature.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream


class SignatureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signature)
        drawView.requestFocus()

        saveButton.setOnClickListener {
           val bitMap =  drawView.get()
            try {
                val folderPath = Environment.getExternalStorageDirectory().absolutePath + "/" + "CereriCobalt"
                val filePath = Environment.getExternalStorageDirectory().absolutePath + "/" + "CereriCobalt/signature.jpeg"

                val dir = File(folderPath);
                if (!dir.exists())
                    dir.mkdirs()
                val fout = FileOutputStream(filePath)
                bitMap?.compress(CompressFormat.JPEG, 100, fout)
                fout.flush();
                fout.close();
                this.finish()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }

        clearButton.setOnClickListener {
            drawView.clear()
        }
    }
}