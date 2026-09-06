package com.veylik.news.presentation

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.lifecycle.lifecycleScope
import coil3.toUri
import com.veylik.news.data.remote.NewsApiService
import com.veylik.news.domain.repository.NewsRepository
import com.veylik.news.presentation.screens.settings.SettingsScreen
import com.veylik.news.presentation.screens.subscriptions.SubscriptionsScreen
import com.veylik.news.presentation.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: NewsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            repository.addSubscription("Kotlin")
        }
        setContent {
            NewsTheme {
                SettingsScreen(
                    onBackClick = {}
                )
            }
        }
    }
}