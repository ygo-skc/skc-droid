package com.skc.app.droid.ui.utility

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
fun InlineDate(date: String) {
    val d = LocalDate.parse(date, DatesUtil.SKC_DATE_FORMAT)
    val parentModifier = Modifier
        .clip(
            RoundedCornerShape(corner = CornerSize(5.dp))
        )
        .background(color = DateGray)

    Row(modifier = parentModifier) {
        Text(
            text = d.month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH),
            modifier = Modifier
                .background(color = DateRed)
                .padding(horizontal = 4.dp),
            color = Color.White,
        )
        Row(modifier = Modifier.padding(horizontal = 4.dp)) {
            Text(
                text = "${d.dayOfMonth}, ",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = d.year.toString(),
                fontWeight = FontWeight.Light
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun InlineDatePreview() {
    SKCTheme {
        InlineDate("2025-10-27")
    }
}