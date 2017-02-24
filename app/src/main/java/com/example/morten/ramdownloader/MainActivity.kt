package com.example.morten.ramdownloader

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get reference to download buttons
        val downloadButtons = mutableMapOf(1 to findViewById(R.id.downloadButton1GB),
                2 to findViewById(R.id.downloadButton2GB),
                4 to findViewById(R.id.downloadButton4GB),
                8 to findViewById(R.id.downloadButton8GB))

        // When a button is clicked, make it semi-transparent
        for (i in downloadButtons.keys) {
            downloadButtons[i]?.setOnClickListener ({
                downloadButtons[i]?.alpha = 0.5.toFloat();
                val dlPage = DownloadingPage();
                var intent = Intent(this as Context, DownloadingPage::class.java)
                intent.putExtra(i.toString(), i)
                startActivityForResult(intent, 0)
                println("${i}GB button clicked!")
                // Restore button transparency after some time
                // If button is clicked several times, the button turns non-transparent 1000ms after the first click
                kotlin.concurrent.thread {
                    Thread.sleep(1000);
                    downloadButtons[i]?.alpha = 1.0.toFloat();
                }.start()
            })
        }

    }
}
