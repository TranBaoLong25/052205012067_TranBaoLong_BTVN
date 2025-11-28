package com.example.btvn_tuan02_loginscreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.btvn_tuan02_loginscreen.ui.theme.BTVN_Tuan02_LoginscreenTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BTVN_Tuan02_LoginscreenTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AgeCheckScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun AgeCheckScreen(modifier: Modifier = Modifier) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }
    
    // Quản lý focus để ẩn bàn phím
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp), // Tăng khoảng cách lề
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        
        // Tiêu đề chính
        Text(
            text = "THỰC HÀNH 01",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black
        )
        
        // Tiêu đề phụ
        Text(
            text = "Kiểm tra độ tuổi",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(top = 8.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Input Card với bóng đổ (Elevation)
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Name Input
                InputRow(
                    label = "Họ và tên",
                    value = name,
                    onValueChange = { name = it },
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                )

                // Age Input
                InputRow(
                    label = "Tuổi",
                    value = age,
                    onValueChange = { if (it.all { char -> char.isDigit() }) age = it },
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                    onDone = { focusManager.clearFocus() }
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Nút kiểm tra được làm đẹp hơn
        //Logic hoạt động của hệ thống kiểm tra tuổi
        Button(
            onClick = {
                focusManager.clearFocus() // Ẩn bàn phím khi ấn nút
                val ageInt = age.toIntOrNull()
                if (ageInt != null && name.isNotBlank()) {
                    val category = when {       // sử dụng when để làm việc
                        ageInt > 65 -> "Người già"
                        ageInt in 6..65 -> "Người lớn"
                        ageInt in 2..6 -> "Trẻ em"
                        else -> "Em bé"
                    }
                    result = "$name - $category"
                } else {
                    result = "Vui lòng nhập đầy đủ tên và tuổi hợp lệ"
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2)),
            shape = RoundedCornerShape(50), // Bo tròn hình viên thuốc
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
            modifier = Modifier
                .height(54.dp)
                .width(200.dp)
        ) {
            Text(
                text = "Kiểm tra",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Hiển thị kết quả nổi bật
        if (result.isNotEmpty()) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFBBDEFB)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = result,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF0D47A1), // Màu chữ xanh đậm
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

// Tách thành component nhỏ để code gọn hơn
@Composable
fun InputRow(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onDone: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            modifier = Modifier.width(90.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = Color.Black
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(onDone = { onDone() }),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Black
            ),
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AgeCheckScreenPreview() {
    BTVN_Tuan02_LoginscreenTheme {
        AgeCheckScreen()
    }
}
