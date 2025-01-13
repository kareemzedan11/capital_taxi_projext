package com.example.capital_taxi.Presentation.ui.Driver.Screens.Login


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Login.Components.driverLoginContent

import com.example.capital_taxi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DriverLoginIn(navController: NavController) {
    val generalColor = colorResource(id = R.color.primary_color)


    Scaffold(
        topBar = {

            TopAppBar(

                title = {
                    Text(
                        stringResource(R.string.Driver_Mode),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Box(
                                modifier = Modifier

                                    .size(36.dp)
                                    .background(Color.Transparent)
                                    .border(4.dp, color = Color.Black, RoundedCornerShape(30.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    modifier = Modifier.size(26.dp),
                                    painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                                    contentDescription = "Back",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = generalColor)

            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Use padding values to avoid overlapping the topBar
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp) // تحديد ارتفاع ثابت للـ Canvas
                        .align(Alignment.TopEnd) // التأكد من محاذاة Canvas في الزاوية العليا اليمنى
                ) {
                    val width = size.width
                    val height = size.height

                    // Define the curved path
                    val path = Path().apply {
                        moveTo(0f, height * 0.4f)
                        cubicTo(
                            width * 0.3f, height * 0.1f,
                            width * 0.7f, height * 0.9f,
                            width, height * 0.4f
                        )
                        lineTo(width, 0f)
                        lineTo(0f, 0f)
                        close()
                    }

                    // Draw a gradient inside the path
                    clipPath(path) {
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    generalColor,
                                    generalColor.copy(alpha = .4f),
                                ),
                                startY = 0f,
                                endY = height
                            ),
                            size = size
                        )
                    }
                }
                driverLoginContent(  navController)
            }
        }
    )
}
