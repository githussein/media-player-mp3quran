package com.example.quranoffline

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.quranoffline.ui.theme.QuranOfflineTheme

class MainActivity : ComponentActivity() {
    private val reciterViewModel: ReciterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuranOfflineTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Home
                    ) {
                        composable<Home> {
                            HomeScreen(
                                modifier = Modifier.systemBarsPadding(),
                                navController = navController
                            )
                        }

                        composable<RadioStations> {
                            Text("Radio Stations")
                        }

                        composable<AllReciter> {
                            AllRecitersScreen(
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .systemBarsPadding(),
                                viewModel = reciterViewModel,
                                navController = navController
                            )
                        }

                        composable<ReciterNavigation> {
                            val reciterId = it.toRoute<ReciterNavigation>().reciterId
                            ReciterScreen(
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .systemBarsPadding(),
                                viewModel = reciterViewModel,
                                reciterId = reciterId,
                            )
                        }
                    }
                }
            }
        }
    }
}

