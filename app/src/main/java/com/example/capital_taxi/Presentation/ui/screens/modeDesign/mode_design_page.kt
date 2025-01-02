package com.example.capital_taxi.Presentation.ui.screens.modeDesign

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R

@Composable
fun modeDesign(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.primary_color).copy(alpha = .3f)),

        ) {

        val generalColor = colorResource(id = R.color.primary_color) // استدعاء اللون داخل دالة Composable

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .height(200.dp)
                .width(200.dp)
                .border(
                    width = 2.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(bottomStart = 222.dp)
                )
                .drawBehind {
                    // رسم اللون داخل الـ border فقط باستخدام اللون من resources
                    val shape = RoundedCornerShape(bottomStart = 222.dp)
                    val path = shape.createOutline(size, LayoutDirection.Ltr, this)

                    drawOutline(outline = path, style = Fill, brush = SolidColor(generalColor))
                }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomStart)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                val width = size.width
                val height = size.height

                // Define the curved path
                val path = Path().apply {
                    moveTo(0f, height * 0.6f) // نقطة البداية (يسار منتصف السفلي)
                    cubicTo(
                        width * 0.3f, height * 0.8f,
                        width * 1f, height * 1f,
                        width, height * 1f
                    )
                    lineTo(width, height) // الزاوية السفلية اليمنى (خط تأكيدي)
                    lineTo(0f, height)    // الزاوية السفلية اليسرى
                    close()
                }

                // Draw a gradient inside the path
                clipPath(path) {
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                generalColor,
                                generalColor,
                            ),
                            startY = 0f,
                            endY = height
                        ),
                        size = size
                    )
                }
            }



            Column(
                modifier = Modifier.padding(top = 80.dp, start = 20.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(1f))

                Text("Please Select: ", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Text(
                    "You can switch to another mode later if you wish.",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600
                )

                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(.95f)
                        .padding(8.dp)

                        .border(2.dp, color = Color.Transparent, RoundedCornerShape(30.dp))
                        .background(
                            Color.White,
                            RoundedCornerShape(30.dp)
                        )
                        .padding(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    onClick = {navController.navigate(Destination.driverLogin.route)}
                ) {
                    Text(
                        text = "Captain",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color(0XFF111111)
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth(.95f)
                        .padding(8.dp)

                        .border(2.dp, color = Color.Transparent, RoundedCornerShape(30.dp))
                        .background(
                            Color.White,
                            RoundedCornerShape(30.dp)
                        )
                        .padding(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    onClick = {navController.navigate(Destination.UserLogin.route)}
                ) {
                    Text(
                        text = "Passenger",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp,
                        color = Color(0XFF111111)
                    )
                }




                Spacer(modifier = Modifier.weight(1f))


            }

        }
    }


}

