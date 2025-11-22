package com.skc.app.droid.ui.card.ygo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.skc.app.droid.R
import com.skc.app.droid.model.IconModifier
import com.skc.app.droid.model.YGOAttribute
import com.skc.app.droid.model.YGOCard.MonsterAssociation

@Composable
fun MonsterAssociation(monsterAssociation: MonsterAssociation?, attribute: YGOAttribute) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 15.dp))
            .background(Color.White.copy(alpha = 0.7f))
            .padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Attribute(attribute, iconModifier = IconModifier.LARGE)
        if (monsterAssociation != null) {
            monsterAssociation.level?.let {
                Level(level = it)
            }
            monsterAssociation.rank?.let {
                Rank(rank = it)
            }
            monsterAssociation.scaleRating?.let {
                ScaleRating(scaleRating = it)
            }
        }
    }
}

@Composable
fun Level(level: Int, iconModifier: IconModifier = IconModifier.REGULAR) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = R.drawable.level),
            contentDescription = "Level",
            contentScale = ContentScale.Fit,
            modifier = iconModifier.modifier,
        )
        Text(text = "x$level")
    }
}

@Composable
fun Rank(rank: Int, iconModifier: IconModifier = IconModifier.REGULAR) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = R.drawable.rank),
            contentDescription = "Rank",
            contentScale = ContentScale.Fit,
            modifier = iconModifier.modifier,
        )
        Text(text = "x$rank")
    }
}

@Composable
fun ScaleRating(scaleRating: Int, iconModifier: IconModifier = IconModifier.REGULAR) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(id = R.drawable.pendscale),
            contentDescription = "Rank",
            contentScale = ContentScale.Fit,
            modifier = iconModifier.modifier,
        )
        Text(text = "x$scaleRating")
    }
}