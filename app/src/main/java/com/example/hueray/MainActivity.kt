package com.example.hueray

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hueray.database.HappyScoreDatabase


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // database thread
        var db = HappyScoreDatabase.getDatabase(application)
    }
}





