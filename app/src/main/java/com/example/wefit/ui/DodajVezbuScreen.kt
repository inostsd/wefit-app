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
import com.example.wefit.model.Vezba
import com.example.wefit.viewmodel.VezbaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DodajVezbuScreen(
    viewModel: VezbaViewModel,
    onNazadClick: () -> Unit
) {
    val naziv = remember { mutableStateOf("") }
    val opis = remember { mutableStateOf("") }
    val brojSetova = remember { mutableStateOf("") }
    val brojPonavljanja = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dodaj novu vežbu") },
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
                label = { Text("Naziv vežbe") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = opis.value,
                onValueChange = { opis.value = it },
                label = { Text("Opis vežbe") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                maxLines = 4
            )

            OutlinedTextField(
                value = brojSetova.value,
                onValueChange = { brojSetova.value = it },
                label = { Text("Broj setova") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = brojPonavljanja.value,
                onValueChange = { brojPonavljanja.value = it },
                label = { Text("Broj ponavljanja po setu") },
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (naziv.value.isNotEmpty() && brojSetova.value.isNotEmpty() && brojPonavljanja.value.isNotEmpty()) {
                        val novaVezba = Vezba(
                            naziv = naziv.value,
                            opis = opis.value,
                            broj_setova = brojSetova.value.toIntOrNull() ?: 0,
                            broj_ponavljanja = brojPonavljanja.value.toIntOrNull() ?: 0
                        )
                        viewModel.ubaciVezbu(novaVezba)
                        onNazadClick()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Text("Dodaj vežbu")
            }
        }
    }
}