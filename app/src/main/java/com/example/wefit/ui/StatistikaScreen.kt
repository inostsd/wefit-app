package com.example.wefit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wefit.viewmodel.TreningViewModel
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StatistikaScreen(
    viewModel: TreningViewModel,
    onNazadClick: () -> Unit
) {
    val treninzi = viewModel.svi_treninzi.collectAsState().value

    // Grupiraj treninge po mesecima
    val treninziPoMesecima = treninzi.groupBy {
        YearMonth.from(it.datum)
    }.toSortedMap(reverseOrder())

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Statistika") },
                navigationIcon = {
                    IconButton(onClick = onNazadClick) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Nazad")
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(treninziPoMesecima.size) { index ->
                val mesec = treninziPoMesecima.keys.toList()[index]
                val treninciMeseca = treninziPoMesecima[mesec] ?: emptyList()

                StatistikaKartica(
                    mesec = mesec.toString(),
                    treninzi = treninciMeseca
                )
            }
        }
    }
}

@Composable
fun StatistikaKartica(
    mesec: String,
    treninzi: List<com.example.wefit.model.Trening>
) {
    val ukupnoMinuta = treninzi.sumOf { it.trajanje_minuta }
    val ukupnoKalorija = treninzi.sumOf { it.calos }
    val brojTreninga = treninzi.size

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Mesec: $mesec",
                style = MaterialTheme.typography.headlineSmall
            )

            Divider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatistikaStavka(
                    naslov = "Broj treninga",
                    vrednost = brojTreninga.toString()
                )
                StatistikaStavka(
                    naslov = "Ukupno minuta",
                    vrednost = ukupnoMinuta.toString()
                )
                StatistikaStavka(
                    naslov = "Kalorije",
                    vrednost = ukupnoKalorija.toString()
                )
            }

            Divider()

            Text(
                text = "Prosečno po treningu:",
                style = MaterialTheme.typography.bodyMedium
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                StatistikaStavka(
                    naslov = "Prosek minuta",
                    vrednost = if (brojTreninga > 0) (ukupnoMinuta / brojTreninga).toString() else "0"
                )
                StatistikaStavka(
                    naslov = "Prosek kalorija",
                    vrednost = if (brojTreninga > 0) (ukupnoKalorija / brojTreninga).toString() else "0"
                )
            }
        }
    }
}

@Composable
fun StatistikaStavka(naslov: String, vrednost: String) {
    Column(
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            text = naslov,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = vrednost,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary
        )
    }
}