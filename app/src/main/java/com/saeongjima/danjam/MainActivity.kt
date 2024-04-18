package com.saeongjima.danjam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.saeongjima.designsystem.theme.DanjamTheme
import com.saeongjima.navigation.OnboardingDestination
import com.saeongjima.navigation.onboardingNavGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            DanjamTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = OnboardingDestination.Splash.route,
                    ) {
                        onboardingNavGraph(
                            onCloseClick = { navController.navigate(OnboardingDestination.Splash.route) },
                            onSignInClick = { navController.navigate(OnboardingDestination.SignIn.route) },
                            onSignUpClick = { navController.navigate(OnboardingDestination.SignUp.route) },
                        )
                    }
                }
            }
        }
    }
}
