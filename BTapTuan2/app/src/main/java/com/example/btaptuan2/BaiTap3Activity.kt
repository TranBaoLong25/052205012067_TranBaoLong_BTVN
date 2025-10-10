package com.example.btaptuan2 // THAY THÀNH TÊN PACKAGE CỦA BẠN

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class BaiTap3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bai_tap3)

        val edtSoLuong: EditText = findViewById(R.id.edtSoLuong)
        val btnTao: Button = findViewById(R.id.btnTao)
        val tvThongBaoLoi: TextView = findViewById(R.id.tvThongBaoLoi)
        val container: LinearLayout = findViewById(R.id.containerDanhSach)

        btnTao.setOnClickListener {
            container.removeAllViews()
            tvThongBaoLoi.text = ""

            val soLuongStr = edtSoLuong.text.toString()

            try {
                val soLuong = soLuongStr.toInt()
                if (soLuong <= 0) {
                    tvThongBaoLoi.text = "Vui lòng nhập số lớn hơn 0"
                    return@setOnClickListener
                }

                for (i in 1..soLuong) {
                    val newButton = Button(this)
                    newButton.text = "$i"
                    val layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams.setMargins(0, 8, 0, 8)
                    newButton.layoutParams = layoutParams

                    container.addView(newButton)
                }

            } catch (e: NumberFormatException) {
                tvThongBaoLoi.text = "Dữ liệu bạn nhập không hợp lệ"
            }
        }
    }
}