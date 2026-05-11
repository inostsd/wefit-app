package com.example.wefit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.wefit.ui.HomeScreen
import com.example.wefit.ui.VezbeScreen
import com.example.wefit.ui.TreninciScreen
import com.example.wefit.viewmodel.VezbaViewModel
import com.example.wefit.viewmodel.TreningViewModel
import com.example.wefit.database.WefiDatabase
import com.example.wefit.repository.VezbaRepository
import com.example.wefit.repository.TreningRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicijalizuj bazu podataka
        val database = WefiDatabase.getDatabase(this)
        val vezbaRepository = VezbaRepository(database.vezbaDao())
        val treningRepository = TreningRepository(database.treningDao())

        val vezbaViewModel = VezbaViewModel(vezbaRepository)
        val treningViewModel = TreningViewModel(treningRepository)

        setContent {
            val currentScreen = remember { mutableStateOf("home") }

            when (currentScreen.value) {
                "home" -> HomeScreen(
                    onVezbeClick = { currentScreen.value = "vezbe" },
                    onTreninciClick = { currentScreen.value = "treninzi" },
                    onStatistikaClick = { currentScreen.value = "statistika" }
                )
                "vezbe" -> VezbeScreen(
                    viewModel = vezbaViewModel,
                    onVezbaClick = { /* TODO */ },
                    onDodajVezbuClick = { /* TODO */ }
                )
                "treninzi" -> TreninciScreen(
                    viewModel = treningViewModel,
                    onTreningClick = { /* TODO */ },
                    onDodajTreningClick = { /* TODO */ }
                )
                "statistika" -> HomeScreen(
                    onVezbeClick = { currentScreen.value = "vezbe" },
                    onTreninciClick = { currentScreen.value = "treninzi" },
                    onStatistikaClick = { currentScreen.value = "statistika" }
                )
            }
        }
    }
}