package com.example.btvn_tuan04_luongdulieu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.btvn_tuan04_luongdulieu.ui.theme.BTVN_Tuan04_LuongDulieuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BTVN_Tuan04_LuongDulieuTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "forget_password") {
        composable("forget_password") {
            ForgetPasswordScreen(navController)
        }
        composable(
            "verify_code/{email}",
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            VerifyCodeScreen(navController, email)
        }
        composable(
            "create_password/{email}/{code}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("code") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val code = backStackEntry.arguments?.getString("code") ?: ""
            CreatePasswordScreen(navController, email, code)
        }
        composable(
            "confirm/{email}/{code}/{password}",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
                navArgument("code") { type = NavType.StringType },
                navArgument("password") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val code = backStackEntry.arguments?.getString("code") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            ConfirmScreen(navController, email, code, password)
        }
    }
}

@Composable
fun HeaderSection(showBack: Boolean = false, onBackClick: () -> Unit = {}) {
    Box(modifier = Modifier.fillMaxWidth().padding(top = 40.dp)) {
        if (showBack) {
            IconButton(onClick = onBackClick, modifier = Modifier.align(Alignment.TopStart)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.Blue
                )
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "UTH",
                color = Color(0xFF0055AA),
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "UNIVERSITY\nOF TRANSPORT\nHO CHI MINH CITY",
                color = Color(0xFF0055AA),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 12.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "SmartTasks",
                color = Color(0xFF0099FF),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun CommonButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF0099FF)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(text = text, color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ForgetPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection()
            Spacer(modifier = Modifier.height(40.dp))
            
            Text(
                text = "Forget Password?",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Enter your Email, we will send you a verification code.",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Your Email") },
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color(0xFF0099FF)
                )
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            CommonButton(text = "Next") {
                if (email.isNotEmpty()) {
                    navController.navigate("verify_code/$email")
                }
            }
        }
    }
}

@Composable
fun VerifyCodeScreen(navController: NavController, email: String) {
    var code by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection(showBack = true, onBackClick = { navController.popBackStack() })
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Verify Code",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Enter the code we just sent you on your registered Email",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Simplified Code Input
            OutlinedTextField(
                value = code,
                onValueChange = { code = it },
                placeholder = { Text("Enter Code") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color(0xFF0099FF)
                ),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(24.dp))
            CommonButton(text = "Next") {
                if (code.isNotEmpty()) {
                    navController.navigate("create_password/$email/$code")
                }
            }
        }
    }
}

@Composable
fun CreatePasswordScreen(navController: NavController, email: String, code: String) {
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection(showBack = true, onBackClick = { navController.popBackStack() })
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Create new password",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Your new password must be different from previously used password",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color(0xFF0099FF)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                placeholder = { Text("Confirm Password") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color(0xFF0099FF)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
            CommonButton(text = "Next") {
                if (password.isNotEmpty() && password == confirmPassword) {
                    navController.navigate("confirm/$email/$code/$password")
                }
            }
        }
    }
}

@Composable
fun ConfirmScreen(navController: NavController, email: String, code: String, password: String) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderSection(showBack = true, onBackClick = { navController.popBackStack() })
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Confirm",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "We are here to help you!",
                fontSize = 14.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Read-only fields to show data flow
            OutlinedTextField(
                value = email,
                onValueChange = {},
                readOnly = true,
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color(0xFF0099FF)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = code,
                onValueChange = {},
                readOnly = true,
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) }, // Using Lock as placeholder for code icon
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color(0xFF0099FF)
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {},
                readOnly = true,
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.LightGray,
                    focusedBorderColor = Color(0xFF0099FF)
                )
            )

            Spacer(modifier = Modifier.height(24.dp))
            CommonButton(text = "Submit") {
                // Submit action
            }
        }
    }
}
