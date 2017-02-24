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
        val downloadButtons = mutableMapOf(1 to findViewById(R.id.downloadButton1GB) as Button)
        downloadButtons[1]?.setOnClickListener ({  println("Button clicked!") })
    }
}
