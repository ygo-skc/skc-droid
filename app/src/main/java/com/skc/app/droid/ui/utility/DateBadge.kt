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
import com.skc.app.droid.ui.theme.SKCTheme
import com.skc.app.droid.ui.theme.dateGray
import com.skc.app.droid.ui.theme.dateRed
import com.skc.app.droid.util.DatesUtil
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

private val parentModifier = Modifier
    .clip(
        RoundedCornerShape(corner = CornerSize(10.dp))
    )
    .width(70.dp)

@Composable
fun DateBadge(date: String, formatter: DateTimeFormatter = DatesUtil.SKC_DATE_FORMAT) {
    val d = LocalDate.parse(date, formatter)


    Column(
        modifier = parentModifier
            .background(color = dateGray()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = d.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
            modifier = Modifier
                .fillMaxWidth()
                .background(color = dateRed()),
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