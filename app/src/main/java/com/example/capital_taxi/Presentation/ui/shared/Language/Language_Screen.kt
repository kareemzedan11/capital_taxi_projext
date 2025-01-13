import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.navigation.NavController
import com.example.capital_taxi.R
import com.example.capital_taxi.Presentation.ui.shared.Language.components.LanguagePreference
import kotlinx.coroutines.delay
import java.util.*

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageDScreen(
    navController: NavController,
    onLanguageSelected: (String) -> Unit,
    context: Context
) {

    val generalColor = colorResource(id = R.color.primary_color)
    val secondColor = colorResource(id = R.color.secondary_color)

    var selectedLanguage by remember { mutableStateOf(LanguagePreference.getSavedLanguage(context)) }
    var isLoading by remember { mutableStateOf(false) }  // State to handle loading progress
    val currentLanguage =
        rememberUpdatedState(selectedLanguage)  // Remember current selected language


    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .background(Color.Transparent)
                                .border(4.dp, color = Color.Black, RoundedCornerShape(30.dp)),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                modifier = Modifier.size(26.dp),
                                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24),
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
                    }
                },
                title = {
                    Text(
                        stringResource(R.string.select_language),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = generalColor)
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(innerPadding)
                .imePadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LanguageButton(
                    language = stringResource(R.string.English),

                    color = secondColor,
                    painter = R.drawable.us,
                    check = if (selectedLanguage == "en") R.drawable.baseline_check_circle_outline_24 else R.drawable.baseline_radio_button_unchecked_24,
                    isSelected = selectedLanguage == "en",
                    isLoading = isLoading,
                    onClick = {
                        onLanguageSelected("en")
                        isLoading = true
                        selectedLanguage = "en"
                        LanguagePreference.saveLanguage(context, "en")
                        updateLocale(context, "en")
                        onLanguageSelected("en")
                    }
                )

                LanguageButton(
                    language =   stringResource(R.string.Arabic),

                    color = secondColor,
                    painter = R.drawable.egypt,
                    check = if (selectedLanguage == "ar") R.drawable.baseline_check_circle_outline_24 else R.drawable.baseline_radio_button_unchecked_24,
                    isSelected = selectedLanguage == "ar",
                    isLoading = isLoading,
                    onClick = {
                        onLanguageSelected("ar")
                        isLoading = true
                        selectedLanguage = "ar"
                        LanguagePreference.saveLanguage(context, "ar")
                        updateLocale(context, "ar")


                    }
                )
            }
        }
    }


    // Force UI recomposition when language changes
    LaunchedEffect(currentLanguage.value) {
        if (Locale.getDefault().language.equals("ar"))

            View.LAYOUT_DIRECTION_RTL else View.LAYOUT_DIRECTION_LTR
        updateLocale(context, currentLanguage.value)
    }

    // Simulating a delay to hide progress circle after language change
    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(1500)  // Simulate a delay (e.g., network operation)
            isLoading = false
        }
    }
}

fun updateLocale(context: Context, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val config = context.resources.configuration
    config.setLocale(locale)

    if (languageCode == "ar") {

        config.setLayoutDirection(Locale("ar"))
        View.LAYOUT_DIRECTION_RTL
        println("View is View.LAYOUT_DIRECTION_RTL ${View.LAYOUT_DIRECTION_RTL}")
    } else {
        config.setLayoutDirection(Locale("en"))
        View.LAYOUT_DIRECTION_LTR
        println("View is View.LAYOUT_DIRECTION_LTR ${View.LAYOUT_DIRECTION_LTR}")
    }

    // Update configuration
    context.resources.updateConfiguration(config, context.resources.displayMetrics)


}



@Composable
fun LanguageButton(
    language: String,
    color: Color,
    painter: Int,
    check: Int,
    isSelected: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit,
) {
    Card(elevation = CardDefaults.elevatedCardElevation(10.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .padding(vertical = 8.dp)
                .background(color, RoundedCornerShape(8.dp))
                .clickable { onClick() }
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(painter),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(40.dp)
                    )

                    Text(
                        text = language,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

                // Show the circular progress indicator if isLoading is true
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.Green,
                        strokeWidth = 2.dp
                    )
                } else {
                    // Circle indicator for selection
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(
                                if (isSelected) Color.Green else Color.Transparent,
                                shape = RoundedCornerShape(50)
                            )
                            .border(
                                2.dp,
                                if (isSelected) Color.Green else Color.Gray,
                                RoundedCornerShape(50)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSelected) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_check_circle_outline_24),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
