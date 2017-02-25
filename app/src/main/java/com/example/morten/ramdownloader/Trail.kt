package com.example.morten.ramdownloader

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by ady on 25/02/2017.
 */

class Trail : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?){

       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_trail)

       val button = findViewById(R.id.closeButton)


       button.setOnClickListener {
           val intent = Intent(this,MainActivity::class.java)
           startActivityForResult(intent,0)
       }

   }

}