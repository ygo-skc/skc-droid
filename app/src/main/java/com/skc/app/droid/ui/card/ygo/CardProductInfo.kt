package com.skc.app.droid.ui.card.ygo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.skc.app.droid.model.ProductItem

@Composable
fun CardProductInfo(products: List<ProductItem>, cardName: String) {
    val rarityCount = products
        .flatMap { it.cardSlot }
        .flatMap { it.rarities }
        .groupingBy { it }
        .eachCount()

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Releases", style = MaterialTheme.typography.titleLarge)
        Rarities(rarityCount, cardName)
        Products()
    }
}

@Composable
private fun Rarities(rarityCount: Map<String, Int>, cardName: String) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Diamond,
                contentDescription = "Rarity",
            )
            Text(text = "Rarities", style = MaterialTheme.typography.titleSmall)
        }
        Text(text = "All unique rarities $cardName was printed in")

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            rarityCount.forEach {
                SuggestionChip(
                    onClick = {},
                    label = {
                        Text(
                            text = "${it.key} (${it.value})",
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun Products() {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = "Products",
            )
            Text(text = "Products", style = MaterialTheme.typography.titleSmall)
        }
    }
}