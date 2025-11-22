package com.skc.app.droid.ui.card.ygo

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.skc.app.droid.model.YGOCardImageVariant
import com.skc.app.droid.model.YGOImageSize
import com.skc.app.droid.ui.card.ygo.components.MonsterAssociation
import com.skc.app.droid.ui.card.ygo.components.Stats
import com.skc.app.droid.ui.theme.cardColorUI
import com.skc.app.droid.ui.theme.onYGOCard
import com.skc.app.droid.ui.utility.YGOCardImage
import com.skc.app.droid.viewmodel.CardViewModel
import com.skc.app.droid.x.parent

@Composable
fun Card(cardID: String) {
    val model: CardViewModel = viewModel(
        factory = CardViewModel.Factory,
        extras = MutableCreationExtras().apply {
            set(CardViewModel.KEY, cardID)
        },
        key = cardID
    )
    val isFetching by model.isFetching.collectAsState()

    LaunchedEffect("Card Model") {
        model.fetchData()
    }

    Column(
        modifier = Modifier
            .parent()
            .verticalScroll(rememberScrollState()),
    ) {
        if (isFetching) {
            CircularProgressIndicator(
                trackColor = MaterialTheme.colorScheme.primaryContainer,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            ElevatedCard {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = model.card.cardColor.cardColorUI())
                        .padding(all = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                        YGOCardImage(
                            size = 140.dp,
                            cardID = model.card.cardID,
                            imageSize = YGOImageSize.SMALL,
                            variant = YGOCardImageVariant.ROUNDED_CORNER,
                            modifier = Modifier
                                .border(
                                    width = 4.dp,
                                    color = Color.White.copy(alpha = 0.7f),
                                    shape = RoundedCornerShape(size = 140.dp / 10)
                                ),
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            MonsterAssociation(
                                monsterAssociation = model.card.monsterAssociation,
                                attribute = model.card.attribute
                            )

                            Text(
                                text = model.card.cardName,
                                fontWeight = FontWeight.SemiBold,
                                color = onYGOCard(),
                                style = MaterialTheme.typography.titleLarge,
                                minLines = 2,
                                maxLines = 2
                            )
                        }
                    }
                    Stats(card = model.card)
                }
            }
        }
    }
}