package com.example.morten.ramdownloader

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.TextView

/**
  Activity for downloading RAM page
 */
class DownloadingPage : AppCompatActivity()  {

    val mHandler = Handler()
    var status = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {

        // For some reason intent.extras.getInt("size") works but not intent.extras.getInt("size")
        val GBsToDownload : Int = intent.extras.getInt("size")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download)
        val text1 = findViewById(R.id.downloadingText) as TextView
        val button = findViewById(R.id.fasterDownloadButton)
        val progressBar = if (findViewById(R.id.progressBar) != null) {
            findViewById(R.id.progressBar) as ProgressBar
        }
        else {
            throw Exception("ProgressBar view was null")
        }

        button.setOnClickListener {
            if(GBsToDownload <= 8) {
                status += 3.0 / GBsToDownload.toDouble()
            }
            else {
                status += 12.0 / GBsToDownload.toDouble()
            }
        }

        // Function that gets called regularly to update the progress bar
        fun updateProgressBar() {
            if (status < 100.0) {
                if(GBsToDownload <= 8) {
                    status += 3.0 /  GBsToDownload.toDouble()
                    progressBar.progress = status.toInt()
                    text1.text =
                    when (status) {
                        in 0..30 -> "Downloading RAM"
                        in 30..55 -> "Installing Windows Phone"
                        in 55..80 ->"Setting IE as default browser"
                        in 80..95 -> "Removing Android"
                        else -> "Complete"
                    }
                }
                else {
                    status += 12.0 /  GBsToDownload.toDouble()
                    progressBar.progress = status.toInt()
                    text1.text =
                    when (status) {
                        in 0..15 -> "Downloading storage"
                        in 15..30 -> "Enabling monadic quantum bits"
                        in 40..55 -> "sudo rm -rf *"
                        in 55..70 -> "sudo rm -rf --no-preserve-root *"
                        in 70..80 -> "Removing Norton anti-virus"
                        in 80..85 -> "Removing NSA"
                        in 85..90 -> "Self destruct in: 3"
                        in 90..94 -> "Self destruct in: 2"
                        else -> "Self destruct in: 1"
                    }
                }
                // Register the next update
                mHandler.postDelayed({ updateProgressBar() }, 200)
            }
            else {
                status += 1.0
                // Display "Complete" for a bit before returning to main menu
                if (status > 120.0) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivityForResult(intent, 2)
                }
                else {
                    mHandler.postDelayed({ updateProgressBar() }, 200)
                }
            }
        }
        updateProgressBar()
    }
}