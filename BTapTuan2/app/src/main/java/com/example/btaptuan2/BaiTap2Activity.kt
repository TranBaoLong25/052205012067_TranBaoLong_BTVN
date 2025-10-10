package com.example.btaptuan2 // THAY THÀNH TÊN PACKAGE CỦA BẠN

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class BaiTap2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai_tap2)

        val edtEmail: EditText = findViewById(R.id.edtEmail)
        val btnKiemTra: Button = findViewById(R.id.btnKiemTraEmail)
        val tvKetQua: TextView = findViewById(R.id.tvKetQuaEmail)

        btnKiemTra.setOnClickListener {
            val email = edtEmail.text.toString()

            if (email.isBlank()) {
                tvKetQua.text = "Email không hợp lệ"
                tvKetQua.setTextColor(getColor(android.R.color.holo_red_dark))
            } else if (!email.contains("@")) {
                tvKetQua.text = "Email không đúng định dạng"
                tvKetQua.setTextColor(getColor(android.R.color.holo_red_dark))
            } else {
                tvKetQua.text = "Bạn đã nhập email hợp lệ"
                tvKetQua.setTextColor(getColor(android.R.color.holo_green_dark))
            }
        }
    }
}