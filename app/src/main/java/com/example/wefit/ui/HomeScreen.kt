package com.example.wefit.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onVezbeClick: () -> Unit,
    onTreninciClick: () -> Unit,
    onStatistikaClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("WeFit - Fitness Tracker") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Button(
                onClick = onVezbeClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text("Vežbe", style = MaterialTheme.typography.headlineSmall)
            }

            Button(
                onClick = onTreninciClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text("Moji Treninzi", style = MaterialTheme.typography.headlineSmall)
            }

            Button(
                onClick = onStatistikaClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
            ) {
                Text("Statistika", style = MaterialTheme.typography.headlineSmall)
            }
        }
    }
}