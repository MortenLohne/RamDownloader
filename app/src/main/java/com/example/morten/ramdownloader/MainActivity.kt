package com.example.morten.ramdownloader

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get reference to download buttons
        val downloadButtons = mutableMapOf(1 to findViewById(R.id.downloadButton1GB),
                2 to findViewById(R.id.downloadButton2GB),
                4 to findViewById(R.id.downloadButton4GB))

        // When a button is clicked, make it semi-transparent
        for (i in downloadButtons.keys) {
            downloadButtons[i]?.setOnClickListener ({
                downloadButtons[i]?.alpha = 0.5.toFloat();
                println("${i}GB button clicked!")
            })
        }

    }
}
