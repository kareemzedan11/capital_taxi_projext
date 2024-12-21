import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun settings(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0XFF46C96B)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Header Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0XFF46C96B))
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
                contentDescription = "Close Icon",
                tint = Color.Black
            )

            // Title
            Text(
                "Settings",
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
                    .verticalScroll(rememberScrollState()), // Enable scrolling
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                // Section 1: Account Settings
                SectionTitle(title = "Account Settings")
                SettingBox(
                    title = "Home",
                    icon = Icons.Default.Home,
                    color = Color(0XFF46C96B),
                    onClick = { /* Handle phone number click */ }
                )
                SettingBox(
                    title = "Add Work",
                    icon = Icons.Default.Home,
                    color = Color(0XFF46C96B),
                    onClick = { /* Handle phone number click */ }
                )
                SettingBox(
                    title = "Phone Number",
                    icon = Icons.Default.Phone,
                    color = Color(0XFF46C96B),
                    onClick = { /* Handle phone number click */ }
                )
                SettingBox(
                    title = "Saved Places",
                    icon = Icons.Default.Home,
                    color = Color(0XFF46C96B),
                    onClick = { /* Handle saved places click */ }
                )
                SettingBox(
                    title = "Communication",
                    color = Color(0XFF46C96B),

                    icon = Icons.Default.Close,
                    onClick = { /* Handle language change */ }
                )
                // Section 2: Preferences
                SectionTitle(title = "Preferences")
                SettingBox(
                    title = "Language",
                    icon = Icons.Default.Add,
                    color = Color(0XFF46C96B),
                    onClick = { /* Handle language change */ }
                )
                SettingBox(
                    title = "Night Mode",
                    icon = Icons.Default.Add,
                    color = Color(0XFF46C96B),
                    onClick = { /* Handle night mode toggle */ }
                )
// Inside the settings function, after the Security section
                SectionTitle(title = "Safety")
                SettingBox(
                    title = "Two-Factor Authentication",
                    icon = Icons.Default.Add,
                    color = Color(0xFF46C96B),
                    onClick = { /* Handle Two-Factor Authentication */ }
                )
                SettingBox(
                    title = "Privacy Settings",
                    icon = Icons.Default.Lock,
                    color = Color(0xFF46C96B),
                    onClick = { /* Handle Privacy Settings */ }
                )
                SettingBox(
                    title = "Security Notifications",
                    icon = Icons.Default.Notifications,
                    color = Color(0xFF46C96B),
                    onClick = { /* Handle Security Notifications */ }
                )
                SettingBox(
                    title = "Emergency Contact",
                    icon = Icons.Default.Phone,
                    color = Color(0xFF46C96B),
                    onClick = { /* Handle Emergency Contact */ }
                )

                // Section 3: Security
                SectionTitle(title = "Security")
                SettingBox(
                    title = "Log Out",
                    color = Color.Red,
                    icon = Icons.Default.ExitToApp,
                    onClick = { /* Handle log out */ }
                )
                SettingBox(
                    title = "Delete Account",
                    color = Color.Red,
                    icon = Icons.Default.Delete,
                    onClick = { /* Handle account deletion */ }
                )
            }
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
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
    color: Color,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp) // Add spacing between items
            .background(Color(0xFFF2F2F2), RoundedCornerShape(12.dp)) // Background color with rounded corners
            .clickable { onClick() }
    ) {
        SettingOptionWithArrow(
            title = title,
            icon = icon,
            color = color,
            onClick = onClick
        )
    }
}

@Composable
fun SettingOptionWithArrow(
    title: String,
    color: Color,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
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
            imageVector = icon,
            contentDescription = title,
            tint = color,
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
