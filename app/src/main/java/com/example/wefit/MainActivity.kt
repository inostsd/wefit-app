package com.example.wefit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.wefit.ui.HomeScreen
import com.example.wefit.ui.VezbeScreen
import com.example.wefit.ui.TreninciScreen
import com.example.wefit.ui.DodajVezbuScreen
import com.example.wefit.ui.DodajTreningScreen
import com.example.wefit.ui.StatistikaScreen
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
                    onVezbaClick = { currentScreen.value = "home" },
                    onDodajVezbuClick = { currentScreen.value = "dodaj_vezbu" }
                )
                "treninzi" -> TreninciScreen(
                    viewModel = treningViewModel,
                    onTreningClick = { currentScreen.value = "home" },
                    onDodajTreningClick = { currentScreen.value = "dodaj_trening" }
                )
                "dodaj_vezbu" -> DodajVezbuScreen(
                    viewModel = vezbaViewModel,
                    onNazadClick = { currentScreen.value = "vezbe" }
                )
                "dodaj_trening" -> DodajTreningScreen(
                    viewModel = treningViewModel,
                    onNazadClick = { currentScreen.value = "treninzi" }
                )
                "statistika" -> StatistikaScreen(
                    viewModel = treningViewModel,
                    onNazadClick = { currentScreen.value = "home" }
                )
            }
        }
    }
}