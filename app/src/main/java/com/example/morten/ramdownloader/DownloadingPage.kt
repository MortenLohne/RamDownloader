package com.example.morten.ramdownloader

import android.os.Bundle
import android.os.Handler
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
        var GBsToDownload : Int = if (intent.dataString == "") {
            intent.dataString.toInt()
        }
        else {
            throw Exception("DownloadingPage's extra data was empty")
        };

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById(R.id.fasterDownloadButton)
        val progressBar = findViewById(R.id.progressBar) as ProgressBar

        button.setOnClickListener {
            status++
        }

        Thread(Runnable {
            while (status < 100) {
                status += 10

                Thread.sleep(200 * GBsToDownload as Long)
                // Update the progress bar
                mHandler.post { progressBar.progress = status }
            }
        }).start()


        button.setOnClickListener {

        }
    }
}