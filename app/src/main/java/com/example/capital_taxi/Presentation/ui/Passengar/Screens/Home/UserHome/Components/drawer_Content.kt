import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R

@Composable
fun drawerContent( navController: NavController) {
    Column(     horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .background(colorResource(R.color.primary_color)),
            contentAlignment = Alignment.Center // Center the Row horizontally and vertically
        ) {
            Row(
                modifier = Modifier.padding(5.dp).clickable { navController.navigate( Destination.Profile.route )},
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(60.dp),
                    tint = Color.Unspecified,
                    contentDescription = null,
                    painter = painterResource(R.drawable.person)
                )
                Spacer(Modifier.width(10.dp))
                Column(modifier = Modifier.padding(vertical = 10.dp) ) {
                    Text("Kareem", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Row {
                        // Rating Stars
                        repeat(5) {
                            Icon(
                                tint = Color.Unspecified,
                                contentDescription = null,
                                painter = painterResource(R.drawable.baseline_star_rate_24)
                            )
                        }
                        Spacer(Modifier.width(5.dp))
                        Text("5", fontSize = 16.sp)
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    tint = Color.Unspecified,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.KeyboardArrowRight
                )
            }
        }

        HorizontalDivider()
        Box(modifier = Modifier.fillMaxWidth()) {

              Column(
                  modifier = Modifier.verticalScroll(rememberScrollState()),
                  horizontalAlignment = Alignment.CenterHorizontally
              ) {

                  navigationDrawerItem(
                      onClick = { navController.navigate(Destination.TripsHistoryScreen.route) },
                      painter = painterResource(R.drawable.history_3949611),
                      text = "Trips History"
                  )

                  Spacer(Modifier.height(10.dp))


                  navigationDrawerItem(
                      onClick = { navController.navigate(Destination.PaymentScreen.route) },
                      painter = painterResource(R.drawable.operation_3080541),
                      text = "Payment"
                  )

                  Spacer(Modifier.height(10.dp))



                  navigationDrawerItem(
                      onClick = { navController.navigate(Destination.voucherScreen.route) },
                      painter = painterResource(R.drawable.voucher_3837379),
                      text = "Coupons"

                  )
                  Spacer(Modifier.height(10.dp))

                  navigationDrawerItem(
                      onClick = { navController.navigate(Destination.userNotification.route) },
                      painter = painterResource(R.drawable.notification),
                      text = "Notifications"
                  )
                   Spacer(Modifier.height(10.dp))

                  navigationDrawerItem(
                      onClick = { navController.navigate(Destination.SafetyScreen.route) },
                      painter = painterResource(R.drawable.safety),
                      text = "Safety"
                  )
                  Spacer(Modifier.height(10.dp))
       navigationDrawerItem(
                      onClick = { navController.navigate(Destination.settings.route) },
                      painter = painterResource(R.drawable.settings_3524636),
                      text = "Settings"
                  )

                  Spacer(Modifier.height(10.dp))

                  navigationDrawerItem(
                      onClick = {navController.navigate(Destination.HelpScreen.route) },
                      painter = painterResource(R.drawable.headphone_18080416),
                      text = "Help"
                  )
                  Spacer(Modifier.height(10.dp))

                  navigationDrawerItem(
                      onClick = { navController.navigate(Destination.voucherScreen.route) },
                      painter = painterResource(R.drawable.helpme2),
                      text = "Support"
                  )
                  Spacer(Modifier.height(10.dp))


                  navigationDrawerItem(
                      onClick = { navController.navigate(Destination.InviteFriendsPage.route) },
                      painter = painterResource(R.drawable.invite),
                      text = "Invite Friends"
                  )



          }
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = { navController.navigate(Destination.UserHomeScreen.route)},
            modifier = Modifier
                .fillMaxWidth(.9f)



                .height(60.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            shape = RoundedCornerShape(8.dp)

        ) {
            Text(
                text = "Become a Driver",
                fontSize = 18.sp,

                )
        }
        Spacer(Modifier.height(20.dp))
    }
    }


@Composable
fun navigationDrawerItem(onClick: () -> Unit, painter: Painter?=null, text: String) {
    NavigationDrawerItem(

        onClick = { onClick() },
        icon = {
        if(painter!=null){
            Icon(
                modifier = Modifier

                    .padding(start = 5.dp, end = 3.dp) // Apply padding inside the background
                    .size(28.dp), // Set the size of the icon
                contentDescription = "menu",
                painter =painter , tint = Color.Unspecified

            )

        }
        },
        selected = false,
        label = {
            Text(
                text, fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        badge = {
            Icon(
                modifier = Modifier

                    .padding(start = 5.dp, end = 3.dp) // Apply padding inside the background
                    .size(28.dp), // Set the size of the icon
                contentDescription = "menu",
                imageVector = Icons.Default.KeyboardArrowRight, tint = Color.Unspecified

            )
        },
    )

}

