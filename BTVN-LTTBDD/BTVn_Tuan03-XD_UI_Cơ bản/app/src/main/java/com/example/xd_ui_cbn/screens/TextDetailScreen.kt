package com.example.xd_ui_cbn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextDetailScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Text Detail",
                        color = Color(0xFF2196F3),
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Gray
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = buildAnnotatedString {
                    append("The quick ")
                    withStyle(style = SpanStyle(color = Color(0xFFD4A017), fontSize = 30.sp, fontWeight = FontWeight.Bold)) {
                        append("Brown")
                    }
                    append("\nfox ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("j u m p s")
                    }
                    append(" ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("over")
                    }
                    append("\nthe ")
                    withStyle(style = SpanStyle(textDecoration = TextDecoration.Underline)) {
                        append("lazy dog")
                    }
                    append(".")
                },
                fontSize = 20.sp,
                lineHeight = 40.sp, // Adjust line height for better spacing like in the image
                color = Color.Black
            )
        }
    }
}
