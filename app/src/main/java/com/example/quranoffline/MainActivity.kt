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
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                        startDestination = "all_reciters"
                    ) {
                        composable("all_reciters") {
                            AllRecitersScreen(
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .systemBarsPadding(),
                                viewModel = reciterViewModel,
                                onReciterClick = { reciterId ->
                                    navController.navigate("reciter/$reciterId")
                                }
                            )
                        }

                        composable("reciter/{reciterId}") { backStackEntry ->
                            val reciterId = backStackEntry.arguments?.getString("reciterId")
                            ReciterScreen(
                                modifier = Modifier
                                    .padding(innerPadding)
                                    .systemBarsPadding(),
                                viewModel = reciterViewModel,
                                reciterId = reciterId.orEmpty(),
                            )
                        }
                    }
                }
            }
        }
    }
}

