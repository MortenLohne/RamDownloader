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
            status += 3.0 / GBsToDownload.toDouble()
        }

        // Function that gets called regularly to update the progress bar
        fun updateProgressBar() {
            if (status < 100.0) {
                status += 3.0 /  GBsToDownload.toDouble()
                progressBar.progress = status.toInt()

                if (status >= 30 && status < 55){
                    text1.text = "Installing Windows Phone"
                } else if(status >= 55 && status < 80){
                    text1.text = "Set IE as default browser"
                } else if(status >= 80 && status < 95){
                    text1.text = "Removing Android"
                } else if(status >= 95){
                    text1.text = "Complete"
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