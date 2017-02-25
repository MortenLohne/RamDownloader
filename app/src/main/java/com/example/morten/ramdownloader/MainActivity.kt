package com.example.morten.ramdownloader

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get reference to download buttons
        val downloadButtons = mutableMapOf(
                1 to findViewById(R.id.downloadButton1GB),
                2 to findViewById(R.id.downloadButton2GB),
                4 to findViewById(R.id.downloadButton4GB),
                8 to findViewById(R.id.downloadButton8GB),
                16 to findViewById(R.id.downloadButton16GB),
                32 to findViewById(R.id.downloadButton32GB),
                64 to findViewById(R.id.downloadButton64GB))

        // When a button is clicked, switch to the download screen
        for (i in downloadButtons.keys) {
            downloadButtons[i]?.setOnClickListener ({
                val intent = Intent(this as Context, DownloadingPage::class.java)
                intent.putExtra("size", i)
                startActivityForResult(intent, 1)
                println("${i}GB button clicked!")
            })
        }

    }
}
