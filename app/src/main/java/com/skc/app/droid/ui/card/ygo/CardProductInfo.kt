package com.skc.app.droid.ui.card.ygo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.LooksOne
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skc.app.droid.model.CardSlot
import com.skc.app.droid.model.ProductItem
import com.skc.app.droid.ui.theme.SKCTheme
import com.skc.app.droid.util.DatesUtil
import com.skc.app.droid.x.daysSinceToday
import com.skc.app.droid.x.format
import com.skc.app.droid.x.isFutureDate
import java.time.LocalDate

@Composable
fun CardProductInfo(products: List<ProductItem>, cardName: String) {
    val rarityDistribution = products
        .flatMap { it.cardSlot }
        .flatMap { it.rarities }
        .groupingBy { it }
        .eachCount()
        .toSortedMap()

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Releases", style = MaterialTheme.typography.titleLarge)
        Rarities(rarityDistribution, cardName)
        Products(products)
    }
}

@Composable
private fun Rarities(rarityDistribution: Map<String, Int>, cardName: String) {
    if (rarityDistribution.isNotEmpty()) {
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
            UniqueRarities(rarityDistribution)
        }
    }
}

@Composable
private fun UniqueRarities(rarityDistribution: Map<String, Int>) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        rarityDistribution.forEach {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(size = 10.dp))
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                    .padding(vertical = 6.dp, horizontal = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = it.value.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = it.key,
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
    }
}

@Composable
private fun Products(products: List<ProductItem>) {
    val firstReleaseDate = if (products.isEmpty()) null else LocalDate.parse(
        products.first().productReleaseDate,
        DatesUtil.SKC_DATE_FORMAT
    )
    val lastReleaseDate = if (products.size < 2) null else LocalDate.parse(
        products.last().productReleaseDate,
        DatesUtil.SKC_DATE_FORMAT
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
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

        if (firstReleaseDate == null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Warning",
                )
                Text(text = "No product data found", style = MaterialTheme.typography.titleSmall)
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            if (firstReleaseDate != null) {
                DataPointCard(
                    header = "${firstReleaseDate.daysSinceToday().format()} day(s)",
                    subHeader = if (firstReleaseDate.isFutureDate()) "From card debuts" else "Since initial printing",
                    icon = Icons.Default.LooksOne,
                    contentDescription = ""
                )
            }

            if (lastReleaseDate != null) {
                DataPointCard(
                    header = "${lastReleaseDate.daysSinceToday().format()} day(s)",
                    subHeader = if (lastReleaseDate.isFutureDate()) "Until next printing" else "Since last printing",
                    icon = Icons.Default.CalendarToday,
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
private fun DataPointCard(
    header: String,
    subHeader: String,
    icon: ImageVector,
    contentDescription: String
) {
    OutlinedCard(
        modifier = Modifier
            .width(width = 180.dp)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(
                    5.dp,
                    Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = contentDescription,
                )
                Text(
                    text = header,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            Text(
                text = subHeader,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardProductInfoPreview() {
    SKCTheme {
        CardProductInfo(products = emptyList(), cardName = "Victory Dragon")
    }
}

@Composable
@Preview(showBackground = true)
fun CardProductInfoPreview2() {
    SKCTheme {
        CardProductInfo(
            products = listOf(
                ProductItem(
                    productId = "STON",
                    productLocale = "en",
                    productName = "Strike of Neos",
                    productType = "Pack",
                    productSubType = "Core Set",
                    productReleaseDate = "2007-02-28",
                    cardSlot = listOf(CardSlot(productPosition = "034", rarities = emptyList()))
                )
            ), cardName = "Victory Dragon"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardProductInfoPreview3() {
    SKCTheme {
        CardProductInfo(
            products = listOf(
                ProductItem(
                    productId = "STON",
                    productLocale = "en",
                    productName = "Strike of Neos",
                    productType = "Pack",
                    productSubType = "Core Set",
                    productReleaseDate = "2007-02-28",
                    cardSlot = listOf(
                        CardSlot(
                            productPosition = "034",
                            rarities = listOf("Ultimate Rare", "Ultra Rare")
                        )
                    )
                )
            ), cardName = "Victory Dragon"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardProductInfoPreview4() {
    SKCTheme {
        CardProductInfo(
            products = listOf(
                ProductItem(
                    productId = "STON",
                    productLocale = "en",
                    productName = "Strike of Neos",
                    productType = "Pack",
                    productSubType = "Core Set",
                    productReleaseDate = "2037-02-28",
                    cardSlot = listOf(
                        CardSlot(
                            productPosition = "034",
                            rarities = listOf("Ultimate Rare", "Ultra Rare")
                        )
                    )
                )
            ), cardName = "Victory Dragon"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardProductInfoPreview5() {
    SKCTheme {
        CardProductInfo(
            products = listOf(
                ProductItem(
                    productId = "STON",
                    productLocale = "en",
                    productName = "Strike of Neos",
                    productType = "Pack",
                    productSubType = "Core Set",
                    productReleaseDate = "2037-02-28",
                    cardSlot = listOf(
                        CardSlot(
                            productPosition = "034",
                            rarities = listOf("Ultimate Rare", "Ultra Rare")
                        )
                    )
                ),
                ProductItem(
                    productId = "STON",
                    productLocale = "en",
                    productName = "Strike of Neos",
                    productType = "Pack",
                    productSubType = "Core Set",
                    productReleaseDate = "2024-05-23",
                    cardSlot = listOf(
                        CardSlot(
                            productPosition = "126",
                            rarities = listOf("Common")
                        )
                    )
                )
            ), cardName = "Victory Dragon"
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CardProductInfoPreview6() {
    SKCTheme {
        CardProductInfo(
            products = listOf(
                ProductItem(
                    productId = "STON",
                    productLocale = "en",
                    productName = "Strike of Neos",
                    productType = "Pack",
                    productSubType = "Core Set",
                    productReleaseDate = "2037-02-28",
                    cardSlot = listOf(
                        CardSlot(
                            productPosition = "034",
                            rarities = listOf("Ultimate Rare", "Ultra Rare")
                        )
                    )
                ),
                ProductItem(
                    productId = "STON",
                    productLocale = "en",
                    productName = "Strike of Neos",
                    productType = "Pack",
                    productSubType = "Core Set",
                    productReleaseDate = "2054-05-23",
                    cardSlot = listOf(
                        CardSlot(
                            productPosition = "126",
                            rarities = listOf("Common")
                        )
                    )
                )
            ), cardName = "Victory Dragon"
        )
    }
}