package com.example.btaptuan2 // THAY THÀNH TÊN PACKAGE CỦA BẠN

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnBaiTap1: Button = findViewById(R.id.btnBaiTap1)
        val btnBaiTap2: Button = findViewById(R.id.btnBaiTap2)
        val btnBaiTap3: Button = findViewById(R.id.btnBaiTap3)

        btnBaiTap1.setOnClickListener {
            startActivity(Intent(this, BaiTap1Activity::class.java))
        }

        btnBaiTap2.setOnClickListener {
            startActivity(Intent(this, BaiTap2Activity::class.java))
        }

        btnBaiTap3.setOnClickListener {
            startActivity(Intent(this, BaiTap3Activity::class.java))
        }
    }
}