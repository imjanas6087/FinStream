package com.example.finstream.screen


import android.R
import android.content.res.Resources
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.finstream.Asset
import com.example.finstream.ui.theme.FinStreamTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DashboardScreen(onAssetClick: (String) -> Unit) {

    val mockAssets = listOf(
        Asset("BTC", "Bitcoin", 64230.50, 2.45),
        Asset("ETH", "Ethereum", 3450.12, -1.20),
        Asset("AAPL", "Apple Inc.", 189.43, 0.85),
        Asset("TSLA", "Tesla", 175.22, -3.10)
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FinStream", fontWeight = FontWeight.Bold) },
                actions = {

                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onBackground,
                    actionIconContentColor = MaterialTheme.colorScheme.onBackground
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {

            Spacer(modifier = Modifier.padding(16.dp))
            Text(
                text = "Portfolio Balance",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                fontSize = 14.sp
            )
            Text(
                text = "$12,450.89",
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.padding(16.dp))

            Text(
                text = "Watch List",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.SemiBold,
                fontSize = 25.sp
            )
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(mockAssets) { asset ->
                    AssetRow(asset = asset, onClick = { onAssetClick(asset.symbol) })
                }
            }

        }
    }
}

@Composable
fun AssetRow(asset: Asset, onClick: () -> Unit) {

    val isPositive = asset.percentage > 0
    val trendColor =
        if (isPositive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    val changeSign = if (isPositive) "+" else ""

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable {}
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = asset.type,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = " ",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                fontSize = 12.sp
            )
        }
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text(if (isPositive) "↗" else "↘", color = trendColor, fontSize = 24.sp)
        }

        Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(1f)) {
            Text(
                "$${asset.price}",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                "$changeSign${asset.percentage}%",
                color = trendColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    FinStreamTheme {
        DashboardScreen({})
    }
}

