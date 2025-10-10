package com.example.btaptuan2 // THAY THÀNH TÊN PACKAGE CỦA BẠN

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class BaiTap1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai_tap1)

        val edtHoTen: EditText = findViewById(R.id.edtHoTen)
        val edtTuoi: EditText = findViewById(R.id.edtTuoi)
        val btnKiemTra: Button = findViewById(R.id.btnKiemTraTuoi)
        val tvKetQua: TextView = findViewById(R.id.tvKetQuaTuoi)

        btnKiemTra.setOnClickListener {
            val hoTen = edtHoTen.text.toString()
            val tuoiStr = edtTuoi.text.toString()

            if (hoTen.isBlank() || tuoiStr.isBlank()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            try {
                val tuoi = tuoiStr.toInt()
                val phanLoai = when {
                    tuoi > 65 -> "Người già"
                    tuoi >= 6 -> "Người lớn"
                    tuoi >= 2 -> "Trẻ em"
                    else -> "Em bé"
                }
                tvKetQua.text = "$hoTen - $tuoi tuổi - là $phanLoai."
            } catch (e: NumberFormatException) {
                tvKetQua.text = "Tuổi nhập không hợp lệ!"
            }
        }
    }
}