package com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.capital_taxi.R

@Composable
fun RideHistoryCard(
    from: String,
    to: String,
    date: String,
    price: String,
    stop: String? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(13.dp),
        elevation = 4.dp,
        backgroundColor = colorResource(R.color.secondary_color),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically

            ) {

                Text(text = "From :", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = from, style = MaterialTheme.typography.body2)


                Spacer(modifier = Modifier.weight(1f))
                Text(text = date, style = MaterialTheme.typography.body2)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically

            ) {
                stop?.let {


                    Text(text = "Stop 1 :", style = MaterialTheme.typography.body1)
                    Spacer(modifier = Modifier.width(8.dp))

                    Text(text = stop, style = MaterialTheme.typography.body2)

                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "To :", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.width(8.dp))

                Text(text = to, style = MaterialTheme.typography.body2)
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Price: $price EGP",
                style = MaterialTheme.typography.h6,
                color = colorResource(R.color.primary_color)
            )
        }
    }
}

@Composable
fun RideHistoryList() {
    LazyColumn {
        items(5) { // Adjust the number of items as needed
            RideHistoryCard(
                from = "Lotfy Iappip Strt",
                to = "Abbas El-Akkad Strt",
                date = "2024/6/12",
                price = "30.00",
                stop = if (it == 1) "Nasrcity Elbank Elahly" else null // Example of a stop for one card
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RideHistoryPreview() {
    RideHistoryList()
}
