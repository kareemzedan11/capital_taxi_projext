import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.MapSection
import com.example.capital_taxi.R

@Composable
fun TopBar(onOpenDrawer: () -> Unit, navController: NavController) {
    Row {
        Box(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = 20.dp) // Adjust padding
                .clip(CircleShape)
                .size(45.dp) // Adjust Box size
                .background(Color(0XFF46C96B)),
            contentAlignment = Alignment.Center // Center the Icon inside the Box
        ) {
            Icon(
                modifier = Modifier
                    .clickable { onOpenDrawer() }
                    .size(35.dp), // Adjust the Icon size as needed
                contentDescription = "menu",
                imageVector = Icons.Default.Menu,
                tint = Color.Black // Optional: Change the icon color
            )
        }


        }



    }

