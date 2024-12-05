import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R

@Composable
fun drawerContent(modifier: Modifier = Modifier, navController: NavController) {
    Row(
        modifier = Modifier.padding(16.dp)
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
        Column(modifier = Modifier.padding(vertical = 10.dp)) {
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
                Text("4.8", fontSize = 16.sp)
            }
        }
    }
    HorizontalDivider()
    NavigationDrawerItem(
        onClick = { navController.navigate(Destination.TripsHistoryScreen.route) },
        icon = {
            Icon(
                modifier = Modifier

                    .padding(20.dp) // Apply padding inside the background
                    .clickable { } // Handle clicks last
                    .size(28.dp), // Set the size of the icon
                contentDescription = "menu",
                painter = painterResource(R.drawable.notes), tint = Color.Unspecified

            )
        },
        selected = false,
        label = {
            Text(
                "Trips History", fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        },
    )

    NavigationDrawerItem(
        onClick = { navController.navigate(Destination.PaymentScreen.route) },
        icon = {
            Icon(
                modifier = Modifier

                    .padding(20.dp) // Apply padding inside the background

                    .size(28.dp), // Set the size of the icon
                contentDescription = "menu",
                painter = painterResource(R.drawable.dollar), tint = Color.Unspecified

            )
        },
        selected = false,
        label = { Text("Payment", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
    )
    Spacer(Modifier.height(5.dp))




    NavigationDrawerItem(
        onClick = { navController.navigate(Destination.voucherScreen.route) },
        icon = {
            Icon(
                modifier = Modifier

                    .padding(20.dp) // Apply padding inside the background
                    .clickable { } // Handle clicks last
                    .size(28.dp), // Set the size of the icon
                contentDescription = "menu",
                painter = painterResource(R.drawable.voucher), tint = Color.Unspecified

            )
        },
        selected = false,
        label = { Text("Voucher", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
    )
    Spacer(Modifier.height(5.dp))

    NavigationDrawerItem(
        onClick = {navController.navigate(Destination.settings.route)},
        icon = {
            Icon(
                modifier = Modifier

                    .padding(20.dp) // Apply padding inside the background
                    .clickable { } // Handle clicks last
                    .size(28.dp), // Set the size of the icon
                contentDescription = "menu",
                painter = painterResource(R.drawable.cogwheel), tint = Color.Unspecified

            )
        },
        selected = false,
        label = { Text("Settings", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
    )
    Spacer(Modifier.height(5.dp))

    NavigationDrawerItem(
        onClick = {},
        icon = {
            Icon(
                modifier = Modifier

                    .padding(20.dp) // Apply padding inside the background
                    .clickable { } // Handle clicks last
                    .size(28.dp), // Set the size of the icon
                contentDescription = "menu",
                painter = painterResource(R.drawable.headphone), tint = Color.Unspecified
            )
        },
        selected = false,
        label = { Text("Help", fontSize = 18.sp, fontWeight = FontWeight.Bold) },
    )
    Spacer(Modifier.height(5.dp))


}

