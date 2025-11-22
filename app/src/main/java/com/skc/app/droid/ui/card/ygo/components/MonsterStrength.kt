package com.skc.app.droid.ui.card.ygo.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun MonsterStrength(monsterAttack: String, monsterDefense: String) {
    Row(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 6.dp))
            .background(MaterialTheme.colorScheme.background.copy(0.9f))
            .padding(all = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = monsterAttack,
            fontWeight = FontWeight.Normal,
            color = Color(0xFFff1744),
            style = MaterialTheme.typography.bodyMedium,
        )

        Text(
            text = monsterDefense,
            fontWeight = FontWeight.Normal,
            color = Color(0xFF2979ff),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}