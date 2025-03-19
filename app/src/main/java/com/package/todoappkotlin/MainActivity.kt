package com.package.todoappkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.package.todoappkotlin.Util.NotificationHelper
import com.package.todoappkotlin.ui.theme.ToDOappKotlinTheme
import com.package.todoappkotlin.uiall.screens.MainScreen
import com.package.todoappkotlin.uiall.screens.SplashScreen
import com.package.todoappkotlin.uiall.viewmodels.TodoViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        // Dagger 2 injection
        (application as App).appComponent.inject(this)

        // Sistem çubuklarının (durum ve navigasyon) görünürlüğünü ayarlar
        WindowCompat.setDecorFitsSystemWindows(window, true)
        super.onCreate(savedInstanceState)

        val notificationHelper = NotificationHelper(this)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(TodoViewModel::class.java)

        setContent {
            ToDOappKotlinTheme {
                // Basit navigation: SplashScreen → MainScreen
                val showSplash = remember { mutableStateOf(true) }

                if (showSplash.value) {
                    SplashScreen { showSplash.value = false }
                    LaunchedEffect(Unit) {
                        delay(2000)
                        showSplash.value = false
                    }
                } else {
                    MainScreen(viewModel = viewModel, notificationHelper = notificationHelper)
                }
            }
        }
    }
}
