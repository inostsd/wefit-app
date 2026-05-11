package com.example.wefit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wefit.model.Vezba
import com.example.wefit.viewmodel.VezbaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreporukiScreen(
    viewModel: VezbaViewModel,
    onNazadClick: () -> Unit,
    onVezbaClick: (Vezba) -> Unit
) {
    val vezbe = viewModel.sve_vezbe.collectAsState().value

    // Preporuke - sortira vežbe po težini (broju setova i ponavljanja)
    val preporukeVezbe = vezbe.sortedByDescending { it.broj_setova * it.broj_ponavljanja }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Preporučene vežbe") },
                navigationIcon = {
                    IconButton(onClick = onNazadClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Nazad")
                    }
                }
            )
        }
    ) { innerPadding ->
        if (preporukeVezbe.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Nema dostupnih vežbi",
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Dodaj vežbe da bi video/a preporuke",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(preporukeVezbe) { vezba ->
                    PreporukaCard(vezba, onVezbaClick)
                }
            }
        }
    }
}

@Composable
fun PreporukaCard(vezba: Vezba, onClick: (Vezba) -> Unit) {
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = vezba.naziv, style = MaterialTheme.typography.headlineSmall)
                    Text(text = vezba.opis, style = MaterialTheme.typography.bodyMedium)
                }

                Badge(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ) {
                    Text(text = "⭐", modifier = Modifier.padding(4.dp))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = "Setovi: ${vezba.broj_setova}",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Surface(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text(
                        text = "Ponavljanja: ${vezba.broj_ponavljanja}",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}