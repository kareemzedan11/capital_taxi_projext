package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.drawerTabs.income

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R


@Composable
fun IncomePage(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Income",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = { navController.popBackStack() }) {
                        Box(
                            modifier = Modifier

                                .size(36.dp)
                                .background(Color.Transparent)
                                .border(4.dp, color = Color.Black, RoundedCornerShape(30.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            androidx.compose.material3.Icon(
                                modifier = Modifier.size(26.dp),
                                painter = painterResource(id = R.drawable.baseline_arrow_back_ios_new_24),
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
                    }
                },
                backgroundColor = Color.White,
                contentColor = Color.Black
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
                    .background(Color.White)
            ) {
                // Total Income and Filter
                TotalIncomeSection()
                Spacer(modifier = Modifier.height(20.dp))
                dataBoxList()

            }
        }
    )
}


@Composable
fun TotalIncomeSection() {
    Card(elevation = 10.dp) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(colorResource(R.color.Icons_color), shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier.padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        "1 july - ",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        "7 july",
                        fontSize = 18.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )

                }
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    "0:00 EGB",
                    fontSize = 50.sp,
                    style = MaterialTheme.typography.h4,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(18.dp))

                Card(
                    modifier = Modifier
                        .fillMaxWidth(),
                    elevation = 20.dp,
                    shape = RoundedCornerShape(8.dp),
                    backgroundColor = Color.White
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),

                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                " Trips",
                                style = MaterialTheme.typography.h5,
                                fontWeight = FontWeight.Bold
                            )


                            Text("0", style = MaterialTheme.typography.button)
                        }


                        Spacer(modifier = Modifier.height(18.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),

                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Text(
                                " Call duration",
                                style = MaterialTheme.typography.h5,
                                fontWeight = FontWeight.Bold
                            )


                            Text("0", style = MaterialTheme.typography.button)

                        }
                        Spacer(modifier = Modifier.height(18.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                " Points",
                                style = MaterialTheme.typography.h5,
                                fontWeight = FontWeight.Bold
                            )


                            Text("0", style = MaterialTheme.typography.button)

                        }

                    }

                }
                Spacer(modifier = Modifier.height(18.dp))

                WithdrawButton("Withdraw Earnings", colorResource(R.color.primary_color))
            }

        }
    }

}

@Composable
fun dataBox(text: String, painter: Painter) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 20.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.White, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    tint = colorResource(R.color.primary_color),
                    modifier = Modifier.size(26.dp),
                    painter = painter,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text, style = MaterialTheme.typography.body1, color = Color.DarkGray)
                Spacer(modifier = Modifier.weight(1f))
                Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
            }
        }
    }
}

@Composable
fun dataBoxList() {
    WithdrawButton("Show details", Color.LightGray)
    Spacer(modifier = Modifier.padding(vertical = 20.dp))

    dataBox("Balance", painter = painterResource(R.drawable.balance))
    Spacer(modifier = Modifier.padding(vertical = 10.dp))

    dataBox("Withdraw Method", painter = painterResource(R.drawable.bank))
    Spacer(modifier = Modifier.padding(vertical = 10.dp))

    Box(modifier = Modifier.background(Color.White)) {
        Column(modifier = Modifier.fillMaxWidth()) {
            dataBox("Invite Offers", painter = painterResource(R.drawable.share))
            HorizontalDivider(thickness = 1.dp, modifier = Modifier.fillMaxWidth())


            dataBox("Income Opportunities", painter = painterResource(R.drawable.income))
            Spacer(modifier = Modifier.padding(vertical = 10.dp))
        }
    }

    dataBox("Trip Log", painter = painterResource(R.drawable.note))
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
}


@Composable
fun IncomeBreakdown() {
    Text("Income Breakdown", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold)
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(5) { index ->
            IncomeItem(
                name = "Ride #${index + 1}",
                date = "Dec ${index + 20}, 2024",
                amount = "$${(index + 1) * 12.34}"
            )
        }
    }
}

@Composable
fun IncomeItem(name: String, date: String, amount: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(name, style = MaterialTheme.typography.subtitle1, fontWeight = FontWeight.Bold)
            Text(date, style = MaterialTheme.typography.body2, color = Color.Gray)
        }
        Text(
            amount,
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
            color = Color.Green
        )
    }
}

@Composable
fun WithdrawButton(text: String, color: Color) {
    Button(
        onClick = { /* TODO: Handle withdraw action */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Text(text, style = MaterialTheme.typography.button)
    }
}
