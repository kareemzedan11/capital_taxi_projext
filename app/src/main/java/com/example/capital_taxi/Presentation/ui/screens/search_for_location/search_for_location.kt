package com.example.capital_taxi.Presentation.ui.screens.search_for_location

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchForLocation(navController: NavController) {
    val context = LocalContext.current

    // ActivityResultLauncher لطلب إذن الوصول للموقع
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                // إذا تم منح الإذن، قم بالتنقل إلى الشاشة الرئيسية
                navController.navigate(Destination.UserHomeScreen.route)
            } else {
                // هنا، يمكنك إظهار رسالة للمستخدم أو عمل إجراء آخر إذا لم يتم منح الإذن
            }
        }
    )

    // تحقق من إذن الوصول للموقع قبل عرض الزر
    val locationPermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
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
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                ) {
                    Image(
                        painter = painterResource(R.drawable.search_location),
                        contentDescription = null
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "We need to know your location , \n Please allow us to access it",
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                // تحقق إذا كان الإذن قد تم منحه مسبقاً
                if (locationPermission == PackageManager.PERMISSION_GRANTED) {
                    // إذا كان الإذن قد تم منحه مسبقاً، التنقل تلقائيًا
                    navController.navigate(Destination.UserHomeScreen.route)
                } else {
                    // إذا لم يتم منح الإذن، أطلب الإذن
                    Button(
                        onClick = {
                            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .height(60.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_color)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Access My Location",
                            fontSize = 18.sp,
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
