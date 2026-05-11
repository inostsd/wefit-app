package com.example.wefit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wefit.model.Trening
import com.example.wefit.viewmodel.TreningViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DodajTreningScreen(
    viewModel: TreningViewModel,
    onNazadClick: () -> Unit
) {
    val naziv = remember { mutableStateOf("") }
    val trajanje = remember { mutableStateOf("") }
    val calorije = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dodaj novi trening") },
                navigationIcon = {
                    IconButton(onClick = onNazadClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Nazad")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = naziv.value,
                onValueChange = { naziv.value = it },
                label = { Text("Naziv treninga (npr. Prsni dan)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = trajanje.value,
                onValueChange = { trajanje.value = it },
                label = { Text("Trajanje (u minutama)") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = calorije.value,
                onValueChange = { calorije.value = it },
                label = { Text("Spalošene kalorije") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (naziv.value.isNotEmpty() && trajanje.value.isNotEmpty()) {
                        val noviTrening = Trening(
                            naziv = naziv.value,
                            datum = LocalDate.now(),
                            trajanje_minuta = trajanje.value.toIntOrNull() ?: 0,
                            calos = calorije.value.toIntOrNull() ?: 0
                        )
                        viewModel.ubaciTrening(noviTrening)
                        onNazadClick()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Dodaj trening")
            }
        }
    }
}