package com.skc.app.droid.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skc.app.droid.ui.theme.SKCTheme

@Composable
fun DBStats(modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(all = 12.dp)) {
            Text(
                "Content",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                "All data is provided by a collection of API's/DB's designed to provide the best Yu-Gi-Oh! information.",
                modifier = Modifier.padding(bottom = 5.dp)
            )
            Row {
                Text("DB\nData")
                Spacer(modifier = Modifier.weight(1f))
                DBMetric(total = "13012", metric = "Cards")
                DBMetric(total = "85", metric = "Ban Lists")
                DBMetric(total = "365", metric = "Products")
                Spacer(modifier = Modifier.weight(1f))
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))
            Text(
                "Konami owns all rights to Yu-Gi-Oh! and all card images used in this app.",
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                "This app is not affiliated with Konami and all assets are used under Fair Use.",
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                "App Version v1.0.0",
                fontWeight = FontWeight.Normal,
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
private fun DBMetric(total: String, metric: String) {
    Column(
        modifier = Modifier.padding(horizontal = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = total,
            textAlign = TextAlign.Center,
        )
        Text(metric, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = true)
@Composable
fun DBStatsPreview() {
    SKCTheme {
        DBStats()
    }
}