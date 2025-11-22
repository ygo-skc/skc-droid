package com.skc.app.droid.ui.card.ygo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skc.app.droid.R
import com.skc.app.droid.model.IconModifier
import com.skc.app.droid.model.YGOAttribute
import com.skc.app.droid.model.YGOCard.MonsterAssociation

@Composable
fun MonsterAssociation(monsterAssociation: MonsterAssociation?, attribute: YGOAttribute) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 20.dp))
            .background(MaterialTheme.colorScheme.background.copy(0.7f))
            .padding(vertical = 6.dp, horizontal = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Attribute(attribute, iconModifier = IconModifier.LARGE)
        if (monsterAssociation != null) {
            monsterAssociation.level?.let {
                Level(level = it, iconModifier = IconModifier.LARGE)
            }
            monsterAssociation.rank?.let {
                Rank(rank = it, iconModifier = IconModifier.LARGE)
            }
            monsterAssociation.scaleRating?.let {
                ScaleRating(scaleRating = it, iconModifier = IconModifier.LARGE)
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
        Text(
            text = "x$level",
            fontWeight = FontWeight.Medium
        )
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
        Text(
            text = "x$rank",
            fontWeight = FontWeight.Medium
        )
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
        Text(
            text = "x$scaleRating",
            fontWeight = FontWeight.Medium
        )
    }
}