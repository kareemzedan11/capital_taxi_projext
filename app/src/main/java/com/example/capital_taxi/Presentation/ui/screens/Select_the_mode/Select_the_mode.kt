package com.example.capital_taxi.Presentation.ui.screens.Select_the_mode

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R

@Composable
fun SelectTheMode(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.primary_color).copy(alpha = .3f))
    ) {

        val generalColor = colorResource(id = R.color.primary_color)

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

                val path = Path().apply {
                    moveTo(0f, height * 0.6f)
                    cubicTo(
                        width * 0.3f, height * 0.8f,
                        width * 1f, height * 1f,
                        width, height * 1f
                    )
                    lineTo(width, height)
                    lineTo(0f, height)
                    close()
                }

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

                // Updated to use string resources
                Text(
                    text = stringResource(R.string.select_the_mode_title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.padding(top = 16.dp))
                Text(
                    text = stringResource(R.string.mode_instruction),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600
                )

                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier
                        .fillMaxWidth(.95f)
                        .padding(8.dp)
                        .border(2.dp, color = Color.Transparent, RoundedCornerShape(30.dp))
                        .background(Color.White, RoundedCornerShape(30.dp))
                        .padding(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    onClick = { navController.navigate(Destination.FaceValidation.route) }
                ) {
                    Text(
                        text = stringResource(R.string.captain_button),
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
                        .background(Color.White, RoundedCornerShape(30.dp))
                        .padding(6.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    onClick = { navController.navigate(Destination.UserHomeScreen.route) }
                ) {
                    Text(
                        text = stringResource(R.string.passenger_button),
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

@Composable
fun ModeOption(language: String, backgroundColor: Color, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(.7f)
            .padding(8.dp)
            .clickable { onClick() }
            .border(2.dp, color = Color.DarkGray, RoundedCornerShape(30.dp))
            .background(
                backgroundColor,
                RoundedCornerShape(30.dp)
            )
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = language, fontWeight = FontWeight.Medium, fontSize = 16.sp)
    }
}
