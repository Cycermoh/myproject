package com.maureen.MyTravelApp.navigation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maureen.MyTravelApp.ui.theme.screens.Booking.BookingScreen
import com.maureen.MyTravelApp.ui.theme.screens.Destination.DestinationDetailScreen
import com.maureen.MyTravelApp.ui.theme.screens.Destination.DestinationListScreen
import com.maureen.MyTravelApp.ui.theme.screens.Login.LoginScreen
import com.maureen.MyTravelApp.ui.theme.screens.Profile.ProfileScreen
import com.maureen.MyTravelApp.ui.theme.screens.Register.RegisterScreen
import com.maureen.MyTravelApp.ui.theme.screens.Search.SearchScreen
import com.maureen.MyTravelApp.ui.theme.screens.home.HomeScreen
//import com.maureen.MyTravelApp.ui.theme.screens.profile.ProfileScreen
import com.maureen.MyTravelApp.ui.theme.screens.splash.SplashScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUTE_SPLASH

) {
    BackHandler {
        navController.popBackStack()
    }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {

        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }

        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }

        composable(ROUTE_HOME) {
            HomeScreen(navController)
        }

        composable(ROUTE_REGISTER) {
            RegisterScreen(navController)
        }
        composable(ROUTE_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUTE_SPLASH) {
            SplashScreen(navController)
        }
        composable(ROUTE_BOOKING) {
            BookingScreen(navController)

        }
        composable(ROUTE_SEARCH) {
            SearchScreen(navController)
        }

        composable(ROUTE_PROFILE) {
            ProfileScreen(navController)
        }
        composable(ROUTE_DESTINATION) {
            DestinationDetailScreen(navController)
        }
        composable(ROUTE_DESTINATION) {
            DestinationListScreen(navController)
        }
    }
}





