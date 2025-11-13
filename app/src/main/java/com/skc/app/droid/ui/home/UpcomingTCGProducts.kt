package com.skc.app.droid.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.skc.app.droid.model.Events
import com.skc.app.droid.ui.utility.DateBadge
import com.skc.app.droid.util.DatesUtil
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun UpcomingTCGProducts(upcomingYGOProducts: Events) {
    Column(
        modifier = Modifier
            .padding(bottom = 10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        upcomingYGOProducts.events.forEach { event ->
            Row(horizontalArrangement = Arrangement.spacedBy(15.dp)) {
                DateBadge(
                    date = event.eventDate,
                    formatter = DatesUtil.HEART_API_DATE_FORMAT
                )
                Column {
                    Text(text = event.name, fontWeight = FontWeight.Bold)
                    MarkdownText(markdown = event.notes)
                }
            }
        }
    }
}