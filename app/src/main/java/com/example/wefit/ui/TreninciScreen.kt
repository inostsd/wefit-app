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
import com.example.wefit.model.Trening
import com.example.wefit.viewmodel.TreningViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TreninciScreen(
    viewModel: TreningViewModel,
    onTreningClick: (Trening) -> Unit,
    onDodajTreningClick: () -> Unit
) {
    val treninzi = viewModel.svi_treninzi.collectAsState().value

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Moji treninzi") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onDodajTreningClick) {
                Icon(Icons.Default.Add, contentDescription = "Dodaj trening")
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
            items(treninzi) { trening ->
                TreningCard(trening, onTreningClick)
            }
        }
    }
}

@Composable
fun TreningCard(trening: Trening, onClick: (Trening) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { onClick(trening) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = trening.naziv, style = MaterialTheme.typography.headlineSmall)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Datum: ${trening.datum}")
                Text(text = "Trajanje: ${trening.trajanje_minuta} min")
            }
            Text(text = "Kalorije: ${trening.calos}", style = MaterialTheme.typography.bodySmall)
        }
    }
}