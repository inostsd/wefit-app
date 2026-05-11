package com.example.wefit.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.wefit.ui.theme.FitnessGreen
import com.example.wefit.ui.theme.FitnessOrange
import com.example.wefit.ui.theme.FitnessBlue
import com.example.wefit.ui.theme.FitnessRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onVezbeClick: () -> Unit,
    onTreninciClick: () -> Unit,
    onStatistikaClick: () -> Unit,
    onPreporukiClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "WeFit",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
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
            // Pozdrav
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(16.dp)
                    ),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = FitnessGreen.copy(alpha = 0.1f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Text(
                        "Dobrodošao/la! 💪",
                        style = MaterialTheme.typography.headlineSmall,
                        color = FitnessGreen
                    )
                    Text(
                        "Započni svoj dan sa treningom",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            // Menu buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuButton(
                    icon = Icons.Default.FitnessCenter,
                    title = "Vežbe",
                    backgroundColor = FitnessBlue,
                    onClick = onVezbeClick,
                    modifier = Modifier.weight(1f)
                )
                MenuButton(
                    icon = Icons.Default.TrendingUp,
                    title = "Treninzi",
                    backgroundColor = FitnessOrange,
                    onClick = onTreninciClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                MenuButton(
                    icon = Icons.Default.BarChart,
                    title = "Statistika",
                    backgroundColor = FitnessRed,
                    onClick = onStatistikaClick,
                    modifier = Modifier.weight(1f)
                )
                MenuButton(
                    icon = Icons.Default.Star,
                    title = "Preporuke",
                    backgroundColor = Color(0xFFFF5722),
                    onClick = onPreporukiClick,
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Footer
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "WeFit v1.0",
                        style = MaterialTheme.typography.labelSmall
                    )
                    Text(
                        "Fitness Tracker za sve",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun MenuButton(
    icon: ImageVector,
    title: String,
    backgroundColor: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(120.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor.copy(alpha = 0.15f)
        ),
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(40.dp),
                tint = backgroundColor
            )
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                color = backgroundColor,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}