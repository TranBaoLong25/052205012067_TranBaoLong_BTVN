package com.example.xd_ui_cbn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.xd_ui_cbn.Screen

data class UIComponentItem(
    val name: String,
    val description: String,
    val route: String? = null
)

data class Section(
    val title: String,
    val items: List<UIComponentItem>,
    val isSpecial: Boolean = false
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val sections = listOf(
        Section(
            "Display", listOf(
                UIComponentItem("Text", "Displays text", Screen.TextDetail.route),
                UIComponentItem("Image", "Displays an image", Screen.ImageDetail.route)
            )
        ),
        Section(
            "Input", listOf(
                UIComponentItem("TextField", "Input field for text", Screen.TextFieldDetail.route),
                UIComponentItem("PasswordField", "Input field for passwords", null) // No route defined yet
            )
        ),
        Section(
            "Layout", listOf(
                UIComponentItem("Column", "Arranges elements vertically", null), // No route defined yet
                UIComponentItem("Row", "Arranges elements horizontally", Screen.RowDetail.route)
            )
        ),
        Section(
            "Tự tìm hiểu", listOf(
                UIComponentItem("Tìm ra tất cả các thành phần UI Cơ bản", "", null)
            ),
            isSpecial = true
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "UI Components List",
                        color = Color(0xFF2196F3), // Blue title
                        fontWeight = FontWeight.Bold
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp)
        ) {
            sections.forEach { section ->
                item {
                    Text(
                        text = section.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                items(section.items) { item ->
                    ComponentItem(item = item, isSpecial = section.isSpecial) {
                        item.route?.let { navController.navigate(it) }
                    }
                }
            }
        }
    }
}

@Composable
fun ComponentItem(item: UIComponentItem, isSpecial: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSpecial) Color(0xFFFFCDD2) else Color(0xFFE3F2FD) // Red-ish for special, Blue-ish for others
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            if (isSpecial) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Normal
                )
            } else {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}
