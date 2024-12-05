import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capital_taxi.R

@Composable
fun IntercityCard() {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .border(5.dp, Color(0XFF46C96B), RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(R.drawable.greencar),
                    contentDescription = "car logo"
                )
                Spacer(modifier = Modifier.weight(1f))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            "Comfort",
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "4",
                            fontSize = 16.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.W500
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(Icons.Default.Person, contentDescription = null)
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            "EGP 231",
                            fontSize = 21.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.W600
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        "3:45am dropoff",
                        fontSize = 18.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.W300
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun requestDesign() {
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Scrollable content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 100.dp) // Leave space for the fixed Box
        ) {
            items(10) {
                IntercityCard()
            }
        }

        // Fixed Box at the bottom
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Red)
                .clickable { isBottomSheetOpen = true } // Open bottom sheet
        ) {
            Text(
                text = "Fixed Box Content",
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // BottomSheet
        if (isBottomSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = { isBottomSheetOpen = false },

            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text("This is the bottom sheet content", fontSize = 18.sp, color = Color.Black)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { isBottomSheetOpen = false }) {
                        Text("Close Sheet")
                    }
                }
            }
        }
    }
}
