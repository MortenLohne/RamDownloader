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
    var counter : Int = 0

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

                if(counter % 4 == 0){
                    //button.setBackgroundColor(Color.BLUE)
                    button.setBackgroundResource(R.drawable.roundedbutton2)

                } else if(counter % 2 == 0) {
                    //button.setBackgroundColor(Color.RED)
                    button.setBackgroundResource(R.drawable.roundedbutton)
                }
                counter++
                if(GBsToDownload <= 8) {
                    status += 3.0 /  GBsToDownload.toDouble()
                    progressBar.progress = status.toInt()
                    text1.text =
                    when (status) {
                        in 0..15 -> "Downloading RAM"
                        in 15..30 -> "Getting 7D bits"
                        in 30..45 -> "Enabling RAM cloud"
                        in 45..60 -> "Installing Windows Phone"
                        in 60..70 -> "Setting IE as default browser"
                        in 70..80 -> "Removing Android"
                        in 80..90 -> "Removing Java"
                        in 90..95 -> "Is there a god?"
                        else -> "Congratulations! RAM acquired"
                    }
                }
                else {
                    status += 12.0 /  GBsToDownload.toDouble()
                    progressBar.progress = status.toInt()
                    text1.text =
                    when (status) {
                        in 0..15 -> "Downloading storage"
                        in 15..25 -> "Getting monadic quantum bits"
                        in 25..35 -> "Superclassing Object"
                        in 35..50 -> "rm -rf *"
                        in 50..55 -> "Backing up nudes"
                        in 55..60 -> "Error: no morten@gmail.com"
                        in 60..70 -> "rm -rf --no-preserve-root *"
                        in 70..75 -> "Removing Norton Antivirus"
                        in 75..80 -> "Removing NSA"
                        in 80..84 -> "Self destruct in: 3"
                        in 84..88 -> "Self destruct in: 2"
                        in 88..92 -> "Self destruct in: 1"
                        in 92..96 -> "rm: command not found"
                        else -> "Download complete!"
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