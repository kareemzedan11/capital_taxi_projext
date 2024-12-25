package com.example.capital_taxi.Presentation.ui.Driver.Screens.Home.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripDetailsForDriver(navController: NavController) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
    )

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            CancellationReasons()
        }
    }
    Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
      Column(modifier = Modifier.padding(vertical =  16.dp)) {
          Card(
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(16.dp),
              shape = RoundedCornerShape(8.dp),
              elevation = CardDefaults.elevatedCardElevation(20.dp)
          ) {
              Box(
                  modifier = Modifier
                      .fillMaxWidth()
                      .background(Color.White)
              ) {
                  Column(
                      modifier = Modifier
                          .fillMaxWidth()
                          .padding(horizontal = 10.dp)
                  ) {
                      Row(
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(vertical = 5.dp),
                          horizontalArrangement = Arrangement.Start,
                          verticalAlignment = Alignment.CenterVertically
                      ) {

                          androidx.compose.material.Icon(
                              modifier = Modifier.size(50.dp),
                              painter = painterResource(R.drawable.person),
                              contentDescription = null,
                              tint = Color.Unspecified
                          )

                          Spacer(modifier = Modifier.width(10.dp))

                          Column {
                              Text(
                                  text = "Ahmed",
                                  color = Color.Black,
                                  fontSize = 18.sp
                              )
                              Row {
                                  Text(
                                      text = "4.5",
                                      color = Color.Black,
                                      fontSize = 16.sp
                                  )
                                  Spacer(Modifier.width(5.dp))

                                  Icon(
                                      tint = Color.Unspecified,
                                      contentDescription = null,
                                      painter = painterResource(R.drawable.baseline_star_rate_24)
                                  )
                              }
                          }
                      }

                      HorizontalDivider(
                          thickness = 2.dp,
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(horizontal = 5.dp, vertical = 10.dp)
                      )

                      RidePointDetails(
                          Locationicon = R.drawable.circle,
                          Destinationicon = R.drawable.travel,
                          LocationText = "Cairo",
                          DestinationText = "Alex",
                          distance1 = "3m (1.2KM)",
                          distance2 = "45m (20KM)",
                          onClick = { }
                      )

                      HorizontalDivider(
                          thickness = 2.dp,
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(horizontal = 5.dp, vertical = 10.dp)
                      )

                      // أزرار الدردشة والمكالمات
                      Row(
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(16.dp),
                          horizontalArrangement = Arrangement.Start,
                          verticalAlignment = Alignment.CenterVertically
                      ) {
                          // زر الدردشة بشكل مربع
                          Box(
                              modifier = Modifier
                                  .fillMaxWidth(.8f)

                                  .background(Color.LightGray, RoundedCornerShape(topStart = 30.dp, bottomStart = 30.dp)) // خلفية مربعة
                                  .padding(10.dp),  // المسافة داخل المربع
                              contentAlignment = Alignment.CenterStart
                          ) {
                              IconButton(
                                  onClick = { }
                              ) {
                                  Icon(
                                      painter = painterResource(id = R.drawable.message),
                                      contentDescription = "Chat",
                                      modifier = Modifier.size(30.dp),
                                      tint = Color.Black // لون الأيقونة
                                  )
                              }
                          }



                          Box(modifier = Modifier.weight(.1f)
                              .background(Color(0XFF46C96B), RoundedCornerShape(topEnd = 30.dp, bottomEnd = 30.dp)) // خلفية مربعة
                              .padding(10.dp),  // المسافة داخل المربع

                          ){
                              IconButton(
                                  modifier = Modifier.clip(CircleShape).background(Color.White),
                                  onClick = { /* اضافة الكود للمكالمة */ }
                              ) {
                                  Icon(
                                      painter = painterResource(id = R.drawable.baseline_phone_24),
                                      contentDescription = "Call",
                                      modifier = Modifier.size(30.dp),
                                      tint = Color.Black
                                  )


                              }
                          }
                      }


                      Button(
                          onClick = { showBottomSheet=true },
                          modifier = Modifier
                              .fillMaxWidth()
                              .padding(horizontal = 16.dp, vertical = 24.dp)
                              .height(50.dp),
                          colors = ButtonDefaults.buttonColors(backgroundColor = Color.LightGray)
                      ) {
                          androidx.compose.material.Text("Cancel Trip", color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                      }
                  }
              }
          }

          Button(
              onClick = { /* Handle invite */ },
              modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 16.dp, vertical = 24.dp)
                  .height(60.dp),
              colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
          ) {
              androidx.compose.material.Text("Stop Accept Trips", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Bold)
          }
      }
    }
}



@Composable
fun CancellationReasons() {
    val reasons = listOf(
        "Passenger canceled the ride",
        "Driver canceled the ride",
        "Driver arrived late",
        "Passenger didn't show up",
        "Trip details were incorrect",
        "Technical issues with the app",
        "Payment issues"
    )

    Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = "Reasons for cancellation:",
                color = Color.Black,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom =  16.dp)
            )


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(reasons) { reason ->
                Box(modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 5.dp, horizontal = 5.dp)
                    .background(Color.LightGray)){
                    Text(
                        text = reason,
                        color = Color.Black,
                        fontSize = 18.sp,
                        modifier = Modifier.padding( 8.dp)
                    )
                }
            }
        }
    }}