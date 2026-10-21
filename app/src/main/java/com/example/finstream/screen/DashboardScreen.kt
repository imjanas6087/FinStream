package com.example.finstream.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import com.example.finstream.BottomNavigation.BottomNavigation
import com.example.finstream.R
import com.example.finstream.data.DashBoardDataBOItem
import com.example.finstream.ui.theme.FinStreamTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DashboardScreen(onAssetClick: (String) -> Unit) {
    val navController = rememberNavController()
    val mockAssets = listOf(
        DashBoardDataBOItem(
            ath = 126080.0,
            ath_change_percentage = -33.00412,
            ath_date = "2025-10-06T10:57:42.000Z",
            atl = 67.81,
            atl_change_percentage = 124468.0129,
            atl_date = "2013-07-05T16:00:00.000Z",
            circulating_supply = 20098843.0,
            current_price = 84468.0,
            fully_diluted_valuation = 1697822263429L,
            high_24h = 84808.0,
            id = "bitcoin",
            image = "https://coin-images.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
            last_updated = "2026-10-21T19:32:53.000Z",
            low_24h = 82458.0,
            market_cap = 1697715579936L,
            market_cap_change_24h = 41396613533.0,
            market_cap_change_percentage_24h = 2.49931,
            market_cap_rank = 1,
            max_supply = 21000000.0,
            name = "Bitcoin",
            price_change_24h = 2059.65,
            price_change_percentage_24h = 2.46677,
            roi = null,
            symbol = "btc",
            total_supply = 20100106.0,
            total_volume = 31352244811L
        )
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
        bottomBar = { BottomNavigation(navController) },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
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
fun AssetRow(asset: DashBoardDataBOItem, onClick: () -> Unit) {

    val isPositive = asset.price_change_percentage_24h > 0
    val trendColor =
        if (isPositive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    val changeSign = if (isPositive) "+" else ""

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 80.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.surface)
            .clickable(onClick = onClick)
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = asset.image,
            contentDescription = "${asset.name} logo",
            contentScale = ContentScale.Crop,
            placeholder = painterResource(R.drawable.ic_coin_placeholder),
            error = painterResource(R.drawable.ic_coin_placeholder),
            modifier = Modifier
                .defaultMinSize(minHeight = 40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        )


        Spacer(modifier = Modifier.width(5.dp))


        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = asset.symbol.uppercase(),
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = asset.name,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                fontSize = 12.sp
            )
        }



        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
            Text(if (isPositive) "↗" else "↘", color = trendColor, fontSize = 24.sp)
        }

        Column(horizontalAlignment = Alignment.End, modifier = Modifier.weight(1f)) {
            Text(
                "$${asset.current_price}",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.width(6.dp))

            Text(
                "$changeSign${"%.2f".format(asset.price_change_percentage_24h)}%",
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

