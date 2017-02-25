package com.example.morten.ramdownloader

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import com.example.morten.ramdownloader.R

/**
 * Created by ady on 24/02/2017.
 */
class DownloadingPage : AppCompatActivity()  {

    val mHandler = Handler()
    var status = 0

    fun startDownload() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        // For some reason intent.extras.getInt("size") works but not intent.extras.getInt("size")
        var GBsToDownload : Int = intent.extras.getInt("size")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        val button = findViewById(R.id.fasterDownloadButton)
        val progressBar = if (findViewById(R.id.progressBar) != null) {
            findViewById(R.id.progressBar) as ProgressBar
        }
        else {
            throw Exception("ProgressBar view was null")
        }

        button.setOnClickListener {
            status += 1
        }

        Thread(Runnable {
            while (status < 100) {
                status += 1

                Thread.sleep(200 * GBsToDownload.toLong())
                // Update the progress bar
                mHandler.post { progressBar.progress = status }
            }

            val intent = Intent(this,MainActivity::class.java)
            startActivityForResult(intent,2)

        }).start()

    }
}