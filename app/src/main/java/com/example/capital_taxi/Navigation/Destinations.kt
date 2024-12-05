package com.example.capital_taxi.Navigation

sealed class Destination(val route: String) {
    object SplashScreen : Destination("splashScreen")
    object StartScreen : Destination("startScreen")
    object OnboardingPager : Destination("OnboardingPager")
    object PhoneVerification : Destination("PhoneVerification")
    object ConfirmInformation : Destination("ConfirmInformation")
    object UserLogin : Destination("UserLogin")
    object UserRegister : Destination("UserRegister")
    object driverLogin : Destination("driverLogin")
    object driverSignUp : Destination("DriverSignUp")
    object SelectTheMode : Destination("SelectTheMode")
    object CertificateOfVehicleRegistration : Destination("CertificateOfVehicleRegistration")
    object DriverLicence : Destination("DriverLicence")
    object FaceValidation : Destination("FaceValidation")
    object NationalIDValidation : Destination("NationalIDValidation")
    object UserHomeScreen : Destination("UserHomeScreen")

    object ValidationNavigation : Destination("ValidationNavigation")
    object PaymentScreen : Destination("PaymentScreen")
    object TripsHistoryScreen : Destination("TripsHistoryScreen")
    object voucherScreen : Destination("voucherScreen")
    object Profile : Destination("Profile")

    object settings : Destination("settings")
}

