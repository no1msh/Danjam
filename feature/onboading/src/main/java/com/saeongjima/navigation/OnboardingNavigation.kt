package com.saeongjima.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.saeongjima.navigation.OnboardingDestination.SignIn
import com.saeongjima.navigation.OnboardingDestination.SignUp
import com.saeongjima.navigation.OnboardingDestination.Splash
import com.saeongjima.signin.SignInScreen
import com.saeongjima.signup.SignUpScreen
import com.saeongjima.splash.SplashScreen

enum class OnboardingDestination(val route: String) {
    Splash(route = "splash"),
    SignIn(route = "singIn"),
    SignUp(route = "signUp"),
}

fun NavGraphBuilder.onboardingNavGraph(
    onCloseClick: () -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    composable(route = Splash.route) {
        SplashScreen(
            onSignInClick = onSignInClick,
            onSignUpClick = onSignUpClick,
        )
    }

    composable(route = SignIn.route) {
        SignInScreen(
            onCloseClick = onCloseClick,
        )
    }

    composable(route = SignUp.route) {
        SignUpScreen(
            onCloseClick = onCloseClick,
        )
    }
}
