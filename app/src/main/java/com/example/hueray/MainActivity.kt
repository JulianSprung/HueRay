package com.example.hueray

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // database thread
        Thread {
            var db = HappinessRoomDatabase.getDatabase(applicationContext)
        }.start()
    }
}





