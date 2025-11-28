package com.example.btvn_tuan03_ql_thvin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.btvn_tuan03_ql_thvin.ui.theme.BTVNTuan03QL_thưViệnTheme

// Data Models
data class Book(val id: String, val title: String)
data class Student(val id: String, val name: String)

// Mock Data
val availableBooks = listOf(
    Book("1", "Sách 01"),
    Book("2", "Sách 02"),
    Book("3", "Sách 03"),
    Book("4", "Sách 04"),
    Book("5", "Sách 05")
)

val initialStudents = listOf(
    Student("S1", "Nguyen Van A"),
    Student("S2", "Nguyen Thi B"),
    Student("S3", "Nguyen Van C")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BTVNTuan03QL_thưViệnTheme {
                LibraryApp()
            }
        }
    }
}

@Composable
fun LibraryApp() {
    // State for current student
    var currentStudentIndex by remember { mutableIntStateOf(0) }
    val students = initialStudents
    val currentStudent = students[currentStudentIndex]

    // State for borrowed books (In-memory storage for demo)
    // Key: Student ID, Value: List of borrowed Books
    val borrowedBooksMap = remember {
        mutableStateMapOf(
            "S1" to listOf(availableBooks[0], availableBooks[1]), // Nguyen Van A has Book 01, 02
            "S2" to listOf(availableBooks[0]),                   // Nguyen Thi B has Book 01
            "S3" to emptyList<Book>()                            // Nguyen Van C has none
        )
    }

    val studentBorrowedBooks = borrowedBooksMap[currentStudent.id] ?: emptyList()

    Scaffold(
        bottomBar = { BottomNavigationBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            Text(
                text = "Hệ thống\nQuản lý Thư viện",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(vertical = 16.dp)
            )

            // Student Info Section
            StudentSection(
                studentName = currentStudent.name,
                onChangeClick = {
                    // Cycle through students
                    currentStudentIndex = (currentStudentIndex + 1) % students.size
                }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Book List Header
            Text(
                text = "Danh sách sách",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(bottom = 8.dp)
            )

            // Borrowed Books Area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFFE0E0E0), shape = RoundedCornerShape(12.dp))
                    .padding(16.dp)
            ) {
                if (studentBorrowedBooks.isEmpty()) {
                    Text(
                        text = "Bạn chưa mượn quyển sách nào\nNhấn 'Thêm' để bắt đầu hành trình đọc sách!",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.align(Alignment.Center)
                    )
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(studentBorrowedBooks) { book ->
                            BookItem(book.title)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Add Button
            Button(
                onClick = {
                    // Logic to borrow a book: Add next available book that is not yet borrowed
                    val currentBooks = borrowedBooksMap[currentStudent.id] ?: emptyList()
                    val currentIds = currentBooks.map { it.id }.toSet()
                    val bookToAdd = availableBooks.firstOrNull { it.id !in currentIds }
                    if (bookToAdd != null) {
                        borrowedBooksMap[currentStudent.id] = currentBooks + bookToAdd
                    }
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f) // Adjust width as per image roughly
                    .height(48.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1)) // Dark blueish
            ) {
                Text("Thêm")
            }
        }
    }
}

@Composable
fun StudentSection(studentName: String, onChangeClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Sinh viên",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = studentName,
                onValueChange = {},
                readOnly = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                shape = RoundedCornerShape(8.dp),
                singleLine = true
            )
            Button(
                onClick = onChangeClick,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0D47A1))
            ) {
                Text("Thay đổi")
            }
        }
    }
}

@Composable
fun BookItem(title: String) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            // Checkbox to mimic the visual in the image
            Checkbox(
                checked = true,
                onCheckedChange = {}, // Read-only in this context
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFFB71C1C), // Red color for checkbox
                    checkmarkColor = Color.White
                )
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color.White
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Quản lý") },
            label = { Text("Quản lý") },
            selected = true,
            onClick = { /* No op */ },
            colors = androidx.compose.material3.NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFF0D47A1),
                selectedTextColor = Color(0xFF0D47A1),
                indicatorColor = Color.Transparent // To remove the pill indicator if desired, or keep default
            )
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Book, contentDescription = "DS Sách") },
            label = { Text("DS Sách") },
            selected = false,
            onClick = { /* No op */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Sinh viên") },
            label = { Text("Sinh viên") },
            selected = false,
            onClick = { /* No op */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BTVNTuan03QL_thưViệnTheme {
        LibraryApp()
    }
}
