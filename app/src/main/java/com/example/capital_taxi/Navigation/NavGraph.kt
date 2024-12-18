package com.example.capital_taxi.Navigation

import PhoneVerification
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Login.DriverLoginIn
import com.example.capital_taxi.Presentation.ui.Driver.Screens.Register.DriverSignUp
import com.example.capital_taxi.Presentation.ui.Driver.Screens.VerficationScreens.Validation_Navigation.ValidationNavigation
import com.example.capital_taxi.Presentation.ui.Driver.Screens.VerficationScreens.faceValidation.FaceValidation
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.TripHistory.TripsHistoryScreen
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.LocationModalBottomSheetContent
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.PickupWithPickoffPoints
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.Components.WhereToButton
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.UserHome.UserHomeScreen
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.Voucher.voucherScreen
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Home.payment.PaymentScreen

import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Login.UserLogin
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.Register.UserRegister
import com.example.capital_taxi.Presentation.ui.Passengar.Screens.profile.Profile
import com.example.capital_taxi.Presentation.ui.screens.Confirm_information.ConfirmInformation
import com.example.capital_taxi.Presentation.ui.screens.OTP.OtpScreen
import com.example.capital_taxi.Presentation.ui.screens.Onboarding.Components.SelectTheMode2
import com.example.capital_taxi.Presentation.ui.screens.Onboarding.OnboardingPager
import com.example.capital_taxi.Presentation.ui.screens.Select_the_mode.SelectTheMode
import com.example.capital_taxi.Presentation.ui.screens.Start.StartScreen
import com.example.capital_taxi.ui.screens.Driver.VerficationScreens.CertificateOfVehicleRegistration
import com.example.capital_taxi.ui.screens.Driver.VerficationScreens.DriverLicence
import com.example.capital_taxi.ui.screens.Driver.VerficationScreens.NationalIDValidation
import settings

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destination.UserHomeScreen.route) {

        composable(Destination.SplashScreen.route) {
            SplashScreen(navController)
        }
        composable(Destination.StartScreen.route) {
            StartScreen(navController)
        }
        composable(Destination.OnboardingPager.route) {
            OnboardingPager(navController, onSignInClick = {})
        }
        composable(Destination.PhoneVerification.route) {
            PhoneVerification(navController)
        }
        composable(Destination.UserRegister.route) {
            UserRegister(navController)
        }
        composable(Destination.UserLogin.route) {
            UserLogin(navController)
        }

        composable(Destination.driverSignUp.route) {
            DriverSignUp(navController)
        }
        composable(Destination.driverLogin.route) {
            DriverLoginIn(navController)
        }
        composable(Destination.SelectTheMode.route) {
            SelectTheMode(navController)
        }
        composable(Destination.CertificateOfVehicleRegistration.route) {
            CertificateOfVehicleRegistration(navController)
        }
        composable(Destination.FaceValidation.route) {
            FaceValidation(navController)
        }
        composable(Destination.NationalIDValidation.route) {
            NationalIDValidation(navController)
        }
        composable(Destination.DriverLicence.route) {
            DriverLicence(navController)
        }
        composable(Destination.ValidationNavigation.route) {
            ValidationNavigation(navController)
        }
        composable(Destination.UserHomeScreen.route) {
            UserHomeScreen(navController)
        }
        composable(Destination.PaymentScreen.route) {
            PaymentScreen(navController)
        }
        composable(Destination.TripsHistoryScreen.route) {
            TripsHistoryScreen(navController)
        }
        composable(Destination.voucherScreen.route) {
            voucherScreen(navController)
        }
        composable(Destination.Profile.route) {
            Profile(navController)
        }
        composable(Destination.settings.route) {
            settings(navController)
        }
        composable(Destination.PickupWithPickoffPoints.route) {
            PickupWithPickoffPoints(navController)
        }
        composable(Destination.OtpScreen.route) {
            OtpScreen(navController)
        }
        composable(Destination.SelectTheMode2.route) {
            SelectTheMode2(navController)
        }
        composable(Destination.WhereToButton.route) {
            WhereToButton(navController)
        }



        composable(
            route = "ConfirmInformation?name={name}&email={email}&photoUrl={photoUrl}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType; defaultValue = "" },
                navArgument("email") { type = NavType.StringType; defaultValue = "" },
                navArgument("photoUrl") { type = NavType.StringType; defaultValue = "" }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val photoUrl = backStackEntry.arguments?.getString("photoUrl")
            ConfirmInformation(navController, name, email, photoUrl)
        }



    }
}
