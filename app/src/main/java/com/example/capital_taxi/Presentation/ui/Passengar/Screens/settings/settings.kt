import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capital_taxi.Helper.PartialBottomSheet
import com.example.capital_taxi.Navigation.Destination
import com.example.capital_taxi.R

@Composable
fun settings(navController: NavController) {
    var isDark by remember { mutableStateOf(false) }
    var showBottomSheet by remember { mutableStateOf(false) }

    PartialBottomSheet(
        showBottomSheet = showBottomSheet,
        onDismissRequest = { showBottomSheet = false }) {

        AppearanceBottomSheetContent(isDark, navController)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.primary_color)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Header Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(colorResource(R.color.primary_color))
        ) {
            // Close Icon
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp, top = 60.dp)
                    .size(40.dp)
                    .align(Alignment.TopStart)
                    .clip(CircleShape)
                    .background(Color.White)
                    .clickable { navController.popBackStack() },
                imageVector = Icons.Default.Close,
                contentDescription = "close",
                tint = Color.Black
            )

            // Title
            Text(
                stringResource(R.string.Settings),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 60.dp),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Scrollable Content
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp))
                .background(Color.White),
            contentAlignment = Alignment.TopStart
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                // Section 1: Account Settings
                SectionTitle(title = stringResource(R.string.Account_Settings))
                SettingBox(
                    title = stringResource(R.string.Home),
                    icon = painterResource(R.drawable.home),
                    onClick = { navController.navigate(Destination.HomePlace.route) }
                )
                SettingBox(
                    title = stringResource(R.string.Add_Work),
                    icon = painterResource(R.drawable.work),
                    onClick = { navController.navigate(Destination.WorkPlace.route) }
                )
                SettingBox(
                    title = stringResource(R.string.Communication),
                    icon = painterResource(R.drawable.messageicon),
                    onClick = { /* Handle communication */ }
                )
                SettingBox(
                    title = stringResource(R.string.Saved_Places),
                    icon = painterResource(R.drawable.baseline_place_24),
                    onClick = { navController.navigate(Destination.SavedPlaces.route) }
                )

                // Section 2: Preferences
                SectionTitle(title = stringResource(R.string.Preferences))
                SettingBox(
                    title = stringResource(R.string.Language),
                    icon = painterResource(R.drawable.language),
                    onClick = { navController.navigate(Destination.LanguageDScreen.route) }
                )
                SettingBox(
                    title = stringResource(R.string.Appearance),
                    icon = painterResource(R.drawable.mode),
                    onClick = { showBottomSheet = true }
                )

                // Section 3: Safety
                SectionTitle(title = stringResource(R.string.Safety))
                SettingBox(
                    title = stringResource(R.string.Two_Factor_Authentication),
                    icon = painterResource(R.drawable.safety),
                    onClick = { /* Handle Two-Factor Authentication */ }
                )
                SettingBox(
                    title = stringResource(R.string.Privacy),
                    icon = painterResource(R.drawable.settings_3524636),
                    onClick = { /* Handle Privacy Settings */ }
                )
                SettingBox(
                    title = stringResource(R.string.Security_Notifications),
                    icon = painterResource(R.drawable.notification),
                    onClick = { /* Handle Security Notifications */ }
                )
                SettingBox(
                    title = stringResource(R.string.Emergency),
                    icon = painterResource(R.drawable.emergency),
                    onClick = { /* Handle Emergency Contact */ }
                )

                // Section 4: Security
                SectionTitle(title = stringResource(R.string.Account_Settings))
                SettingBox(
                    title = stringResource(R.string.Logout),
                    isred = true,
                    icon = painterResource(R.drawable.logout),
                    onClick = { /* Handle log out */ }
                )
                SettingBox(
                    title = stringResource(R.string.Delete_Account),
                    isred = true,
                    icon = painterResource(R.drawable.baseline_delete_24),
                    onClick = { /* Handle account deletion */ }
                )
            }
        }
    }
}
@Composable
fun AppearanceBottomSheetContent(isDark: Boolean, navController: NavController) {

    var isDark1 by remember { mutableStateOf(isDark) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.Appearance),
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.W700
            )
            HorizontalDivider(
                thickness = 2.dp,
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp)
            )

            Spacer(modifier = Modifier.padding(20.dp))

            // Light Mode Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.LightGray)
                    .border(1.dp, color = Color.Gray),
                elevation = CardDefaults.elevatedCardElevation(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { isDark1 = false }
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(colorResource(R.color.secondary_color)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_light_mode_24),
                            contentDescription = stringResource(id = R.string.light_mode),
                            tint = Color.Unspecified,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        stringResource(id = R.string.light_mode),
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (!isDark1) {
                        Icon(
                            painter = painterResource(R.drawable.selected),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(26.dp))

            // Dark Mode Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(Color.LightGray)
                    .border(1.dp, color = Color.Gray),
                elevation = CardDefaults.elevatedCardElevation(10.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { isDark1 = true }
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(colorResource(R.color.secondary_color)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_dark_mode_24),
                            contentDescription = stringResource(id = R.string.dark_mode),
                            tint = Color.Unspecified,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        stringResource(id = R.string.dark_mode),
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    if (isDark1) {
                        Icon(
                            painter = painterResource(R.drawable.selected),
                            contentDescription = stringResource(id = R.string.dark_mode),
                            tint = Color.Unspecified,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.padding(20.dp))

            Button(
                onClick = { navController.navigate(Destination.SelectTheMode.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .height(60.dp),
                colors = ButtonDefaults.buttonColors(colorResource(R.color.primary_color)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.save_button),
                    fontSize = 18.sp,
                    color = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.cancel_button),
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.W700
            )
        }
    }
}



@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier
            .padding(vertical = 8.dp)
    )
}

@Composable
fun SettingBox(
    title: String,
    icon: Painter,
    onClick: () -> Unit,
    isred: Boolean? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp) // Add spacing between items
            .background(
                Color(0xFFF2F2F2),
                RoundedCornerShape(12.dp)
            ) // Background color with rounded corners
            .clickable { onClick() }
    ) {
        SettingOptionWithArrow(
            title = title,
            icon = icon,
            isred = isred,
            onClick = onClick
        )
    }
}

@Composable
fun SettingOptionWithArrow(
    title: String,
    isred: Boolean? = null,
    icon: Painter,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp) // Larger height for more spacious options
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = title,
            tint = if (isred != null) Color.Red else colorResource(R.color.primary_color),
            modifier = Modifier.size(32.dp) // Larger icon size
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = title,
            fontSize = 20.sp, // Larger text size
            fontWeight = FontWeight.Medium,
            color = Color.Black,
            modifier = Modifier.weight(1f) // Text takes up the remaining space
        )
        Icon(
            imageVector = Icons.Default.KeyboardArrowRight, // Neutral arrow icon
            contentDescription = "Navigate",
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
    }
}