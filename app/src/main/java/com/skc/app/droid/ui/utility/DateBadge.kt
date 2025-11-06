package com.skc.app.droid.ui.utility

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skc.app.droid.ui.theme.DateGray
import com.skc.app.droid.ui.theme.DateRed
import com.skc.app.droid.ui.theme.SKCTheme
import com.skc.app.droid.util.DatesUtil
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DateBadge(date: String) {
    val d = LocalDate.parse(date, DatesUtil.SKC_DATE_FORMAT)

    val width = 70.dp
    val parentModifier = Modifier
        .clip(
            RoundedCornerShape(corner = CornerSize(10.dp))
        )
        .width(width)
        .background(color = DateGray)

    Column(
        modifier = parentModifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = d.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = DateRed),
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.labelLarge
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy((-3).dp)
        ) {
            Text(
                text = d.dayOfMonth.toString(),
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = d.year.toString(),
                fontWeight = FontWeight.Light,
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun DateBadgePreview() {
    SKCTheme {
        DateBadge("2025-10-27")
    }
}