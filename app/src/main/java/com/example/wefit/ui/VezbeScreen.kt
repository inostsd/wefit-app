package com.example.wefit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wefit.model.Vezba
import com.example.wefit.viewmodel.VezbaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VezbeScreen(
    viewModel: VezbaViewModel,
    onVezbaClick: (Vezba) -> Unit,
    onDodajVezbuClick: () -> Unit
) {
    val vezbe = viewModel.sve_vezbe.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vežbe") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onDodajVezbuClick) {
                Icon(Icons.Default.Add, contentDescription = "Dodaj vežbu")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(vezbe) { vezba ->
                VezbaCard(vezba, onVezbaClick)
            }
        }
    }
}

@Composable
fun VezbaCard(vezba: Vezba, onClick: (Vezba) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { onClick(vezba) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = vezba.naziv, style = MaterialTheme.typography.headlineSmall)
            Text(text = vezba.opis, style = MaterialTheme.typography.bodyMedium)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Setovi: ${vezba.broj_setova}")
                Text(text = "Ponavljanja: ${vezba.broj_ponavljanja}")
            }
        }
    }
}