package com.example.quranoffline

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.quranoffline.ui.theme.QuranOfflineTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {

    private var mediaPlayerService: MediaPlayerService? = null
    private var isBound = false
    private lateinit var reciterViewModel: ReciterViewModel

    private val isPlayingState = MutableStateFlow<Boolean>(false)

    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MediaPlayerService.MediaPlayerBinder
            mediaPlayerService = binder.getService()
            isBound = true

            // Now that service is connected, initialize the ViewModel
            mediaPlayerService?.let {
                reciterViewModel = ViewModelProvider(
                    this@MainActivity,
                    MediaPlayerViewModelFactory(it)
                )[ReciterViewModel::class.java]
            }

            CoroutineScope(Dispatchers.Main).launch {
                mediaPlayerService?.isPlaying
                    ?.map { it }
                    ?.collectLatest {
                        isPlayingState.emit(it)
                    }
            }
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            mediaPlayerService = null
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        if (isBound) {
            unbindService(serviceConnection)
            isBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MediaPlayerService::class.java)
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)

        enableEdgeToEdge()
        setContent {
            QuranOfflineTheme {
                val navController = rememberNavController()
                val isPlaying by isPlayingState.collectAsState()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.fillMaxSize()) {
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

                        // Overlay
                        if (isPlaying) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Transparent),
                                contentAlignment = Alignment.BottomCenter // Align to the bottom
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(horizontal = 8.dp), // Padding for the overlay
                                    verticalArrangement = Arrangement.Bottom
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.Center, // Center the buttons
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(100.dp)
                                            .clip(RoundedCornerShape(20.dp))
                                            .background(Color.Cyan)

                                    ) {
                                        IconButton(onClick = {
                                            TODO()
                                        }) {
                                            Icon(
                                                imageVector = Icons.Default.PlayArrow,
                                                contentDescription = "Play",
                                                tint = Color.White
                                            )
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

