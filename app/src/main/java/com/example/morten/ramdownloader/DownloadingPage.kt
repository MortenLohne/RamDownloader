package com.example.morten.ramdownloader

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.morten.ramdownloader.R

/**
 * Created by ady on 24/02/2017.
 */
class DownloadingPage constructor(val GBsToDownload: Int) : AppCompatActivity()  {
    fun startDownload() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}