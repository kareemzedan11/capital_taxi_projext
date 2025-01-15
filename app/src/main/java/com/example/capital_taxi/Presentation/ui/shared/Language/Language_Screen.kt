import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        BackButton()
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
                    isSelected = selectedLanguage == "en",
                    isLoading = isLoading,
                    onClick = {
                        selectedLanguage = "en"  // Immediately update selected language
                        isLoading = true  // Show loading indicator
                        onLanguageSelected("en")
                        LanguagePreference.saveLanguage(context, "en")
                        updateLocale(context, "en")
                    }
                )

                LanguageButton(
                    language = stringResource(R.string.Arabic),
                    color = secondColor,
                    painter = R.drawable.egypt,
                    isSelected = selectedLanguage == "ar",
                    isLoading = isLoading,
                    onClick = {
                        selectedLanguage = "ar"  // Immediately update selected language
                        isLoading = true  // Show loading indicator
                        onLanguageSelected("ar")
                        LanguagePreference.saveLanguage(context, "ar")
                        updateLocale(context, "ar")
                    }
                )
            }
        }
    }

    // Handle language update effect
    LaunchedEffect(selectedLanguage) {
        updateLocale(context, selectedLanguage)
    }

    // Simulate loading
    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(1500)
            isLoading = false
        }
    }
}


@Composable
fun BackButton() {
    Box(
        modifier = Modifier
            .size(36.dp)
            .background(Color.Transparent)
            .border(4.dp, color = Color.Black, shape = RoundedCornerShape(30.dp)),
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

fun updateLanguage(context: Context, languageCode: String, selectedLanguage: String, onLanguageSelected: (String) -> Unit) {
    if (selectedLanguage != languageCode) {
        LanguagePreference.saveLanguage(context, languageCode)
        onLanguageSelected(languageCode)
        updateLocale(context, languageCode)
    }
}

fun updateLocale(context: Context, languageCode: String) {
    val locale = Locale(languageCode)
    Locale.setDefault(locale)

    val config = context.resources.configuration
    config.setLocale(locale)
    config.setLayoutDirection(locale)

    context.resources.updateConfiguration(config, context.resources.displayMetrics)
}

@Composable
fun LanguageButton(
    language: String,
    color: Color,
    painter: Int,
    isSelected: Boolean,
    isLoading: Boolean,
    onClick: () -> Unit
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

                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.Green,
                        strokeWidth = 2.dp
                    )
                } else {
                    SelectionIndicator(isSelected)
                }
            }
        }
    }
}

@Composable
fun SelectionIndicator(isSelected: Boolean) {
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
