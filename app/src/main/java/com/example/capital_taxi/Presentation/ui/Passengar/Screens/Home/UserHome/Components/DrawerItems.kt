package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import TopBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R
import drawerContent
import kotlinx.coroutines.launch


@Composable
fun DrawerScreen(navController: NavController) {

    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var scope = rememberCoroutineScope()
    val isDrawerOpen = drawerState.isOpen
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.70f)
            ) {
                drawerContent(navController = navController)
            }


        }, gesturesEnabled = isDrawerOpen
    ) {

        Scaffold(topBar = {

            TopBar(
                onOpenDrawer = {
                    scope.launch {
                        drawerState.apply {
                            if (drawerState.isClosed) drawerState.open() else drawerState.close()
                        }
                    }
                },
                navController = navController
            )
        }) { padding ->

            ScreenContent(modifier = Modifier.padding(padding))
        }
    }

}



@Composable
fun EnableLocationServices() {
    Box(
        modifier = Modifier

            .fillMaxWidth()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {


        Column (horizontalAlignment = Alignment.CenterHorizontally){


            Box(
                modifier = Modifier

                    .height(100.dp)
                    .width(200.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .windowInsetsPadding(WindowInsets.navigationBars)
                        .padding(horizontal = 20.dp),
                    contentDescription = "the service not available ",
                    painter = painterResource(R.drawable.logo9)
                )


            }
            Spacer(Modifier.height(50.dp))

            Text(
                buildAnnotatedString {
                    withStyle(style = SpanStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)) {
                        append("Enable Location\nServices\n")
                    }

                    withStyle(style = SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.W600)) {
                        append("Please enable location services so\nwe can offer personalized features and\nservices based on where you are")
                    }
                },
                modifier = Modifier.padding(vertical = 16.dp, horizontal = 16.dp),
                color = Color.Gray
            )
            Spacer(Modifier.height(30.dp))

            Button(
                onClick = { /* Handle new method */ },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier


                    .height(80.dp)
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(Color(0xffFFA500))
            ) {
                Text("Enable in Settings",
                    fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            }

        }
    }
}



@Composable
fun ServiceAvailability() {
    Box(
        modifier = Modifier

            .fillMaxWidth()
            .background(Color.White), contentAlignment = Alignment.Center
    ) {
        Column {
            Image(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(horizontal = 20.dp),
                contentDescription = "the service not available ",
                painter = painterResource(R.drawable.logo7)
            )
            Text(
                "Our service is not available in your area",

                modifier = Modifier

                    .padding(vertical = 16.dp, horizontal = 16.dp),
                color = Color.Black, fontSize = 24.sp, fontWeight = FontWeight.Bold
            )

            Button(
                onClick = { /* Handle new method */ },
                shape = RoundedCornerShape(50.dp),
                modifier = Modifier


                    .height(100.dp)
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 30.dp),
                colors = ButtonDefaults.buttonColors(Color.Black)
            ) {
                Text("Enter Address", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

        }
    }
}
